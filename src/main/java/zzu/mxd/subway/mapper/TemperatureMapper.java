package zzu.mxd.subway.mapper;

import org.apache.ibatis.annotations.Param;
import zzu.mxd.subway.entity.Temperature;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 温度 Mapper 接口
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
public interface TemperatureMapper extends BaseMapper<Temperature> {

    Temperature selectOneDescByUid(@Param("uid") Integer uid);
}
