package ru.stock.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stock.dto.CategoryDTO;
import ru.stock.entities.Category;
import ru.stock.repositories.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(String titleCategory) {
        Category category = new Category();
        category.setTitleCategory(titleCategory);
        categoryRepository.save(category);
        return category;
    }
}
