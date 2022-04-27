package com.adi.productmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO )
    @Column(name = "category_id")
    private int category_id;

    //ADI ORI FTW MEN ORIGINALS
    @Column(name = "Brand")
    private String Brand;

    @Column(name = "Division")
    private String Div;

    @Column(name = "Gender")
    private String Gender;

    @Column(name = "Age")
    private String Age;

    @Column(name = "Title")
    private String Title;

    public String getPath() {
        return Brand + " " + Div +" "+  Gender +" " + Age +" "+ Title ;
    }

    @Transient
    private String Path;

    //ADI ORI FTW MEN ORIGINALS
    @Override
    public String toString() {
        return "category {" +
                "Brand='" + Brand + '\'' +
                ", Div='" + Div + '\'' +
                ", Gender=" + Gender + '\'' +
                ", Age=" + Age + '\'' +
                ", Category=" + Title + '\'' +
                '}';
    }

 //   @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = CascadeType.ALL)
    @Transient
    private Set<Product> products = new HashSet<Product>();
}
