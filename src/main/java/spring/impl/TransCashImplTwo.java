package spring.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import spring.intf.TransCashIntf;

@Service("impl2")
public class TransCashImplTwo implements TransCashIntf{

	private static final Logger LOGGER = LoggerFactory.getLogger(TransCashImplTwo.class);

	public TransCashImplTwo() {

	}

	@Override
	public BigDecimal moveMoney(BigDecimal money) {
		LOGGER.info("执行第二个实现类!");
		LOGGER.debug("执行第二个实现类!");
		System.out.println("第二个实现类!");
		return null;
	}
}
