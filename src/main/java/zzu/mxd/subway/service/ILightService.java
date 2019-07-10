package zzu.mxd.subway.service;

import zzu.mxd.subway.entity.Light;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 光线 服务类
 * </p>
 *
 * @author mxd
 * @since 2019-04-18
 */
public interface ILightService extends IService<Light> {

    Light selectOneDescByUid(Integer uid);
}
