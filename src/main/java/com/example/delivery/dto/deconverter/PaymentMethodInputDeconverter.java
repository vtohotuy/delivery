package com.example.delivery.dto.deconverter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.delivery.domain.model.PaymentMethod;
import com.example.delivery.dto.input.PaymentMethodInput;

@Component
public class PaymentMethodInputDeconverter {

	@Autowired
	private ModelMapper modelMapper;

	public PaymentMethod toDomainObject(PaymentMethodInput paymentMethodInput) {
		return modelMapper.map(paymentMethodInput, PaymentMethod.class);
	}

	public void copyToDomainObject(PaymentMethodInput paymentMethodInput, PaymentMethod paymentMethod) {
		modelMapper.map(paymentMethodInput, paymentMethod);
	}

}
