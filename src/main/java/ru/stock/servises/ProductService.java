package ru.stock.servises;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stock.entities.Category;
import ru.stock.entities.Product;
import ru.stock.exceptions.DataHasNotChanged;
import ru.stock.exceptions.DataNotInDBException;
import ru.stock.mappers.CategoryMapper;
import ru.stock.mappers.ProductMapper;
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
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
                          CategoryService categoryService, ProductMapper productMapper, CategoryMapper categoryMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
    }

    /**
     * Метод создания нового продукта
     *
     * @param dto        в json передаем параметры/поля продукта titleProduct, vendorCode, description, price, categoryId, quantity
     * @param categoryId по ID категории предается существующая в базе.
     * @return возвращаем продукт
     */
    public Product createProduct(ProductDTO dto, Long categoryId) {
        Product product = productMapper.toProduct(dto);
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

    /**
     * Метод обновления продукта
     *
     * @param dto       в json передаем необходимые параметры
     * @param productId ID продукта
     * @return возвращаем обновленный продукт
     */
    public Optional<Product> updateProduct(ProductDTO dto, Long productId) {
            Category category = categoryMapper.toCategory(dto.getCategoryDto());
            Long categoryId = category.getId();
            Optional<Product> productOptional = getProductById(productId);
            if (productRepository.existsById(productId)) {
                productOptional.ifPresent(product -> {
                    product.setTitleProduct(dto.getTitleProduct());
                    product.setVendorCode(dto.getVendorCode());
                    product.setDescription(dto.getDescription());
                    if (dto.getQuantity() != 0)
                        product.setQuantity(dto.getQuantity() + product.getQuantity());
                    if (dto.getPrice() != null)
                        product.setPrice(dto.getPrice());
                    if (categoryId != null && category.getTitleCategory() != null) {
                        product.setCategory(categoryService.getCategoryById(categoryId));
                    }
                    product.setCategory(category);
                    productRepository.save(product);
                });
            }
        return Optional.ofNullable(productRepository.findById(productId).orElseThrow(DataNotInDBException::new));
    }

    /**
     * Метод добавления продута, добавляем необходимое количество продукта к уже существующему,
     * если пытаемся добавить 0 выбрасываем исключение.
     *
     * @param productId Id продукта
     * @param quantity  количество которое необходимо добавить
     */
    public void addingProducts(Long productId, int quantity) {
        boolean isExists = isExistsProduct(productId);
        boolean isQuantityChange = (quantity != 0);
        Optional<Product> productOptional = getProductById(productId);
        if ((isExists) && isQuantityChange) {
            productOptional.ifPresent(product -> {
                product.setQuantity(product.getQuantity() + quantity);
                product.setLastQuantityTime(LocalDateTime.now());
                productRepository.save(product);
            });
        } else {
            productOptional.ifPresent(product -> {
                String title = product.getTitleProduct();
                throw new DataHasNotChanged(title, productId);
            });
        }
    }

    private boolean isExistsProduct(Long productId) {
        return productRepository.existsById(productId);
    }

    /**
     * Метод получения названия по ID, если Id не верный выбрасываем исключение.
     *
     * @param productId ID продукта
     * @return возвращаем название продукта
     */
    public String getTitleProductById(Long productId) {
        if (productRepository.existsById(productId)) {
            Product product = productRepository.findProductById(productId);
            return product.getTitleProduct();
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

    public List<Product> getListAllProducts(){
        return productRepository.findAll();
    }

    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

}
