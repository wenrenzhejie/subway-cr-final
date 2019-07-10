package zzu.mxd.subway.mapper;

import org.apache.ibatis.annotations.Param;
import zzu.mxd.subway.entity.SitAccelerometer;
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
public interface SitAccelerometerMapper extends BaseMapper<SitAccelerometer> {

    SitAccelerometer selectOneDescByUid(@Param("uid") Integer uid);

    List<SitAccelerometer> select32DescByUid(@Param("uid") Integer uid);

    SitAccelerometer selectOneOrderByUid(@Param("uid") Integer uid);

    List<SitAccelerometer> selectAll();
}
