package zzu.mxd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置
 *
 * @author leaf
 * <p>date: 2018-05-07 19:54</p>
 * <p>version: 1.0</p>
 */
@Configuration
@Profile({"dev"})
@EnableSwagger2()
public class Swagger2Config {

    /**
     * 访问地址: http://localhost:8084/swagger-ui.html
     *
     * @return api
     */

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("zzu.mxd.subway.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("地铁舒适度研究后台 + swagger项目api文档")
                .description("一款基于swagger2打造的在线api文档")
                .version("v1.0")
                .build();
    }
}
