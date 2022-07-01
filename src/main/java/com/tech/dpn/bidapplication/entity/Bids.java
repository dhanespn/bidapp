package com.tech.dpn.bidapplication.entity;

public class Bids {
    private Double bidAmout;
    private String email;
    private String phoneNumber;
    private long buyerId;

    public Double getBidAmout() {
        return bidAmout;
    }

    public void setBidAmout(Double bidAmout) {
        this.bidAmout = bidAmout;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public Bids(Double bidAmout, String email, String phoneNumber, long buyerId) {
        this.bidAmout = bidAmout;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.buyerId = buyerId;
    }
}
