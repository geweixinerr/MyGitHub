package page;

import static org.apache.commons.lang3.StringUtils.*;

import java.util.function.Supplier;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import exception.BusinessRuntimeException;

/**
 * 分页工具类,基于PageHelper
 * 
 * @author gewx
 **/
public final class PageHelperUtils {

	private static int ZERO = 0;
	
	/**
	 * 通用分页方法
	 * 
	 * @author gewx
	 * @param pageParameter 分页参数
	 * @param pageResult 分页数据集
	 * @return void
	 **/
	public static <T> Pages<T> limit(PageParameter pageParameter, Supplier<Page<T>> pageResult) {
		if (pageParameter.getStartpage() == null) {
			throw new BusinessRuntimeException("页码必填");
		}
		
		if (pageParameter.getPagesize() == null) {
			throw new BusinessRuntimeException("页行必填");
		}
		
		if (pageParameter.getPagesize().intValue() == ZERO) {
			throw new BusinessRuntimeException("页行必须大于0");
		}
		
		PageHelper.startPage(pageParameter.getStartpage(), pageParameter.getPagesize());
		if (isNotBlank(pageParameter.getSortname())) {
			PageHelper.orderBy(pageParameter.getSortname());
			if (pageParameter.isSymbol()) {
				PageHelper.orderBy(pageParameter.getSortname() + " DESC");
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
