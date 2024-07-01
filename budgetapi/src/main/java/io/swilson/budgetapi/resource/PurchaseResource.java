package io.swilson.budgetapi.resource;

import io.swilson.budgetapi.model.Category;
import io.swilson.budgetapi.model.Purchase;
import io.swilson.budgetapi.model.PurchaseRequest;
import io.swilson.budgetapi.model.Response;
import io.swilson.budgetapi.service.CategoryService;
import io.swilson.budgetapi.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static java.util.Map.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseResource {
    private final PurchaseService purchaseService;
    private final CategoryService categoryService;

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Response> createPurchase(@RequestBody @Valid PurchaseRequest purchaseRequest) {
        Category category = categoryService.get(purchaseRequest.getCategoryId());
        if (category == null) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(Response.builder()
                            .timeStamp(now())
                            .message("Invalid category id")
                            .status(NOT_FOUND)
                            .statusCode(NOT_FOUND.value())
                            .build());
        }

        Purchase purchase = new Purchase();
        purchase.setDescription(purchaseRequest.getDescription());
        purchase.setDate(purchaseRequest.getDate());
        purchase.setAmount(purchaseRequest.getAmount());
        purchase.setCategory(category);

        Purchase createdPurchase = purchaseService.create(purchase);

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Purchase created succesfully.")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping(value = "/")
    public ResponseEntity<Response> getPurchases() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("purchases",purchaseService.list(50)))
                        .message("Purchases retrieved.")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response> getPurchase(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("purchase",purchaseService.get(id)))
                        .message("Purchase retrieved.")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deletePurchase(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("deleted", purchaseService.delete(id)))
                        .message("Purchase deleted.")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
