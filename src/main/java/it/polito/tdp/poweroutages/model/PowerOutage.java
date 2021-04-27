package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;

public class PowerOutage {

	private int id;
	private int nercId;
	private int customers;
	private LocalDateTime da;
	private LocalDateTime a;
	
	public PowerOutage(int id, int nercId, int customers, LocalDateTime da, LocalDateTime a) {
		this.id = id;
		this.nercId = nercId;
		this.customers = customers;
		this.da = da;
		this.a = a;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNercId() {
		return nercId;
	}

	public void setNercId(int nercId) {
		this.nercId = nercId;
	}

	public int getCustomers() {
		return customers;
	}

	public void setCustomers(int customers) {
		this.customers = customers;
	}

	public LocalDateTime getDa() {
		return da;
	}

	public void setDa(LocalDateTime da) {
		this.da = da;
	}

	public LocalDateTime getA() {
		return a;
	}

	public void setA(LocalDateTime a) {
		this.a = a;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+this.id;
	}
	
}
