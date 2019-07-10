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
import zzu.mxd.subway.entity.Magnetic;
import zzu.mxd.subway.service.IMagneticService;

/**
 * <p>
 * 磁场 前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
@Api(tags = "磁场管理")
@RestController
@RequestMapping("/subway/magnetic")
public class MagneticController {

    @Autowired
    private IMagneticService magneticService;

    @ApiOperation("新增磁场数据")
    @PostMapping(consumes = "application/json")
    public R add(@RequestBody Magnetic magnetic){
        magneticService.save(magnetic);
        return R.succ();
    }

    @ApiOperation("分页条件查询磁场")
    @PostMapping(value = "/selectPage",consumes = "application/json")
    public R<IPage<Magnetic>> selectPage(@RequestBody Magnetic magnetic,@RequestBody PageRequest request){
        Page<Magnetic> page = new Page<>(request.getCurrent(),request.getSize());
        page = (Page<Magnetic>) magneticService.page(page,new QueryWrapper<>(magnetic));
        return R.succC(page);
    }
}
