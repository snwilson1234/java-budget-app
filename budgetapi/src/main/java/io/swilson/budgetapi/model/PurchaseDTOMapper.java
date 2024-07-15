package io.swilson.budgetapi.model;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PurchaseDTOMapper implements Function<Purchase, PurchaseDTO> {

    @Override
    public PurchaseDTO apply(Purchase purchase) {
        return new PurchaseDTO(
                purchase.getId(),
                purchase.getDescription(),
                purchase.getAmount(),
                purchase.getDate(),
                purchase.getCategory()
        );
    }
}
