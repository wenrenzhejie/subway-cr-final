package zzu.mxd.subway.service;

import zzu.mxd.subway.entity.SitAccelerometer;
import zzu.mxd.subway.entity.StanceAccelerometer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 坐姿加速度 服务类
 * </p>
 *
 * @author mxd
 * @since 2019-04-03
 */
public interface IStanceAccelerometerService extends IService<StanceAccelerometer> {

    StanceAccelerometer selectOneOrderByUid(Integer uid);

    List<StanceAccelerometer> select32DescByUid(Integer uid);
}
