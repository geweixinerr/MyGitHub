package spring.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import spring.intf.TransCashIntf;

@Service("transFactory")
public class TransFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransFactory.class);
	
	@Autowired
	@Qualifier("impl1")
	private TransCashIntf cash;

	public TransFactory() {
		// TODO Auto-generated constructor stub
	}

	public void getInstance() {
		LOGGER.info("启动获取实例!");
		cash.moveMoney(null);
	}
}
