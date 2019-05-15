package it.sella.smb.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpRestClient {

	@Value("${restClient.connect.timeout}")
	private int CONNECT_TIMEOUT;

	@Value("${restClient.socket.timeout}")
	private int SOCKET_TIMEOUT;

	//@Value("${restClient.max.request.retry}")
	//private int MAX_RETRY;

	public <RClass> RClass getForObject(String url, Class<RClass> typeClass) {
		final RestTemplate restTemplate = new RestTemplate();
		setTimeout(restTemplate);
		return restTemplate.getForObject(url, typeClass);
	}

	public <RClass> ResponseEntity<RClass> getForEntity(String url, Class<RClass> typeClass) {
		final RestTemplate restTemplate = new RestTemplate();
		setTimeout(restTemplate);
		return restTemplate.getForEntity(url, typeClass);
	}

	public <PayLoad, RClass> ResponseEntity<RClass> postForEntity(String url, PayLoad payload, Class<RClass> typeClass,HttpHeaders headers ) {
		final RestTemplate restTemplate = new RestTemplate();
		setTimeout(restTemplate);
		if(headers==null) {
			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		}
		final HttpEntity<PayLoad> entity = new HttpEntity<PayLoad>(payload, headers);
		return restTemplate.postForEntity(url, entity, typeClass);
	}

	public <PayLoad, RClass> RClass postForObject(String url, PayLoad payload, Class<RClass> typeClass,HttpHeaders headers ) {
		final RestTemplate restTemplate = new RestTemplate();
		setTimeout(restTemplate);
		if(headers==null) {
			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		}
		final HttpEntity<PayLoad> entity = new HttpEntity<PayLoad>(payload, headers);
		return restTemplate.postForObject(url, entity, typeClass);
	}

	public void setTimeout(RestTemplate restTemplate) {

		final SimpleClientHttpRequestFactory rf =
				(SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
		rf.setReadTimeout(SOCKET_TIMEOUT * 1000);
		rf.setConnectTimeout(CONNECT_TIMEOUT * 1000);


	}

}
