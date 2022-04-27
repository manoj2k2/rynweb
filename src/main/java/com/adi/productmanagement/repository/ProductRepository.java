package com.adi.productmanagement.repository;

import com.adi.productmanagement.model.Category;
import com.adi.productmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer > {

    @Modifying
    @Query("select c from Product c  where c.Title =:title")
    List<Product> findByTitleContaining(@Param("title") String title);




}
