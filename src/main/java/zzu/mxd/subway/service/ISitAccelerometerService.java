package zzu.mxd.subway.service;

import zzu.mxd.subway.entity.SitAccelerometer;
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
public interface ISitAccelerometerService extends IService<SitAccelerometer> {

    SitAccelerometer selectOneDescByUid(Integer uid);

    List<SitAccelerometer> select32DescByUid(Integer uid);

    SitAccelerometer selectOneOrderByUid(Integer uid);

    List<SitAccelerometer> selectAll();

}
