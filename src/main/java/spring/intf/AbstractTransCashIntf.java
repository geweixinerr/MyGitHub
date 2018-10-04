package spring.intf;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import spring.bean.Student;
import spring.utils.SpringContextUtils;

/**
 * @author gewx 默认实现,抽象聚合公共元素 
 * 
 * **/
public abstract class AbstractTransCashIntf implements TransCashIntf {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTransCashIntf.class);
	
	@Autowired
	private SpringContextUtils context;
	
	@Autowired
	private Student student;

	@Override
	public BigDecimal moveMoney(BigDecimal money) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BigDecimal calculateMoney(BigDecimal money) {
		LOGGER.info("默认实现!");
		//集合BeanFactory
		List<String> arrayList = (List<String>)context.getApplicationContextInstance().getBean("listBean");
		System.out.println("arrayList--->" + arrayList);
		System.out.println("输出DTO---->" + student);
		return null;
	}

}
