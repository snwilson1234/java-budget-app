package io.swilson.budgetapi.resource;

import io.swilson.budgetapi.model.Response;
import io.swilson.budgetapi.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.time.LocalDateTime.now;
import static java.util.Map.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseResource {
    // TODO: implement resource
    private final PurchaseService purchaseService;

    @GetMapping("/")
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

}
