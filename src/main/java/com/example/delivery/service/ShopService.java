package com.example.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.delivery.domain.model.PaymentMethod;
import com.example.delivery.domain.model.Shop;
import com.example.delivery.repository.ShopRepository;


@Service
public class ShopService {

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private PaymentMethodService paymentMethodService;
	
	public Shop search(Long shopId) {
		return shopRepository.findById(shopId).get();
	}

	@Transactional
	public Shop save(Shop shop) {
		return shopRepository.save(shop);
	}
	
	@Transactional
	public void activate(Long shopId) {
		Shop currentShop = search(shopId);
		
		currentShop.activate();
	}
	
	@Transactional
	public void deactivate(Long shopId) {
		Shop currentShop = search(shopId);
		
		currentShop.deactivate();
	}
	
	@Transactional
	public void open(Long shopId) {
	    Shop currentShop = search(shopId);
	    
	    currentShop.open();
	}

	@Transactional
	public void close(Long shopId) {
	    Shop currentShop = search(shopId);
	    
	    currentShop.close();
	}  
	
	@Transactional
	public void disassociatePaymentMethod(Long shopId, Long paymentMethodId) {
		Shop shop = search(shopId);
		PaymentMethod paymentMethod = paymentMethodService.search(paymentMethodId);
		
		shop.removePaymentMethod(paymentMethod);
	}
	
	@Transactional
	public void associatePaymentMethod(Long shopId, Long paymentMethodId) {
		Shop shop = search(shopId);
		PaymentMethod paymentMethod = paymentMethodService.search(paymentMethodId);
		
		shop.insertPaymentMethod(paymentMethod);
	}
	
}
