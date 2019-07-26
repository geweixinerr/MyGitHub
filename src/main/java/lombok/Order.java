package lombok;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Order implements Serializable {

	private static final long serialVersionUID = -5152910235022553549L;

	private String orderId;
	
	private String goodsName;
}
