package ru.stock.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class ProductDTO implements Serializable {
    @Schema(description = "ID продукта")
    private final Long productId;

    @Schema(description = "Артикул продукта")
    private final String vendorCode;

    @Schema(description = "Название")
    private final String titleProduct;

    @Schema(description = "Описание")
    private final String description;

    @Schema(description = "Категория")
    private final CategoryDTO categoryDto;

    @Schema(description = "Цена")
    private final BigDecimal price;

    @Schema(description = "Дата и время последнего изменения количества")
    private final LocalDateTime lastQuantityTime;

    @Schema(description = "Дата создания")
    private final LocalDateTime dateOfCreation;

    public ProductDTO(Long productId, String vendorCode, String titleProduct, String description, CategoryDTO categoryDto, BigDecimal price, LocalDateTime lastQuantityTime, LocalDateTime dateOfCreation) {
        this.productId = productId;
        this.vendorCode = vendorCode;
        this.titleProduct = titleProduct;
        this.description = description;
        this.categoryDto = categoryDto;
        this.price = price;
        this.lastQuantityTime = lastQuantityTime;
        this.dateOfCreation = dateOfCreation;
    }


}
