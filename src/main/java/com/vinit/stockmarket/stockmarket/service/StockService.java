package com.vinit.stockmarket.stockmarket.service;

import java.util.List;

import com.vinit.stockmarket.stockmarket.model.Stock;

public interface StockService {
	 List<Stock> getStocks(int page, int limit);
}
