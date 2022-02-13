package com.vinit.stockmarket.stockmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinit.stockmarket.stockmarket.model.Stock;
import com.vinit.stockmarket.stockmarket.repository.StockJpaRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockJpaRepository stockRepository;

	@Override
	public List<Stock> getStocks(int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}


}
