package io.swilson.budgetapi.resource;

import io.swilson.budgetapi.model.Response;
import io.swilson.budgetapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.time.LocalDateTime.now;
import static java.util.Map.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryResource {
//    private final CategoryS
    private final CategoryService categoryService;
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
