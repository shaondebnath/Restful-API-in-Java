package de.tub.ise.anwsys.topping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToppingController {
	
	@Autowired
	private ToppingService toppingService;
	
	/*@RequestMapping(method = RequestMethod.GET, path = "/ToppingString")
	public List<String> getAllToppingString() {
		return toppingService.getAllToppingsString();

	}*/
	
	@RequestMapping(method = RequestMethod.GET, path = "/pizza/{pizzaId}/topping")
	public List<Topping> getAllTopping(@PathVariable long pizzaId) {
		return toppingService.getAllToppings(pizzaId);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/pizza/{pizzaId}/topping/{toppingId}")
	public Topping getTopping(@PathVariable long toppingId) {
		return toppingService.getTopping(toppingId);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/pizza/{pizzaId}/topping/{newTopping}")
	public String addToppingByString(@PathVariable long pizzaId, @PathVariable String newTopping){ 
		newTopping = newTopping+","+String.valueOf(pizzaId);//@RequestBody
		return toppingService.addToppingByString(newTopping);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/pizza/{pizzaId}/topping")
	public void addTopping(@PathVariable long pizzaId, @RequestBody Topping newTopping){  //@RequestBody
		newTopping.setPizzaId(pizzaId);
		toppingService.addTopping(newTopping);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/pizza/{pizzaId}/topping/{toppingId}")
	public void updateTopping(@PathVariable long pizzaId, @PathVariable long toppingId, @RequestBody Topping topping){  
		topping.setPizzaId(pizzaId);
		toppingService.updateTopping(toppingId, topping);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/pizza/{pizzaId}/topping/{toppingId}")
	public void deleteTopping(@PathVariable long pizzaId, @PathVariable long toppingId){  
		toppingService.deleteTopping(pizzaId, toppingId);
	}
	
}

/*
 [
    {

        "name": "Cheese Pizza 4 ",
        "size": " Standerd",
        "price": 4.2
    },
    {

        "name": "Cheese Pizza 5 ",
        "size": " Standerd",
        "price": 4.2
    },
    {

        "name": "Cheese Pizza 6 ",
        "size": " Standerd",
        "price": 4
    }
]
 */
