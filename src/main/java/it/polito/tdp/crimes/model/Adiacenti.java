package it.polito.tdp.crimes.model;

public class Adiacenti {

	private String r1;
	private String r2;
	private Double peso;
	
	public Adiacenti(String r1, String r2, double peso) {
		super();
		this.r1 = r1;
		this.r2 = r2;
		this.peso = peso;
	}
	
	public String getR1() {
		return r1;
	}
	
	public void setR1(String r1) {
		this.r1 = r1;
	}
	
	public String getR2() {
		return r2;
	}
	
	public void setR2(String r2) {
		this.r2 = r2;
	}
	
	public Double getPeso() {
		return peso;
	}
	
	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return r1 + " +++ " + r2;
	}
	
	
}
