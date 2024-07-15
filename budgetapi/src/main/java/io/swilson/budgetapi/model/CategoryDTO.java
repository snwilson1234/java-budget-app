package io.swilson.budgetapi.model;

import java.util.List;

public record CategoryDTO(
        Long id,
        String name,
        Integer budget,
        List<Purchase> purchases
){}
