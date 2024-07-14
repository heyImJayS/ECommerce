package dev.jays.ecommerce.services;

import dev.jays.ecommerce.models.Order;
import dev.jays.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderSeviceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public String createOrder(Order order) {
        Order result = orderRepository.save(order);
        return result.getUuid().toString();
    }
}
