package annotation.date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

/**
 * @author geweixinerr 注解验证器
 * 
 **/
public class DateFormatValid implements ConstraintValidator<DateFormat, String> {

	private DateFormat date;

	@Override
	public void initialize(DateFormat date) {
		this.date = date;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isBlank(value)) {
			return true;
		} else {
			System.out.println(date.required());
			return false;
		}
	}

}
