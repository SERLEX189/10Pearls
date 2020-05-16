package com.tenPearls.apirest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "client")

public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "nit")
	private String nit;
	
	
	@NotEmpty(message = "Full name cannot be empty.")
	@Column(name = "full_name")
	private String fullName;
	
	@NotEmpty(message = "Address cannot be empty.")
	@Column(name = "address")
	private String address;
	
	@NotEmpty(message = "Phone cannot be empty.")
	@Column(name = "phone")
	private String phone;
	
	@NotNull(message = "Credit limit cannot be empty.")
	@Min(0)
	@Column(name = "credit_limit")
	private Long creditLimit;
	
	@Column(name = "available_credit")
	private Long availableCredit;
	

	@NotNull(message = "Visits percentage cannot be empty.")
	@Min(0)
	@Column(name = "visits_percentage")
	private Long visitsPercentage;
	
	@NotNull(message = "City cannot be empty.")
	@Min(0)
	@Column(name = "city_id")
	private Long city_id;
	

	@NotNull(message = "State cannot be empty.")
	@Min(0)
	@Column(name = "client_id_sate")
	private Long clientIdState;
	
	
	@NotNull(message = "Country cannot be empty.")
	@Min(0)
	@Column(name = "client_id_Country")
	private Long clientIdCountry;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "client_nit")
	private List<Visit> visitClients;
	

	public Client() {
		this.visitClients = new ArrayList<Visit>();
	}
	
	public void addVisitClient(Visit visit) {
		this.visitClients.add(visit);
	}
	
	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Long creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Long getAvailableCredit() {
		return availableCredit;
	}

	public void setAvailableCredit(Long availableCredit) {
		this.availableCredit = availableCredit;
	}

	public Long getVisitsPercentage() {
		return visitsPercentage;
	}

	public void setVisitsPercentage(Long visitsPercentage) {
		this.visitsPercentage = visitsPercentage;
	}



	public Long getCity_id() {
		return city_id;
	}

	public void setCity_id(Long city_id) {
		this.city_id = city_id;
	}

	public Long getClientIdState() {
		return clientIdState;
	}

	public void setClientIdState(Long clientIdState) {
		this.clientIdState = clientIdState;
	}

	public Long getClientIdCountry() {
		return clientIdCountry;
	}

	public void setClientIdCountry(Long clientIdCountry) {
		this.clientIdCountry = clientIdCountry;
	}

	public List<Visit> getVisitClients() {
		return visitClients;
	}

	public void setVisitClients(List<Visit> visitClients) {
		this.visitClients = visitClients;
	}
	
	
	
}
