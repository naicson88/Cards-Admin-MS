package com.naicson.yugioh.util.exceptions;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApiExceptions {
	
	private final String msg;
	private final int statusCode;
	private final HttpStatus error;
	private final ZonedDateTime time;
	
	public ApiExceptions(String msg, int statusCode, HttpStatus httpStatus, ZonedDateTime time) {
		this.msg = msg;	
		this.error = httpStatus;
		this.time = time;	
		this.statusCode = statusCode;
	}
	
	public String getMsg() {
		return msg;
	}

	public HttpStatus getHttpStatus() {
		return error;
	}
	public ZonedDateTime getTime() {
		return time;
	}

	public int getStatusCode() {
		return statusCode;
	}
	
}
