package com.tech.dpn.bidapplication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tech.dpn.bidapplication.entity.Buyer;
import com.tech.dpn.bidapplication.exception.BidAppException;
import com.tech.dpn.bidapplication.service.BuyerService;
import com.tech.dpn.bidapplication.service.ProducerService;

@CrossOrigin
@RestController
@RequestMapping("/e-auction/api/v1")
public class BuyerController {

    Logger LOG = LoggerFactory.getLogger(BuyerController.class);

    @Autowired
    BuyerService buyerService;

    @Autowired
    ProducerService producerService;

    /**
     * @param buyer
     * @return
     */
    @PostMapping("/buyer/place-bid")
    public ResponseEntity<?> bidAProduct(@Valid @RequestBody Buyer buyer) {
        LOG.info("BuyerController : Started bidAProduct function");
        try {
            String returnString = buyerService.bidAProduct(buyer);
            //producerService.sendMessage(buyer.getEmail() +">>"+buyer.getProductId()+"@"+buyer.getBidAmount());
            return new ResponseEntity<String>(returnString, HttpStatus.CREATED);
        } catch (BidAppException exception) {
            LOG.error("Bid can not places : " + exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception exception) {
            LOG.error("Excepton in  bidAProduct function: " + exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * @param productId
     * @param buyerEmailId
     * @param newBidAmount
     * @return
     */
    @PutMapping("/buyer/update-bid/{productId}/{buyerEmailId}/{newBidAmount}")
    public ResponseEntity<?> updateBidForProduct(@PathVariable(value = "productId") Long productId,
                                                 @PathVariable(value = "buyerEmailId") String buyerEmailId,
                                                 @PathVariable(value = "newBidAmount") Double newBidAmount) {
        LOG.info("BuyerController : Started updateBidForProduct function");
        try {
            LOG.info("BuyerController : Started calling the updateBidOfProduct:(buyerService.class)  function");
            String returnString = buyerService.updateBidOfProduct(productId, buyerEmailId, newBidAmount);
            producerService.sendMessage(buyerEmailId+">>"+productId+"@"+newBidAmount);
            return new ResponseEntity<String>(returnString, HttpStatus.CREATED);
        } catch (BidAppException exc) {
            LOG.error("can not update a bid for a product with id: {}, since {}", productId, exc.getMessage());
            return new ResponseEntity<>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception exception) {
            LOG.error("Excepton in  updateBidOfProduct function: " + exception.getMessage());
            return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    @GetMapping("/common/category")
    public ResponseEntity<?> updateBidForProduct() {
        LOG.info("BuyerController : Started updateBidForProduct function");
            LOG.info("BuyerController : Started calling the updateBidOfProduct:(buyerService.class)  function");
            List<String> returnString = buyerService.getCategory();
            return new ResponseEntity<List<String>>(returnString, HttpStatus.CREATED);
    }
    
    	
    

    /**
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
