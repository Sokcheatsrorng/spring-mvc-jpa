package co.istad.springwebmvc.service;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findProducts(String name, Boolean isStatus);
    void createNewProduct(ProductCreateRequest request);
    void editProductByUUID(ProductEditRequest request, String uuid);
    Boolean deleteProductByUUID(String uuid);
    void deleteProductByUUIDWithoutReturn(String uuid);
   ProductResponse findProductById(Integer id);
   ProductResponse findProductByUUid(String uuid);
}
