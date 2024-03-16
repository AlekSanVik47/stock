package ru.stock.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "category", unique = true)
    private String titleCategory;
}
