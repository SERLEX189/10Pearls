package com.tenPearls.apirest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sale_representative")
public class SaleRepresentative implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sale_representative")
	private Long idSaleRepresentative;
	
	@Column(name = "name_sale")
	private String nameSsale;


	
	public Long getIdSaleRepresentative() {
		return idSaleRepresentative;
	}

	public void setIdSaleRepresentative(Long idSaleRepresentative) {
		this.idSaleRepresentative = idSaleRepresentative;
	}

	public String getNameSsale() {
		return nameSsale;
	}

	public void setNameSsale(String nameSsale) {
		this.nameSsale = nameSsale;
	}


	

	
}
