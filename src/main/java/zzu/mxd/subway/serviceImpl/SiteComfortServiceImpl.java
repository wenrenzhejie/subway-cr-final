package zzu.mxd.subway.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import zzu.mxd.subway.entity.SiteComfort;
import zzu.mxd.subway.mapper.SiteComfortMapper;
import zzu.mxd.subway.service.ISiteComfortService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 列车线上每个站点的舒适度值 服务实现类
 * </p>
 *
 * @author mxd
 * @since 2019-04-04
 */
@Service
public class SiteComfortServiceImpl extends ServiceImpl<SiteComfortMapper, SiteComfort> implements ISiteComfortService {

    @Autowired
    SiteComfortMapper siteComfortMapper;


    @Override
    public SiteComfort selectLastByName(String name) {
        return siteComfortMapper.selectLastByName(name);
    }

    @Override
    public List<SiteComfort> selectListByNameAndDateTime(Integer dic_id, LocalDateTime datetime) {
        return siteComfortMapper.selectListByNameAndDateTime(dic_id,datetime);
    }

}
