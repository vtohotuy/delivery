package com.example.delivery.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressInput {

	@NotBlank
	private String zipCode;

	@NotBlank
	private String street;

	@NotBlank
	private String number;

	private String complement;

	@NotBlank
	private String district;

}
