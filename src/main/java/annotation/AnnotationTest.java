package annotation;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public class AnnotationTest {

	@ListNotNull
	private List<String> name;

	public static void main(String[] args) {
		AnnotationTest t = new AnnotationTest();
		Validator valid = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<AnnotationTest>> validators = valid.validate(t);
		for (ConstraintViolation<AnnotationTest> constraintViolation : validators) {
			String message = constraintViolation.getPropertyPath() + "|" + constraintViolation.getMessage() + "|"
					+ constraintViolation.getInvalidValue();

			System.out.println("注解校验异常:" + message);
		}
	}
	
}