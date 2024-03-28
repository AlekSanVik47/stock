package ru.stock.servises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.stock.dto.CategoryDTO;
import ru.stock.dto.ProductDTO;
import ru.stock.entities.Category;
import ru.stock.entities.Product;
import ru.stock.mappers.CategoryMapper;
import ru.stock.mappers.ProductMapper;
import ru.stock.repositories.CategoryRepository;
import ru.stock.repositories.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductDTO productDTO;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private Product product;
    @InjectMocks
    private CategoryService categoryService;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Инициализация моков

        productService = new ProductService(productRepository,categoryRepository,categoryService, productMapper, categoryMapper);
    }

    @Test
    public void createProductTest() {
        // Подготавливаем тестовые данные
        CategoryDTO categoryDTO = new CategoryDTO(1L, "Category 1");
        Category category = new Category(1L, "Category 1");
        Mockito.when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.of(category));
        Mockito.when(categoryService.isExistsCategory(1L)).thenReturn(true);
        Mockito.when(categoryService.getCategoryById(1L)).thenReturn(category);
        // Используем мок объект productDTO
        Mockito.when(productDTO.getTitleProduct()).thenReturn("Product 1");
        Mockito.when(productDTO.getDescription()).thenReturn("Description 1");
        Mockito.when(productDTO.getCategoryDto()).thenReturn(categoryDTO);
        Mockito.when(productDTO.getPrice()).thenReturn(BigDecimal.TEN);
        Mockito.when(productDTO.getQuantity()).thenReturn(5);

        Mockito.when(productMapper.toProduct(any(ProductDTO.class))).thenReturn(product);

        // Используем мок объект Product
        Mockito.when(product.getTitleProduct()).thenReturn("Product 1");
        Mockito.when(product.getVendorCode()).thenReturn("123");
        Mockito.when(product.getDescription()).thenReturn("Description 1");
        Mockito.when(product.getCategory()).thenReturn(category);
        Mockito.when(product.getPrice()).thenReturn(BigDecimal.TEN);
        Mockito.when(product.getQuantity()).thenReturn(5);
        Mockito.when(product.getDateOfCreation()).thenReturn(LocalDateTime.now());
        Mockito.when(product.getLastQuantityTime()).thenReturn(LocalDateTime.now());
        Mockito.when(product.getId()).thenReturn(1L);
        Mockito.when(productRepository.save(product)).thenReturn(product);


        Long idTest;
        idTest = 1L;
        Product createdProduct;
        createdProduct = productService.createProduct(productDTO, idTest);
        assertEquals(product, createdProduct);
    }

    @Test
    void updateProduct() {
    }

    @Test
    void addingProducts() {
    }

    @Test
    void getTitleProductById() {
    }

    @Test
    void getProductByTitleProduct() {
    }

    @Test
    void getProductByCategory() {
    }

    @Test
    void getProductByVendorCode() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void getListAllProducts() {
    }

    @Test
    void deleteProduct() {
    }
}