package de.tub.ise.anwsys.pizza;

import java.util.Arrays;

public enum PizzaSize {
	Standard("standard"),
	Large("large");
	
	private String value;
	
	private PizzaSize(String value) {
		this.value = value;
	}
	
	public static PizzaSize fromSize(String value) {
		for (PizzaSize size : values()) {
			if (size.value.equalsIgnoreCase(value)) {
				return size;
			}
		}
		throw new IllegalArgumentException(
				"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}
	

}
