package de.tub.ise.anwsys.pizzaorder;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface OrderItemRepository extends CrudRepository<OrderItem, Long>{
	public List<OrderItem> findByOrderId(long orderId);

}
