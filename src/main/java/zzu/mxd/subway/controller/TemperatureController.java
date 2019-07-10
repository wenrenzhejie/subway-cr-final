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
import zzu.mxd.subway.entity.Temperature;
import zzu.mxd.subway.service.ITemperatureService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 温度 前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
@Api(tags = "温度管理")
@RestController
@RequestMapping("/subway/temperature")
public class TemperatureController {

    @Autowired
    private ITemperatureService temperatureService;

    @ApiOperation("新增温度数据 && 温度舒适度：")
    @PostMapping(consumes = "application/json")
    public R add(@RequestBody Temperature temperature){
        Double temp = temperature.getValue().doubleValue();
        int integer = 0;
        if (temp<=16){
            integer = 0;//冷，不舒适
        }else if (16<=temp && temp<=18){
            integer = 1;//有些冷，略不舒适
        } else if (18<temp && temp<=22) {
            integer = 2;//舒适
        }else if(22<temp && temp<=25){
            integer = 3;//非常舒适
        }else if(25<temp && temp<=30){
            integer = 2;//舒适
        }else if(30<temp && temp<=36){
            integer = 1;//有些热，略不舒适
        }else if (temp>36){
            integer = 0;//热，不舒适
        }
        temperature.setComfortDegree(integer);
        temperatureService.save(temperature);
        return R.succC(integer);
    }

    @ApiOperation("温度变化曲线")
    @PostMapping(value = "/temperatureChangeCurve",consumes = "application/json")
    public R<IPage<Temperature>> temperatureChangeCurve(@RequestBody Temperature temperature){
        Page<Temperature> page = new Page<>(1,50);
        page = (Page<Temperature>) temperatureService.page(page,new QueryWrapper<>(temperature));
        return R.succC(page);
    }

}
