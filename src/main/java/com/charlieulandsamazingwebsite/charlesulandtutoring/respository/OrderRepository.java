package com.charlieulandsamazingwebsite.charlesulandtutoring.respository;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
