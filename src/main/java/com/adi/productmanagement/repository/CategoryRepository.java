package com.adi.productmanagement.repository;

import com.adi.productmanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Modifying
    @Query("select c from Category c  where c.Title =:title")
    List<Category> findByTitleContaining(@Param("title") String title);

}
