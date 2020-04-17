package page;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 分页参数
 * **/
@Setter
@Getter
@ToString
public class PageParameter implements Serializable {

	private static final long serialVersionUID = -7696440725170038746L;
	
	/**
	 * 排序页码
	 * **/
	private int startPage;
	
	/**
	 * 页行
	 * **/
	private int pageSize;
	
	/**
	 * 排序列
	 * **/
	private String sortName;
	
	/**
	 * 排序方式false/ASC, true/DESC
	 * **/
	private boolean symbol;

}
