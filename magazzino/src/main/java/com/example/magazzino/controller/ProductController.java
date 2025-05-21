package com.example.magazzino.controller;

import com.example.magazzino.model.dto.ProductDto;
import com.example.magazzino.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "magazzino/product")
@Tag(name = "Product API", description = "Gestione dei prodotti")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(summary = "Crea il prodotto", description = "Metedo che crea un nuovo prodotto")
    public ProductDto registerNewProduct (@RequestBody ProductDto product) {
        return productService.create(product);
    }

    @DeleteMapping(path = "{idProduct}")
    @Operation(summary = "Elimina prodotto", description = "Metodo che elimina un prodotto")
    public void deleteProduct(@PathVariable("idProduct") Long idProduct) {
        productService.delete(idProduct);
    }

    @PutMapping(path = "{idProduct}")
    @Operation(summary = "Aggiorna i prodotti", description = "Metodo che aggiorna un prodotto")
    public void updateProduct(
            @PathVariable("idProduct") Long idProduct,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) Integer stock) {
        productService.update(idProduct, description, category, quantity, stock);
    }

    @GetMapping
    @Operation(summary = "Lista completa", description = "Ritorna la lista di tutti i prodotti")
    public List<ProductDto> getAllProducts() {
        return productService.getAll();
    }
}
