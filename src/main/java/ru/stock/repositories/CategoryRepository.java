package ru.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stock.entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Long findIdByTitleCategory(String titleCategory);
    Category findByTitleCategory(String titleCategory);
    Category findCategoryById(Long categoryId);


}
