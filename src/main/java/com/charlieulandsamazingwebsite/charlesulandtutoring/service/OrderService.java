package com.charlieulandsamazingwebsite.charlesulandtutoring.service;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.Order;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.Student;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringClass;
import com.charlieulandsamazingwebsite.charlesulandtutoring.respository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    public void addOrder(TutoringClass tutoringClass, Student student, Long totalAmount) {
        Order order = new Order();
        order.setTutoringClass(tutoringClass);

        order.setStudent(student);
        order.setTotalAmount(totalAmount);
        orderRepository.save(order);
    }
}
