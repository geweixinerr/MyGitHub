package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

/**
 * @author gewx Redis客户端
 * **/
public final class RedisRun {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		JedisShardInfo  jedisShardInfo = new JedisShardInfo("127.0.0.1", 6379);
        Jedis jedis = new Jedis(jedisShardInfo);
        jedis.set("Java", "Hello World!");
        System.out.println("val:" + jedis.get("Java"));
	}
}
