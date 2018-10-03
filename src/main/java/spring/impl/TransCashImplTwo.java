package spring.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import spring.intf.TransCashIntf;

@Service("impl2")
public class TransCashImplTwo implements TransCashIntf{

	public TransCashImplTwo() {

	}

	@Override
	public BigDecimal moveMoney(BigDecimal money) {
		System.out.println("第二个实现类!");
		return null;
	}
}
