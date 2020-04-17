package page;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * 分页辅助工具类
 * 
 * @author gewx
 **/
public final class PageHelp {

	// 校验数值ZERO
	private static final int ZERO = 0;

	// 默认页码
	private static final int DEFAULT_PAGENUM = 1;

	/**
	 * 分页辅助方法
	 * 
	 * @author gewx
	 * @param count 待分页数据集总数, pageList 当前页数据集, pageNum 页码, pageSize 页行
	 * @return 分页对象Page
	 **/
	public static <T extends Serializable, U extends Number> Pages<T> limit(Supplier<U> count,
			Supplier<List<T>> pageList, int pageNum, int pageSize) {
		Pages<T> page = new Pages<>();
		page.setTotalNum(ZERO);
		page.setTotalPageNum(ZERO);
		page.setPageNum(ZERO >= pageNum ? DEFAULT_PAGENUM : pageNum);
		page.setPageSize(pageSize);
		page.setPages(Collections.emptyList());

		Optional<Integer> optTp = Optional.empty();
		Number totalNum = count.get();
		if (totalNum.intValue() != ZERO) {
			page.setTotalNum(totalNum.intValue());
			List<T> list = pageList.get();
			page.setPages(list);

			int tp = totalNum.intValue() / pageSize;
			if (totalNum.intValue() % pageSize != ZERO) {
				tp = tp + 1;
			}
			optTp = Optional.of(tp);
			page.setTotalPageNum(optTp.get());
		}

		return page;
	}
}
