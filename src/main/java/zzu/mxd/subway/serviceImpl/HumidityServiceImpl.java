package zzu.mxd.subway.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import zzu.mxd.subway.entity.Humidity;
import zzu.mxd.subway.mapper.HumidityMapper;
import zzu.mxd.subway.service.IHumidityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 湿度 服务实现类
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
@Service
public class HumidityServiceImpl extends ServiceImpl<HumidityMapper, Humidity> implements IHumidityService {

    @Autowired
    HumidityMapper humidityMapper;

    @Override
    public Humidity selectOneDescByUid(Integer uid) {
        return humidityMapper.selectOneDescByUid(uid);
    }
}
