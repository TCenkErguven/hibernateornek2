package com.bilgeadam.hibernateornek.namedquery;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
	protected static SessionFactory sessionFactory;
	
	protected void setup() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		
		try {
				sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
				StandardServiceRegistryBuilder.destroy(registry); 
		} 	
	}
	
	public static void main(String[] args) {
		
		Main m = new Main();
		m.setup();
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		Query query = session.getNamedQuery("Member.findAll"); //getNamedQuery ile yazılırsa from Member.findAll yapıcaz
		List liste = query.getResultList();
		System.out.println("Gelen Uye sayısı" + liste.size());
		
		Query queryById = session.getNamedQuery("Member.findById");
		queryById.setParameter("id", 1);
		Member member = (Member) queryById.getSingleResult();
		System.out.println("Member Adı " + member.getAd());
		
		Query queryByAd = session.getNamedQuery("Member.findByAd");
		queryByAd.setParameter("ad", "Uye1 Ad");
		Member memberAd = (Member) queryByAd.getSingleResult();
		System.out.println("Member Adına göre " + memberAd.getAd());
		
		
		
		session.getTransaction().commit();
		m.exit();

	}
	
	
	
	
	protected void exit() {
		sessionFactory.close();
	}

}
