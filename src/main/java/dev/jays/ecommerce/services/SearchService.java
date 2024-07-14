package dev.jays.ecommerce.services;

import dev.jays.ecommerce.dtos.GenericProductDTO;
import dev.jays.ecommerce.dtos.SortParameter;
import dev.jays.ecommerce.elasticrepos.ProductElasticSearchRepo;
import dev.jays.ecommerce.models.Product;
import dev.jays.ecommerce.repositories.ProductRepository;
import dev.jays.ecommerce.util.EcommerceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SearchService {
    @Autowired
    private ProductRepository productRepository;

    private ProductElasticSearchRepo productElasticSearchRepo;

    public Page<GenericProductDTO> searchProductBasedOnPriceRange(Double startPrice,
                                                                  Double endPrice,
                                                                  int pageNo,
                                                                  int pageSize,
                                                                  List<SortParameter> sortParameters){
        //Here what is happening -> 1st we sort based on Title in descending order
        //if for those data having same titles...then we sort those data based on price ...

//        Sort sort = Sort.by("title").descending()
//                .and(
//                        Sort.by("price").descending()
//                )
//                .and(
//                        Sort.by("averageRating").ascending()
//                );

        Sort sort =null ;
        if(!sortParameters.isEmpty()) {
            if (sortParameters.get(0).getSortType().equals(EcommerceConstants.ASCENDING)) {
                sort = Sort.by(sortParameters.get(0).getParameterName());
            } else {
                sort = Sort.by(sortParameters.get(0).getParameterName()).descending();
            }
        }
        for(int i=1; i<sortParameters.size(); i++){
            if(sortParameters.get(i).getSortType().equals(EcommerceConstants.ASCENDING)){
                sort = sort.and(Sort.by(sortParameters.get(i).getParameterName()));
            }else{
                sort = sort.and(Sort.by(sortParameters.get(i).getParameterName()).descending());
            }
        }


        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Product> result = productRepository.findProductInPriceRange(startPrice, endPrice, pageable);
        //Converting Page<Product> to Page<GenericProductDTO>
        List<GenericProductDTO> productData= new ArrayList<>();
        List<Product> pqr= result.get().collect(Collectors.toList());
        for(Product product: result.getContent()){
            productData.add(GenericProductDTO.convertProductToGenericProductDTO(product));
        }

        Page<GenericProductDTO> finalPage = new PageImpl<>(productData, pageable, result.getTotalElements() );
        return finalPage;
    }

    public Page<GenericProductDTO> searchProductBasedOnDescription(String searchDesc,
                                                                  int pageNo,
                                                                  int pageSize,
                                                                  List<SortParameter> sortParameters){
        Sort sort =null ;
        if(!sortParameters.isEmpty()) {
            if (sortParameters.get(0).getSortType().equals(EcommerceConstants.ASCENDING)) {
                sort = Sort.by(sortParameters.get(0).getParameterName());
            } else {
                sort = Sort.by(sortParameters.get(0).getParameterName()).descending();
            }
        }
        for(int i=1; i<sortParameters.size(); i++){
            if(sortParameters.get(i).getSortType().equals(EcommerceConstants.ASCENDING)){
                sort = sort.and(Sort.by(sortParameters.get(i).getParameterName()));
            }else{
                sort = sort.and(Sort.by(sortParameters.get(i).getParameterName()).descending());
            }
        }


        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return null;

    }
}
