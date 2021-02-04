package com.example.delivery.dto.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.delivery.domain.model.PaymentMethod;
import com.example.delivery.dto.PaymentMethodDto;


@Component
public class PaymentMethodDtoConverter {

	@Autowired
    private ModelMapper modelMapper;
    
    public PaymentMethodDto toModel(PaymentMethod paymentMethod) {
        return modelMapper.map(paymentMethod, PaymentMethodDto.class);
    }
    
    public List<PaymentMethodDto> toCollectionModel(Collection<PaymentMethod> paymentMethods) {
        return paymentMethods.stream()
                .map(paymentMethod -> toModel(paymentMethod))
                .collect(Collectors.toList());
    }
	
}
