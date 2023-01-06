package com.bilgeadam.hibernateornek.join;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
	protected static SessionFactory sessionFactory;

	public static void main(String[] args) {
		
		
		Calisan c = new Calisan();
		Arac a = new Arac();
		a.setAracAdi("bmw 1");
		
		c.setCalisanAdi("İbrahim Gökyar");
		c.setArac(a);
		

	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	try {

		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(a);
		session.save(c);
		session.getTransaction().commit();
		session.close();
	}catch(Exception ex) {
		StandardServiceRegistryBuilder.destroy(registry); 
	}
}
protected void exit() {
	sessionFactory.close();
}
}