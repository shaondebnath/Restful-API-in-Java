package de.tub.ise.anwsys.topping;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Topping {

	
	@Id
    @GeneratedValue
    @Column(name="id")
    private long id;
	
	@Column(name="name")
    private String name;
	
	@Column(name="price")
	private float price;
	
	private long pizzaId;

	
	public Topping() {
	
	}
	
	public Topping(String name, float price, long pizzaId) {
		super();
		//this.id = count.incrementAndGet();
		//this.id = id;
		this.name = name;
		this.price = price;
		this.setPizzaId(pizzaId);
	}
	
	public long getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public long getPizzaId() {
		return pizzaId;
	}

	public void setPizzaId(long pizzaId) {
		this.pizzaId = pizzaId;
	}

		

}
