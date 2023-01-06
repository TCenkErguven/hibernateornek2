package com.bilgeadam.hibernateornek;


import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.ArrayList;
import java.util.List;

public class KitapManager {
	protected SessionFactory sessionFactory;
	
	protected void setup() {
		
		//hibernate.cfg.xml dosyasındaki konfigürsayona bak, ne yapman gerektiğin gör
		
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
	
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory(); //Default olarak hibernate.cfg.xml içinde ararw
		}catch(Exception ex) {
			StandardServiceRegistryBuilder.destroy(registry); 
			//yaptığım değişikliklerle alakalı bir sorun yaşanırsan bunları RAM'den kaldır.
		}
	}
	protected void exit() {
		sessionFactory.close();
	}
	
	public void KayitEkle() 
	{
		Kitap kitap = new Kitap();
		kitap.setBaslik("");
		kitap.setYazar("");
		kitap.setFiyat(3);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(kitap); //insert into kitap("başlik","Yazar","Price") values("Kitap1","İbrahim",33);
		session.getTransaction().commit();
		session.close();
	}
	
	public void KayitEkle(String baslik,String yazar,int fiyat) 
	{
		Kitap kitap = new Kitap();
		kitap.setBaslik(baslik);
		kitap.setYazar(yazar);
		kitap.setFiyat(fiyat);
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(kitap); //insert into kitap("başlik","Yazar","Price") values("Kitap1","İbrahim",33);
			session.getTransaction().commit();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			session.getTransaction().rollback();
		}
		session.close();
	}
	
	public void KayitEkle(Kitap kitap) 
	{
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(kitap); //insert into kitap("başlik","Yazar","Price") values("Kitap1","İbrahim",33);
		session.getTransaction().commit(); //bir sorun oluşursa yapılan işlemler geri alıyor, bu satır ok'Lenmesse SQL'de işlemler uygulanmıyor. Save metoduyla alakalı sorun çıktıysa
		session.close();
	}
	
	public void KayitGetir() {
		Session session = sessionFactory.openSession();
		long kitapId = 1;
		Kitap kitap = session.get(Kitap.class, kitapId);
		System.out.println("Başlık : " + kitap.getBaslik());
		System.out.println("Fiyat : " + kitap.getFiyat());
	}
	
	public List<Kitap> listeGetir() {
		
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM Kitap ");
		List<Kitap> kitapListesi = query.getResultList();
		return kitapListesi;
		/*
		for(Kitap kitap : kitapListesi)
		{
			System.out.println(kitap.getBaslik());
		}
		*/
	}
	
	public void ListGetir() {

		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM Kitap ");
		List<Kitap> kitapListesi = query.getResultList();
		
		for(Kitap kitap : kitapListesi)
		{
			System.out.println(kitap.getBaslik());
		}
		
	}
	
	public void Sayfalama() {
		Session session = sessionFactory.openSession();
		String hql = "FROM Kitap";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(5); //Kaç tane alacağı
		@SuppressWarnings("unchecked")
		List<Kitap> sonucListesi = query.getResultList();
		for(Kitap kitap: sonucListesi)
		{
			System.out.println(kitap.getBaslik());
		}
	}
	
	public void update() {
		Kitap kitap = new Kitap();
		kitap.setId(1);
		kitap.setBaslik("Yeni Kitap");
		kitap.setYazar("Yeni Yazar");
		kitap.setFiyat(150);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(kitap); //update Kitap set Baslik="Yeni Kitap",Yazar="Yeni Yazar",Fiyat=150 where id : 1
		session.getTransaction().commit();
		session.close();
	}
	
	public void ParametreliGetir()
	{
		Session session = sessionFactory.openSession();
		String hql = "FROM Kitap where baslik = :baslik";
		Query query = session.createQuery(hql);
		query.setParameter("baslik", "Kitap2");
		List liste = query.getResultList();
		System.out.println("kayıt sayısı " + liste.size());
	}
	
	public void AramayaGoreGetir(String aranacakKelime)
	{
		Session session = sessionFactory.openSession();
		String hql = "FROM Kitap where baslik like :baslik";
		Query query = session.createQuery(hql);
		query.setParameter("baslik", "%"+aranacakKelime+"%");
		List liste = query.getResultList();
		System.out.println("arama sonuç sayısı " + liste.size());
	}
	
	
	
	
	
	public static void main(String[] args)
	{
		KitapManager manager = new KitapManager();
		manager.setup();
	//	manager.update();
	//	manager.listeGetir();
	//	manager.KayitEkle();
	//	manager.Sayfalama();
	//	manager.KayitGetir();
		manager.ParametreliGetir();
		manager.exit();
	}
}
