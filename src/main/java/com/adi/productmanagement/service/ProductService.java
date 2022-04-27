package com.adi.productmanagement.service;

import com.adi.productmanagement.model.Attribute;
import com.adi.productmanagement.model.Category;
import com.adi.productmanagement.model.Product;
import com.adi.productmanagement.repository.AttributeRepository;
import com.adi.productmanagement.repository.CategoryRepository;
import com.adi.productmanagement.repository.ProductRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Getter
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AttributeRepository  attributeRepository;

    public List<Product> GetAllProducts(    ) {
       List<Product> productList = productRepository.findAll();

       productList.forEach(product -> {
           // set category and attributes
           SetCategoryAndAtrr(product);
       } );

       return  productList;
    }

    public Product AddProduct(Product product) {
        return productRepository.save(product);
    }

    public boolean RefreshProductAttribute(int product_id, List<Attribute> attributes) {

        var product = productRepository.findById(product_id).get();
        attributeRepository.deleteAttributesByProduct_Id(product_id);
        attributes.forEach( attribute -> attribute.setProduct_Id(product_id));
        attributeRepository.saveAll(attributes);

        return true;
    }

    public boolean mapProductCategory(int product_id, Category category) {

        var product = productRepository.findById(product_id).get();
        var categortyFromDB = categoryRepository.getById(category.getCategory_id());
        product.setCategory_id(categortyFromDB.getCategory_id());

        productRepository.save(product);

        return true;
    }

    public Product GetProduct(int product_id) {
        Product product = productRepository.findById(product_id).get();
        SetCategoryAndAtrr(product);

        return  product;
    }

    private void SetCategoryAndAtrr(Product product) {
        // Local category
    int category_id = product.getCategory_id();
        if(  category_id > 0) {
            Category category = categoryRepository.getById(category_id);
            product.setCategory(category);
        }

        // Local attributes
        List<Attribute>  attributes = attributeRepository.findAllByProductId(product.getId());
        product.setAttributes(attributes);
    }
}
