package de.tub.ise.anwsys.pizzaorder;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PizzaOrderController {
	
	@Autowired
	private PizzaOrderService pizzaOrderService;
	
	/*@RequestMapping(method = RequestMethod.GET, path = "/pizzaString")
	public List<String> getAllPizzaString() {
		return pizzaOrderService.getAllPizzasString();

	}*/
	
	@RequestMapping(method = RequestMethod.GET, path = "/order")
	public List<PizzaOrder> getAllPizzaOrder() {
		
		
		
		return pizzaOrderService.getAllPizzaOrders();
		//return finalPizzaList;

	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/order/{id}")
	public PizzaOrder getPizzaOrder(@PathVariable long id) {
		return pizzaOrderService.getPizzaOrder(id);
	}
	

	@RequestMapping(method = RequestMethod.POST, path = "/order")
	public void addPizzaOrder(@RequestBody String newOrder) throws IOException{  //@RequestBody
		
		
		pizzaOrderService.addPizzaOrder(newOrder);
	}
	
	/*@RequestMapping(method = RequestMethod.PUT, path = "/pizza/{id}")
	public String updatePizza(@PathVariable long id, @RequestBody PizzaOrder pizza){  
		return pizzaOrderService.updatePizza(id, pizza);
	}*/
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/order/{id}")
	public void deletePizzaOrder(@PathVariable long id){  
		pizzaOrderService.deletePizzaOrder(id);
	}
	
}

