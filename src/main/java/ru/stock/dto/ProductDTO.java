package ru.stock.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
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

    @Schema(description = "количество")
    private final int quantity;

    @Schema(description = "Дата и время последнего изменения количества")
    private final LocalDateTime lastQuantityTime;

    @Schema(description = "Дата создания")
    private final LocalDateTime dateOfCreation;


}
