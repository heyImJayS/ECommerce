package dev.jays.ecommerce.dtos;

import dev.jays.ecommerce.models.StockAvailability;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequestDTO {
    private int pageNo=0;
    private int pageSize=10;
    private Double startPrice ;
    private Double endPrice;
    private List<String> color;
    private List<String> brand;
    private String availability;
    private String searchDesc;

    private List<SortParameter> sortParameters;
}
