package io.swilson.budgetapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.AUTO;

/*
    Category model.

    @Entity defines a db entity, lets us connect to DB, and requires we set an @Id primary key.
    @Table defines this as a db table with certain name.
    @Data gives us getters & setters for each db column (id, name, ...)
    @NoArgsConstructor generates a constructor with no args, if possible.
    @AllArgsConstructor generates a constructor with all args.

    id: primary key
    name: name of the category, for example: "Groceries"
    budget: the total amount allocated to spending in this category for one month
    purchases: the Purchases made in this category.

*/

@Entity(name = "Category")
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @NotEmpty(message = "name cannot be empty")
    @Column(length = 30, nullable = false)
    private String name;

    private Integer budget;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Purchase> purchases = new ArrayList<>();
}
