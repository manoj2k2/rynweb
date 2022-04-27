package com.adi.productmanagement.repository;

import com.adi.productmanagement.model.Attribute;
import com.adi.productmanagement.model.Category;
import com.adi.productmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

    @Modifying()
    @Transactional
    @Query( value = "delete from Attribute b where b.Product_Id= ?1")
    void deleteAttributesByProduct_Id(@Param("Product_Id") int Product_Id);

    @Query("select a from Product p join Attribute a on a.Product_Id= p.id where a.Product_Id =:Product_Id")
    List<Attribute> findAllByProductId(@Param("Product_Id") int Product_Id);
}
