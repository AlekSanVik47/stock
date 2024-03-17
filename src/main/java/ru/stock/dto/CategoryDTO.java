package ru.stock.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CategoryDTO implements Serializable {
    @Schema(description = "ID продукта")
    private final Long id;
    @Schema(description = "Название категории")
    private final String titleCategory;
}
