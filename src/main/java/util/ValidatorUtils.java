package util;

import lombok.ToString;

import static util.StringUtil.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;

/**
 * 验证框架工具类
 *
 * @author gewx
 **/
public final class ValidatorUtils {

	/**
	 * 验证工具工厂类
	 **/
	private static ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();

	/**
	 * 验证工具方法
	 *
	 * @author pengqh
	 * @param list 验证集合bean
	 * @param bool 过滤条件反转:true|过滤传递字段, false|过滤未传递字段
	 * @param args 过滤字段
	 * @return 验证消息Bean
	 **/
	public static <T> FieldBean validator(List<T> list, boolean bool, String... args) {
		Validator validator = FACTORY.getValidator();
		Iterator<T> it = list.iterator();
		while (it.hasNext()) {
			T t = it.next();
			Set<ConstraintViolation<T>> resultSet = validator.validate(t);
			resultSet = resultSet.stream()
					.filter(item -> bool ? !Arrays.asList(args).contains(getString(item.getPropertyPath()))
							: Arrays.asList(args).contains(getString(item.getPropertyPath())))
					.collect(Collectors.toSet());

			for (ConstraintViolation<T> item : resultSet) {
				return new FieldBean(item.getMessageTemplate(), Boolean.TRUE);
			}
		}
		return new FieldBean(StringUtils.EMPTY, Boolean.FALSE);
	}

	/**
	 * 验证工具方法
	 *
	 * @author pengqh
	 * @param list 验证集合bean
	 * @param args 过滤字段
	 * @return 验证消息Bean
	 **/
	public static <T> FieldBean validator(List<T> list, String... args) {
		return validator(list, true, args);
	}

	/**
	 * 验证工具方法
	 *
	 * @author pengqh
	 * @param bean 验证bean
	 * @param bool 过滤条件反转:true|过滤传递字段, false|过滤未传递字段
	 * @param args 过滤字段
	 * @return 验证消息Bean
	 **/
	public static <T> FieldBean validator(T bean, boolean bool, String... args) {
		Validator validator = FACTORY.getValidator();
		Set<ConstraintViolation<T>> resultSet = validator.validate(bean);
		resultSet = resultSet.stream()
				.filter(item -> bool ? !Arrays.asList(args).contains(getString(item.getPropertyPath()))
						: Arrays.asList(args).contains(getString(item.getPropertyPath())))
				.collect(Collectors.toSet());

		for (ConstraintViolation<T> item : resultSet) {
			return new FieldBean(item.getMessageTemplate(), Boolean.TRUE);
		}
		return new FieldBean(StringUtils.EMPTY, Boolean.FALSE);
	}

	/**
	 * 验证工具方法
	 *
	 * @author pengqh
	 * @param bean 验证bean
	 * @param args 过滤字段
	 * @return 验证消息Bean
	 **/
	public static <T> FieldBean validator(T bean, String... args) {
		return validator(bean, true, args);
	}

	/**
	 * 验证工具方法
	 * 
	 * @author gewx
	 * @param bean         验证bean
	 * @param propertyName 验证属性
	 * @return 验证消息Bean
	 **/
	public static <T extends Serializable> FieldBean validator(T bean, String propertyName) {
		Validator validator = FACTORY.getValidator();
		Set<ConstraintViolation<T>> resultSet = validator.validateProperty(bean, propertyName);
		FieldBean resultBean = new FieldBean(StringUtils.EMPTY, Boolean.FALSE);
		Iterator<ConstraintViolation<T>> it = resultSet.iterator();
		while (it.hasNext()) {
			ConstraintViolation<T> result = it.next();
			resultBean = new FieldBean(result.getMessageTemplate(), Boolean.TRUE);
		}

		return resultBean;
	}

	/**
	 * 验证工具方法
	 * 
	 * @author gewx
	 * @param bean 验证bean
	 * @return 验证消息Bean
	 **/
	public static <T extends Serializable> FieldBean validator(T bean) {
		Validator validator = FACTORY.getValidator();
		Set<ConstraintViolation<T>> resultSet = validator.validate(bean);

		FieldBean resultBean = new FieldBean(StringUtils.EMPTY, Boolean.FALSE);
		Iterator<ConstraintViolation<T>> it = resultSet.iterator();
		while (it.hasNext()) {
			ConstraintViolation<T> result = it.next();
			resultBean = new FieldBean(result.getMessageTemplate(), Boolean.TRUE);
			break;
		}
		return resultBean;
	}

	/**
	 * 数据验证结果载体类
	 **/
	@ToString
	public static class FieldBean {

		private final String msg;

		private final Boolean success;

		public FieldBean(String msg, Boolean success) {
			this.msg = msg;
			this.success = success;
		}

		public Boolean isSuccess() {
			return this.success;
		}

		public String getMsg() {
			return this.msg;
		}
	}
}
