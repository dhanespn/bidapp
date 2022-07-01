package com.tech.dpn.bidapplication.entity;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.tech.dpn.bidapplication.utils.CategoryConstraint;

@Document(collection = "product")
public class Product {

    @Id
    private Long id;

    @Field("productName")
    @NotBlank(message = "Product Name is mandotory")
    @Size(min = 5, max = 30, message = "Product Name should have character length between 5 and 30 ")
    private String productName;
    @Field("shortDescription")
    private String shortDescription;
    @Field("detailedDescription")
    private String detailedDescription;
    @Field("category")
    @CategoryConstraint
    private String category;
    @Field("startingPrice")
    private double startingPrice;
    
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Field("bidEndDate")
    private LocalDate bidEndDate;

    @Valid
    @NotNull(message = "Seller details is mandatory while adding a product")
    private Seller seller;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public LocalDate getBidEndDate() {
        return bidEndDate;
    }

    public void setBidEndDate(LocalDate bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
