package com.masai.dao;

import java.util.List;

import com.masai.entity.Customer;
import com.masai.entity.Feedback;
import com.masai.entity.Issues;
import com.masai.entity.LoggedIn;
import com.masai.entity.Status;
import com.masai.exception.DuplicateDataException;
import com.masai.exception.NoRecordFound;
import com.masai.exception.SomethingWentWrong;
import com.masai.utility.EMUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class CustomerDAOImpl implements CustomerDAO{

	@Override
	public void addCustomer(Customer customer) throws DuplicateDataException, SomethingWentWrong {
		
		EntityManager em = null;
		
		try {
			em = EMUtils.createConection();
			Query query = em.createQuery(" SELECT Count(c) From Customer c WHERE username = :username");
							query.setParameter("username", customer.getUsername());
			
			
			if((long) query.getSingleResult()>0) {
				throw new DuplicateDataException("************ Customer already exists ************");
			}
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(customer);
			et.commit();
		
		}
		catch(PersistenceException pe) {
			throw new SomethingWentWrong(pe.getMessage());
		}
		finally {
			if(em!=null) {
				em.close();
			}
		}
		
	}

	@Override
	public void logIn(String username, String password) throws NoRecordFound {
		
		EntityManager em = null;
		
		try {
			em = EMUtils.createConection();
			
			Query query = em.createQuery("SELECT c.id FROM Customer c WHERE username = :un AND password = :pw");
			
			query.setParameter("un", username);
			query.setParameter("pw", password);
			
			List<Integer> list = query.getResultList();
			
			if(list.size() == 0) {
				throw new NoRecordFound("************** User not found,Register as new customer **************");
				
			}
			LoggedIn.userid = list.get(0);
		}catch(PersistenceException pe) {
			System.out.println(pe.getMessage());
		}finally {
			if(em!= null) {
				em.close();
			}
		}
		
	}


	@Override
	public void raiseIssue(String issue) throws SomethingWentWrong {
		
		EntityManager em = null;
		
		try {
			em = EMUtils.createConection();
			
			Customer cust = em.find(Customer.class, LoggedIn.userid);
			
			Issues newIssue = new Issues(issue, Status.OPEN,cust,null,null);
			
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(newIssue);
		et.commit();
			
			
		}catch(PersistenceException pe) {
			throw new SomethingWentWrong(pe.getMessage());
		}
		finally {
			if(em!=null) {
				em.close();
			}
		}
		
	}

	
	@Override
	public void giveFeedback(int id, String feedback, int rating) throws SomethingWentWrong {
		
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EMUtils.createConection();
			
			Issues issue = em.find(Issues.class, id);
			Feedback fb = new Feedback(feedback,rating,issue);
			
			et = em.getTransaction();
			
			et.begin();
			em.persist(fb);
			et.commit();
			
			
		}catch(PersistenceException pe) {
			throw new SomethingWentWrong(pe.getMessage());
		}finally {
			if(em!=null) {
				em.close();
			}
		}
		
	}

	@Override
	public void deleteAccount() throws SomethingWentWrong {
		
		EntityManager em = null;
		EntityTransaction et = null;
		
		try {
			em = EMUtils.createConection();
			Customer cust = em.find(Customer.class, LoggedIn.userid);
			
			et = em.getTransaction();
			et.begin();
			em.remove(cust);
			et.commit();
					
			
		}catch(PersistenceException e) {
			throw new SomethingWentWrong(e.getMessage());
		}finally {
			em.close();
		}
		
	}


	

}
