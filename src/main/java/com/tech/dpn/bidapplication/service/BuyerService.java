package com.tech.dpn.bidapplication.service;

import java.util.List;

import com.tech.dpn.bidapplication.entity.Buyer;
import com.tech.dpn.bidapplication.exception.BidAppException;

public interface BuyerService {
    public String bidAProduct(Buyer buyer) throws BidAppException;

    public String updateBidOfProduct(Long productId, String emailId, Double newAmount) throws BidAppException;

	public List<String> getCategory();
}
