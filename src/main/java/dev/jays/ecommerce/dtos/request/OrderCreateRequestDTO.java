package dev.jays.ecommerce.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCreateRequestDTO {
    List<String> products;
}
