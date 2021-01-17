package de.tub.ise.anwsys.pizza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pizza {

	/*public enum Size {
		Standard, 
		Large;  
	}
	*//*
	 id	integer($int64)
name	string
size	string
Size

Enum:
Array [ 2 ]
price	number($float64)
Price including toppings. 
	 
	 */
	//private static final AtomicInteger count = new AtomicInteger(0);
	//@Id
	//private Integer id;
	/* 
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    */
	
	@Id
    @GeneratedValue
    @Column(name="id")
    private long id;
	
	@Column(name="name")
    private String name;
	
	@Column(name="size")
	private PizzaSize size;
	
	@Column(name="price")
	private float price;
	
	
	public Pizza() {
	
	}
	
	public Pizza(String name, PizzaSize size) {
		super();
		//this.id = count.incrementAndGet();
		//this.id = id;
		this.name = name;
		this.size = size;
		//this.price = price;
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

	public PizzaSize getSize() {
		return size;
	}
	
	public void setSize(PizzaSize size) {
		this.size = size;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	

}
