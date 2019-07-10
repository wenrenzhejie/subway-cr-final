package zzu.mxd.subway.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import zzu.mxd.subway.entity.Light;
import zzu.mxd.subway.mapper.LightMapper;
import zzu.mxd.subway.service.ILightService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 光线 服务实现类
 * </p>
 *
 * @author mxd
 * @since 2019-04-18
 */
@Service
public class LightServiceImpl extends ServiceImpl<LightMapper, Light> implements ILightService {

    @Autowired
    private LightMapper lightMapper;

    @Override
    public Light selectOneDescByUid(Integer uid) {
        return lightMapper.selectOneDescByUid(uid);
    }
}
