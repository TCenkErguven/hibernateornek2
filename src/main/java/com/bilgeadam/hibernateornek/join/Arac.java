package com.bilgeadam.hibernateornek.join;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="arac")
public class Arac {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int aracId;
	
	@Column(name="arac_adi")
	private String aracAdi;

	public int getAracId() {
		return aracId;
	}

	public void setAracId(int aracId) {
		this.aracId = aracId;
	}

	public String getAracAdi() {
		return aracAdi;
	}

	public void setAracAdi(String aracAdi) {
		this.aracAdi = aracAdi;
	}
	

	
}
