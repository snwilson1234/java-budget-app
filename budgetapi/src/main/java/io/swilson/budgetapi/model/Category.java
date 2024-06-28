package io.swilson.budgetapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.AUTO;

// MODEL WE WANT TO MANIPULATE

//connect to DB
@Entity //require @Id primary key
@Data// give us access to getters and setters
@NoArgsConstructor //allow us to create users w no args or with all args
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @NotEmpty(message = "name cannot be empty")
    @Column(length = 30, nullable = false)
    private String name;

    private Integer budget;
    //TODO: add purchases
}
