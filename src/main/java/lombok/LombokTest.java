package lombok;

/**
 * @author gewx Lombok测试类
 * **/
public class LombokTest {

	public static void main(String[] args) {
		Order o = new Order();
		o.setGoodsName("苹果手机");
		o.setOrderId("GOODSID20190726");
		
		System.out.println(o);
	}
}
