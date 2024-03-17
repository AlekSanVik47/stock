package ru.stock.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.stock.entities.Product;
import ru.stock.servises.ProductService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Operation(description = "Добавление/создание продукта")
    @PostMapping
    public ResponseEntity<Product> createProductController(@Parameter(description = "Запрос на создание продукта", required = true)
                                                           @RequestParam(value = "vendorCode") String vendorCode,
                                                           @RequestParam(value = "titleProduct") String titleProduct,
                                                           @RequestParam(value = "lastQuantityTime", required = false) LocalDateTime lastQuantityTime,
                                                           @RequestParam(value = "dateOfCreation", required = false)  LocalDateTime dateOfCreation,
                                                           @RequestParam(value = "description") String description,
                                                           @RequestParam(value = "price") BigDecimal price,
                                                           @RequestParam(value = "titleCategory") String titleCategory) {
        return ResponseEntity.ok(productService.createProduct(titleProduct, vendorCode, description, lastQuantityTime, dateOfCreation,
                price, titleCategory));
    }
}
