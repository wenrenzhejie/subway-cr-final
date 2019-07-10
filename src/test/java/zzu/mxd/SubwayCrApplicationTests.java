package zzu.mxd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import zzu.mxd.subway.entity.SitAccelerometer;
import zzu.mxd.subway.service.ISitAccelerometerService;
import zzu.mxd.utils.RedisUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubwayCrApplicationTests {

    /**
     * redis：
     * 1.启动redis服务端：进入redis安装目录，命令行输入redis-server.exe redis.windows.conf
     * 2.启动redis客户端：命令行输入redis-cli.exe -h 139.196.124.38 -p 6379
     * 3.不重启redis设置密码：config set requirepass 123456
     * 4.查看密码：config get requirepass
     * 5.密码验证：auth 123456
     * 6.删除key:del key
     * 7.插入key:set key
     * 8.插叙key:get key
     * 9.查询List:lindex exercises 0
     */
    //TODO 1.redis使用测试：获取题目2.redis配置深入研究

    private static final String key = "firstData";//这里的key值可以自己修改
    public static final Logger logger = LoggerFactory.getLogger(SitAccelerometer.class);

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ISitAccelerometerService sitAccelerometerService;

    /**
     *查询数据:若不存在则插入
     */
    @Test
    public void redisGetTest() {
        //如果缓存存在
        if(redisUtils.exists(key)){
            SitAccelerometer sitAccelerometer = (SitAccelerometer) redisUtils.get(key);
//            List<SitAccelerometer> exercises = JSON.parseArray(object.toString(),SitAccelerometer.class);//Json转List
            logger.info("从缓存获取的数据"+ sitAccelerometer.toString());
        }else{
            redisAddTest();
        }
    }

    /**
     * 存储数据：将加速度存储到内存
     */
    @Test
    public void redisAddTest(){
        SitAccelerometer sitAccelerometer =  sitAccelerometerService.selectOneOrderByUid(1);
        redisUtils.set(key,sitAccelerometer);//JSON.toJSONString(sitAccelerometer)
//		redisUtils.lPush(key,JSON.toJSONString(exercises));//List添加
    }

    /**
     *删除数据：从内存缓存中删除加速度
     */
    @Test
    public void redisDelTest(){
        //如果缓存存在
        if(redisUtils.exists(key)){
            redisUtils.remove(key);
            logger.info("从缓存中删除数据");
        }else {
            logger.info("缓存中没有数据！");
        }
    }

    @Autowired
    private RedisCli redisCli;

    @Test
    public void contextLoads() {
        //测试使用Jedis连接linux服务器上的redis
        StringRedisTemplate redis = redisCli.getTemplate();
        redis.opsForValue().set("name", "Jack");
        String firstName = redis.opsForValue().get("name");
        System.out.println(firstName);
        redis.opsForValue().set("name", "Rose");
        String secondName = redis.opsForValue().get("name");
        System.out.println(secondName);
    }


}
