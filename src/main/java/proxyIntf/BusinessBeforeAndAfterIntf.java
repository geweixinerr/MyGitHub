package proxyIntf;

/**
 *  业务验证接口<br> 
 *  业务验证接口,抽象出业务执行前与执行后验证
 */
public interface BusinessBeforeAndAfterIntf<T> {

	/**
	 * @author 业务执行前置
	 * 
	 * **/ 
	@SuppressWarnings("unchecked")
	T before(T... object);
	
	/**
	 * @author 业务执行后置
	 * 
	 * **/ 
	@SuppressWarnings("unchecked")
	T after(T... Object);
}
