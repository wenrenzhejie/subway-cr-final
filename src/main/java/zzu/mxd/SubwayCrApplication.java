package zzu.mxd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("zzu.mxd.**.mapper")
public class SubwayCrApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(SubwayCrApplication.class, args);
    }

    /**
     * 把web项目打成war包部署到外部tomcat运行时需要改变启动方式
     */
    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SubwayCrApplication.class);
    }
    */

    //TODO 添加banner.txt到resources目录下，启动项目横幅自动显示

    //TODO 一键清空数据库，一键导出数据到excel
}
