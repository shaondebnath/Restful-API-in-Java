package de.tub.ise.anwsys.pizzaorder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;




@Entity
public class PizzaOrder implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name="id")
    private long id;
	
	/*
	@ManyToOne
	@JoinColumn(name="orderId", referencedColumnName="orderId")
	OrderItem orderItems;*/
	
	
	@Column(name="recipient")
	private String recipient;
	
	@Column(name="totalPrice")
	float totalPrice;
	
	@Column(name="orderItem")
	String orderItem;
	
	public PizzaOrder() {
	
	}
	
	public PizzaOrder(float totalPrice, String email, String orderItem) {
	//public PizzaOrder(List<OrderItem> orderItems, String email) {		
		super();
				
		//this.orderItemId = orderItemId;
		this.totalPrice = totalPrice;
		this.recipient=email;
		this.orderItem=orderItem;
	}

	public long getId() {
		return id;
	}

	/*public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	
	
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}*/

	

	public String getRecipient() {
		return recipient;
	}

	public String getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(String orderItem) {
		this.orderItem = orderItem;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}


	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	
	
}
