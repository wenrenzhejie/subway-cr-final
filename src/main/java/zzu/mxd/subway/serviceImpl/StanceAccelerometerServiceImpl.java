package zzu.mxd.subway.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import zzu.mxd.subway.entity.StanceAccelerometer;
import zzu.mxd.subway.mapper.StanceAccelerometerMapper;
import zzu.mxd.subway.service.IStanceAccelerometerService;
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
public class StanceAccelerometerServiceImpl extends ServiceImpl<StanceAccelerometerMapper, StanceAccelerometer> implements IStanceAccelerometerService {

    @Autowired
    private StanceAccelerometerMapper stanceAccelerometerMapper;

    @Override
    public StanceAccelerometer selectOneOrderByUid(Integer uid) {
        return stanceAccelerometerMapper.selectOneOrderByUid(uid);
    }

    @Override
    public List<StanceAccelerometer> select32DescByUid(Integer uid) {
        return stanceAccelerometerMapper.select32DescByUid(uid);
    }
}
