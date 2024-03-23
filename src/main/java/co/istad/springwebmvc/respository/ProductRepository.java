package co.istad.springwebmvc.respository;

import co.istad.springwebmvc.model.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {}

