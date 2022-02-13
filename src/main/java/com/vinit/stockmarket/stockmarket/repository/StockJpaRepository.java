package com.vinit.stockmarket.stockmarket.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.vinit.stockmarket.stockmarket.model.Stock;

@Repository
@Component
public interface StockJpaRepository extends JpaRepository<Stock, Long> {

	Page<Stock> findByNameContaining(String nameStock, Pageable pageable);
}
