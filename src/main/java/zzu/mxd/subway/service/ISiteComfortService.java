package zzu.mxd.subway.service;

import zzu.mxd.subway.entity.SiteComfort;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 列车线上每个站点的舒适度值 服务类
 * </p>
 *
 * @author mxd
 * @since 2019-04-04
 */
public interface ISiteComfortService extends IService<SiteComfort> {

    SiteComfort selectLastByName(String s);

    List<SiteComfort> selectListByNameAndDateTime(Integer dic_id, LocalDateTime dateTime);

}
