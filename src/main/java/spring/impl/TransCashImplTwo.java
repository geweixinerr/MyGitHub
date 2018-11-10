package spring.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import spring.intf.AbstractTransCashIntf;

@Service("impl2")
public class TransCashImplTwo extends AbstractTransCashIntf {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransCashImplTwo.class);
	
	public TransCashImplTwo() {

	}

	@Override
	public BigDecimal moveMoney(BigDecimal money) {
		LOGGER.info("执行第二个实现类!");
		System.out.println("第二个实现类!");
		return null;
	}

	@Override
	public BigDecimal calculateMoney(BigDecimal money) {
		System.out.println("执行目标方法=============start=============");
        System.out.println("子类具体实现!");
		System.out.println("执行目标方法=============end=============");
		return money;
	}

}
