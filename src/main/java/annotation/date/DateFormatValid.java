package annotation.date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import util.DateUtils;

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
		if (date.required() && StringUtils.isBlank(value)) {
			return false;
		}
		if (StringUtils.isNotBlank(value)) {
			return DateUtils.validDate(value, date.value());
		}
		return true;
	}
}
