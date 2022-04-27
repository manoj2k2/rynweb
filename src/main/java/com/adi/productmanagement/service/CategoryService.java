package com.adi.productmanagement.service;

import com.adi.productmanagement.repository.CategoryRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

}
