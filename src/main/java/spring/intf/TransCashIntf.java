/**
 * 
 */
package spring.intf;

import java.math.BigDecimal;

/**
 * @author gewx
 *
 */
public interface TransCashIntf {

	/**
	 * @author Administrator 移动现金
	 * @return 移动后的数值
	 * **/
	BigDecimal moveMoney(BigDecimal money);
	
	/**
	 * @author Administrator 计算金额
	 * **/
	BigDecimal calculateMoney(BigDecimal money);
}
