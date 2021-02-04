package com.example.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.delivery.domain.model.Shop;
import com.example.delivery.dto.PaymentMethodDto;
import com.example.delivery.dto.converter.PaymentMethodDtoConverter;
import com.example.delivery.service.ShopService;

@RestController
@RequestMapping(path = "/shops/{shopId}/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShopPaymentMethodController {

	@Autowired
	private ShopService shopService;

	@Autowired
	private PaymentMethodDtoConverter paymentMethodDtoConverter;

	@GetMapping
	public List<PaymentMethodDto> list(@PathVariable Long shopId) {
		Shop shop = shopService.search(shopId);

		return paymentMethodDtoConverter.toCollectionModel(shop.getPaymentMethods());
	}

	@DeleteMapping("/{paymentMethodId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disassociate(@PathVariable Long shopId, @PathVariable Long paymentMethodId) {
		shopService.disassociatePaymentMethod(shopId, paymentMethodId);
	}

	@PutMapping("/{paymentMethodId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associate(@PathVariable Long shopId, @PathVariable Long paymentMethodId) {
		shopService.associatePaymentMethod(shopId, paymentMethodId);
	}

}
