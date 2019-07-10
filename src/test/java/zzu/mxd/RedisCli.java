package zzu.mxd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCli {

    private StringRedisTemplate template;

    @Autowired
    public RedisCli(StringRedisTemplate template) {
        this.template = template;
    }

    public StringRedisTemplate getTemplate() {
        return template;
    }
}

