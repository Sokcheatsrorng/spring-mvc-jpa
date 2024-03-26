package co.istad.springwebmvc.respository;

import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
//   select * from products where uuid = 'uuid';
    Optional<Product> findByUuid(String uuid);
//    select * from products where name = 'name';
    List<Product> findByName(String name);
//  select * from products where ilike = '%name%';
    List<Product> findByNameContainsIgnoreCase(String name);
}

