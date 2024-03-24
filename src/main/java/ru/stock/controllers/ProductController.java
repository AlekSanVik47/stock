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

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

//    @Operation(description = "Добавление значения количества продукта")
//    @PutMapping(value = "{productId}")
//    public ResponseEntity<String> addingProducts(@Parameter(description = "Добавление значения количества продукта")
//                                                 @PathVariable(value = "productId") Long productId,
//                                                 @RequestParam(value = "quantity", required = false) int quantity) {
//        productService.addingProducts(productId, quantity);
//        String msg = "В " +
//                productService.getTitleProductById(productId).toString() +
//                " успешно добавлены продукты в количестве " +
//                quantity + " шт.";
//        return ResponseEntity.ok("Добавлен");
//    }
}
