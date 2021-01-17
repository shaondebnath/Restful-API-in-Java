package de.tub.ise.anwsys.topping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.tub.ise.anwsys.pizza.PizzaRepository;
import de.tub.ise.anwsys.response.CustomException;
import de.tub.ise.anwsys.topping.Topping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;



@Service
public class ToppingService {
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	@Autowired
	private PizzaRepository pizzaRepository;
	

	public List<Topping> getAllToppings(long pizzaId){
		List<Topping> toppings = new ArrayList<>();
		toppingRepository.findByPizzaId(pizzaId)
		.forEach(toppings::add);
		
		if(toppings.size() == 0)
			throw new CustomException(404, "No toppings exist for pizzaId: "+pizzaId+". Please add some data using post method", HttpStatus.NOT_FOUND);
		
		return toppings;
	}
	
	public List<Topping> getAllToppingsForPizza(long pizzaId){
		List<Topping> toppings = new ArrayList<>();
		toppingRepository.findByPizzaId(pizzaId)
		.forEach(toppings::add);
		
		return toppings;
	}
	
	public Topping getTopping(long id) {		
		
		if(toppingRepository.findOne(id)!= null) {
			return toppingRepository.findOne(id);
		}
		else
			throw new CustomException(404, "Toppping for Id: "+id +" not found", HttpStatus.NOT_FOUND);
			
	}
	
	//for testing
	public String addToppingByString(String newTopping) {
		
		List<String> newToppingRow = new ArrayList<String>(Arrays.asList(newTopping.split("-")));
		for(int i=0; i < newToppingRow.size(); i++ ) {
			List<String> newToppingDetails = new ArrayList<String>(Arrays.asList(newToppingRow.get(i).split(",")));
			if(newToppingDetails.size()==2) {
				//toppings.add(new Topping(newToppingDetails.get(0),Float.valueOf(newToppingDetails.get(1)),Long.valueOf(newToppingDetails.get(2))));
				toppingRepository.save(new Topping(newToppingDetails.get(0), Float.valueOf(newToppingDetails.get(1)),Long.valueOf(newToppingDetails.get(2))));
				
			}
			else
				return "Error in Syntax: "+ newToppingRow.get(i);				
		}
		
		return "Successfully "+ newToppingRow.size() +" item(s) Added...";
		
	}
	
	
	public void addTopping(Topping newTopping) {
		if(pizzaRepository.findOne(newTopping.getPizzaId())==null)
			throw new CustomException(404, "Topping can not be added. Because Pizza for Id: "+newTopping.getPizzaId() +" not found.", HttpStatus.NOT_FOUND);
		
		
		toppingRepository.save(newTopping);
		throw new CustomException(200, "Topping added sucessfully", HttpStatus.OK);
		
	}
	
	public void updateTopping(long id, Topping topping) {
		if(pizzaRepository.findOne(topping.getPizzaId())==null)
			throw new CustomException(404, "Pizza for Id: "+topping.getPizzaId() +" not found", HttpStatus.NOT_FOUND);
		
		if(toppingRepository.findOne(id).getPizzaId()!=topping.getPizzaId())
			throw new CustomException(404, "This toppping: "+id+" does not belong to Pizza id "+topping.getPizzaId()+". You may want to update for PizzaId: "+ toppingRepository.findOne(id).getPizzaId(), HttpStatus.NOT_FOUND);
			
		if(toppingRepository.findOne(id)!= null) {
			Topping updateTopping = toppingRepository.findOne(id);			
			updateTopping.setName(topping.getName());
			updateTopping.setPrice(topping.getPrice());
			updateTopping.setPizzaId(topping.getPizzaId());
			
			toppingRepository.save(updateTopping);
			
			throw new CustomException(200, "Pizza updated sucessfully", HttpStatus.OK);
			
		}
		else
			throw new CustomException(404, "Topping for Id: "+id +" not found", HttpStatus.NOT_FOUND);
	}
	
	public void deleteTopping(long pizzaId, long toppingId) {
		
		if(toppingRepository.findOne(toppingId)!= null) {
			Topping topping = toppingRepository.findOne(toppingId);
			if(pizzaRepository.findOne(topping.getPizzaId())==null)
				throw new CustomException(404, "Pizza for Id: "+topping.getPizzaId() +" not found", HttpStatus.NOT_FOUND);
			
			if(toppingRepository.findOne(toppingId).getPizzaId()!=pizzaId)
				throw new CustomException(404, "Toppping id: "+toppingId+" does not belong to Pizza id "+pizzaId+". You may want to delete for PizzaId: "+ topping.getPizzaId() + ".", HttpStatus.NOT_FOUND);
			
			
			toppingRepository.delete(toppingId);
			throw new CustomException(200, "Topping for Id: "+toppingId +" sucessfully deleted", HttpStatus.OK);
		}
		else
			throw new CustomException(404, "Topping for Id: "+toppingId +" not found", HttpStatus.NOT_FOUND);
	}
	
}
