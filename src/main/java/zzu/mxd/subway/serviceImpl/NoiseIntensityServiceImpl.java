package zzu.mxd.subway.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import zzu.mxd.subway.entity.NoiseIntensity;
import zzu.mxd.subway.mapper.NoiseIntensityMapper;
import zzu.mxd.subway.service.INoiseIntensityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 噪声强度 服务实现类
 * </p>
 *
 * @author mxd
 * @since 2019-04-15
 */
@Service
public class NoiseIntensityServiceImpl extends ServiceImpl<NoiseIntensityMapper, NoiseIntensity> implements INoiseIntensityService {

    @Autowired
    NoiseIntensityMapper noiseIntensityMapper;

    @Override
    public NoiseIntensity selectOneDescByUid(Integer uid) {
        return noiseIntensityMapper.selectOneDescByUid(uid);
    }
}
