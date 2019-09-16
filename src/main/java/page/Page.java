package page;

import java.util.List;

import lombok.Data;

/**
 * @author gewx 分页工具类
 * **/
@Data
public final class Page<T> {

	private int totalNum; //总行
	
	private int pageSize; //页行
	
	private int pageNum; //页码
	
	private List<T> page; //当前页数据集
}
