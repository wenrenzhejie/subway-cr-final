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
import zzu.mxd.subway.entity.Humidity;
import zzu.mxd.subway.service.IHumidityService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 湿度 前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
@Api(tags = "湿度管理")
@RestController
@RequestMapping("/subway/humidity")
public class HumidityController {

    @Autowired
    private IHumidityService humidityService;

    @ApiOperation("新增湿度数据 && 湿度舒适度：-1不舒适；0不舒适；1略不舒适；2舒适；3非常舒适；")
    @PostMapping(consumes = "application/json")
    public R<Integer> add(@RequestBody Humidity humidity){
        Double value = humidity.getValue().doubleValue();
        int integer = 0;
        if (value<30){
            integer = -1;//不舒适
        }else if (30<value && value<=40){
            integer = 1;//有些干，略不舒适
        }else if(40<value && value<=60){
            integer = 3;//非常舒适
        }else if(60<value && value<=70){
            integer = 2;//舒适
        }else if(70<value && value<=80){
            integer = 1;//有些闷，略不舒适
        }else if (value>80){
            integer = 0;//不舒适
        }
        humidity.setComfortDegree(integer);
        humidityService.save(humidity);
        return R.succC(integer);
    }

    @ApiOperation("湿度变化图")
    @PostMapping(value = "/humidityChangeCurve",consumes = "application/json")
    public R<IPage<Humidity>> humidityChangeCurve(@RequestBody Humidity humidity) {
        Page<Humidity> page = new Page<>(1,50);
        page = (Page<Humidity>) humidityService.page(page,new QueryWrapper<>(humidity));
        return R.succC(page);
    }

}
