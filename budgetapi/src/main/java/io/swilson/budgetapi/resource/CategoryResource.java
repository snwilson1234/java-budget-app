package io.swilson.budgetapi.resource;

import io.swilson.budgetapi.model.*;
import io.swilson.budgetapi.repo.CategoryRepo;
import io.swilson.budgetapi.service.CategoryService;
import io.swilson.budgetapi.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.Map.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryResource {
    private final CategoryService categoryService;
    private final PurchaseService purchaseService;
    private final CategoryRepo categoryRepo;

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Response> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setBudget(categoryRequest.getBudget());

        categoryService.create(category);

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Category created successfully.")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @GetMapping("/")
    public ResponseEntity<Response> getCategories() {
        return ResponseEntity.ok(
            Response.builder()
                .timeStamp(now())
                .data(of("categories", categoryService.list(30)))
                .message("Categories retrieved.")
                .status(OK)
                .statusCode(OK.value())
                .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCategory(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("category", categoryService.get(id)))
                        .message("Category retrieved.")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCategory(@PathVariable("id") Long id) {
        List<Purchase> purchases = categoryService.get(id).purchases();
        for (int i = 0; i < purchases.size(); i++) {
            purchaseService.delete(purchases.get(i).getId());
        }
        categoryService.delete(id);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Category successfully deleted.")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/{id}/overages")
    public ResponseEntity<Response> getOverages(@PathVariable("id") Long id) {
        Collection<Long> overageIds = categoryService.getOverages(id);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("overages", overageIds))
                        .message("Successfully retrieved overage ids.")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
