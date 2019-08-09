package concurrent;

/**
 * @author gewx 函数式接口,并发业务逻辑处理
 **/

@FunctionalInterface
public interface OneByOne<T> {

	T invoke();
}
