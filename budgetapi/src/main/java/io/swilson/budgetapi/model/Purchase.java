package io.swilson.budgetapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "description cannot be empty")
    @Column(length = 80, nullable = false)
    private String description;

    @NotEmpty(message = "amount cannot be empty")
    @Column(nullable = false)
    private int amount;

    @NotEmpty(message = "date cannot be empty")
    @Column(length = 10, nullable = false)
    private String date;

    //TODO: add relationship to category
}
