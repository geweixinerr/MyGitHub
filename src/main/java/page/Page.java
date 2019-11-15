package page;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author gewx 分页工具类
 **/
@Data
public final class Page<T> implements Serializable {

	private static final long serialVersionUID = -4184578058752100571L;

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
