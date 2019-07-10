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
import zzu.mxd.common.R;
import zzu.mxd.subway.entity.HeartRate;
import zzu.mxd.subway.service.IHeartRateService;

import java.math.BigDecimal;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-04-03
 */
@Api(tags = "心率管理")
@RestController
@RequestMapping("/subway/heartRate")
public class HeartRateController {

    @Autowired
    private IHeartRateService heartRateService;

    @ApiOperation("新增心率数据")
    @PostMapping(consumes = "application/json")
    public R<Integer> add(@RequestBody HeartRate heartRate){
        int integer = 0;
        Double value = heartRate.getValue().doubleValue();
        if (value>60 && value<=100){
            integer = 3;//正常
        }else if (value>40 && value<=60){
            integer = 2;//胸闷、乏力、头晕
        }else if (value>35 && value<=40){
            integer = 1;//胸部闷痛、头晕、晕厥甚至猝死
        }
        heartRate.setComfortDegree(integer);
        heartRateService.save(heartRate);
        return R.succC(integer);
    }


    @ApiOperation("心率变化图 && 心率舒适度：3正常；2胸闷、乏力、头晕；1胸部闷痛、头晕、晕厥甚至猝死")
    @PostMapping(value = "/heartChangeCurve",consumes = "application/json")
    public R<IPage<HeartRate>> heartChangeCurve(@RequestBody HeartRate heartRate){
        Page<HeartRate> page = new Page<>(1,50);
        page = (Page<HeartRate>) heartRateService.page(page,new QueryWrapper<>(heartRate));
        return R.succC(page);
    }

}
