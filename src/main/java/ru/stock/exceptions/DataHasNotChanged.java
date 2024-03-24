package ru.stock.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DataHasNotChanged extends IllegalArgumentException {
    private static final String HAS_NOT_CHANGED = "значения не изменились";

    public DataHasNotChanged(String titleField, Long productId) {
    }

    public String getHasNotChanged(String titleField, Long id) {
        String msg = "Для поля " + titleField  + HAS_NOT_CHANGED + "в продукте с ID: " + id;

        return msg;
    }
}
