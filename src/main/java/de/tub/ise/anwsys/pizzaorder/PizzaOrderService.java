package de.tub.ise.anwsys.pizzaorder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.tub.ise.anwsys.pizza.PizzaRepository;
import de.tub.ise.anwsys.pizza.PizzaService;
import de.tub.ise.anwsys.response.CustomException;
import de.tub.ise.anwsys.topping.Topping;
import de.tub.ise.anwsys.topping.ToppingService;



@Service
public class PizzaOrderService {
	
	@Autowired
	private PizzaService pizzaService;

	@Autowired
	ToppingService toppingService;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	PizzaOrderRepository pizzaOrderRepository;

	
	public List<PizzaOrder> getAllPizzaOrders(){
		
		List<PizzaOrder> pizzaOrders = new ArrayList<>();
		pizzaOrderRepository.findAll()  // get all pizza list
		.forEach(pizzaOrders::add);
		
		if(pizzaOrders.size() == 0)
			throw new CustomException(404, "No pizza Order exist. Please place using post method", HttpStatus.NOT_FOUND);
		
	
		return pizzaOrders;
		
	}
	
	public PizzaOrder getPizzaOrder(long id) {
		return pizzaOrderRepository.findOne(id);
			
	}
	
	public void addPizzaOrder(String newPizzaOrder) throws IOException {
		
		//List<String> newOrder = new ArrayList<String>(Arrays.asList(newPizzaOrder.split("-")));
		
		 final ObjectMapper mapper = new ObjectMapper();
	        final JsonNode json = mapper.readTree(newPizzaOrder);
	        String pizzaIdStr = String.valueOf(json.at("/orderItem/pizzaId"));
	        long pizzaId = Long.valueOf(pizzaIdStr);
	        
	        String quantityStr = String.valueOf(json.at("/orderItem/quantity"));
	        int quantity = Integer.valueOf(quantityStr);
	       
	        String recipient = String.valueOf(json.at("/recipient"));
	        
	        if(pizzaService.getPizza(pizzaId)==null)
	        	throw new CustomException(404, "Pizza for Id: "+pizzaId +" not found", HttpStatus.NOT_FOUND);
	        
	        List<Topping> toppings = toppingService.getAllToppingsForPizza(pizzaId);
			float pizzaPrice =pizzaService.getPizza(pizzaId).getPrice();
			if(toppings.size()>0) {
				for(int j=0; j< toppings.size();j++) {
					pizzaPrice = pizzaPrice + toppings.get(j).getPrice();
					
				}				
			}
			pizzaPrice = pizzaPrice*quantity;
			String orderItem = "{pizzaId: "+pizzaId + ", quantity: "+quantity+"}";
			PizzaOrder thisPizzaOrder = new PizzaOrder(pizzaPrice, recipient, orderItem);
			long thisPizzaOrderId = thisPizzaOrder.getId();
			
			pizzaOrderRepository.save(thisPizzaOrder);
			
			OrderItem thisOrderItem = new OrderItem(thisPizzaOrderId, pizzaId, quantity);
			orderItemRepository.save(thisOrderItem);
			
			throw new CustomException(200, "Order Place Successfully for Pizza for Id: "+pizzaId +" totalPrice: "+pizzaPrice + ", Recipient: "+ recipient, HttpStatus.OK);
	       
	   
	}
	/*
	public PizzaOrder addPizzaOrder(PizzaOrder newPizzaOrder) {
	//public String addPizzaOrder(){
		
		
		/*List<OrderItem>orderItem = newPizzaOrder.getOrderItems();
		String a="list = ";
		for(int i =0; i< orderItem.size(); i++) {
			a = a + "<>"+orderItem.get(i).getPizzaId() + ", " +  orderItem.get(i).getQuantity();
		}
		*/
		/*PizzaOrder newPizzaOrder = new PizzaOrder();
		List<OrderItem>orderItem = new ArrayList<>();
		for (int i =0; i<2; i++) {
			OrderItem myItem = new OrderItem(Long.valueOf(i),i+10);
			orderItem.add(myItem);
		}
		newPizzaOrder.setOrderItems(orderItem);
		newPizzaOrder.setRecipient("aaaaaaaaaa");*/
		
		/*//String a="list = ";
		for(int i =0; i< orderItem.size(); i++) {
			a = a + " | "+orderItem.get(i).getPizzaId() + "," +  orderItem.get(i).getQuantity();
		}* /
		
		
	
		pizzaOrderRepository.save(newPizzaOrder);
		return newPizzaOrder;
		//return "Successfully item(s) Added...";
		
	}*/
	
	/*public String updatePizzaOrder(long id, PizzaOrder pizza) {
		if(pizzaOrderRepository.findOne(id)!= null) {
			PizzaOrder updatePizzaOrder = pizzaOrderRepository.findOne(id);	
			updatePizzaOrder.setName(pizza.getName());
			updatePizzaOrder.setSize(pizza.getSize());
			float pizzaPrice = 0.0f;
			if(pizza.getSize() == PizzaOrderSize.Standard) pizzaPrice = standardPrice; else if(pizza.getSize()== PizzaOrderSize.Large) pizzaPrice = largePrice;
				updatePizzaOrder.setPrice(pizzaPrice);
			
			pizzaOrderRepository.save(updatePizzaOrder);
			
			return "Successfully Updated";
			
		}
		else
			return "Did not find the ID";
			
	}*/
	
	public void deletePizzaOrder(long id) {
		if(pizzaOrderRepository.findOne(id)!= null) {
			pizzaOrderRepository.delete(id);
			throw new CustomException(200, "Order for Id: "+id +" sucessfully deleted", HttpStatus.OK);
		}
		else
			throw new CustomException(404, "Order for Id: "+id +" not found", HttpStatus.NOT_FOUND);
	}
		

}
