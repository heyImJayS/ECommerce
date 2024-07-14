package dev.jays.ecommerce.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParameter {
    //Parameter name is Column name on the basis of which we will perform sorting
    private String parameterName;
    private String sortType;   //ASC for Ascending , DESC for descending
}
