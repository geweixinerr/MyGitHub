package concurrent;

/**
 * 函数式接口,并发业务逻辑处理
 * 
 * @author gewx
 **/
@FunctionalInterface
public interface Runnable {

	/**
	 * 分布式锁执行单元
	 * 
	 * 
	 * @author gewx
	 * @throws Exception
	 * @return void
	 **/
	void invoke();
}
