package edu.mum.rest.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import edu.mum.domain.Item;
import edu.mum.rest.RestHttpHeader;
import edu.mum.rest.service.ItemRestService;

@Component
public class ItemRestServiceImpl implements ItemRestService {

	@Autowired
	RestHttpHeader restHttpHeader;
	
	public List<Item> findAll() {
		
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
		return Arrays.asList(restTemplate.exchange("http://localhost:8080/Lab_12_RestfulWS/itemsrest/", HttpMethod.GET, restHttpHeader.getHttpEntity(), Item[].class).getBody());
 	}

	public Item findOne(Long index) {
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
 		return (restTemplate.exchange("http://localhost:8080/Lab_12_RestfulWS/itemsrest/"+ index, HttpMethod.GET, restHttpHeader.getHttpEntity(), Item.class).getBody());
	}

	public Item save(Item member) {
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
		HttpEntity<Item> httpEntity = new HttpEntity<Item>(member, restHttpHeader.getHttpHeaders());
		restTemplate.postForObject("http://localhost:8080/Lab_12_RestfulWS/itemsrest/add/", httpEntity, Item.class);
		return null;
	}

}
