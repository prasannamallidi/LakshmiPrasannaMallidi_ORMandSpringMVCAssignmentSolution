package com.crm.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Customer;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


@Repository
public class CustomerServiceImpl implements  CustomerService{

	private SessionFactory sessionFactory;
	private org.hibernate.Session session;
	
	@Autowired
	CustomerServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try{
			session = sessionFactory.getCurrentSession();
		}
		catch(HibernateException e) {
			session = sessionFactory.openSession();
		}
	}

	@Transactional
	public List<Customer> findAll() {
		
		Transaction tx = session.beginTransaction();
		List<Customer> customers = session.createQuery("from customer").list();
		tx.commit();
		return customers;
	}

	@Transactional
	public Customer findById(int theId) {
		Customer customer = new Customer();
		Transaction tx = session.beginTransaction();
		customer = session.get(Customer.class, theId);
		tx.commit();
		return customer;
	}

	@Transactional
	public void save(Customer customer) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		tx.commit();
		
	}

	@Transactional
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Customer customer = session.get(Customer.class, theId);
		session.delete(customer);
		tx.commit();
		
	}

}
