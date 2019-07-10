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
import zzu.mxd.subway.entity.Light;
import zzu.mxd.subway.service.ILightService;

import java.math.BigDecimal;

/**
 * <p>
 * 光线 前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-04-18
 */
@Api(tags = "光线强度管理")
@RestController
@RequestMapping("/subway/light")
public class LightController {

    @Autowired
    private ILightService lightService;

    @ApiOperation("新增光线强度数据 && 光线强度分析：3非常舒适；2舒适；1不舒适")
    @PostMapping(consumes = "application/json")
    public R add(@RequestBody Light light){
        //后期考虑批量存取：一次插入2s
        int integer = 0;
        double value = light.getValue().doubleValue();
        if (value>100 && value<=300){
            integer = 3;//非常舒适
        }else if (value>300 && value<=500){
            integer = 2;//舒适
        }else if (value>500){
            integer = 1;//不舒适
        }
        light.setComfortDegree(integer);
        lightService.save(light);
        return R.succC(integer);
    }

    @ApiOperation("光线强度变化曲线")
    @PostMapping(value = "lightChangeCurve",consumes = "application/json")
    public R<IPage<Light>> lightChangeCurve(@RequestBody Light light){
        Page<Light> page = new Page<>(1,50);
        page = (Page<Light>) lightService.page(page,new QueryWrapper<>(light));
        return R.succC(page);
    }

}
