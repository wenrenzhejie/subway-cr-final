package zzu.mxd.subway.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zzu.mxd.common.PageRequest;
import zzu.mxd.common.R;
import zzu.mxd.subway.entity.User;
import zzu.mxd.subway.service.IUserService;

/**
 * <p>
 *  用户表 前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-03-14
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/subway/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 测试通过
     * @param id
     * @return
     */
    @ApiOperation("查询单个用户")
    @GetMapping("/{id}")
    public R<User> get(@ApiParam("用户ID")@PathVariable(name = "id")String id){
        User user = userService.getById(id);
        return R.succC(user);
    }

    /**
     * 测试通过
     * @param user
     * @param request
     * @return
     */
    @ApiOperation("分页条件查询用户")
    @PostMapping(value = "/selectPage",consumes = "application/json")
    public R<IPage<User>> selectPage(@RequestBody User user,@RequestBody PageRequest request){
        Page<User> page = new Page<>(request.getCurrent(),request.getSize());
        page = (Page<User>) userService.page(page,new QueryWrapper<>(user));
        return R.succC(page);
    }

    /**
     * 测试通过
     * @param user
     * @return
     */
    @ApiOperation("增加用户")
    @PostMapping()
    public R<String> add(@RequestBody User user){
        userService.save(user);
        return R.succ();
    }

    /**
     * 测试通过
     * @param user
     * @return
     */
    @ApiOperation("修改用户信息")
    @PutMapping()
    public R<String> update(@RequestBody User user){
        userService.updateById(user);
        return R.succ();
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public R login(@RequestParam("账号")String username,
                           @RequestParam("密码")String password,
                           @RequestParam("确认密码")String rePass){
        if (password.equals(rePass)){
            User user = new User();
            user.setName(username);
            user.setPassword(password);
            QueryWrapper wrapper = new QueryWrapper<>(user);
            User user1 = userService.getOne(wrapper);
            if (user1!=null){
                return R.succMC(user1.getId(),"用户ID");
            }else{
                return R.errM("没有此用户");
            }
        }else{
            return R.errM("密码不一致");
        }
    }

    @ApiOperation("注册用户")
    @PostMapping(value = "/register",consumes = "application/json")
    public R<String> register(@RequestParam("账号")String username,
                              @RequestParam("密码")String password,
                              @RequestParam("确认密码")String rePass){
        if (password.equals(rePass)){
            User user = new User();
            user.setName(username);
            user.setPassword(password);
            userService.save(user);
            return R.succ();
        }else{
            return R.errM("密码不一致");
        }
    }
}
