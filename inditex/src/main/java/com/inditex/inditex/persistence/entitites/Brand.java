package com.inditex.inditex.persistence.entitites;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name="brands")
public class Brand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String brandName;

    @OneToMany(mappedBy = "brand")
    private Set<Price> prices;

    public Brand(){}

    public Brand(String brandName){
        this.setBrandName(brandName);
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

}
