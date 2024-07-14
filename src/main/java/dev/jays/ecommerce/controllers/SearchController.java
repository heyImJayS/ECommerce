package dev.jays.ecommerce.controllers;

import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.dtos.SearchRequestDTO;
import dev.jays.ecommerce.response.ApiEntity;
import dev.jays.ecommerce.response.ApiResponseObject;
import dev.jays.ecommerce.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @PostMapping
    public ResponseEntity<ApiResponseObject> searchProduct(@RequestBody SearchRequestDTO searchRequestDTO){

        HttpStatus httpStatus = HttpStatus.OK;
        String message= "";
        Page<GenericProductDTO> result= null;
        try{
            result= searchService.searchProductBasedOnPriceRange(searchRequestDTO.getStartPrice(),
                    searchRequestDTO.getEndPrice(),
                    searchRequestDTO.getPageNo() ,
                    searchRequestDTO.getPageSize(),
                    searchRequestDTO.getSortParameters());
            if(result!=null) {
                message = "Product Found";
                return new ResponseEntity<ApiResponseObject>(
                        new ApiEntity<Page<GenericProductDTO>>(message, result), httpStatus);
            }else{
                message = "Product Not Found";
                return new ResponseEntity<ApiResponseObject>(
                        new ApiEntity<Page<GenericProductDTO>>(message), httpStatus);
            }
        }catch(Exception e){
            return new ResponseEntity<ApiResponseObject>(
                    new ApiEntity<Page<GenericProductDTO>>(e.getMessage()), httpStatus);
        }

    }

    @PostMapping("/elastic")
    public ResponseEntity<ApiResponseObject> searchProductbyElastic(@RequestBody SearchRequestDTO searchRequestDTO){

        HttpStatus httpStatus = HttpStatus.OK;
        String message= "";
        Page<GenericProductDTO> result= null;
        try{
            result= searchService.searchProductBasedOnDescription(searchRequestDTO.getSearchDesc(),
                    searchRequestDTO.getPageNo() ,
                    searchRequestDTO.getPageSize(),
                    searchRequestDTO.getSortParameters());
            if(result!=null) {
                message = "Product Found";
                return new ResponseEntity<ApiResponseObject>(
                        new ApiEntity<Page<GenericProductDTO>>(message, result), httpStatus);
            }else{
                message = "Product Not Found";
                return new ResponseEntity<ApiResponseObject>(
                        new ApiEntity<Page<GenericProductDTO>>(message), httpStatus);
            }
        }catch(Exception e){
            return new ResponseEntity<ApiResponseObject>(
                    new ApiEntity<Page<GenericProductDTO>>(e.getMessage()), httpStatus);
        }

    }
}
