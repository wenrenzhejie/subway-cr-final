package zzu.mxd.subway.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import zzu.mxd.common.PageRequest;
import zzu.mxd.common.R;
import zzu.mxd.subway.entity.NoiseIntensity;
import zzu.mxd.subway.service.INoiseIntensityService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * <p>
 * 噪声强度 前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-04-15
 */
@Api(tags = "噪声强度管理")
@RestController
@RequestMapping("/subway/noiseIntensity")
public class NoiseIntensityController {

    @Autowired
    private INoiseIntensityService iNoiseIntensityService;

    @ApiOperation("新增噪声强度数据 && 噪声舒适度：3非常舒适；2舒适；1不舒适")
    @PostMapping(consumes = "application/json")
    public R add(@RequestBody NoiseIntensity noiseIntensity){
        Double temp = noiseIntensity.getValue().doubleValue();
        int integer = 0;
        if (30<=temp && temp<60){
            integer = 3;//非常舒适
        }else if (60<=temp && temp<90){
            integer = 2;//舒适
        } else if (temp>90) {
            integer = 1;//不舒适
        }
        noiseIntensity.setComfortDegree(integer);
        iNoiseIntensityService.save(noiseIntensity);
        return R.succC(integer);
    }

    @ApiOperation("噪声变化曲线")
    @PostMapping(value = "/selectPage",consumes = "application/json")
    public R<IPage<NoiseIntensity>> selectPage(@RequestBody NoiseIntensity noiseIntensity, @RequestBody PageRequest request){
        Page<NoiseIntensity> page = new Page<>(request.getCurrent(),request.getSize());
        page = (Page<NoiseIntensity>) iNoiseIntensityService.page(page,new QueryWrapper<>(noiseIntensity));
        return R.succC(page);
    }


    @ApiOperation("录音文件上传")
    @PostMapping("/upload")
    @ResponseBody public String upload(@RequestParam("mFile") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        String filePath = "/root/recordAudio/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {

        } return "上传失败！";
    }

}
