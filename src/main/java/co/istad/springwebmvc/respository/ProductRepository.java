package co.istad.springwebmvc.respository;

import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findByUuid(String uuid);
}

