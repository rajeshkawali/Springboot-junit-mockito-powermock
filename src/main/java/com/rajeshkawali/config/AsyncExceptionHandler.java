package com.rajeshkawali.config;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;
/**
   @author Rajesh Kawali
*/
@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
	@Override
	public void handleUncaughtException(Throwable ex, Method method, Object... args) {
		System.out.println("Method Name: " + method.getName());
		System.out.println("args: " + Arrays.toString(args));
		System.out.println("Error Message: " + ex.getMessage());
	}
}
