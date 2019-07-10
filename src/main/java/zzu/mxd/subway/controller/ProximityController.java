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
import zzu.mxd.subway.entity.Proximity;
import zzu.mxd.subway.service.IProximityService;

/**
 * <p>
 * 距离（临近性） 前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
@Api(tags = "距离管理")
@RestController
@RequestMapping("/subway/proximity")
public class ProximityController {

    @Autowired
    private IProximityService proximityService;

    @ApiOperation("新增距离数据")
    @PostMapping(consumes = "application/json")
    public R add(@RequestBody Proximity proximity){
        proximityService.save(proximity);
        return R.succ();
    }

    @ApiOperation("分页条件查询距离")
    @PostMapping(value = "/selectPage",consumes = "application/json")
    public R<IPage<Proximity>> selectPage(@RequestBody Proximity proximity,@RequestBody PageRequest request){
        Page<Proximity> page = new Page<>(request.getCurrent(),request.getSize());
        page = (Page<Proximity>) proximityService.page(page,new QueryWrapper<>(proximity));
        return R.succC(page);
    }
}
