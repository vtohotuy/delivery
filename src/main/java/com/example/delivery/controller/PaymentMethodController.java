package com.example.delivery.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.delivery.domain.model.PaymentMethod;
import com.example.delivery.dto.PaymentMethodDto;
import com.example.delivery.dto.converter.PaymentMethodDtoConverter;
import com.example.delivery.dto.deconverter.PaymentMethodInputDeconverter;
import com.example.delivery.dto.input.PaymentMethodInput;
import com.example.delivery.repository.PaymentMethodRepository;
import com.example.delivery.service.PaymentMethodService;

@RestController
@RequestMapping(path = "/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentMethodController {

	@Autowired
	private PaymentMethodRepository paymentMethodRepository;

	@Autowired
	private PaymentMethodService paymentMethodService;

	@Autowired
	private PaymentMethodDtoConverter paymentMethodDtoConverter;

	@Autowired
	private PaymentMethodInputDeconverter paymentMethodInputDeconverter;

	@GetMapping
	public ResponseEntity<List<PaymentMethodDto>> list() {
		
		List<PaymentMethod> allPaymentMethods = paymentMethodRepository.findAll();

		List<PaymentMethodDto> paymentMethodDTOs = paymentMethodDtoConverter
				.toCollectionModel(allPaymentMethods);
		
		return ResponseEntity.ok()
				.body(paymentMethodDTOs);
		
	}

	@GetMapping("/{paymentMethodId}")
	public ResponseEntity<PaymentMethodDto> search(@PathVariable Long paymentMethodId) {
		
		PaymentMethod paymentMethod = paymentMethodService.search(paymentMethodId);
		  
		PaymentMethodDto paymentMethodDTO =  paymentMethodDtoConverter.toModel(paymentMethod);
		  
		return ResponseEntity.ok().body(paymentMethodDTO);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PaymentMethodDto insert(@RequestBody @Valid PaymentMethodInput paymentMethodInput) {
		PaymentMethod paymentMethod = paymentMethodInputDeconverter.toDomainObject(paymentMethodInput);

		paymentMethod = paymentMethodService.save(paymentMethod);

		return paymentMethodDtoConverter.toModel(paymentMethod);
	}

	@PutMapping("/{paymentMethodId}")
	public PaymentMethodDto update(@PathVariable Long paymentMethodId,
			@RequestBody @Valid PaymentMethodInput paymentMethodInput) {
		PaymentMethod currentPaymentMethod = paymentMethodService.search(paymentMethodId);

		paymentMethodInputDeconverter.copyToDomainObject(paymentMethodInput, currentPaymentMethod);

		currentPaymentMethod = paymentMethodService.save(currentPaymentMethod);

		return paymentMethodDtoConverter.toModel(currentPaymentMethod);
	}

	@DeleteMapping("/{paymentMethodId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long paymentMethodId) {
		paymentMethodService.remove(paymentMethodId);
	}

}
