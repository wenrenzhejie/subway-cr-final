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
import zzu.mxd.subway.entity.Vibration;
import zzu.mxd.subway.service.IVibrationService;

/**
 * <p>
 * 振动 前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
@Api(tags = "振动管理")
@RestController
@RequestMapping("/subway/vibration")
public class VibrationController {

    @Autowired
    private IVibrationService iVibrationService;

    @ApiOperation("新增振动数据")
    @PostMapping(consumes = "application/json")
    public R add(@RequestBody Vibration vibration){
        iVibrationService.save(vibration);
        return R.succ();
    }

    @ApiOperation("分页条件查询振动")
    @PostMapping(value = "/selectPage",consumes = "application/json")
    public R<IPage<Vibration>> selectPage(@RequestBody Vibration vibration){
        Page<Vibration> page = new Page<>(1,50);
        page = (Page<Vibration>) iVibrationService.page(page,new QueryWrapper<>(vibration));
        return R.succC(page);
    }
}
