package com.masai.dao;

import java.util.List;
import java.util.Scanner;

import com.masai.App;
import com.masai.entity.Feedback;
import com.masai.entity.Issues;
import com.masai.entity.LoggedIn;
import com.masai.entity.Status;
import com.masai.entity.SupportRepresentative;
import com.masai.exception.DuplicateDataException;
import com.masai.exception.NoRecordFound;
import com.masai.exception.SomethingWentWrong;
import com.masai.service.SupportService;
import com.masai.service.SupportServiceImpl;
import com.masai.utility.EMUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class SupportDAOImpl implements SupportDAO{

	@Override
	public void addCSR(SupportRepresentative csr) throws DuplicateDataException {
		
		EntityManager em = null;
		
		try {
			em = EMUtils.createConection();
			
			Query query = em.createQuery("SELECT Count(c) FROM SupportRepresentative c WHERE username = :username");
			query.setParameter("username", csr.getUsername());
			
			if((long) query.getSingleResult()>0) {
				throw new DuplicateDataException("***************** Account already exits **************");
			}
			
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(csr);
			et.commit();
		}catch(PersistenceException e) {
			throw new DuplicateDataException(e.getMessage());
		}
		finally {
			em.close();
		}
		
		
	}

	@Override
	public void logIn(String username, String password) throws NoRecordFound {
		EntityManager em = null;
		
		try{
			
			em = EMUtils.createConection();
			
			Query query = em.createQuery("SELECT c.id FROM SupportRepresentative c WHERE username = :un AND password = :pw");
			query.setParameter("un", username);
			query.setParameter("pw", password);
			
			List<Integer> list = query.getResultList();
			
			if(list.size()==0) {
				throw new NoRecordFound("***************** Account does not exists *******************");
			}
			
			LoggedIn.userid = list.get(0);
			
			
		}catch(PersistenceException pe) {
			throw new NoRecordFound(pe.getMessage());
		}finally {
			if(em!=null) {
				em.close();
			}
		}
		
		
	}

	@Override
	public List<Issues> viewIssue() throws SomethingWentWrong {
		
		EntityManager em = null;
		List<Issues> list = null;
		
		try {
			em = EMUtils.createConection();
			
			Query query = em.createQuery("SELECT c FROM Issues c");
			
			list = query.getResultList();
			
			if(list.size()==0) {
				System.out.println("**************** Issue list is empty *************");
			}
			return list;
			
		}catch(PersistenceException pe) {
			System.out.println(pe.getMessage());
		}
		finally {
			if(em != null) {
				em.close();
			}
		}
		
		return list;
	}

	@Override
	public void assignToSelf() throws SomethingWentWrong {
		
		EntityManager em = null;
		EntityTransaction et = null;
		Scanner sc = new Scanner(System.in);
		try {
			em = EMUtils.createConection();
			
			SupportRepresentative csr = em.find(SupportRepresentative.class,LoggedIn.userid);
			SupportService service = new SupportServiceImpl();
			
			service.viewIssue();
			
			System.out.println("Enter Issue_id to be assigned");
			int id = sc.nextInt();
			
			Issues issue = em.find(Issues.class, id);
			
			if(issue != null) {
				System.out.println();
				et = em.getTransaction();
				
				et.begin();
				issue.setCsr(csr);
				et.commit();
			}
			
		}catch(PersistenceException pe) {
			throw new SomethingWentWrong(pe.getMessage());
		}
		finally {
			if(em != null) {
				em.close();
			}
		}
	}

	@Override
	public void assignToOther(int id) throws SomethingWentWrong {
		
		EntityManager em = null;
		EntityTransaction et = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			em = EMUtils.createConection();
			
			SupportRepresentative csr = em.find(SupportRepresentative.class,LoggedIn.userid);
			SupportService service = new SupportServiceImpl();
			
			service.viewIssue();
			Issues issue = em.find(Issues.class, id);
			
			if(issue != null) {
				System.out.println();
				et = em.getTransaction();
				
				et.begin();
				issue.setCsr(csr);
				et.commit();
			}
			
			
			
		}catch(PersistenceException e) {
			throw new SomethingWentWrong(e.getMessage());
		}finally {
			em.close();
		}
		
	}

	
	@Override
	public void replyToIssue(int id, String reply) throws SomethingWentWrong {
		
		EntityManager em = null;
		EntityTransaction et = null;
		
		
		try {
			em = EMUtils.createConection();
			
			Issues issue = em.find(Issues.class, id);
			if(issue!= null) {
				System.out.println();
				et = em.getTransaction();
				
				et.begin();
				issue.setReply(reply);
				et.commit();
			}
			
		}catch(PersistenceException e) {
			throw new SomethingWentWrong(e.getMessage());
		}finally {
			em.close();
		}
		
	}


	@Override
	public void closeIssue(int id) throws SomethingWentWrong {
	
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			
			em = EMUtils.createConection();
			
			Issues issue = em.find(Issues.class, id);
			
			if(issue != null) {
				System.out.println();
				et = em.getTransaction();
				
				et.begin();
				issue.setStatus(Status.CLOSED);
				et.commit();
			}
			
			
		}catch(PersistenceException pe) {
			throw new SomethingWentWrong(pe.getMessage());
		}finally {
			if(em!=null) {
				em.close();
			}
		}
		
	}


	@Override
	public List<Feedback> viewFeedback() throws SomethingWentWrong, NoRecordFound{
		
		EntityManager em = null;
		EntityTransaction et = null;
		
		List<Feedback> list = null;
		
		try {
			
			em = EMUtils.createConection();
			Query query = em.createQuery("SELECT c From Feedback c");
			
			list = query.getResultList();
			
			if(list.size()==0) {
				throw new NoRecordFound("*************** Feedback not available ********************");
			}
//			return list;
		}catch(PersistenceException pe) {
			throw new SomethingWentWrong(pe.getMessage());
		}finally {
			if(em != null) {
				em.close();
			}
		}
		
		
		return list;
	}

	@Override
	public void deleteAccount() throws SomethingWentWrong, NoRecordFound {
		
		EntityManager em = null;
		EntityTransaction et = null;
		
		try {
			
			em = EMUtils.createConection();
			SupportRepresentative sp = em.find(SupportRepresentative.class, LoggedIn.userid); 
			
			et = em.getTransaction();
			et.begin();
			em.remove(sp);
			et.commit();
			
			
		}catch(PersistenceException e) {
			throw new SomethingWentWrong(e.getMessage());
		}
		finally {
			em.close();
		}
		
		
	}


	@Override
	public void deleteIssue(int id) throws SomethingWentWrong, NoRecordFound {
		
		EntityManager em = null;
		EntityTransaction et = null;
		
		try {
			
			em = EMUtils.createConection();
			Issues issue = em.find(Issues.class, id); 
			
			if(issue!=null) {
				et = em.getTransaction();
				et.begin();
				em.remove(issue);
				et.commit();
			}else {
				throw new NoRecordFound("Issue is not found for given id");
			}
			
			
		}catch(PersistenceException e) {
			throw new SomethingWentWrong(e.getMessage());
		}
		finally {
			em.close();
		}
	}

	
	



}
