package zzu.mxd.subway.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import zzu.mxd.subway.entity.Temperature;
import zzu.mxd.subway.mapper.TemperatureMapper;
import zzu.mxd.subway.service.ITemperatureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 温度 服务实现类
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
@Service
public class TemperatureServiceImpl extends ServiceImpl<TemperatureMapper, Temperature> implements ITemperatureService {

    @Autowired
    TemperatureMapper temperatureMapper;

    @Override
    public Temperature selectOneDescByUid(Integer uid) {
        return temperatureMapper.selectOneDescByUid(uid);
    }
}
