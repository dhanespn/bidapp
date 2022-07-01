package com.tech.dpn.bidapplication.controller;


import com.tech.dpn.bidapplication.entity.Product;
import com.tech.dpn.bidapplication.entity.ProductModel;
import com.tech.dpn.bidapplication.exception.BidAppException;
import com.tech.dpn.bidapplication.service.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/e-auction/api/v1/seller")
public class SellerController {

    Logger LOG = LoggerFactory.getLogger(SellerController.class);

    @Autowired
    SellerService sellerService;

    /**
     * @param product
     * @return
     */
    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
        LOG.info("SellerController : addProduct function started");
        Product returnproduct1 = sellerService.insertNewProduct(product);
        LOG.info("SellerController : addProduct function ended" + returnproduct1);
        return new ResponseEntity<Product>(returnproduct1, HttpStatus.CREATED);
    }
    
    /**
     * 
     * @param product
     * @return
     */
    @GetMapping("/product")
    @CrossOrigin
    public ResponseEntity<?> getListOfProduct() {
        LOG.info("SellerController : addProduct function started");
        List<Product> returnproduct1 = sellerService.getProductList();
        LOG.info("SellerController : addProduct function ended" + returnproduct1);
        return new ResponseEntity<List<Product>>(returnproduct1, HttpStatus.OK);
    }
    
    /**
     * 
     * @param product
     * @return
     */
    @GetMapping("/product/{productId}")
    @CrossOrigin
    public ResponseEntity<?> getDetailsOfProduct(@PathVariable(value = "productId") Long productId) {
        LOG.info("SellerController : addProduct function started");
        Product returnproduct1 = sellerService.getDetailsOfProduct(productId);
        LOG.info("SellerController : addProduct function ended" + returnproduct1);
        return new ResponseEntity<Product>(returnproduct1, HttpStatus.OK);
    }


    /**
     * @param productId
     * @return
     */
    @GetMapping("/show-bids/{productId}")
    public ResponseEntity<?> showProductBid(@PathVariable(value = "productId") Long productId) {
        LOG.info("SellerController : showProductBid function started");
        try {
            LOG.info("SellerController : calling  getProductDetails(sellerService)");
            return new ResponseEntity<ProductModel>(sellerService.getProductDetails(productId), HttpStatus.OK);
        } catch (BidAppException exception) {
            LOG.error("SellerController : exception in showProductBid"+exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param productId
     * @return
     */
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "productId") Long productId) {
        LOG.info("SellerController : deleteProduct function started");
        try {
            LOG.info("SellerController : calling  deleteProductDetails(sellerService)");
            return new ResponseEntity<String>(sellerService.deleteProductDetails(productId), HttpStatus.OK);
        } catch (BidAppException exception) {
            LOG.error("SellerController : exception in deleteProduct"+exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
