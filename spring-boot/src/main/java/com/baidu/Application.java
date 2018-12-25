package com.baidu;

import com.baidu.model.PropertiesTest;
import com.baidu.model.RunnableTask1;
import com.baidu.service.TestService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@MapperScan("com.baidu.dao")
@RestController
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@ServletComponentScan(value= "com.baidu.listener")
public class Application {

	public static void main (String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private Logger logger = LoggerFactory.getLogger(Logger.class);

	@Autowired
	private PropertiesTest propertiesTest;

	@Autowired
	private TestService testService;

	@RequestMapping(value = "test1",method = RequestMethod.GET)
	public String testWelcome(String name){
		return name+"欢迎来到spring-boot";
	}

	@RequestMapping(value = "test2",method = RequestMethod.GET)
	public String test2(){
		logger.info(propertiesTest.getTmallId());
		logger.info(propertiesTest.getbValue());
		logger.info(propertiesTest.getbNumber());
		logger.info(propertiesTest.getBignumber());
		logger.info(propertiesTest.getbTest1());
		logger.info(propertiesTest.getbTest2());
		return "测试OK!";
	}

	/**
	 * 同步执行,依次执行
	 * @return
	 */
	@RequestMapping(value = "test3",method = RequestMethod.GET)
	public String test3(){
		testService.getTest2();
		logger.info(Thread.currentThread().getName()+"==========主线程名");
		return "同步,解析完成......";
	}

	/**
	 * 异步执行,主线程和线程任务互不影响
	 * @return
	 */
	@RequestMapping(value = "test4",method = RequestMethod.GET)
	public String test4(){
		ExecutorService service = Executors.newFixedThreadPool(5);
		RunnableTask1 task1 = new RunnableTask1();
		service.execute(task1);
		logger.info("=========》当前线程名："+Thread.currentThread().getName());
		return "异步,解析完成......";
	}

	@Bean
	public RequestContextListener requestContextListener(){
		return new RequestContextListener();
	}
}
