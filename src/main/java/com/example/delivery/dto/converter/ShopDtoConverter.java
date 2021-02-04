package com.example.delivery.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.delivery.domain.model.Shop;
import com.example.delivery.dto.ShopDto;

@Component
public class ShopDtoConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public ShopDto toModel(Shop shop) {
		return modelMapper.map(shop, ShopDto.class);
	}
	
	public List<ShopDto> toCollectionModel(List<Shop> shops) {
		return shops.stream()
				.map(shop -> toModel(shop))
				.collect(Collectors.toList());
	}
	
}
