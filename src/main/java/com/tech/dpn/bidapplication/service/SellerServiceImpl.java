package com.tech.dpn.bidapplication.service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.dpn.bidapplication.entity.Bids;
import com.tech.dpn.bidapplication.entity.Buyer;
import com.tech.dpn.bidapplication.entity.Product;
import com.tech.dpn.bidapplication.entity.ProductModel;
import com.tech.dpn.bidapplication.entity.Seller;
import com.tech.dpn.bidapplication.exception.BidAppException;
import com.tech.dpn.bidapplication.repository.BuyerRepository;
import com.tech.dpn.bidapplication.repository.ProductRepository;

@Service
public class SellerServiceImpl implements SellerService {
    Logger LOG = LoggerFactory.getLogger(SellerServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BuyerRepository buyerRepository;

    @Override
    public void incrementId() {
    }

    @Override
    public Product insertNewProduct(Product product) {
        LOG.info("SellerServiceImpl : Started insertNewProduct function");
        Product lastProd = productRepository.findTopByOrderByIdDesc();
        if(product.getId() == null ||  product.getId() == 0 ) {
	        if (productRepository.count() == 0) {
	            product.setId(1L);
	            LOG.info("The new product id is {}", 1 );
	        } else {
	            product.setId(lastProd.getId() + 1);
	            LOG.info("The new product id is {}",lastProd.getId() + 1 );
	        }
	        
        }
       
        LOG.info("SellerServiceImpl : End of insertNewProduct function" );
        return productRepository.save(product);
    }

    @Override
    public ProductModel getProductDetails(Long productId) throws BidAppException {
        LOG.info("SellerServiceImpl : Started getProductDetails function");
        try {
            Product product = productRepository.findAll().stream().filter(prd -> prd.getId() == productId).findFirst().get();
            List<Buyer> buyerList = buyerRepository.findByProductIdOrderByBidAmountDesc(productId);
            return mapProdcuctAndBuyerInformmation(product, buyerList);
        }catch(Exception exc){
            LOG.error("Error in SellerServiceImpl : Started getProductDetails function: "+ exc.getMessage());
            throw new BidAppException("Some error while fetching data: "+ exc.getMessage());
        }
    }

    private ProductModel mapProdcuctAndBuyerInformmation(Product product, List<Buyer> buyerList) {
        LOG.info("SellerServiceImpl : Started mapProdcuctAndBuyerInformmation function");
        ProductModel productModel = new ProductModel();
        productModel.setProductId(product.getId());
        productModel.setProductName(product.getProductName());
        productModel.setShortDescription(product.getShortDescription());
        productModel.setDetailedDescription(product.getDetailedDescription());
        productModel.setCategory(product.getCategory());
        productModel.setStartingPrice(product.getStartingPrice());
        productModel.setBidEndDate(product.getBidEndDate());
        LOG.info("SellerServiceImpl : Started mapProdcuctAndBuyerInformmation  function fetching the bid details of product {} "+product.getId());
        List<Bids> bidsList = buyerList.stream().map(buyer -> new Bids(buyer.getBidAmount(),buyer.getEmail(),buyer.getPhone(),buyer.getBuyerId())).collect(Collectors.toList());
        productModel.setBidDetails(bidsList);
        LOG.info("SellerServiceImpl : end of  mapProdcuctAndBuyerInformmation  function");
        return productModel;
    }

    @Override
    public String deleteProductDetails(Long productId) throws BidAppException {
        LOG.info("SellerServiceImpl : Started deleteProductDetails function");
        try {
            LOG.info("SellerServiceImpl : Started deleteProductDetails function");
            Product deleteComponenet = productRepository.findAll().stream().filter(prd -> prd.getId() == productId).findFirst().get();
            Instant instant = deleteComponenet.getBidEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant();
            if (instant.toEpochMilli() < Instant.now().toEpochMilli()) {
                throw new BidAppException("Can not delete a product with older bid end date");
            }
            productRepository.delete(deleteComponenet);
            LOG.info("SellerServiceImpl : end of  deleteProductDetails function");
            return "The Product with ID " + productId + " is deleted";
        }catch(Exception e){
            LOG.info("SellerServiceImpl : error in  deleteProductDetails function : "+ e);
            throw new BidAppException("There is issue while delete the product : "+ e);
        }
    }

	@Override
	public List<Product> getProductList() {
		return productRepository.findAll();
	}

	@Override
	public Product getDetailsOfProduct(Long productId) {
		return productRepository.findAll().stream().filter(prd -> prd.getId() == productId).findFirst().get();
	}


}
