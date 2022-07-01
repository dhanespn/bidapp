package com.tech.dpn.bidapplication.repository;

import com.tech.dpn.bidapplication.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {

    @Query(value="{ 'id' : ?0 },fields={'productId','bidEndDate'}",count = true)
    Long checkExistance(Long productId);

    @Query(value="{ 'id' : ?0 },fields={'productId','bidEndDate'}")
    Product checkExistanceAndEndDate(Long productId);

    Product  findTopByOrderByIdDesc();


}
