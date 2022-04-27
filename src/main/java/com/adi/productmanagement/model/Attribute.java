package com.adi.productmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Accessors(chain=true)
@JsonIgnoreProperties(ignoreUnknown = true)

@Table(name = "attributes")
public class Attribute {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO )
    private int Attr_Id;

    @Column(name = "Product_Id")
    private Integer Product_Id;

    @Column(name = "Color_Code")
    private String ColorCode;

    @Column(name = "Size_Code")
    private  String SizeCode;
}
