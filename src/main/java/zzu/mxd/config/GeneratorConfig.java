package zzu.mxd.config;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @Description : MybatisPlus代码生成器
 * 
 */
public class GeneratorConfig {
    //作者
    private static String authorName="mxd";
    //table名
    private static String [] table = new String[] {
            /*"subway_gyroscope","","subway_light","subway_linearAcceleration",
            "subway_magnetic","subway_orientation","subway_proximity",
            "subway_sitAccelerometer","subway_siteComfort","subway_sitSpectrum",
            "subway_stanceAccelerometer","subway_stanceComfort"，"subway_stanceSpectrum",
            ,"subway_user","subway_vibration"*/
            "subway_siteComfort"
    };
    //文件路径
    private static String path = "H:\\GeneratorWorkspace\\SubwayResearch";
    //模块名
    private static String moudleName = "subway";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        gc.setOutputDir(path);//输出路径
        gc.setAuthor(authorName);
        gc.setOpen(true);//生成后打开文件夹
        gc.setFileOverride(true);//是否覆盖文件
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://139.196.124.38:3306/scr?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Mxd@123456");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moudleName);
        pc.setParent("zzu.mxd");// 自定义包路径
        pc.setController("controller");//控制器包名
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("serviceImpl");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("complete",  "Generation success!");  //自定义完成后输出信息
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义xml的生成模板
        focList.add(new FileOutConfig("") { //mapper的xml文件模板引擎：/templates/mapper.xml.vm
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return path + "/zzu/mxd/" +pc.getModuleName()
                        + "/mapper/xml/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setEntity("/templates/entity.java"));//实体类模板引擎

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);//表名生成策略：下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//字段名生成策略：下划线转驼峰
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");// 自定义实体父类
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");// 自定义controller父类
        strategy.setInclude(table);// 需要生成的表，多个表名传数组
//        strategy.setSuperEntityColumns("id");// 自定义实体，公共字段
//        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");//表前缀
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine()); //指定模板引擎，默认是VelocityTemplateEngine
        /**
         * VelocityTemplateEngine的配套模板文件：mapper.xml.vm
         * FreemarkerTemplateEngine的配套模板文件：entity.java.ftl
         */
        mpg.execute();
    }

}