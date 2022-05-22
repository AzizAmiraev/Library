package com.example.librarydemo.repository;

import com.example.librarydemo.models.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query(value = "SELECT * FROM `category` WHERE `category` = ?", nativeQuery = true)
    Category getCategoryByName(String name);
}
