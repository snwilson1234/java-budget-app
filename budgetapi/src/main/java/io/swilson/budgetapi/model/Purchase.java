package io.swilson.budgetapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
    Purchase model.

    id: primary key
    description: a short message to describe the purchase
    amount: the total amount of the purchase
    date: the date the purchase occurred, in the format "YYYY-MM-DD"
    category: the category this purchase belongs to

*/

@Entity(name = "Purchase")
@Table(name = "purchase")
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

    @Column(nullable = false)
    private int amount;

    @NotEmpty(message = "date cannot be empty")
    @Column(length = 10, nullable = false)
    private String date;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;
}
