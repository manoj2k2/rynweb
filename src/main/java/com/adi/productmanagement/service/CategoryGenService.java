package com.adi.productmanagement.service;

import com.adi.productmanagement.generic.ServiceInterface;
import com.adi.productmanagement.model.Category;
import com.adi.productmanagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryGenService<Category, Integer> extends ServiceInterface<Category, Integer> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public JpaRepository<Category, Integer> getJpaRepository() {

        return (JpaRepository<Category, Integer>) categoryRepository;
    }
}
