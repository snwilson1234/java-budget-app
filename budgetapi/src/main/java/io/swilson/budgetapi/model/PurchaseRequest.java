package io.swilson.budgetapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
public class PurchaseRequest {
    @NotEmpty(message = "description cannot be empty")
    protected String description;

    @NotNull(message = "amount cannot be null")
    private Integer amount;

    @NotEmpty(message = "date cannot be null")
    private String date;

    @NotNull(message = "categoryId cannot be null")
    private Long categoryId;
}
