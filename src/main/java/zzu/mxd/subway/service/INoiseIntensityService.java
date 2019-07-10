package zzu.mxd.subway.service;

import zzu.mxd.common.R;
import zzu.mxd.subway.entity.NoiseIntensity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 噪声强度 服务类
 * </p>
 *
 * @author mxd
 * @since 2019-04-15
 */
public interface INoiseIntensityService extends IService<NoiseIntensity> {

    NoiseIntensity selectOneDescByUid(Integer uid);
}
