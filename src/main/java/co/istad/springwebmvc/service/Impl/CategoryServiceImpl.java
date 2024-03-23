package co.istad.springwebmvc.service.Impl;

import co.istad.springwebmvc.dto.CategoryRequest;
import co.istad.springwebmvc.dto.CategoryResponse;
import co.istad.springwebmvc.model.Category;
import co.istad.springwebmvc.respository.CategoryRepository;
import co.istad.springwebmvc.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<CategoryResponse> findCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category-> new CategoryResponse(
                        category.getName(),
                        category.getDescription()
                )).toList();
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
      Category category = categoryRepository.findById(id)
              .orElseThrow(()->new ResponseStatusException(
                      HttpStatus.NOT_FOUND,
                      "Category has not been found!"
              ));
      return new CategoryResponse(category.getName(),category.getDescription());
    }


    @Override
    public CategoryResponse findCategoryByName(String name) {
        return null;
    }

    @Override
    public void createNewCategory(CategoryRequest request) {
        // check category if existed
        if(categoryRepository.existsByName(request.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category already existed!"
            );
        }
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        categoryRepository.save(category);
    }

    @Override
    public void editCategoryById(CategoryRequest request, Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category has not been found!"
                ));
        category.setName(request.name());
        category.setDescription(request.description());
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryByID(Integer id) {
        //check id to delete Category
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category has not been found!"
                ));
        categoryRepository.delete(category);
    }


}
