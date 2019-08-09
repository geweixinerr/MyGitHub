package redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author gewx Spring整合Redis
 **/
public final class SpringRedisRun {

	@SuppressWarnings({ "unchecked", "resource", "rawtypes" })
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-redis.xml");
		RedisTemplate<String, String> template = (RedisTemplate) context.getBean("redisTemplate");
		// 设置字符串序列化器
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		String val = template.opsForValue().get("Java");
		System.out.println("val: " + val);
	}
}
