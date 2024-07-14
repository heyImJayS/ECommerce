package dev.jays.ecommerce.services;

import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.models.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    public String createOrder(Order order);
}
