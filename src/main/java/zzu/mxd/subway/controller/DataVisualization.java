package zzu.mxd.subway.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zzu.mxd.subway.entity.*;
import zzu.mxd.subway.service.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Api(tags = "数据可视化")
@Controller
public class DataVisualization {
    //TODO springboot配置返回HTML
    @Autowired
    private ISitAccelerometerService sitAccelerometerService;

    @Autowired
    private ITemperatureService temperatureService;

    @Autowired
    private IPressureService pressureService;

    @Autowired
    private ILightService lightService;

    @Autowired
    private IHumidityService humidityService;

    @Autowired
    private INoiseIntensityService noiseIntensityService;

    @GetMapping("/home")
    public String home(Model model) {
        return "html/home";
    }



    @ApiOperation("3D图像")
    @GetMapping("/selectAll")
    @ResponseBody
    public Object[][] selectAll(){
        int size = 110;
        Object[][] array = new Object[size*6+1][3];
        Page page = new Page(1,size);
        List<SitAccelerometer> sitAccelerometerList = sitAccelerometerService.page(page,new QueryWrapper<>()).getRecords();
        array[0][0] = "value";
        array[0][1] = "type";
        array[0][2] = "datetime";
        for (int i = 0;i<size;i++){
            array[i+1][0] = sitAccelerometerList.get(i).getX();
            array[i+1][1] = "加速度";
            array[i+1][2] = sitAccelerometerList.get(i).getDatetime();
        }
        List<Temperature> temperatureList = temperatureService.page(page,new QueryWrapper<>()).getRecords();
        for (int i = 0;i<size;i++){
            array[i+size+1][0] = temperatureList.get(i).getValue();
            array[i+size+1][1] = "温度";
            array[i+size+1][2] = temperatureList.get(i).getDatetime();
        }
        List<Pressure> pressureList = pressureService.page(page,new QueryWrapper<>()).getRecords();
        for (int i = 0;i<size;i++){
            array[i+2*size+1][0] = pressureList.get(i).getValue();
            array[i+2*size+1][1] = "压强";
            array[i+2*size+1][2] = pressureList.get(i).getDatetime();
        }
        List<Light> lightList = lightService.page(page,new QueryWrapper<>()).getRecords();
        for (int i = 0;i<size;i++){
            array[i+3*size+1][0] = lightList.get(i).getValue();
            array[i+3*size+1][1] = "光线强度";
            array[i+3*size+1][2] = lightList.get(i).getDatetime();
        }
        List<Humidity> humidityList = humidityService.page(page,new QueryWrapper<>()).getRecords();
        for (int i = 0;i<size;i++){
            array[i+4*size+1][0] = humidityList.get(i).getValue();
            array[i+4*size+1][1] = "湿度";
            array[i+4*size+1][2] = humidityList.get(i).getDatetime();
        }
        List<NoiseIntensity> noiseIntensityList = noiseIntensityService.page(page,new QueryWrapper<>()).getRecords();
        for (int i = 0;i<size;i++){
            array[i+5*size+1][0] = noiseIntensityList.get(i).getValue();
            array[i+5*size+1][1] = "噪声强度";
            array[i+5*size+1][2] = noiseIntensityList.get(i).getDatetime();
        }

        return array;
    }


    /*@Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/html/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }*/

}
