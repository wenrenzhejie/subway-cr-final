package zzu.mxd.subway.serviceImpl;

import zzu.mxd.subway.entity.StanceComfort;
import zzu.mxd.subway.mapper.StanceComfortMapper;
import zzu.mxd.subway.service.IStanceComfortService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 列车线上每个站点的坐姿舒适度值：5分钟更新一次 服务实现类
 * </p>
 *
 * @author mxd
 * @since 2019-04-19
 */
@Service
public class StanceComfortServiceImpl extends ServiceImpl<StanceComfortMapper, StanceComfort> implements IStanceComfortService {

}
