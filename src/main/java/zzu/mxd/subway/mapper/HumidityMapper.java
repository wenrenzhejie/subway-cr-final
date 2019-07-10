package zzu.mxd.subway.mapper;

import zzu.mxd.subway.entity.Humidity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 湿度 Mapper 接口
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
public interface HumidityMapper extends BaseMapper<Humidity> {

    Humidity selectOneDescByUid(Integer uid);
}
