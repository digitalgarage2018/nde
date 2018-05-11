package it.iseed.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.iseed.entities.Order;
import it.iseed.services.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "/findById", method = RequestMethod.GET)
	public ResponseEntity<Order> getHouseById(@RequestParam(value = "id") int id){
		Order order = orderService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(order);
	}

}
