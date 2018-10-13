package proxyImpl;

import org.springframework.stereotype.Service;

import proxyIntf.BusinessBeforeAndAfterIntf;

@Service
public class BusinessBeforeAndAfterImpl implements BusinessBeforeAndAfterIntf<String> {

	@Override
	public String before(String... object) {
        System.out.println("执行前置----------------->" + object[0]);
        String result = "前置结果";
		return result;
	}

	@Override
	public String after(String... object) {
        System.out.println("执行后置----------------->" + object[0]);
        String result = "后置结果";
		return result;
	}

}
