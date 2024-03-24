package ru.stock.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stock.entities.Product;
import ru.stock.exceptions.DataHasNotChanged;
import ru.stock.exceptions.DataNotInDBException;
import ru.stock.repositories.CategoryRepository;
import ru.stock.repositories.ProductRepository;
import ru.stock.dto.ProductDTO;

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


    public Product createProduct(ProductDTO dto, Long categoryId) {
        Product product = new Product();
        product.setTitleProduct(dto.getTitleProduct());
        product.setVendorCode(dto.getVendorCode());
        product.setDescription(dto.getDescription());
        product.setDateOfCreation(LocalDateTime.now());
        product.setLastQuantityTime(LocalDateTime.now());
        product.setPrice(dto.getPrice());
        product.setCategory(categoryService.getCategoryById(categoryId));
        product.setQuantity(dto.getQuantity());
        productRepository.save(product);
        return product;
    }

    public Optional<Product> updateProduct(ProductDTO dto, Long productId) {
        Optional<Product> productOptional = getProductById(productId);
        if (addingProducts(productId, dto.getQuantity())) {
            productOptional.ifPresent(product -> {
                product.setTitleProduct(dto.getTitleProduct());
                product.setVendorCode(dto.getVendorCode());
                product.setDescription(dto.getDescription());
                product.setQuantity(dto.getQuantity());
                product.setPrice(dto.getPrice());
                productRepository.save(product);
            });
        }
        return productOptional;
    }

    public boolean addingProducts(Long productId, int quantity) {
        boolean isExists = productRepository.existsById(productId);
        Optional<Product> productOptional = getProductById(productId);
        if ((isExists)) {
            productOptional.ifPresent(product -> {
                boolean isQuantityChange = quantity !=0;
                if (isQuantityChange) {
                    int newQuantity = product.getQuantity() + quantity;
                    product.setQuantity(newQuantity);
                    product.setLastQuantityTime(LocalDateTime.now());
                    productRepository.save(product);
                    return;
                } else {
                    String titleField = "Quantity";
                    throw new DataHasNotChanged(titleField, productId);
                }
            });
        }
        throw new DataNotInDBException();
    }

public String getTitleProductById (Long productId){
        if (productRepository.existsById(productId)) {
            return productRepository.findTitleProductById(productId);
        }
        throw new DataNotInDBException();
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

    public Optional<Product> getProductById(Long productId) {
        return Optional.ofNullable(productRepository.findById(productId).orElseThrow(DataNotInDBException::new));

    }


}
