package concurrent;

/**
 * 函数式接口,并发业务逻辑处理
 * 
 * @author gewx
 **/
@FunctionalInterface
public interface Callable<T> {

	/**
	 * 分布式锁执行单元
	 * 
	 * 
	 * @author gewx
	 * @return 返回业务单元执行结果
	 **/
	T invoke();
}
