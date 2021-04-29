package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PowerOutage {

	private int id;
	private int nercId;
	private int customers;
	private LocalDateTime da;
	private LocalDateTime a;
	private double durataOre;
	private int anno;
	
	public PowerOutage(int id, int nercId, int customers, LocalDateTime da, LocalDateTime a) {
		this.id = id;
		this.nercId = nercId;
		this.customers = customers;
		this.da = da;
		this.a = a;
		this.durataOre = this.da.until(this.a, ChronoUnit.MINUTES)/60.0;
		this.anno = this.da.getYear();
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

	public double getDurataOre() {
		return durataOre;
	}

	public int getAnno() {
		return anno;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+this.id;
	}
	
}
