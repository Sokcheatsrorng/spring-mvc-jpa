package co.istad.springwebmvc.service.Impl;

import co.istad.springwebmvc.dto.ProductDto;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.service.ProductService;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private List<Product> productList ;
    public ProductServiceImpl(){
        productList = new ArrayList<>();
        Product p1= new Product();
        p1.setId(1);
        p1.setUuid(UUID.randomUUID().toString());
        p1.setName("Product1");
        p1.setPrice(23.3);
        p1.setQty(2);
        p1.setImportedDate(LocalDate.now());
        p1.setIsStatus(true);
        Product p2= new Product();
        p2.setId(2);
        p2.setUuid(UUID.randomUUID().toString());
        p2.setName("Product2");
        p2.setPrice(23.3);
        p2.setQty(3);
        p2.setImportedDate(LocalDate.now());
        p2.setIsStatus(true);
        productList.add(p1);
        productList.add(p2);
    }

    @Override
    public List<ProductDto> findProducts(String name,Boolean isStatus) {
        return productList
                .stream()
                .filter(pro->pro.getIsStatus().equals(isStatus) && pro.getName().toLowerCase().contains(name.toLowerCase()))
                .map(product ->
                    new ProductDto(
                            product.getUuid(),
                            product.getName(),
                            product.getPrice(),
                            product.getQty()
                    )
                )
                .toList();

    }

    @Override
    public ProductDto findProductById(Integer id) {
        return productList
                .stream()
                .filter(product-> product.getId().equals(id) && product.getIsStatus().equals(true))
                .map(product -> new ProductDto(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                        )).findFirst().orElseThrow();
    }

    @Override
    public ProductDto findProductByUUid(String uuid) {
        return productList
                .stream()
                .filter(product-> product.getUuid().equals(uuid) && product.getIsStatus().equals(true))
                .map(product -> new ProductDto(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                )).findFirst().orElseThrow();
    }


}
