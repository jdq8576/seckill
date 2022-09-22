package cn.edu.njtech.seckill.dao.cache;


import cn.edu.njtech.seckill.entity.Seckill;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@Component
public class RedisDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JedisPool jedisPool;

    @Resource
    private RedisTemplate<String, Seckill> redisTemplate;

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    /**
     * 反序列化的过程
     *
     * @param seckillId
     * @return
     */
    public Seckill getSeckill(long seckillId) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckillId;
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    Seckill seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    logger.info("从Redis中获取数据：" + seckill.toString());
                    return seckill;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 序列化过程
     *
     * @param seckill
     * @return
     */
    public String putSeckill(Seckill seckill) {
        /**
         * redisTemplate访问redis
         */
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckill.getSeckillId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate());
                int timeout = 600;
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                logger.info("向redis中插入数据：" + seckill);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }

}
