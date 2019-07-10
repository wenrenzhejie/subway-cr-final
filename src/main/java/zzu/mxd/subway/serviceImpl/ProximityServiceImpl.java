package zzu.mxd.subway.serviceImpl;

import zzu.mxd.subway.entity.Proximity;
import zzu.mxd.subway.mapper.ProximityMapper;
import zzu.mxd.subway.service.IProximityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 距离（临近性） 服务实现类
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
@Service
public class ProximityServiceImpl extends ServiceImpl<ProximityMapper, Proximity> implements IProximityService {

}
