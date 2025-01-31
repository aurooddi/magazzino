package com.example.magazzino.controller;

import com.example.magazzino.model.dto.ProductDto;
import com.example.magazzino.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "magazzino/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void registerNewProduct (@RequestBody ProductDto product) {
        productService.create(product);
    }

    @DeleteMapping(path = "{idProduct}")
    public void deleteProduct(@PathVariable("idProduct") Long idProduct) {
        productService.delete(idProduct);
    }

    @PutMapping(path = "{idProduct}")
    public void updateProduct(
            @PathVariable("idProduct") Long idProduct,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) Integer stock) {
        productService.update(idProduct, description, category, quantity, stock);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAll();
    }
}
