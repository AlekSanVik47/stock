package ru.stock.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stock.dto.ProductDTO;
import ru.stock.entities.Product;
import ru.stock.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product createProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setTitleProduct(productDTO.getTitleProduct());
        product.setDescription(productDTO.getDescription());
        product.setDateOfCreation(productDTO.getDateOfCreation());
        product.setLastQuantityTime(productDTO.getLastQuantityTime());
        product.setCategory(productDTO.getCategoryDto());
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
