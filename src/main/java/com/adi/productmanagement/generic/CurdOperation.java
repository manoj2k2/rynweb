package com.adi.productmanagement.generic;

import com.adi.productmanagement.model.Category;
import com.adi.productmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurdOperation<T> implements  ServiceInterface  {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<T>> getAll(@RequestParam(required = false) String name) {
        try {
            List<T> categories = new ArrayList<T>();
            if (name == null)
                categoryService.getCategoryRepository().findAll().forEach(categories::add);
            else
                categoryService.getCategoryRepository().findByTitleContaining(name).forEach(categories::add);
            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getCategoryById(@PathVariable("id") int id) {
        Optional<T> categoryData = categoryService.getCategoryRepository().findById(id);
        if (categoryData.isPresent()) {
            return new ResponseEntity<>(categoryData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<T> createCategory(@RequestBody T category) {
        try {
            Category _category = categoryService.getCategoryRepository()
                    .save(category);
            return new ResponseEntity<>(_category, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> updateCategory(@PathVariable("id") int id, @RequestBody T category) {
        Optional<T> categoryData = categoryService.getCategoryRepository().findById(id);
        if (categoryData.isPresent()) {
            Category _category = categoryData.get();
            _category.setBrand(category.getBrand());
            _category.setAge(category.getAge());
            _category.setGender(category.getGender());
            _category.setDiv(category.getDiv());
            _category.setTitle(category.getTitle());

            return new ResponseEntity<>(categoryService.getCategoryRepository().save(_category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        try {
            categoryService.getCategoryRepository().deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            categoryService.getCategoryRepository().deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
