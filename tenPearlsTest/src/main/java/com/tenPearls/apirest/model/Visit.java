package com.tenPearls.apirest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "visit")
public class Visit implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_visit")
	private Long idVisit;

	@Column (name = "city_id")
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "city_id" )
	private Long cityId;
	//private City cityId;

	@NotNull(message = "Sales representative cannot be empty.")
	@Column(name = "id_sale")
	private Long idSale;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	private Date createAt;
	

	@NotNull(message = "Net cannot be empty.")
	@Column(name = "net")
	private Long net;
	
	//Visit total = Net * Visits Percentage
	@Column(name = "visit_total")
	private Long visitTotal;
	
	
	@NotEmpty(message = "Description  cannot be empty.")
	@Column(name = "description")
	private String description;
	
	
	@NotEmpty(message = "The visit need a Client.")
	@Column(name = "client_nit")
	private String clientNit;
	
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}


	public Long getIdVisit() {
		return idVisit;
	}



	public void setIdVisit(Long idVisit) {
		this.idVisit = idVisit;
	}




	public Long getCityId() {
		return cityId;
	}


	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}


	public Date getCreateAt() {
		return createAt;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}



	public Long getNet() {
		return net;
	}


	public void setNet(Long net) {
		this.net = net;
	}


	public Long getVisitTotal() {
		return visitTotal;
	}


	public void setVisitTotal(Long visitTotal) {
		this.visitTotal = visitTotal;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Long getIdSale() {
		return idSale;
	}


	public void setIdSale(Long idSale) {
		this.idSale = idSale;
	}


	public String getClientNit() {
		return clientNit;
	}


	public void setClientNit(String clientNit) {
		this.clientNit = clientNit;
	}
	
	
	
}
