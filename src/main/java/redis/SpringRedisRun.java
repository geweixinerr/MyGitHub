package redis;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author gewx Spring整合Redis
 **/
public final class SpringRedisRun {

	private static final ApplicationContext context = new ClassPathXmlApplicationContext("Spring-redis.xml");

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static final RedisTemplate<String, String> template = (RedisTemplate) context.getBean("redisTemplate");

	static {
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
	}
	
	public static void main(String[] args) {
		SpringRedisRun object = new SpringRedisRun();
		// object.getSetData("Java","Java very Good!");

		object.redisTransaction();
	}

	/**
	 * @author gewx 普通get/set
	 **/
	public void getSetData(String key, String value) {
		// 设置字符串序列化器
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		template.opsForValue().set(key, value);
		String val = template.opsForValue().get(key);
		System.out.println("val: " + val);
	}

	/**
	 * @author gewx 事务处理
	 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void redisTransaction() {
		template.execute((SessionCallback) session -> {
			session.watch("lock"); //乐观锁事务监控. 直到exec命令执行前,只要lock键被操作过,事务将不被执行.
			session.multi(); //开启事务
			session.opsForValue().setIfAbsent("lock", "20190810");
			session.expire("lock", 30, TimeUnit.SECONDS);
			session.opsForValue().set("expire", String.valueOf(new Date().getTime()));
			/**
			 * 这里的list返回的值按照API命令返回值一一对应.譬如:
			 * 第一条命令: setIfAbsent 返回boolean, 放置在list[0]
			 * 第二条命令: expire 返回boolean, 放置在list[1] 
			 * 第三条命令: set 无返回值,list不存储
			 * **/
			List list = session.exec(); //执行事务
			System.out.println(list);
			return list;
		});
	}
}
