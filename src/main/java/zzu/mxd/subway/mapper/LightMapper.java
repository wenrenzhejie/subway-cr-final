package zzu.mxd.subway.mapper;

import org.apache.ibatis.annotations.Param;
import zzu.mxd.subway.entity.Light;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 光线 Mapper 接口
 * </p>
 *
 * @author mxd
 * @since 2019-04-18
 */
public interface LightMapper extends BaseMapper<Light> {

    Light selectOneDescByUid(@Param("uid") Integer uid);
}
