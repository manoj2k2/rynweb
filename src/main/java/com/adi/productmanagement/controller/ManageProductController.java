package com.adi.productmanagement.controller;

import com.adi.productmanagement.model.Attribute;
import com.adi.productmanagement.model.Category;
import com.adi.productmanagement.model.Product;
import com.adi.productmanagement.service.CurrencyExchangeService;
import com.adi.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/manage")
// rest controller for product category and attribute assignment and read operation
public class ManageProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @RequestMapping("/all")
    public List<Product> GetAllProducts()
    {
       return productService.GetAllProducts();
    }

    @RequestMapping("/{product_id}")
    public Product GetAllProducts(@PathVariable int product_id )
    {
        return productService.GetProduct(product_id);
    }

    @PostMapping("/save")
    // check exchange rate
    // convert currency using flexio api
    // Add product
    public Product AddProduct( @RequestBody Product product)
    {
        float priceInEuro = currencyExchangeService.ConvertPrice(product.getPrice(), product.getPriceCurrency());
        product.setPrice(priceInEuro);
        product.setBaseCurrency("EUR");

        return productService.AddProduct(product);
    }

    @PostMapping("/setCategory/{product_id}")
    public ResponseEntity<Boolean> mapProductCategory(@PathVariable int product_id , @RequestBody Category category )
    {
        boolean result= productService.mapProductCategory(product_id, category);
        if(result)
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        else
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_MODIFIED);
    }


    @PostMapping("/RefreshProductAttribute/{product_id}")
    public ResponseEntity<Boolean> RefreshProductAttribute(@PathVariable int product_id , @RequestBody List<Attribute> attributes)
    {
        boolean result= productService.RefreshProductAttribute(product_id, attributes);
        if(result)
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        else
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_MODIFIED);
    }

}
