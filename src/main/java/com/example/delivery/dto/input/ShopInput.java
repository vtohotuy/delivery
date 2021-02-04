package com.example.delivery.dto.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShopInput {

	@NotBlank
	private String name;

	@NotNull
	@PositiveOrZero
	private BigDecimal shippingFee;

	@Valid
	@NotNull
	private AddressInput address;

}
