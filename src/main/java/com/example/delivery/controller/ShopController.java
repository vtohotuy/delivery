package com.example.delivery.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.delivery.domain.model.Shop;
import com.example.delivery.dto.ShopDto;
import com.example.delivery.dto.converter.ShopDtoConverter;
import com.example.delivery.dto.deconverter.ShopInputDeconverter;
import com.example.delivery.dto.input.ShopInput;
import com.example.delivery.repository.ShopRepository;
import com.example.delivery.service.ShopService;

@RestController
@RequestMapping(path = "/shops", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShopController {

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private ShopService shopService;

	@Autowired
	private ShopDtoConverter shopDtoConverter;

	@Autowired
	private ShopInputDeconverter shopInputDeconverter;

	@GetMapping
	public List<ShopDto> list() {
		return shopDtoConverter.toCollectionModel(shopRepository.findAll());
	}

	@GetMapping("/{shopId}")
	public ShopDto search(@PathVariable Long shopId) {
		Shop shop = shopService.search(shopId);
		return shopDtoConverter.toModel(shop);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ShopDto insert(@RequestBody @Valid ShopInput shopInput) {
		Shop shop = new Shop();
		try {
			shop = shopInputDeconverter.toDomainObject(shopInput);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return shopDtoConverter.toModel(shopService.save(shop));
	}

	@PutMapping("/{shopId}")
	public ShopDto update(@PathVariable Long shopId, @RequestBody @Valid ShopInput shopInput) {

		Shop currentShop = shopService.search(shopId);
		shopInputDeconverter.copyToDomainObject(shopInput, currentShop);
		return shopDtoConverter.toModel(shopService.save(currentShop));
	}

	@PutMapping("/{shopId}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void activate(@PathVariable Long shopId) {
		shopService.activate(shopId);
	}

	@DeleteMapping("/{shopId}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deactivate(@PathVariable Long shopId) {
		shopService.deactivate(shopId);
	}

	@PutMapping("/{shopId}/opening")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void open(@PathVariable Long shopId) {
		shopService.open(shopId);
	}

	@PutMapping("/{shopId}/closure")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void close(@PathVariable Long shopId) {
		shopService.close(shopId);
	}
}
