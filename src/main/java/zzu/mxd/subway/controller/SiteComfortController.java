package zzu.mxd.subway.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zzu.mxd.common.R;
import zzu.mxd.subway.entity.SiteComfort;
import zzu.mxd.subway.service.ISiteComfortService;
import zzu.mxd.utils.DateUtil;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * <p>
 * 列车线上每个站点的坐姿舒适度值（多用户） 前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-04-04
 */
@Api(tags = "列车线上每个站点的坐姿舒适度值（多用户）")
@RestController
@RequestMapping("/subway/siteComfort")
public class SiteComfortController {

    @Autowired
    private ISiteComfortService siteComfortService;

    @ApiOperation("列车线上所有站点舒适度值：两站间约2~3分钟")
    @PostMapping(value = "/select",consumes = "application/json")
    public R<Double> select(@RequestParam Integer dic_id){
        List<SiteComfort> siteComforts = siteComfortService.selectListByNameAndDateTime(dic_id,DateUtil.timeBefore(10));//eg:"南四环-站马屯"
        double sum = 0;
        double num = 0;
        for (int i = 0;i<siteComforts.size();i++){
            sum = sum + siteComforts.get(i).getValue();
            num++;
        }
        return R.succC(sum/num);
    }
}
