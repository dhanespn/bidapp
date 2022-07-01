package com.tech.dpn.bidapplication.repository;

import com.tech.dpn.bidapplication.entity.Buyer;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerRepository extends MongoRepository<Buyer, Long> {

    @Query(value = "{ 'productId' : ?0,'email':?1 }", count = true)
    public Long findBuyerBybuyerIdAndEmailId(Long id, String emailID);

    List<Buyer> findByProductIdOrderByBidAmountDesc(long productId);


}