package ru.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.stock.entities.Category;
import ru.stock.entities.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findProductByCategoryId (Long categoryId);
    List<Product> findProductByTitleProduct (String title);

    Product findProductByVendorCode (String vendorCode);

    @Override
    List<Product> findAll();
    @Query("select p from Product p where p.id = ?1")
    @Override
    Optional<Product> findById(Long id);

    @Override
    boolean existsById(Long aLong);

    String findTitleProductById(Long productId);

    @Transactional
    @Modifying
    @Query("""
            update Product p set p.vendorCode = ?1, p.titleProduct = ?2, p.description = ?3, p.category = ?4, p.price = ?5, p.lastQuantityTime = ?6, p.dateOfCreation = ?7, p.quantity = ?8
            where p.id = ?9""")
    void updateById(String vendorCode, String titleProduct, String description, Category category, BigDecimal price, LocalDateTime lastQuantityTime, LocalDateTime dateOfCreation, int quantity, Long id);

    @Query("SELECT p FROM Product p WHERE p.id = :productId")
    Product findProductById(Long productId);
}
