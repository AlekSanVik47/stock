package ru.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.stock.entities.Category;
import ru.stock.entities.Product;

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
}
