package zzu.mxd.subway.service;

import zzu.mxd.subway.entity.Temperature;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 温度 服务类
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
public interface ITemperatureService extends IService<Temperature> {

    Temperature selectOneDescByUid(Integer uid);
}
