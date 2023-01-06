package com.bilgeadam.hibernateornek.join;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="calisan")
public class Calisan {
	
	@Id
	@Column(name = "calisan_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int calisanId;
	
	@Column(name="calisan_adi")
	private String calisanAdi;
	
	@OneToOne
	@JoinColumn(name="arac_id")
	private Arac arac;

	public int getCalisanId() {
		return calisanId;
	}

	public void setCalisanId(int calisanId) {
		this.calisanId = calisanId;
	}

	public String getCalisanAdi() {
		return calisanAdi;
	}

	public void setCalisanAdi(String calisanAdi) {
		this.calisanAdi = calisanAdi;
	}

	public Arac getArac() {
		return arac;
	}

	public void setArac(Arac arac) {
		this.arac = arac;
	}
	
	
	
}
