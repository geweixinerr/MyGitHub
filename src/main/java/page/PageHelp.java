package page;

import java.io.Serializable;
import java.util.ArrayList;
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
	 **/
	public static <T extends Serializable, U extends Number> Page<T> limit(Supplier<U> count,
			Supplier<List<T>> pageList, int pageNum, int pageSize) {
		Page<T> page = new Page<>();
		page.setPageNum(ZERO >= pageNum ? 1 : pageNum);
		page.setPageSize(pageSize);
		page.setTotalNum(ZERO);
		page.setPage(Collections.emptyList());

		Optional<Integer> tp = Optional.empty();
		Number totalNum = count.get();
		if (totalNum.intValue() != ZERO) {
			List<T> list = pageList.get();
			page.setTotalNum(list.size());
			page.setPage(list);

			int _tp = totalNum.intValue() / pageSize;
			if (totalNum.intValue() % pageSize != ZERO) {
				_tp = _tp + 1;
			}
			tp = Optional.of(_tp);
			page.setTotalNum(tp.get());
		}

		return page;
	}

	public static void main(String[] args) {
		Page<BbsModel> page = PageHelp.limit(() -> {
			return 3;
		}, () -> {
			List<BbsModel> list = new ArrayList<>();
			BbsModel model = new BbsModel();
			model.setAuthor("geweixin");
			model.setContent("很棒啊~");
			
			list.add(model);
			list.add(model);
			list.add(model);
			return list;
		}, 1, 2);
		
		System.out.println("page----> " + page);
	}
}
