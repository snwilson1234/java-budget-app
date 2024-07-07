package io.swilson.budgetapi.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotEmpty(message = "name cannot be empty")
    protected String name;

    protected Integer budget;
}
