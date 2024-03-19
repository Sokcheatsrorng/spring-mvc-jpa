package co.istad.springwebmvc.service;

import co.istad.springwebmvc.dto.ProductDto;
import co.istad.springwebmvc.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> findProducts(String name,Boolean isStatus);

   ProductDto findProductById(Integer id);
   ProductDto findProductByUUid(String uuid);
}
