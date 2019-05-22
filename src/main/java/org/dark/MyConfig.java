package org.dark;

import java.util.Collections;
import java.util.List;

import org.dark.interceptor.MyInterceptor;
import org.dark.intf.MyLoadBalance;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

/**
 * 當前類模擬的是為每一個RestTemplate添加一個攔截器的功能
 * @author liwei
 *
 */
@Configuration
public class MyConfig {
	
	//這裡使用autowire的目的，就是讓工程中所有使用了@MyLoadBalance註解的都會加入到這個集合中。依賴注入的思想
	@Autowired(required = false)
	@MyLoadBalance
	private List<RestTemplate> tpls = Collections.emptyList();
	
	@Bean
	public SmartInitializingSingleton lbInitializing() {
		return new SmartInitializingSingleton() {
			
			public void afterSingletonsInstantiated() {
				System.out.println(tpls.size());
				
				for(RestTemplate tpl : tpls) {
					List<ClientHttpRequestInterceptor> interceptors = tpl.getInterceptors();
					interceptors.add(new MyInterceptor());
					tpl.setInterceptors(interceptors);
				}
			}
		};
	}
}
