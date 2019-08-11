package concurrent;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import exception.ConcurrentException;

/**
 * @author gewx 并发处理,基于Redis setNx/watch 乐观锁控制
 **/

@Service
public final class ConcurrentOneByOne {

	private static final String VALUE = "true";

	private static final int DEFAULT_TIME_OUT = 15;
	
	@Autowired
	private RedisTemplate<String,String> redisTemplate;

	public ConcurrentOneByOne addConcurrentKey(String _key) {		
		this.key = _key;
		this.timeOut = DEFAULT_TIME_OUT;
		return this;
	}

	public ConcurrentOneByOne addKeyTimeOut(Integer _timeOut) {
		this.timeOut = _timeOut;
		return this;
	}

	private String key;

	private Integer timeOut;

	public <T> T execute(OneByOne<T> one) {
		try {
			beforeByWatch();
			T t = one.invoke();
			return t;
		} finally {
			after();
		}
	}

	/**
	 * @author gewx 低版本基于watch乐观锁实现分布式锁
	 * **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void beforeByWatch() {
		if (StringUtils.isBlank(this.key)) {
			throw new ConcurrentException("Concurrent Key is Not Empty~");
		}
		
		redisTemplate.watch(this.key);
		String val = redisTemplate.opsForValue().get(this.key);
		if (StringUtils.isNotBlank(val)) {
			throw new ConcurrentException("并发业务逻辑处理中,请稍后再试[0]~");
		}
		
		redisTemplate.execute((SessionCallback) connection -> {
			connection.multi();
			connection.opsForValue().set(this.key, VALUE);
			connection.expire(this.key, this.timeOut, TimeUnit.SECONDS);
			List result = connection.exec();
			if (result == null) {
				throw new ConcurrentException("并发业务逻辑处理中,请稍后再试[1]~");
			}
			return result;
		});
	}
	
	@SuppressWarnings("unused")
	private void beforeBySetNx() {
		if (StringUtils.isBlank(this.key)) {
			throw new ConcurrentException("Concurrent Key is Not Empty~");
		}

		boolean isSet = redisTemplate.opsForValue().setIfAbsent(this.key, VALUE);
		if (!isSet) {
			throw new ConcurrentException("并发业务逻辑处理中,请稍后再试~");
		}
		//此处命令存在连接中断的问题,即过期命令设置失败.
		redisTemplate.expire(this.key,this.timeOut, TimeUnit.SECONDS);
	}
	
	//需要高版本支持
	/*
	private void beforeBySetNx() {
		if (StringUtils.isBlank(this.key)) {
			throw new ConcurrentException("Concurrent Key is Not Empty~");
		}

		boolean isSet = redisTemplate.opsForValue().setIfAbsent(this.key, VALUE, this.timeOut, TimeUnit.SECONDS);
		if (!isSet) {
			throw new ConcurrentException("并发业务逻辑处理中,请稍后再试~");
		}
	}
	*/

	private void after() {
		this.key = StringUtils.EMPTY;
		this.timeOut = DEFAULT_TIME_OUT;
		redisTemplate.delete(this.key);
	}
}
