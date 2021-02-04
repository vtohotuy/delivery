package com.example.delivery.dto.deconverter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.delivery.domain.model.Shop;
import com.example.delivery.dto.input.ShopInput;

@Component
public class ShopInputDeconverter {

	@Autowired
	private ModelMapper modelMapper;

	public Shop toDomainObject(ShopInput shopInput) {
		return modelMapper.map(shopInput, Shop.class);
	}

	public void copyToDomainObject(ShopInput shopInput, Shop shop) {
		modelMapper.map(shopInput, shop);
	}

}
