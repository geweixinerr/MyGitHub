package page;

import static org.apache.commons.lang3.StringUtils.*;

import java.io.Serializable;
import java.util.function.Supplier;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 分页工具类,基于PageHelper
 * 
 * @author gewx
 **/
public final class PageHelperUtils {

	/**
	 * 通用分页方法
	 * 
	 * @author gewx
	 * @param pageParameter 分页参数
	 * @param pageResult 分页数据集
	 * @return void
	 **/
	public static <T extends Serializable> Pages<T> limit(PageParameter pageParameter, Supplier<Page<T>> pageResult) {
		PageHelper.startPage(pageParameter.getStartPage(), pageParameter.getPageSize());
		if (isNotBlank(pageParameter.getSortName())) {
			PageHelper.orderBy(pageParameter.getSortName());
			if (pageParameter.isSymbol()) {
				PageHelper.orderBy(pageParameter.getSortName() + " DESC");
			}
		}

		Page<T> page = pageResult.get();
		Pages<T> pages = new Pages<T>();
		pages.setTotalPageNum(page.getPages());
		pages.setTotalNum(page.getTotal());
		pages.setPageNum(page.getPageNum());
		pages.setPageSize(page.getPageSize());
		pages.setPages(page.getResult());
		return pages;
	}
}
