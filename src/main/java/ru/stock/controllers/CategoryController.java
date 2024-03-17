package ru.stock.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stock.dto.CategoryDTO;
import ru.stock.entities.Category;
import ru.stock.servises.CategoryService;

@RestController
@RequestMapping("category")
@Tag(name = "CategoryController", description = "API категорий продуктов")
@Validated

public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Operation(description = "Добавление/создание категории")
    @PostMapping
    public ResponseEntity<Category> createProductController(@Parameter(description = "Запрос на создание категории продукта", required = true)
                                                           @RequestBody(required = false)
                                                            CategoryDTO request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }
}
