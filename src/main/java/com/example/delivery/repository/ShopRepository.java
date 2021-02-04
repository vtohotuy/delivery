package com.example.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.delivery.domain.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

}
