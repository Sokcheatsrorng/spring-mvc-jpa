package co.istad.springwebmvc.service.Impl;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.respository.ProductRepository;
import co.istad.springwebmvc.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j  // using for view Log form Lombok
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public List<ProductResponse> findProducts()
    {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductResponse(
                      product.getUuid(),
                      product.getName(),
                      product.getPrice(),
                      product.getQty()

                )).toList();

    }
    @Override
    public void createNewProduct(ProductCreateRequest request) {
        Product newProduct = new Product();
        newProduct.setUuid(UUID.randomUUID().toString());
        newProduct.setName(request.name());
        newProduct.setPrice(request.price());
        newProduct.setQty(request.qty());
        newProduct.setImportedDate(LocalDate.now());
        productRepository.save(newProduct);
    }

    @Override
    public void editProductById(ProductEditRequest request, Integer id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Product with ID " + id + " not found"
                        ));
        existingProduct.setName(request.name());
        existingProduct.setPrice(request.price());
        productRepository.save(existingProduct);
    }

    @Override
    public void deleteProductById(Integer id) {
           Product existingProduct = productRepository.findById(id)
                   .orElseThrow(()->
                           new ResponseStatusException(
                                   HttpStatus.NOT_FOUND,
                                   "Product with ID " + id + " not found"
                           ));
           productRepository.delete(existingProduct);
    }


    @Override
    public ProductResponse findProductById(Integer id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product with ID " + id + " not found"
                ));
        return new ProductResponse(
                existingProduct.getUuid(),
                existingProduct.getName(),
                existingProduct.getPrice(),
                existingProduct.getQty());

    }

    @Override
    public ProductResponse findProductByUUid(String uuid) {
      return null;
    }


}
