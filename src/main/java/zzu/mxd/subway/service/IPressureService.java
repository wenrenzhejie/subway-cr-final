package zzu.mxd.subway.service;

import zzu.mxd.subway.entity.Pressure;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 气压 服务类
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
public interface IPressureService extends IService<Pressure> {

    Pressure selectOneDescByUid(Integer uid);
}
