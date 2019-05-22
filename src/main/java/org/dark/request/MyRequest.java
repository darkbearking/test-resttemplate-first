package org.dark.request;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;

public class MyRequest implements HttpRequest{

	HttpRequest sourceRequest;
	
	//構造，接受原請求，然後對原請求進行轉化
	public MyRequest(HttpRequest sourceRequest) {
		this.sourceRequest = sourceRequest;
	}
	
	public HttpHeaders getHeaders() {
		return sourceRequest.getHeaders();
	}

	public HttpMethod getMethod() {
		return sourceRequest.getMethod();
	}

	/**
	 * 當前方法對URI進行了改寫，不必糾結為新的URI要跳轉到"http://localhost:8080/hello"，當它是瞎寫的就行
	 * 這樣的目的，是為了演示spring的ribbon負載均衡的思想，其思想就是uri的改寫。
	 * 與我們所不同的在於，spring考慮的業務邏輯更加複雜而已。
	 * 所以，對於我們而言，可以從這個攔截器看到spring內核的思想。
	 */
	public URI getURI() {
		try {
			URI newUri = new URI("http://localhost:8080/hello");
			return newUri;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return sourceRequest.getURI();
	}

}
