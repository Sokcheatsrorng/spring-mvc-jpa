package co.istad.springwebmvc.service;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findProducts();
    void createNewProduct(ProductCreateRequest request);
    void editProductById(ProductEditRequest request, Integer id);

   void deleteProductById(Integer id);
   ProductResponse findProductById(Integer id);
   ProductResponse findProductByUUid(String uuid);
}
