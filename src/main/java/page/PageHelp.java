package page;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author gewx 分页辅助工具类
 **/
public final class PageHelp {

	private static final int ZERO = 0;

	/**
	 * @author gewx 分页辅助方法
	 * @param count 待分页数据集总数, pageList 当前页数据集, pageNum 页码, pageSize 页行
	 * @return 分页对象Page
	 **/
	public static <T extends Serializable, U extends Number> Page<T> limit(Supplier<U> count,
			Supplier<List<T>> pageList, int pageNum, int pageSize) {
		Page<T> page = new Page<>();
		page.setTotalNum(ZERO);
		page.setTotalPageNum(ZERO);
		page.setPageNum(ZERO >= pageNum ? 1 : pageNum);
		page.setPageSize(pageSize);
		page.setPage(Collections.emptyList());

		Optional<Integer> optTp = Optional.empty();
		Number totalNum = count.get();
		if (totalNum.intValue() != ZERO) {
			page.setTotalNum(totalNum.intValue());
			List<T> list = pageList.get();
			page.setPage(list);

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
