package annotation.date;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { DateFormatValid.class }) // 指明验证类
public @interface DateFormat {

	String value();

	String message() default "日期格式不匹配~";

	boolean required() default false;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
