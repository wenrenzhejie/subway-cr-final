package zzu.mxd.subway.serviceImpl;

import zzu.mxd.subway.entity.User;
import zzu.mxd.subway.mapper.UserMapper;
import zzu.mxd.subway.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author mxd
 * @since 2019-03-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
