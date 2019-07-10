package zzu.mxd.subway.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import zzu.mxd.common.R;
import zzu.mxd.subway.entity.*;
import zzu.mxd.subway.service.ISitAccelerometerService;
import zzu.mxd.subway.service.ISiteComfortService;
import zzu.mxd.utils.RedisUtils;
import zzu.mxd.utils.SittingPostureWeighting;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * <p>
 * 坐姿加速度 前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-04-03
 */
@Api(tags = "坐姿加速度管理")
@RestController
@RequestMapping("/subway/sitAccelerometer")
public class SitAccelerometerController {

    @Autowired
    private ISitAccelerometerService accelerometerService;

    @Autowired
    private ISiteComfortService siteComfortService;

    @Autowired
    RedisUtils redisUtils;

    @ApiOperation("上传第一条加速度数据")
    @PostMapping(value = "/before",consumes = "application/json")
    public R beforeAdd(@RequestBody SitAccelerometer sitAccelerometer){
        //上传第一条加速度数据，并存入redis内存缓存
        accelerometerService.save(sitAccelerometer);
//        accelerometerService.save(new SitAccelerometer(new BigDecimal(0),new BigDecimal(0),new BigDecimal(0),sitAccelerometer.getUid()));
        redisUtils.set("firstData"+sitAccelerometer.getUid(),sitAccelerometer);
        System.out.println("内存缓存中："+redisUtils.get("firstData"+sitAccelerometer.getUid()));
        return R.succ();
    }

    @ApiOperation("新增坐姿加速度数据 && 时域直观分析：3非常舒适；2较好舒适；1舒适；0较差舒适；-1不舒适")
    @PostMapping(consumes = "application/json")
    public R add(@RequestBody SitAccelerometer sitAccelerometer){
        //从redis中查询第一条加速度数据
        SitAccelerometer firstData = (SitAccelerometer) redisUtils.get("firstData"+sitAccelerometer.getUid());
        //将加减偏移量后的数据存入加速度表
        BigDecimal x = sitAccelerometer.getX().subtract(firstData.getX());
        BigDecimal y = sitAccelerometer.getY().subtract(firstData.getY());
        BigDecimal z = sitAccelerometer.getZ().subtract(firstData.getZ());
        SitAccelerometer after = new SitAccelerometer(x,y,z,sitAccelerometer.getUid());
        accelerometerService.save(after);
        //x方向加速度数据
        Double xValue = sitAccelerometer.getX().doubleValue();
        int integer = 0;
        if (xValue<=0.1){
            integer = 3;//非常舒适
        }else if(0.1<xValue && xValue<=0.12){
            integer = 2;//较好舒适
        }else if(0.12<xValue && xValue<=0.15){
            integer = 1;//舒适
        }else if(0.15<xValue && xValue<=0.18){
            integer = 0;//较差舒适
        }else if(0.18<xValue){
            integer = -1;//不舒适
        }
        return R.succC(integer);
    }

    /**
     * 坐姿方式加速度频域分析：5s为一组进行分析（单用户）
     * @param comprehensiveComfort
     * @return
     */
    @ApiOperation("坐姿方式加速度频域分析（一个站点）：3非常舒适；2舒适；1不舒适")
    @PostMapping(value = "/sitSpectrumAnalysis",consumes = "application/json")
    public R<Integer> sitSpectrumAnalysis(@RequestBody ComprehensiveComfort comprehensiveComfort){
        int size = 32;
        //每5s分析进行一次频域分析（5s采集32条数据）
        List<SitAccelerometer> sitAccelerometers = accelerometerService.select32DescByUid(comprehensiveComfort.getUid());
        //保存x、y、z各方向加速度数据
        double[] inputData_x = new double[size];
        double[] inputData_y = new double[size];
        double[] inputData_z = new double[size];
        for (int i = 0;i<sitAccelerometers.size();i++){
            SitAccelerometer sitAccelerometer = sitAccelerometers.get(i);
            inputData_x[i] = sitAccelerometer.getX().doubleValue();
            inputData_y[i] = sitAccelerometer.getY().doubleValue();
            inputData_z[i] = sitAccelerometer.getZ().doubleValue();
        }
        double x = SittingPostureWeighting.weight_x(inputData_x);//对x轴坐姿加权
        double y = SittingPostureWeighting.weight_y(inputData_y);//对y轴坐姿加权的
        double z = SittingPostureWeighting.weight_z(inputData_z);//对z轴坐姿加权的
        double N = SittingPostureWeighting.sitPostureWeighting(x,y,z);
        System.out.println(N);
        DecimalFormat df = new DecimalFormat("0.000");
//        System.out.println("aaaaaaaaaaaaaaaaaaa"+df.format(N));
        int i = 0;
        if (N<3.5){
            i = 3;//非常舒适
        }else if (3.5<=N && N<4.5){
            i = 2;//舒适
        }else if (4.5<=N){
            i = 1;//不舒适
        }
        siteComfortService.save(new SiteComfort(i,comprehensiveComfort.getDic_id(),LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))),new BigDecimal(df.format(N)),comprehensiveComfort.getUid()));//保存该站点加速度频域分析结果、算法结果，即舒适度值、N、用户ID
        return R.succC(i);
    }

    /*@ApiOperation("每两站清空一次")
    @PostMapping(value = "/clearAll",consumes = "application/json")
    public R clearAll(@RequestBody Integer uid){
        QueryWrapper wrapper = new QueryWrapper<>().eq("uid",uid);
        accelerometerService.remove(wrapper);
        return R.succ();
    }*/

    /**
     * 坐姿振幅图：只需对x傅里叶变换
     * @param sitAccelerometer
     * @return
     */
    @ApiOperation("坐姿振幅图")
    @PostMapping(value = "/sittingAmplitudePicture",consumes = "application/json")
    public R<List<Amplitude>> sittingAmplitudePicture(@RequestBody SitAccelerometer sitAccelerometer){
        //每次查询并分析最近的32个数据（4s上传一次数据）
        List<SitAccelerometer> sitAccelerometers = accelerometerService.select32DescByUid(sitAccelerometer.getUid());
        double[] inputData_x = new double[32];
        for (int i = 0;i<sitAccelerometers.size();i++){
            inputData_x[i] = sitAccelerometers.get(i).getX().doubleValue();
        }
        List<Amplitude> amplitudes = SittingPostureWeighting.spectrumPicture(inputData_x);
        return R.succC(amplitudes);//频谱图;
    }

    /**
     * TODO (每个站点采集数据少于1200，每个站点计算一个舒适度值)
     * @param sitAccelerometer
     * @return
     */
    @ApiOperation("坐姿方式加速度频域分析（所有站点）：3非常舒适；2舒适；1不舒适")
    @PostMapping(value = "/siteComfortValue",consumes = "application/json")
    public R<Double> siteComfortValue(@RequestBody SitAccelerometer sitAccelerometer){
        return R.succC(0.0);
    }

}
