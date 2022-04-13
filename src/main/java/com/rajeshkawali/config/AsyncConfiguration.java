
package com.rajeshkawali.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
  @author Rajesh Kawali
  
  @see http://www.bigsoft.co.uk/blog/2009/11/27/rules-of-a-threadpoolexecutor-pool-size
  
  1.If the number of threads is less than the corePoolSize, create a new Thread to run a new task.
  2.If the number of threads is equal (or greater than) the corePoolSize, put the task into the queue.
  3.If the queue is full, and the number of threads is less than the maxPoolSize, create a new thread to run tasks in.
  4.If the queue is full, and the number of threads is greater than or equal to maxPoolSize, reject the task. 
 */

@EnableAsync
@Configuration
public class AsyncConfiguration {

	@Autowired
	private AsyncExceptionHandler asyncExceptionHandler;

	@Bean("asyncExecutor")
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(20);
		taskExecutor.setMaxPoolSize(500);
		taskExecutor.setQueueCapacity(500);
		taskExecutor.setThreadNamePrefix("AsyncThread-");
		taskExecutor.initialize();
		return taskExecutor;
	}

	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return asyncExceptionHandler;
	}
}