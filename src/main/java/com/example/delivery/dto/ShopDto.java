package com.example.delivery.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShopDto {

	private Long id;
	
	private String name;
	
	private BigDecimal shippingFee;
	
	private Boolean active;
	private AddressDto address;
	private Boolean open;
	
}
