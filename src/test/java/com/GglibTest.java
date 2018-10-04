package spring.reflect;

import org.junit.Test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

public class GglibTest {

	public GglibTest() {

	}

	@Test
	public void testFixedValue(){
	    Enhancer enhancer = new Enhancer();
	    enhancer.setSuperclass(CglibSimple.class);
	    enhancer.setCallback(new FixedValue() {
	        @Override
	        public Object loadObject() throws Exception {
	            return "Hello cglib";
	        }
	    });
	    
	    CglibSimple proxy = (CglibSimple) enhancer.create();
	    System.out.println(proxy.test(null)); //拦截test，输出Hello cglib
	    System.out.println(proxy.toString()); 
	    System.out.println(proxy.getClass());
	    System.out.println(proxy.hashCode());
	}
}
