package ru.stock.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
@Data
public class CategoryDTO implements Serializable {
    @Schema(description = "ID продукта")
    private final Long id;
    @Schema(description = "Название категории")
    private final String titleCategory;

    public CategoryDTO(Long id, String titleCategory) {
        this.id = id;
        this.titleCategory = titleCategory;
    }


}
