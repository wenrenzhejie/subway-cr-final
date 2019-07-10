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
import zzu.mxd.subway.entity.Gyroscope;
import zzu.mxd.subway.service.IGyroscopeService;

/**
 * <p>
 * 陀螺仪 前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
@Api(tags = "陀螺仪管理")
@RestController
@RequestMapping("/subway/gyroscope")
public class GyroscopeController{

    @Autowired
    private IGyroscopeService gyroscopeService;

    @ApiOperation("新增陀螺仪数据")
    @PostMapping(consumes = "application/json")
    public R add(@RequestBody Gyroscope gyroscope){
        gyroscopeService.save(gyroscope);
        return R.succ();
    }

    @ApiOperation("分页条件查询陀螺仪")
    @PostMapping(value = "/selectPage",consumes = "application/json")
    public R<IPage<Gyroscope>> selectPage(@RequestBody Gyroscope gyroscope){
        Page<Gyroscope> page = new Page<>(1,50);
        page = (Page<Gyroscope>) gyroscopeService.page(page,new QueryWrapper<>(gyroscope));
        return R.succC(page);
    }
}
