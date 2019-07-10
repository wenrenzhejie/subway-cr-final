package zzu.mxd.subway.mapper;

import org.apache.ibatis.annotations.Param;
import zzu.mxd.subway.entity.Pressure;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 气压 Mapper 接口
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
public interface PressureMapper extends BaseMapper<Pressure> {

    Pressure selectOneDescByUid(@Param("uid") Integer uid);
}
