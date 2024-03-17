package ru.stock.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stock.entities.Product;
import ru.stock.repositories.CategoryRepository;
import ru.stock.repositories.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }





    public Product createProduct(String titleProduct, String vendorCode, String description, LocalDateTime lastQuantityTime,
                                 LocalDateTime dateOfCreation, BigDecimal price,  String titleCategory){
        Product product = new Product();
        product.setTitleProduct(titleProduct);
        product.setVendorCode(vendorCode);
        product.setDescription(description);
        product.setDateOfCreation(dateOfCreation);
        product.setLastQuantityTime(lastQuantityTime);
        product.setPrice(price);
        product.setCategory(categoryService.createCategory(titleCategory));
        productRepository.save(product);
        return product;
    }




    public List<Product> getProductByTitleProduct(String titleProduct) {
        return productRepository.findProductByTitleProduct(titleProduct);
    }

    public List<Product> getProductByCategory(Long categoryId) {
        return productRepository.findProductByCategoryId(categoryId);
    }

    public Product getProductByVendorCode(String vendorCode) {
        return productRepository.findProductByVendorCode(vendorCode);
    }

    public Optional<Product> getProductById (Long productId){
        return productRepository.findById(productId);
    }


}
