package spring.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import spring.intf.TransCashIntf;

@Service("impl1")
public class TransCashImplOne implements TransCashIntf{

	private static final Logger LOGGER = LoggerFactory.getLogger(TransCashImplOne.class);
	
	public TransCashImplOne() {

	}

	@Override
	public BigDecimal moveMoney(BigDecimal money) {
		LOGGER.info("执行第一个实现类!");
        System.out.println("第一个实现类!");
		return null;
	}
}
