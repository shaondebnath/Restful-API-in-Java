package de.tub.ise.anwsys.pizza;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.tub.ise.anwsys.pizza.Pizza;
import de.tub.ise.anwsys.response.CustomException;
import de.tub.ise.anwsys.topping.Topping;
import de.tub.ise.anwsys.topping.ToppingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;



@Service
public class PizzaService {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Autowired
	ToppingService toppingService;
	
	
/*	PizzaSize size = PizzaSize.valueOf("Standard");
	List<Pizza> mypizzas = new ArrayList<Pizza>(Arrays.asList(
			new Pizza("Cheese Pizza", size),
			new Pizza("Chicken Pizza", size),
			new Pizza("Chicken Pizza", size),
			new Pizza("Pizza Margarita", size)
			));
	*/
	
/*	private static List<Pizza> pizzas = new ArrayList<Pizza>(Arrays.asList(
			new Pizza("Cheese Pizza", "Standerd", 4.20f),
			new Pizza("Chicken Pizza", "Standerd", 5.20f),
			new Pizza("Chicken Pizza", "Large", 7.20f),
			new Pizza("Pizza Margarita", "Standerd", 6.20f)
			));
	
	public List<String> getAllPizzasString(){
		List<String> pizzaMenu = new ArrayList<String>();
						
		for(int i=0; i < pizzas.size();i++) {
			String item;
			item = "Id = " + pizzas.get(i).getID() + ", Name = " +pizzas.get(i).getName() + " , Size = " + pizzas.get(i).getSize() + ", Price = " + pizzas.get(i).getPrice();
			pizzaMenu.add(item);
		}
			
		return pizzaMenu;
	}*/
	
	float standardPrice = 5.0f;
	float largePrice = 8.5f;
	
	public List<Pizza> getAllPizzas(){
		List<Pizza> pizzas = new ArrayList<>();
		pizzaRepository.findAll()  // get all pizza list
		.forEach(pizzas::add);
		
		if(pizzas.size() == 0)
			throw new CustomException(404, "No pizza exist. Please add Pizza using post method", HttpStatus.NOT_FOUND);
			
		//
		List<Pizza> finalPizzaList = new ArrayList<>();
		for (int i=0; i< pizzas.size(); i++) {
			Pizza thisPizza;
			thisPizza= pizzas.get(i);
			float pizzaPrice = thisPizza.getPrice();
			//update pizza price = base price + all other topping price
			List<Topping> toppings = toppingService.getAllToppingsForPizza(thisPizza.getID());
			
			if(toppings.size()>0) {
				for(int j=0; j< toppings.size();j++) {
					pizzaPrice = pizzaPrice + toppings.get(j).getPrice();
					
				}
				thisPizza.setPrice(pizzaPrice);
			}
			
			
			finalPizzaList.add(thisPizza);
		}
			
		//return pizzas;
		return finalPizzaList;
		
	}
	
	public Pizza getPizza(long id) {
		//return pizzaRepository.findOne(id);
		if(pizzaRepository.findOne(id)!= null) {
			return pizzaRepository.findOne(id);
		}
		else
			throw new CustomException(404, "Pizza for Id: "+id +" not found", HttpStatus.NOT_FOUND);
			
	}
	
	//for testing
	public String addPizzaByString(String newPizza) {
		
		List<String> newPizzaRow = new ArrayList<String>(Arrays.asList(newPizza.split("-")));
		for(int i=0; i < newPizzaRow.size(); i++ ) {
			List<String> newPizzaDetails = new ArrayList<String>(Arrays.asList(newPizzaRow.get(i).split(",")));
			if(newPizzaDetails.size()==2) {
				//pizzas.add(new Pizza(newPizzaDetails.get(0), newPizzaDetails.get(1), Float.valueOf(newPizzaDetails.get(2))));
				
				float pizzaPrice = 0;
				if(newPizzaDetails.get(1).equals("Standard")) pizzaPrice = standardPrice; else if(newPizzaDetails.get(1).equals("Large")) pizzaPrice = largePrice;  
				//pizzaRepository.save(new Pizza(newPizzaDetails.get(0), PizzaSize.valueOf(newPizzaDetails.get(1)), pizzaPrice));
				
				Pizza thisPizza = new Pizza	(newPizzaDetails.get(0), PizzaSize.valueOf(newPizzaDetails.get(1)));
				thisPizza.setPrice(pizzaPrice);
				
				pizzaRepository.save(thisPizza);
				
				
			}
			else
				return "Error in Syntax: "+ newPizzaRow.get(i);				
		}
		
		return "Successfully "+ newPizzaRow.size() +" item(s) Added...";
		
	}
	
	public void addPizza(Pizza newPizza) {
		float pizzaPrice = 0;
				
		if(newPizza.getSize() == PizzaSize.Standard) pizzaPrice = standardPrice; else if(newPizza.getSize()== PizzaSize.Large) pizzaPrice = largePrice;
		
		if(newPizza.getName()=="")
			throw new CustomException(200, "Pizza name can not be empty", HttpStatus.NOT_ACCEPTABLE);
				
		newPizza.setPrice(pizzaPrice);
		pizzaRepository.save(newPizza);
		
		throw new CustomException(200, "Pizza added sucessfully", HttpStatus.OK);
		
	}
	
	public void updatePizza(long id, Pizza pizza) {
		if(pizzaRepository.findOne(id)!= null) {
			Pizza updatePizza = pizzaRepository.findOne(id);	
			updatePizza.setName(pizza.getName());
			updatePizza.setSize(pizza.getSize());
			float pizzaPrice = 0.0f;
			if(pizza.getSize() == PizzaSize.Standard) pizzaPrice = standardPrice; else if(pizza.getSize()== PizzaSize.Large) pizzaPrice = largePrice;
				updatePizza.setPrice(pizzaPrice);
			
			pizzaRepository.save(updatePizza);
			
			throw new CustomException(200, "Pizza updated sucessfully", HttpStatus.OK);
			
		}
		else
			throw new CustomException(404, "Data for Id: "+id +" not found", HttpStatus.NOT_FOUND);
			
	}
	
	public void deletePizza(long id) {
		if(pizzaRepository.findOne(id)!= null) {
			pizzaRepository.delete(id);
			throw new CustomException(200, "Data for Id: "+id +" sucessfully deleted", HttpStatus.OK);
		}
		else
			throw new CustomException(404, "Data for Id: "+id +" not found", HttpStatus.NOT_FOUND);
	}
		

}
