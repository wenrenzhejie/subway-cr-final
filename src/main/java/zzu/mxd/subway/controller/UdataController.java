package zzu.mxd.subway.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import zzu.mxd.common.PageRequest;
import zzu.mxd.common.R;
import zzu.mxd.subway.entity.Udata;
import zzu.mxd.subway.entity.User;
import zzu.mxd.subway.service.IUdataService;

/**
 * <p>
 *  用户数据管理 前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-03-14
 */
@Api(tags = "用户数据管理")
@RestController
@RequestMapping("/subway/udata")
public class UdataController {

    @Autowired
    public IUdataService udataService;

    /**
     * 测试通过
     * @param udata
     * @param request
     * @return
     */
    @ApiOperation("分页条件查询单个用户数据")
    @PostMapping("/selectPage")
    public R<IPage<Udata>> selectPage(@RequestBody Udata udata, PageRequest request) {
        IPage<Udata> page = new Page(request.getCurrent(), request.getSize());
        page = udataService.page(page, new QueryWrapper<>(udata));
        return R.succC(page);
    }

    /**
     * 测试通过
     * @param udata
     * @return
     */
    /*@ApiOperation("新增单个用户数据")
    @PostMapping()
    public R<String> add(@RequestBody Udata udata){
        udataService.save(udata);
        return R.succ();
    }*/

    /*@ApiOperation("新增单个用户数据")
    @PostMapping()
    public R<String> adda(@RequestParam("加速度") String dataAccelerometer,
                          @RequestParam("方向") String dataOrientation,
                          @RequestParam("光线") String dataLight,
                          @RequestParam("温度") String dataTemperature,
                          @RequestParam("磁场") String dataMagnetic,
                          @RequestParam("距离") String dataProximity,
                          @RequestParam("所属用户ID") Long dataUid){
        Udata udata = new Udata();
        udata.setDataAccelerometer(dataAccelerometer);
        udata.setDataOrientation(dataOrientation);
        udata.setDataLight(dataLight);
        udata.setDataTemperature(dataTemperature);
        udata.setDataMagnetic(dataMagnetic);
        udata.setDataProximity(dataProximity);
        udata.setDataUid(dataUid);
        udataService.save(udata);
        return R.succ();
    }*/

}
