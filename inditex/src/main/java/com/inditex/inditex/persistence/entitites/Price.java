package com.inditex.inditex.persistence.entitites;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="prices")
public class Price implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String productId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    private Long priceList; //Identificador de la tarifa de precios aplicable.
    private Long priority;
    private Double price; // Costo final
    private String curr;


    @Transient
    private Long rate; //Tarifa a aplicar


    @ManyToOne
    private Brand brand;


    public Price(){}

    public Price(Date startDate, Date endDate, Long priceList,
                 String productId, Long priority,
                 Double price, String curr, Brand brand){
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setPriceList(priceList);
        this.setProductId(productId);
        this.setPriority(priority);
        this.setPrice(price);
        this.setCurr(curr);
        this.setBrand(brand);


    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String toString() {
        return "{"+"id="+getId()+ ", " +
                "productId: " + getProductId() + ", " +
                "priceList: " + getPriceList() + ", " +
                "startDate: " + getStartDate() + ", " +
                "endDate: " + getEndDate() + ", " +
                "brand: " + getBrand().getBrandName() + ", " +
                "price: " + getPrice() +"}";
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price )) return false;
        return Id != null && Id.equals(((Price) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
