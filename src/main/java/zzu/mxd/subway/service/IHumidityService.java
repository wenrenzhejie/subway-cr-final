package zzu.mxd.subway.service;

import zzu.mxd.subway.entity.Humidity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 湿度 服务类
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
public interface IHumidityService extends IService<Humidity> {

    Humidity selectOneDescByUid(Integer uid);

}
