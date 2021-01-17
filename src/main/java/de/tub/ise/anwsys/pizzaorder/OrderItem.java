package de.tub.ise.anwsys.pizzaorder;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderItem implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@Column(name="orderId", unique = false)
	private long orderId;
	
	@Column(name="pizzaId")
	private long pizzaId;
	@Column(name="quantity")
	private int quantity;

	
	public OrderItem() {
		
	}
	
	public OrderItem(long orderId, long pizzaId, int quantity) {
		super();
		this.orderId = orderId;
		this.pizzaId = pizzaId;
		this.quantity = quantity;
		
	}
	

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getPizzaId() {
		return pizzaId;
	}

	public void setPizzaId(long pizzaId) {
		this.pizzaId = pizzaId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
