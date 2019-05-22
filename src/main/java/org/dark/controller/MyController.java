package org.dark.controller;

import org.dark.intf.MyLoadBalance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
public class MyController {

	/**
	 * 這裡需要引入bean註解才可以，其目的是為了將這兩個方法加入到spring容器中進行管理
	 * 只有這樣，才能被MyConfig中的RestTemplate集合加載
	 * @return
	 */
	@Bean
	@MyLoadBalance
	public RestTemplate tplA() {
		return new RestTemplate();
	}
	
	@Bean
	@MyLoadBalance
	public RestTemplate tplB() {
		return new RestTemplate();
	}
	
	@RequestMapping(value = "/call", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String call() {
		RestTemplate tpl = tplA();
		String json = tpl.getForObject("http://hello-service/call", String.class);
		return json;
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String hello() {
		return "Hello World";
	}
}
