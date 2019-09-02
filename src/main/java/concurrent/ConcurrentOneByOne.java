package concurrent;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import exception.ConcurrentException;

/**
 * @author gewx 并发处理,基于Redis setNx控制
 **/
@Component
public final class ConcurrentOneByOne {

	private static final String VALUE = "true";

	private static final int DEFAULT_TIME_OUT = 15;

	private static final ThreadLocal<String> KEY = new ThreadLocal<>();

	private static final ThreadLocal<Integer> TIME_OUT = new ThreadLocal<>();

	private static final ThreadLocal<String> TIPS = new ThreadLocal<>();
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public ConcurrentOneByOne addConcurrentKey(String _key) {
		KEY.set(_key);
		TIME_OUT.set(DEFAULT_TIME_OUT);
		return this;
	}

	public ConcurrentOneByOne addKeyTimeOut(Integer _timeOut) {
		TIME_OUT.set(_timeOut);
		return this;
	}

	public ConcurrentOneByOne tips(String _tips) {
		TIPS.set(_tips);
		return this;
	}

	public <T> T execute(OneByOne<T> one) {
		try {
			before();
			T t = one.invoke();
			return t;
		} finally {
			after();
		}
	}

	private void before() {
		String _key = KEY.get();
		if (StringUtils.isBlank(_key)) {
			throw new ConcurrentException("Concurrent Key is Not Empty~");
		}

		boolean isSet = redisTemplate.opsForValue().setIfAbsent(_key, VALUE);
		if (!isSet) {
			String _tips = TIPS.get();
			if (StringUtils.isNotBlank(_tips)) {
				throw new ConcurrentException(_tips);
			} else {
				throw new ConcurrentException("Concurrent Mode Fail~");
			}
		}
		// 此处命令存在连接中断的问题,即过期命令设置失败.
		redisTemplate.expire(_key, TIME_OUT.get(), TimeUnit.SECONDS);
	}

	private void after() {
		try {
			redisTemplate.delete(KEY.get());
		} finally {
			KEY.remove();
			TIME_OUT.remove();
			TIPS.remove();
		}
	}
}
