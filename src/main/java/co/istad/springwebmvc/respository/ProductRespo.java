package co.istad.springwebmvc.respository;

import co.istad.springwebmvc.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRespo {
   List<Product> getAllProducts();
}
