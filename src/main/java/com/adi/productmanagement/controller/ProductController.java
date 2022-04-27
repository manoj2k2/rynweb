package com.adi.productmanagement.controller;

import com.adi.productmanagement.model.Product;
import com.adi.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
// rest controller for product CURD operation
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping ("/all")
    public ResponseEntity<List<Product>> getAll(@RequestParam(required = false) String name) {
        try {
            List<Product> products = new ArrayList<Product>();
            if (name == null)
                productService.getProductRepository().findAll().forEach(products::add);
            else
                productService.getProductRepository().findByTitleContaining(name).forEach(products::add);

            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
        Optional<Product> productData = productService.getProductRepository().findById(id);
        if (productData.isPresent()) {
            return new ResponseEntity<>(productData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product _product = productService.getProductRepository()
                    .save(product);
            return new ResponseEntity<>(_product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") int id, @RequestBody Product product) {
        Optional<Product> productData = productService.getProductRepository().findById(id);
        if (productData.isPresent()) {
            Product _product = productData.get();
            _product.setPrice(product.getPrice());
            _product.setCode(product.getCode());
            _product.setTitle(product.getTitle());
            _product.setTitle(product.getTitle());

            return new ResponseEntity<>(productService.getProductRepository().save(_product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        try {
            productService.getProductRepository().deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/all")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            productService.getProductRepository().deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
