package com.vinit.stockmarket.stockmarket.exception.handler;

public class StockNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockNotFoundException(String message) {
        super(message);
    }
}
