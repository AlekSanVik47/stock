package ru.stock.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stock.dto.ProductDTO;
import ru.stock.entities.Product;
import ru.stock.servises.ProductService;

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
    public ResponseEntity<Product> createProductController(@Parameter(description = "запрос на создание продукта", required = true)
                                                           @RequestBody(required = false)
                                                           ProductDTO request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }
}
