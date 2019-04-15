package com.example.demo.util;

import com.example.demo.bean.Result;

public class ResHelper {
	public static final String CODE_ERROR = "1";
	public static final String CODE_SUCCESS = "0";
	
	public static final String MESSAGE_SUCCESS = "success";
	
	public static <T> Result<T> success() {
		Result<T> result = new Result<T>();
		result.setCode(CODE_SUCCESS);
		result.setMessage(MESSAGE_SUCCESS);
		return result;
	}
	
	public static <T> Result<T> success(T content) {
		Result<T> result = new Result<T>();
		result.setCode(CODE_SUCCESS);
		result.setMessage(MESSAGE_SUCCESS);
		result.setContent(content);
		return result;
	}

	public static <T> Result<T> error(String message) {
		Result<T> result = new Result<T>();
		result.setCode(CODE_ERROR);
		result.setMessage(message);
		return result;
	}
	
}
