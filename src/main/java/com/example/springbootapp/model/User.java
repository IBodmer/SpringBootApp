package com.example.springbootapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String lastName;
    private String email;
    private Integer age;
}
