package com.tech.dpn.bidapplication.BuyerServiceImplTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.tech.dpn.bidapplication.entity.Buyer;
import com.tech.dpn.bidapplication.entity.Product;
import com.tech.dpn.bidapplication.entity.Seller;
import com.tech.dpn.bidapplication.exception.BidAppException;
import com.tech.dpn.bidapplication.repository.BuyerRepository;
import com.tech.dpn.bidapplication.repository.ProductRepository;
import com.tech.dpn.bidapplication.service.BuyerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BuyerServiceTest {

	@InjectMocks
	BuyerServiceImpl buyerServiceImpl;

	@Mock
	ProductRepository productRepository;

	@Mock
	BuyerRepository buyerRepository;

	@Mock
	MongoTemplate mongoTemplate;

	private Product getProduct() {

		Product product = new Product();
		Seller seller = new Seller();
		product.setProductName("TestProduct");
		product.setShortDescription("TestProduct Description");
		product.setCategory("TestCategory");
		product.setDetailedDescription("TestProduct detailed Description");
		product.setBidEndDate(LocalDate.now());
		product.setStartingPrice(100);
		product.setId(1235L);
		seller.setFirstName("TestFirst");
		seller.setLastName("lastName");
		seller.setAddress("TestAddress");
		seller.setCity("TestCity");
		seller.setPhone("1234567890");
		seller.setState("TX");
		seller.setPin("6999654");
		seller.setEmail("testEmai@test.com");
		product.setSeller(seller);
		return product;
	}
	
	private Buyer getBuyer() {
		Buyer buyer = new Buyer();
		buyer.setFirstName("SellerFirstName");
		buyer.setLastName("SellerLastName");
		buyer.setBuyerId(123);
		buyer.setEmail("test@test.com");
		buyer.setPhone("9874561230");
		buyer.setState("TX");
		buyer.setProductId(1235L);
		buyer.setPin("655698");
		buyer.setCity("CA");
		return buyer;
	}

	@Test
	public void bidAProductTest() throws BidAppException {
		when(productRepository.checkExistance(Mockito.anyLong())).thenReturn(1L);
		when(buyerRepository.findBuyerBybuyerIdAndEmailId(Mockito.anyLong(), Mockito.any(String.class))).thenReturn(1L);
		when(productRepository.checkExistanceAndEndDate(Mockito.anyLong())).thenReturn(getProduct());
		assertThatThrownBy(() -> buyerServiceImpl.bidAProduct(getBuyer()))
        .isInstanceOf(BidAppException.class);

	}
	
//	@Test()
//	public void bidAProductTest2() throws BidAppException {
//		when(productRepository.checkExistance(Mockito.anyLong())).thenReturn(0L);
//		when(buyerRepository.findBuyerBybuyerIdAndEmailId(Mockito.anyLong(), Mockito.any(String.class))).thenReturn(1L);
//		when(productRepository.checkExistanceAndEndDate(Mockito.anyLong())).thenReturn(getProduct());
//		assertThatThrownBy(() -> buyerServiceImpl.bidAProduct(getBuyer()))
//        .isInstanceOf(BidAppException.class);
//
//	}
	
	@Test()
	public void bidAProductTest3() throws BidAppException {
		when(productRepository.checkExistance(Mockito.anyLong())).thenReturn(1L);
		when(buyerRepository.findBuyerBybuyerIdAndEmailId(Mockito.anyLong(), Mockito.any(String.class))).thenReturn(1L);
		Product pro = getProduct();
		pro.setBidEndDate(LocalDate.now().plusDays(1));
		when(productRepository.checkExistanceAndEndDate(Mockito.anyLong())).thenReturn(pro);
		assertThatThrownBy(() -> buyerServiceImpl.bidAProduct(getBuyer()))
        .isInstanceOf(BidAppException.class);

	}
	
	@Test()
	public void bidAProductTest4() throws BidAppException {
		when(productRepository.checkExistance(Mockito.anyLong())).thenReturn(1L);
		when(buyerRepository.findBuyerBybuyerIdAndEmailId(Mockito.anyLong(), Mockito.any(String.class))).thenReturn(0L);
		Product pro = getProduct();
		pro.setBidEndDate(LocalDate.now().plusDays(1));
		when(productRepository.checkExistanceAndEndDate(Mockito.anyLong())).thenReturn(pro);
		String h = buyerServiceImpl.bidAProduct(getBuyer());
		assertNotNull(h);
	}
}
