package co.istad.springwebmvc.controller;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.service.Impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.PresentationDirection;
import java.awt.print.Book;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
   public final ProductServiceImpl productService;

   @PutMapping("/{id}")
   void editExistingProduct(@RequestBody ProductEditRequest request,
                            @PathVariable Integer id){
       productService.editProductById(request,id);


   }
   @ResponseStatus(HttpStatus.NO_CONTENT)
   @DeleteMapping("/{id}")
   void  deleteProductByUuid(@PathVariable Integer id){
        productService.deleteProductById(id);
   }
   @ResponseStatus(HttpStatus.CREATED)
   @PostMapping
   void createNewProduct(@Valid  @RequestBody ProductCreateRequest request){
       System.out.println("REQUEST: "+ request);
       productService.createNewProduct(request);
   }

    @Operation(summary = "Find all Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the products",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id product",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content) })

    @GetMapping
    ResponseEntity<Map<String,Object>> findProduct(){
        Map<String,Object> data = Map.of(
                "Message","Products have been found!",
                "data",productService.findProducts()

        );

        return ResponseEntity.accepted().body(data);
//        return new ResponseEntity<>( Map.of(
//                "Message","Products have been found!",
//                "data",productService.findProducts(name,isStatus)
//        ), HttpStatus.ACCEPTED);

    }
    //using @PathVariable
    @GetMapping("/{id}")
    public Map<String,Object> findProductById(@PathVariable Integer id){
        return Map.of(
                "Message","Product has been found",
                "data",productService.findProductById(id)
        );
    }
    @GetMapping("/uuid/{uuid}")
    public Map<String,Object> findProductByUUid(@PathVariable String uuid){
        return Map.of(
                "Message","Product has been found",
                "data",productService.findProductByUUid(uuid)
        );
    }
}
