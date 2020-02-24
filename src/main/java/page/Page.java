package page;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 分页工具类
 * 
 * @author gewx
 **/
@Data
public final class Page<T> implements Serializable {

	private static final long serialVersionUID = 6677852537002542077L;

	/**
	 * 总行数
	 **/
	private int totalNum;

	/**
	 * 总页数
	 **/
	private int totalPageNum;

	/**
	 * 页行
	 **/
	private int pageSize;

	/**
	 * 页码
	 **/
	private int pageNum;

	/**
	 * 当前页数据集
	 **/
	private List<T> page;
}
