package co.istad.springwebmvc.service.Impl;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public List<ProductResponse> findProducts(String name, Boolean isStatus) {
        return productList
                .stream()
                .filter(pro->pro.getIsStatus().equals(isStatus) && pro.getName().toLowerCase().contains(name.toLowerCase()))
                .map(product ->
                    new ProductResponse(
                            product.getUuid(),
                            product.getName(),
                            product.getPrice(),
                            product.getQty()
                    )
                )
                .toList();

    }

    @Override
    public void createNewProduct(ProductCreateRequest request) {
        Product newProduct = new Product();
        newProduct.setId(productList.size()+1);
        newProduct.setName(request.name());
        newProduct.setPrice(request.price());
        newProduct.setQty(request.qty());
        newProduct.setUuid(UUID.randomUUID().toString());
        newProduct.setImportedDate(LocalDate.now());
        newProduct.setIsStatus(true);
        productList.add(newProduct);

    }

    @Override
    public void editProductByUUID(ProductEditRequest request, String uuid) {
        //Check UUID if it eixisted
        Long count = productList.stream()
                .filter(pro->pro.getUuid().equals(uuid))
                .peek(pro ->{
                    pro.setName(request.name());
                    pro.setPrice(request.price());
                }).count();
        System.out.println("After Edit: "+ count);

    }

    @Override
    public Boolean deleteProductByUUID(String uuid) {
        return productList
                .removeIf(product -> product.getUuid().equals(uuid));

    }

    @Override
    public ProductResponse findProductById(Integer id) {
        return productList
                .stream()
                .filter(product-> product.getId().equals(id) && product.getIsStatus().equals(true))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                        )).findFirst().orElseThrow();
    }

    @Override
    public ProductResponse findProductByUUid(String uuid) {
        return productList
                .stream()
                .filter(product-> product.getUuid().equals(uuid) && product.getIsStatus().equals(true))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                )).findFirst().orElseThrow();
    }


}
