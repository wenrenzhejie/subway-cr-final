package zzu.mxd.subway.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import zzu.mxd.subway.entity.SitAccelerometer;
import zzu.mxd.subway.mapper.SitAccelerometerMapper;
import zzu.mxd.subway.service.ISitAccelerometerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 坐姿加速度 服务实现类
 * </p>
 *
 * @author mxd
 * @since 2019-04-03
 */
@Service
public class SitAccelerometerServiceImpl extends ServiceImpl<SitAccelerometerMapper, SitAccelerometer> implements ISitAccelerometerService {

    @Autowired
    SitAccelerometerMapper sitAccelerometerMapper;

    @Override
    public SitAccelerometer selectOneDescByUid(Integer uid) {
        return sitAccelerometerMapper.selectOneDescByUid(uid);
    }

    @Override
    public List<SitAccelerometer> select32DescByUid(Integer uid) {
        return sitAccelerometerMapper.select32DescByUid(uid);
    }

    @Override
    public SitAccelerometer selectOneOrderByUid(Integer uid) {
        return sitAccelerometerMapper.selectOneOrderByUid(uid);
    }

    @Override
    public List<SitAccelerometer> selectAll() {
        return sitAccelerometerMapper.selectAll();
    }
}
