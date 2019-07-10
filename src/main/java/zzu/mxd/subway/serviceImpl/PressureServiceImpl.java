package zzu.mxd.subway.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import zzu.mxd.subway.entity.Pressure;
import zzu.mxd.subway.mapper.PressureMapper;
import zzu.mxd.subway.service.IPressureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 气压 服务实现类
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
@Service
public class PressureServiceImpl extends ServiceImpl<PressureMapper, Pressure> implements IPressureService {
    @Autowired
    PressureMapper pressureMapper;
    @Override
    public Pressure selectOneDescByUid(Integer uid) {
        return pressureMapper.selectOneDescByUid(uid);
    }
}
