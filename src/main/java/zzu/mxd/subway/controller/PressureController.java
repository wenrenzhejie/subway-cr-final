package zzu.mxd.subway.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import zzu.mxd.common.PageRequest;
import zzu.mxd.common.R;
import zzu.mxd.subway.entity.Pressure;
import zzu.mxd.subway.service.IPressureService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 气压 前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
@Api(tags = "气压管理")
@RestController
@RequestMapping("/subway/pressure")
public class PressureController {

    @Autowired
    private IPressureService pressureService;

    @ApiOperation("新增气压数据 && 气压变化率变化曲线图：3很舒适；1不舒适；0极不舒适；2(小于3s，略有不适;大于3s,不舒适)")
    @PostMapping(consumes = "application/json")
    public R<Integer> add(@RequestBody Pressure pressure){
        double rate = pressure.getValue().doubleValue();//TODO 求导后，求绝对值，得到变化率
        int integer = 0;
        if (rate<=0.33){
            integer = 3;//很舒适
        }else if(rate>0.33 && rate<=0.37){
            integer = 2;//TODO 小于3s，略有不适;大于3s,不舒适
        }else if (rate>0.37 && rate<0.41){
            integer = 1;//不舒适
        }else if (rate>0.41){
            integer = 0;//极不舒适
        }
        pressure.setComfortDegree(integer);
        pressureService.save(pressure);
        return R.succC(integer);
    }

    @ApiOperation("气压变化曲线图")
    @PostMapping(value = "/changeCurve",consumes = "application/json")
    public R<IPage<Pressure>> changeCurve(@RequestBody Pressure pressure){
        Page<Pressure> page = new Page<>(1,50);
        page = (Page<Pressure>) pressureService.page(page,new QueryWrapper<>(pressure));
        return R.succC(page);
    }

}
