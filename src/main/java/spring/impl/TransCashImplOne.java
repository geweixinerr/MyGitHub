package spring.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import spring.intf.TransCashIntf;

@Service("impl1")
public class TransCashImplOne implements TransCashIntf{

	public TransCashImplOne() {

	}

	@Override
	public BigDecimal moveMoney(BigDecimal money) {
		System.out.println("第一个实现类!");
		return null;
	}
}
