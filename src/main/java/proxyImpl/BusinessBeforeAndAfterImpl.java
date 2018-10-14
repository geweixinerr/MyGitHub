package proxyImpl;

import org.springframework.stereotype.Service;

import proxyIntf.BusinessBeforeAndAfterIntf;

/**
 * @author gewx 之前业务代码的事前与事后
 * **/
@Service
public class BusinessBeforeAndAfterImpl implements BusinessBeforeAndAfterIntf<Object> {

	@Override
	public String before(Object... object) {
        System.out.println("执行前置----------------->" + object[0]);
        String result = "前置结果";
		return result;
	}

	@Override
	public String after(Object... object) {
        System.out.println("执行后置----------------->" + object[0]);
        String result = "后置结果";
		return result;
	}
}
