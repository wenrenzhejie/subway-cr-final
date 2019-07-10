package zzu.mxd.subway.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zzu.mxd.common.R;
import zzu.mxd.subway.entity.ComprehensiveComfort;
import zzu.mxd.subway.entity.StanceAccelerometer;
import zzu.mxd.subway.entity.StanceComfort;
import zzu.mxd.subway.service.IStanceAccelerometerService;
import zzu.mxd.subway.service.IStanceComfortService;
import zzu.mxd.utils.StancePostureWeighting;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


/**
 * <p>
 * 站姿加速度 前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-04-03
 */
@Api(tags = "站姿加速度管理")
@RestController
@RequestMapping("/subway/stanceAccelerometer")
public class StanceAccelerometerController {

    @Autowired
    private IStanceAccelerometerService stanceAccelerometerService;

    @Autowired
    private IStanceComfortService stanceComfortService;

    @ApiOperation("新增站姿加速度数据 && 时域直观分析：3非常舒适；2较好舒适；1舒适；0较差舒适；-1不舒适")
    @PostMapping(consumes = "application/json")
    public R add(@RequestBody StanceAccelerometer sitAccelerometer){
        //查询该用户的第一条加速度数据，加入redis缓存
        StanceAccelerometer firstData = stanceAccelerometerService.selectOneOrderByUid(sitAccelerometer.getUid());
        if (firstData == null){
            stanceAccelerometerService.save(sitAccelerometer);
            stanceAccelerometerService.save(new StanceAccelerometer(new BigDecimal(0),new BigDecimal(0),new BigDecimal(0),sitAccelerometer.getUid()));
        }else{
            BigDecimal x = sitAccelerometer.getX().subtract(firstData.getX());
            BigDecimal y = sitAccelerometer.getY().subtract(firstData.getY());
            BigDecimal z = sitAccelerometer.getZ().subtract(firstData.getZ());
            StanceAccelerometer after = new StanceAccelerometer(x,y,z,sitAccelerometer.getUid());
            //将加减偏移量后的数据存入加速度表
            stanceAccelerometerService.save(after);
        }
        //x方向加速度数据
        Double x = sitAccelerometer.getX().doubleValue();
        int integer = 0;
        if (x<=0.1){
            integer = 3;//非常舒适
        }else if(0.1<x && x<=0.12){
            integer = 2;//较好舒适
        }else if(0.12<x && x<=0.15){
            integer = 1;//舒适
        }else if(0.15<x && x<=0.18){
            integer = 0;//较差舒适
        }else if(0.18<x){
            integer = -1;//不舒适
        }
        return R.succC(integer);
    }

    /**
     * 站姿方式加速度频域分析
     * @param comprehensiveComfort
     * @return
     */
    @ApiOperation("站姿方式加速度频域分析")
    @PostMapping(value = "/stanceSpectrumAnalysis",consumes = "application/json")
    public R<Double> stanceSpectrumAnalysis(@RequestBody ComprehensiveComfort comprehensiveComfort){
        int size = 32;
        //查询最近两个站点之间的加速度数据（32条）
        List<StanceAccelerometer> stanceAccelerometers = stanceAccelerometerService.select32DescByUid(comprehensiveComfort.getUid());
        double[] inputData_x = new double[512];
        double[] inputData_y = new double[512];
        double[] inputData_z = new double[512];
        for (int i = 0;i<stanceAccelerometers.size();i++){
            StanceAccelerometer stanceAccelerometer = stanceAccelerometers.get(i);
            inputData_x[i] = stanceAccelerometer.getX().doubleValue();
            inputData_y[i] = stanceAccelerometer.getY().doubleValue();
            inputData_z[i] = stanceAccelerometer.getZ().doubleValue();
        }
        double x = StancePostureWeighting.weight_x(inputData_x);//站姿加权的x轴
        double y = StancePostureWeighting.weight_y(inputData_y);//站姿加权的y轴
        double z = StancePostureWeighting.weight_z(inputData_z);//站姿加权的z轴
        double N = StancePostureWeighting.stancePostureWeighting(x,y,z);
        int i = 0;
        if (N<3.5){
            i = 3;//非常舒适
        }else if (3.5<=N && N<4.5){
            i = 2;//舒适
        }else if (4.5<=N){
            i = 1;//不舒适
        }
        stanceComfortService.save(new StanceComfort(i,comprehensiveComfort.getDic_id(),LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")))));//保存该站点加速度频域分析结果，即舒适度值
        return R.succC(N);
    }

}
