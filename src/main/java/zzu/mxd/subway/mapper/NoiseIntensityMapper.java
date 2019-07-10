package zzu.mxd.subway.mapper;

import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import zzu.mxd.subway.entity.NoiseIntensity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 噪声强度 Mapper 接口
 * </p>
 *
 * @author mxd
 * @since 2019-04-15
 */
@Mapper
public interface NoiseIntensityMapper extends BaseMapper<NoiseIntensity> {


    NoiseIntensity selectOneDescByUid(@Param("uid") Integer uid);
}
