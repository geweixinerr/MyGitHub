package concurrent;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import exception.ConcurrentException;

/**
 * @author gewx 并发处理,基于Redis setNx控制
 **/

@Service
public final class ConcurrentOneByOne {

	private static final String VALUE = "true";

	private static final int DEFAULT_TIME_OUT = 15;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public ConcurrentOneByOne build(String _key) {		
		this.key = _key;
		this.timeOut = DEFAULT_TIME_OUT;
		return this;
	}

	public ConcurrentOneByOne build(String _key, Integer _timeOut) {
		this.key = _key;
		this.timeOut = _timeOut;
		return this;
	}

	private String key;

	private Integer timeOut;

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
		if (StringUtils.isBlank(this.key)) {
			throw new ConcurrentException("Concurrent Key is Not Empty~");
		}

		boolean isSet = redisTemplate.opsForValue().setIfAbsent(this.key, VALUE, this.timeOut, TimeUnit.SECONDS);
		if (!isSet) {
			throw new ConcurrentException("并发业务逻辑处理中,请稍后再试~");
		}
	}

	private void after() {
		this.key = StringUtils.EMPTY;
		this.timeOut = DEFAULT_TIME_OUT;
		redisTemplate.delete(this.key);
	}
}
