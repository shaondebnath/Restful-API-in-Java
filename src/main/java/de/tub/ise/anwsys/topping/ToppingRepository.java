package de.tub.ise.anwsys.topping;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ToppingRepository extends CrudRepository<Topping, Long>{
	public List<Topping> findByPizzaId(long pizzaId);
	
}
