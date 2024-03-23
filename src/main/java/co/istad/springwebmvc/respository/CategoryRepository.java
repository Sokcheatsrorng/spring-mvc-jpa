package co.istad.springwebmvc.respository;

import co.istad.springwebmvc.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
   boolean existsByName(String name);
}
