package io.swilson.budgetapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.AUTO;

//connect to DB
@Entity //require @Id primary key
@Data // give us access to getters and setters
@NoArgsConstructor //allow us to create users
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "username cannot be empty or null")
    private String username;
    @NotEmpty(message = "password cannot be empty or null")
    private String password;
}
