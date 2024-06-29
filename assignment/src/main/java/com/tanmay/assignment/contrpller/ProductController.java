package com.tanmay.assignment.contrpller;

import com.tanmay.assignment.entity.Product;
import com.tanmay.assignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/test")
    public String test(){
        return "Hello";
    }
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProductQuantity(@PathVariable Long id, @RequestBody Integer quantity) {
        return productService.updateProductQuantity(id, quantity);
    }

    @GetMapping("/inventory/value")
    public BigDecimal getTotalInventoryValue() {
        return productService.getTotalInventoryValue();
    }
}
