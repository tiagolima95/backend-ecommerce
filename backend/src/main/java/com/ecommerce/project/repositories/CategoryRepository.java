package com.ecommerce.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.project.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
