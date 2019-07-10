package zzu.mxd.subway.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import zzu.mxd.subway.entity.SiteComfort;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 列车线上每个站点的舒适度值 Mapper 接口
 * </p>
 *
 * @author mxd
 * @since 2019-04-04
 */
@Mapper
public interface SiteComfortMapper extends BaseMapper<SiteComfort> {

    SiteComfort selectLastByName(@Param("name") String name);

    List<SiteComfort> selectListByNameAndDateTime(@Param("dicId") Integer dic_id, @Param("datetime") LocalDateTime datetime);
}
