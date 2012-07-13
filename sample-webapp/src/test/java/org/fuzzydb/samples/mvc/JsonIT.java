package org.fuzzydb.samples.mvc;

import org.codehaus.jackson.map.ObjectMapper;
import org.fuzzydb.samples.GenericEntity;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class JsonIT {
	
	private final RestTemplate template;
	private final String requestBody = "{\"postcode\": \"CB4\", \"description\":\"something\"}";
//	private final String requestBody = "{\"description\":\"something\"}";
//	private final String requestBody = "{ \"postcode\": \"CB4\" }";

	public JsonIT() {
		template = new RestTemplate(); // Use default as we need StringHttpMessageConverter for request
		// as well as Jackson one for response
	}
	
	@Test
	@Ignore("Integration test needs filtering and running with deployed app")
	public void testSimpleSearch() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);
		
		ResponseEntity<String> entity = template.postForEntity("http://localhost:8081/fuzzydb-sample-webapp/autos/json/search", request, String.class);
		System.out.println(entity.getBody());
	}
	
	@Test
	public void testJsonDeser() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		GenericEntity readValue = mapper.readValue(requestBody, GenericEntity.class);
		
		System.out.println(readValue.getPostcode());
	}

}
