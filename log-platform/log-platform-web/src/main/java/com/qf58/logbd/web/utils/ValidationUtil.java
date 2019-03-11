package com.qf58.bd.web.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;

/**
 * Description: validate 实体类验证
 *
 * @Author: weishenpeng
 * Date: 2017/12/21
 * Time: 上午 10:36
 */
public class ValidationUtil {
	private static ValidatorFactory factory = null;
	private static Validator validator = null;

	static {
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	private ValidationUtil() {

	}

	public static Validator getValidator() {
		return validator;
	}

	/**
	 * 获取验证结果
	 *
	 * @param bindingResult
	 * @return
	 */
	public static boolean validFieldHasError(BindingResult bindingResult) {
		return bindingResult.hasErrors();
	}

	/**
	 * 获取验证错误信息
	 *
	 * @param bindingResult
	 * @return
	 */
	public static String getFieldErrorMessage(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder errorMsg = new StringBuilder();
			List<FieldError> errors = bindingResult.getFieldErrors();
			int i = 0;
			for (FieldError fieldError : errors) {
				if (i != 0) {
					errorMsg.append(";");
				}
				errorMsg.append(fieldError.getField() + " : " + fieldError.getDefaultMessage());
				i++;
			}
			return errorMsg.toString();
		}
		return "";
	}
}
