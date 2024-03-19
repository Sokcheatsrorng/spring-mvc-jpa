package co.istad.springwebmvc.controller;

import co.istad.springwebmvc.service.Impl.ProductServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
   public final ProductServiceImpl productService;
    @GetMapping
    public Map<String,Object> findProduct(@RequestParam(required = false,defaultValue ="") String name,
                                          @RequestParam(required = false,defaultValue ="true") Boolean isStatus){
        return Map.of(
                "Message","Products have been found!",
                "data",productService.findProducts(name,isStatus)
        );
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
