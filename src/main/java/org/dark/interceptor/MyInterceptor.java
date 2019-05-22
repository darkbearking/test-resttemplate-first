package org.dark.interceptor;

import java.io.IOException;

import org.dark.request.MyRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * 當前類實現了攔截器接口
 * @author liwei
 *
 */
public class MyInterceptor implements ClientHttpRequestInterceptor{

	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		System.out.println("這是自定義攔截器");
		System.out.println("  " + request.getURI());
		System.out.println("這是舊的URI " + request.getURI());
		
		HttpRequest newRequest = new MyRequest(request);
				System.out.println("這是新的的URI " + newRequest.getURI());
				
 		return execution.execute(newRequest, body);
	}

}
