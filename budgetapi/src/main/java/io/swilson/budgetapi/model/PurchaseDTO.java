package io.swilson.budgetapi.model;

public record PurchaseDTO(
        Long id,
        String description,
        Integer amount,
        String date,
        Category category
) {}
