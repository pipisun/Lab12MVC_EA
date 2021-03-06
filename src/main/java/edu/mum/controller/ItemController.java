package edu.mum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.domain.Item;
import edu.mum.rest.service.ItemRestService;

@Controller
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	ItemRestService itemRestService;
 
 	@RequestMapping({"","/all"})
	public String list(Model model) {
		model.addAttribute("items", itemRestService.findAll());
		return "items";
	}
	
 	@RequestMapping("/item")
	public String getItemById(Model model, @RequestParam("id") Long id) {

		Item item = itemRestService.findOne(id);
		model.addAttribute("item", item);
		return "item";
	}

	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewItemForm(@ModelAttribute("newItem") Item newItem) {
	   return "addItem";
	}
	   
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewItemForm(@ModelAttribute("newItem") @Valid Item itemToBeAdded, BindingResult result) {
		if(result.hasErrors()) {
			return "addItem";
		}

 		try {
 			itemRestService.save(itemToBeAdded);
		} catch (Exception up) {
	      System.out.println("Transaction Failed!!!");
 
		}
		
	   	return "redirect:/items";
	}
	
   
}
