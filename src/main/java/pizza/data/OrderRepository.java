package pizza.data;

import org.springframework.data.repository.CrudRepository;

import pizza.Order;

public interface OrderRepository 
         extends CrudRepository<Order, Long> {

}
