package com.vinit.stockmarket.stockmarket.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


/***
 * 
 * @author pahwa
 *
 */

@Entity
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private BigDecimal currentprice;
	
	public BigDecimal getCurrentprice() {
		return currentprice;
	}

	public void setCurrentprice(BigDecimal currentprice) {
		this.currentprice = currentprice;
	}

	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}

	@NotNull
	private Timestamp lastupdate;

	public Stock() {

	}

	public Stock(long id, String name, BigDecimal currentprice, Timestamp lastupdate) {
		super();
		this.id = id;
		this.name = name;
		this.currentprice = currentprice;
		this.lastupdate = lastupdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
