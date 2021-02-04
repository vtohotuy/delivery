package com.example.delivery.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Shop {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String name;

	@NotNull
	@PositiveOrZero
	@Column(name = "shipping_fee", nullable = false)
	private BigDecimal shippingFee;

	@Embedded
	private Address address;

	private Boolean active = Boolean.TRUE;

	private Boolean open = Boolean.FALSE;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dateRegister;

	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dateUpdate;

	@ManyToMany
	@JoinTable(name = "shop_payment_method", joinColumns = @JoinColumn(name = "shop_id"), inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
	private Set<PaymentMethod> paymentMethods = new HashSet<>();

	public void activate() {
		setActive(true);
	}

	public void deactivate() {
		setActive(false);
	}

	public boolean removePaymentMethod(PaymentMethod paymentMethod) {
		return getPaymentMethods().remove(paymentMethod);
	}

	public boolean insertPaymentMethod(PaymentMethod paymentMethod) {
		return getPaymentMethods().add(paymentMethod);
	}

	public void open() {
		setOpen(true);
	}

	public void close() {
		setOpen(false);
	}

}
