//package com.tech.dpn.bidapplication.BuyerServiceImplTest;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import com.tech.dpn.bidapplication.entity.Buyer;
//import com.tech.dpn.bidapplication.entity.Product;
//import com.tech.dpn.bidapplication.entity.ProductModel;
//import com.tech.dpn.bidapplication.entity.Seller;
//import com.tech.dpn.bidapplication.exception.BidAppException;
//import com.tech.dpn.bidapplication.repository.BuyerRepository;
//import com.tech.dpn.bidapplication.repository.ProductRepository;
//import com.tech.dpn.bidapplication.service.SellerServiceImpl;
//
//@RunWith(MockitoJUnitRunner.class)
//public class SellerServiceTest {
//	@InjectMocks
//	SellerServiceImpl sellerServiceImpl;
//	@Mock
//	ProductRepository productRepository;
//	@Mock
//	BuyerRepository buyerRepository;
//	
//	private Product getProduct() {
//		
//		Product product = new Product();
//		Seller seller = new Seller();
//		product.setProductName("TestProduct");
//		product.setShortDescription("TestProduct Description");
//		product.setCategory("TestCategory");
//		product.setDetailedDescription("TestProduct detailed Description");
//		product.setBidEndDate(LocalDate.now());
//		product.setStartingPrice(100);
//		product.setId(1235L);
//		seller.setFirstName("TestFirst");
//		seller.setLastName("lastName");
//		seller.setAddress("TestAddress");
//		seller.setCity("TestCity");
//		seller.setPhone("1234567890");
//		seller.setState("TX");
//		seller.setPin("6999654");
//		seller.setEmail("testEmai@test.com");
//		product.setSeller(seller);
//		return product;
//	}
//	
//	@Test
//	public void insertNewProductTest() {
//		Product lastProd  = new Product();
//		lastProd.setId(1234L);
//		Product product = getProduct();
//		when(productRepository.findTopByOrderByIdDesc()).thenReturn(lastProd);
//		when(productRepository.save(product)).thenReturn(product);
//		when(productRepository.count()).thenReturn(1L);
//		sellerServiceImpl.insertNewProduct(product);
//		assertNotNull(product);
//		assertEquals(product.getSeller().getFirstName(),"TestFirst");
//	}
//	
//	@Test
//	public void insertNewProductTest2withId1() {
//		Product lastProd  = new Product();
//		lastProd.setId(0L);
//		Product product = getProduct();
//		when(productRepository.findTopByOrderByIdDesc()).thenReturn(lastProd);
//		when(productRepository.save(product)).thenReturn(product);
//		when(productRepository.count()).thenReturn(0L);
//		sellerServiceImpl.insertNewProduct(product);
//		assertNotNull(product);
//		assertEquals(product.getSeller().getFirstName(),"TestFirst");
//	}
//	
//	
//	//@Test
//	public void getProductDetails() throws BidAppException {
//		Product product = getProduct();
//		List<Product> productList = new ArrayList<>();
//		productList.add(product);
//		
//		Buyer buyer = new Buyer();
//		buyer.setFirstName("SellerFirstName");
//		buyer.setLastName("SellerLastName");
//		buyer.setBuyerId(123);
//		buyer.setEmail("test@test.com");
//		buyer.setPhone("9874561230");
//		buyer.setState("TX");
//		buyer.setProductId(1235L);
//		buyer.setPin("655698");
//		buyer.setCity("CA");
//		List<Buyer> byerList = new ArrayList<>();
//		
//		ProductModel productModel = new ProductModel();
//		productModel.setProductId(1235L);
//		
//		when(productRepository.findAll()).thenReturn(productList);
//		when(buyerRepository.findByProductIdOrderByBidAmountDesc(Mockito.anyLong())).thenReturn(byerList);
//		assertNotNull(sellerServiceImpl.getProductDetails(1235L));
//		assertEquals(sellerServiceImpl.getProductDetails(1235L).getProductId(),1235L);
//	}
//	
//	
//	//@Test
//	public void deleteProductDetails() throws BidAppException {
//		Product product = getProduct();
//		List<Product> productList = new ArrayList<>();
//		productList.add(product);
//		
//		Buyer buyer = new Buyer();
//		buyer.setFirstName("SellerFirstName");
//		buyer.setLastName("SellerLastName");
//		buyer.setBuyerId(123);
//		buyer.setEmail("test@test.com");
//		buyer.setPhone("9874561230");
//		buyer.setState("TX");
//		buyer.setProductId(1235L);
//		buyer.setPin("655698");
//		buyer.setCity("CA");
//		List<Buyer> byerList = new ArrayList<>();
//		
//		ProductModel productModel = new ProductModel();
//		productModel.setProductId(1235L);
//		
//		when(productRepository.findAll()).thenReturn(productList);
//		//Mockito.doNothing(productRepository.delete(product));
//		assertNotNull(sellerServiceImpl.getProductDetails(1235L));
//		assertEquals(sellerServiceImpl.getProductDetails(1235L).getProductId(),1235L);
//	}
//}
