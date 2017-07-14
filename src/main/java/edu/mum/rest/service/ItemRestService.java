package edu.mum.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import edu.mum.domain.Item;

@Component
public interface ItemRestService {

 	public List<Item> findAll();

	public Item findOne(Long index);

	public Item save(Item member);

}
