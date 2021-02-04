package com.example.delivery.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {

	private String zipCode;

	private String street;

	private String number;

	private String complement;

	private String district;

}
