package com.tech.dpn.bidapplication.entity;

import java.time.LocalDate;
import java.util.List;

public class ProductModel {
    private long productId;
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    private String category;
    private Double startingPrice;
    private LocalDate bidEndDate;

    List<Bids> bidDetails;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

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

    public Double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public LocalDate getBidEndDate() {
        return bidEndDate;
    }

    public void setBidEndDate(LocalDate bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    public List<Bids> getBidDetails() {
        return bidDetails;
    }

    public void setBidDetails(List<Bids> bidDetails) {
        this.bidDetails = bidDetails;
    }
}
