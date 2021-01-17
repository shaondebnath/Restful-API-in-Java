package de.tub.ise.anwsys.pizza;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;
	
	/*@RequestMapping(method = RequestMethod.GET, path = "/pizzaString")
	public List<String> getAllPizzaString() {
		return pizzaService.getAllPizzasString();

	}*/
	
	@RequestMapping(method = RequestMethod.GET, path = "/pizza")
	public List<Pizza> getAllPizza() {
		return pizzaService.getAllPizzas();
		//return finalPizzaList;

	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/pizza/{id}")
	public Pizza getPizza(@PathVariable long id) {
		return pizzaService.getPizza(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/pizza/{newPizza}")
	public String addPizzaByString(@PathVariable String newPizza){  //@RequestBody
		return pizzaService.addPizzaByString(newPizza);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/pizza")
	public void addPizza(@RequestBody Pizza newPizza){  //@RequestBody
		pizzaService.addPizza(newPizza);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/pizza/{id}")
	public void updatePizza(@PathVariable long id, @RequestBody Pizza pizza){  
		pizzaService.updatePizza(id, pizza);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/pizza/{id}")
	public void deletePizza(@PathVariable long id){  
		pizzaService.deletePizza(id);
	}
	
}

/*
 [
    {

        "name": "Cheese Pizza 4 ",
        "size": " Standerd",
        
    },
    {

        "name": "Cheese Pizza 5 ",
        "size": " Standerd",
        
    },
    {

        "name": "Cheese Pizza 6 ",
        "size": " Standerd",
        
    }
]
 */
