package co.istad.springwebmvc.controller;

import co.istad.springwebmvc.dto.CategoryRequest;
import co.istad.springwebmvc.dto.CategoryResponse;
import co.istad.springwebmvc.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteById( @PathVariable Integer id){
       categoryService.deleteCategoryByID(id);
    }
    @PutMapping("/{id}")
    public void editCategoryById( @PathVariable Integer id,
                                 @Valid @RequestBody CategoryRequest request){
        categoryService.editCategoryById(request,id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createNewCategory(@Valid @RequestBody CategoryRequest request){

        categoryService.createNewCategory(request);
    }
    @Operation(summary = "Find all Categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the category",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content) })

    @GetMapping
   Map<String,Object> findCategories(){
        return Map.of("Category",categoryService.findCategories());
    }
    @GetMapping("/{id}")
    public Map<String,Object> findCategoryById( @PathVariable Integer id){
        return Map.of(
                "Message","Category has been found",
                "Category",categoryService.findCategoryById(id)

        );
    }
}
