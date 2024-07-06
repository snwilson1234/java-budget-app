package io.swilson.budgetapi.resource;

import io.swilson.budgetapi.model.Category;
import io.swilson.budgetapi.model.CategoryRequest;
import io.swilson.budgetapi.model.Response;
import io.swilson.budgetapi.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static java.util.Map.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryResource {
    private final CategoryService categoryService;

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Response> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setBudget(categoryRequest.getBudget());

        Category createdCategory = categoryService.create(category);

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

    @GetMapping("/delete/{id}")
    public ResponseEntity<Response> deleteCategory(@PathVariable("id") Long id) {
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
}
