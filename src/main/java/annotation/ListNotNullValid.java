package annotation;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author geweixinerr 注解验证器 
* 
* **/
public class ListNotNullValid implements ConstraintValidator<ListNotNull, List<String>>{

    @SuppressWarnings("unused")
    private ListNotNull list;
    
    @Override
    public void initialize(ListNotNull list) {
        this.list = list;
    }

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        if (value != null && value.size() != 0) {
            return true;
        } else {
            context.disableDefaultConstraintViolation(); //关闭默认验证message(Delete动作)
            //新增message
            context.buildConstraintViolationWithTemplate("List不能为NULL OR Empty!").addConstraintViolation();
            return false;
        }
    }

}
