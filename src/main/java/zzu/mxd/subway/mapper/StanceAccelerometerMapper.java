package zzu.mxd.subway.mapper;

import org.apache.ibatis.annotations.Param;
import zzu.mxd.subway.entity.StanceAccelerometer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 坐姿加速度 Mapper 接口
 * </p>
 *
 * @author mxd
 * @since 2019-04-03
 */
public interface StanceAccelerometerMapper extends BaseMapper<StanceAccelerometer> {

    StanceAccelerometer selectOneOrderByUid(@Param("uid") Integer uid);

    List<StanceAccelerometer> select32DescByUid(@Param("uid") Integer uid);
}
