package com.adi.productmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Accessors(chain=true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO )
    private int id;

    @Column(name = "Code")
    private String Code;

    @Column(name = "Title")
    private String Title;

    @Column(name = "Price", nullable = true, precision = 8)
    private float Price;

    @Column(name = "BaseCurrency" ,nullable = true )
    private String BaseCurrency;

    @Transient
    private String PriceCurrency;

    @Column(name = "category_id",nullable = true )
    private Integer category_id =0;

    @Transient
    private Category category;

    @Transient
    private List<Attribute> Attributes = new ArrayList<>();

}
