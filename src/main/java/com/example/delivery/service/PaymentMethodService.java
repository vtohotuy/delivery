package com.example.delivery.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.delivery.domain.model.PaymentMethod;
import com.example.delivery.repository.PaymentMethodRepository;

@Service
public class PaymentMethodService {

	@Autowired
	private PaymentMethodRepository paymentMethodRepository;

	@Transactional
	public PaymentMethod save(PaymentMethod paymentMethod) {
		return paymentMethodRepository.save(paymentMethod);
	}

	@Transactional
	public void remove(Long paymentMethodId) {
		try {
			paymentMethodRepository.deleteById(paymentMethodId);
			paymentMethodRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			System.out.println(e.getMessage());

		} catch (DataIntegrityViolationException e) {
			System.out.println(e.getMessage());
		}
	}

	public PaymentMethod search(Long paymentMethodId) {
		return paymentMethodRepository.findById(paymentMethodId).get();
	}

}
