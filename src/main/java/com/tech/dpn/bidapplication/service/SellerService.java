package com.tech.dpn.bidapplication.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.tech.dpn.bidapplication.entity.Product;
import com.tech.dpn.bidapplication.entity.ProductModel;
import com.tech.dpn.bidapplication.exception.BidAppException;

@Service
public interface SellerService {

    public void incrementId();

    public Product insertNewProduct(Product product);

    public ProductModel getProductDetails(Long productId) throws BidAppException;

    public String deleteProductDetails(Long productId) throws BidAppException;

	public List<Product> getProductList();

	public Product getDetailsOfProduct(Long productId);


}
