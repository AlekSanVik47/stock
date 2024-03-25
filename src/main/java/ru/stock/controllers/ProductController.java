package ru.stock.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stock.dto.ProductDTO;
import ru.stock.entities.Product;
import ru.stock.servises.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("product")
@Tag(name = "ProductController", description = "API продуктов")
@Validated
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(description = "Создание продукта")
    @PostMapping
    public ResponseEntity<Product> createProductController(@Parameter(description = "Создание продукта", required = true)
                                                           @RequestParam(value = "categoryId") Long categoryId,
                                                           @RequestBody(required = false) ProductDTO request) {
        return ResponseEntity.ok(productService.createProduct(request, categoryId));
    }

    @Operation(description = "Добавление продукта (количество)")
    @PutMapping(value = "add/{productId}")
    public ResponseEntity<String> addingProducts(@Parameter(description = "Добавление значения количества продукта")
                                                 @PathVariable(value = "productId") Long productId,
                                                 @RequestParam(value = "quantity", required = false) int quantity) {
        productService.addingProducts(productId, quantity);
        String titleProduct = productService.getTitleProductById(productId);
        String msg = "В " +
                 titleProduct +
                " успешно добавлены продукты в количестве " +
                quantity + " шт.";
        return ResponseEntity.ok(msg);
    }
    @Operation(description = "Обновление продукта")
    @PutMapping(value ="update/{productId}")
    public ResponseEntity<Optional<Product>> updateProduct(@Parameter(description = "Добавление значения количества продукта")
                                                     @PathVariable(value = "productId") Long productId,
                                                           @RequestBody(required = false) ProductDTO request){
        ;
        return ResponseEntity.ok(productService.updateProduct(request, productId));
    }
}
