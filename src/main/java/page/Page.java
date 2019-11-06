package page;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author gewx 分页工具类
 * **/
@Data
public final class Page<T> implements Serializable {

	private static final long serialVersionUID = 7108964245494609558L;

	private int totalNum; // 总行数

	private int totalPageNum; // 总页数

	private int pageSize; // 页行

	private int pageNum; // 页码

	private List<T> page; // 当前页数据集
}
