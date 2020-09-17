package annotation.date;

import util.ValidatorUtils;

public final class DateFormatMain {

	public static void main(String[] args) {
		Dto dto = new Dto();
//		dto.setDate("2020/9/1");
		
		ValidatorUtils.FieldBean bean = ValidatorUtils.validator(dto);
		if (bean.isSuccess()) {
			System.out.println(bean.getMsg());
		}
	}
}
