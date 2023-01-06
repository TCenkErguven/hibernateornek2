package com.bilgeadam.hibernateornek.namedquery;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "member")
@NamedQueries({ 
	@NamedQuery(name = "Member.findAll",query = "from Member"),
	@NamedQuery(name = "Member.findById",query = "from Member where id=:id"),
	@NamedQuery(name = "Member.findByAd",query = "from Member where ad=:ad"),
	@NamedQuery(name = "Member.findBySoyad",query = "from Member where soyad=:soyad")
})
public class Member implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "ad")
	private String ad;
	
	@Column (name = "soyad")
	private String soyad;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	
	
	
}
