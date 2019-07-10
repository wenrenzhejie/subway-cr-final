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
import zzu.mxd.subway.entity.Orientation;
import zzu.mxd.subway.service.IOrientationService;

/**
 * <p>
 * 方向 前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
@Api(tags = "方向管理")
@RestController
@RequestMapping("/subway/orientation")
public class OrientationController {

    @Autowired
    private IOrientationService orientationService;

    @ApiOperation("新增方向数据")
    @PostMapping(consumes = "application/json")
    public R add(@RequestBody Orientation orientation){
        orientationService.save(orientation);
        return R.succ();
    }

    @ApiOperation("分页条件查询方向")
    @PostMapping(value = "/selectPage",consumes = "application/json")
    public R<IPage<Orientation>> selectPage(@RequestBody Orientation orientation,@RequestBody PageRequest request){
        Page<Orientation> page = new Page<>(request.getCurrent(),request.getSize());
        page = (Page<Orientation>) orientationService.page(page,new QueryWrapper<>(orientation));
        return R.succC(page);
    }
}
