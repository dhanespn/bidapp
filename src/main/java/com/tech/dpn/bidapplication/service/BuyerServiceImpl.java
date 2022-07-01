package com.tech.dpn.bidapplication.service;

import com.tech.dpn.bidapplication.entity.Buyer;
import com.tech.dpn.bidapplication.entity.Product;
import com.tech.dpn.bidapplication.exception.BidAppException;
import com.tech.dpn.bidapplication.repository.BuyerRepository;
import com.tech.dpn.bidapplication.repository.ProductRepository;
import com.tech.dpn.bidapplication.utils.Category;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BuyerServiceImpl implements BuyerService {
    Logger LOG =  LogManager.getLogger(BuyerServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    MongoTemplate mongoTemplate;


    public String bidAProduct(Buyer buyer) throws BidAppException {
        LOG.info("BuyerServiceImpl : Started bidAProduct function");
        Product product = null;
        Buyer buyer1 = null;
        long countOfExistance = 0L;
            if(buyer.getProductId() != 0) {
                countOfExistance = productRepository.checkExistance(buyer.getProductId());
            }
            long countOfBuyerBidForAProduct = buyerRepository.findBuyerBybuyerIdAndEmailId(buyer.getProductId(), buyer.getEmail());
            if (countOfExistance != 0) {
                product = productRepository.checkExistanceAndEndDate(buyer.getProductId());
                if (product.getBidEndDate().isAfter(LocalDate.now())) {
                    if (countOfBuyerBidForAProduct == 0) {
                        incrementId(buyer);
                        buyerRepository.save(buyer);
                    } else {
                        LOG.error("The same product is already bid by same buyer productID: {} for user {} ",buyer.getProductId(),buyer.getEmail());
                        throw new BidAppException("The same product is already bid by same buyer");
                    }
                } else {
                    LOG.error("Since the product's bid end date is expired : {} for user {} ",buyer.getProductId(),buyer.getEmail());
                    throw new BidAppException("Since the product's bid end date is expired ");
                }
            } else {
                LOG.error("Since the product is not existing Bid can not done : {} for user {} ",buyer.getProductId(),buyer.getEmail());
                throw new BidAppException("Since the product is not existing Bid can not done");
            }
        LOG.info("Congrats...Bid infomation of product {} for buyer {} {} is placed", buyer.getProductId(), buyer.getFirstName(), buyer.getLastName());
        return "Congrats...Bid infomation of product " + buyer.getProductId() + " for buyer " + buyer.getFirstName() + " " + buyer.getLastName() + " is placed ";
    }

    @Override
    public String updateBidOfProduct(Long productId, String emailId, Double newAmount) throws BidAppException {
        LOG.info("BuyerServiceImpl : Started updateBidOfProduct function");
        Product product = null;
        long countOfExistance = 0L;
        if(productId != 0) {
            countOfExistance = productRepository.checkExistance(productId);
        }
        if (countOfExistance != 0) {
            product = productRepository.checkExistanceAndEndDate(productId);
            if (product.getBidEndDate().isAfter(LocalDate.now())) {
                try {
                    Query query = new Query();
                    query.addCriteria(Criteria.where("productId").is(productId).and("email").is(emailId));
                    Update update = new Update();
                    update.set("bidAmount", newAmount);
                    mongoTemplate.findAndModify(query, update, Buyer.class);
                } catch (Exception exception){
                    LOG.error("Error happens when trying to update the bid amount: {}" ,exception.getMessage());
                    throw new BidAppException("Error happens when trying to update the bid amount: "+ exception.getMessage());
                }
            } else {
                LOG.error("Since the product's bid end date is expired: {}" ,productId);
                throw new BidAppException("Since the product's bid end date is expired ");
            }
        } else {
            LOG.error("Can not update product since it is not existing: {}" ,productId);
            throw new BidAppException("Can not update product since it is not existing");
        }
        LOG.error("The bid amount is updated for product {} with amount {} by buyer with email id {}" ,productId,newAmount,emailId);
        return "The bid amount is updated for product "+ productId + " with amount "+ newAmount +" by buyer with email id " + emailId;
    }

    private void incrementId(Buyer buyer) {
        if (buyerRepository.count() == 0) {
            buyer.setBuyerId(1L);
        } else {
            buyer.setBuyerId(buyerRepository.count() + 1);
        }
    }

	@Override
	public List<String> getCategory() {
		return Stream.of(Category.values()).map(Enum::name)
                .collect(Collectors.toList());
	}

}
