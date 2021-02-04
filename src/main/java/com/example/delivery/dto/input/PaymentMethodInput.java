package com.example.delivery.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentMethodInput {

	@NotBlank
	private String description;

}
