package com.masai.service;

import java.util.List;
import java.util.Scanner;

import com.masai.dao.SupportDAO;
import com.masai.dao.SupportDAOImpl;
import com.masai.entity.Feedback;
import com.masai.entity.Issues;
import com.masai.entity.LoggedIn;
import com.masai.entity.Status;
import com.masai.entity.SupportRepresentative;
import com.masai.exception.DuplicateDataException;
import com.masai.exception.NoRecordFound;
import com.masai.exception.SomethingWentWrong;

public class SupportServiceImpl implements SupportService{

	@Override
	public void addCSR(SupportRepresentative csr) throws DuplicateDataException {
		
		SupportDAO dao = new SupportDAOImpl();
		dao.addCSR(csr);
		
	}

	@Override
	public void logIn(String username, String password) throws NoRecordFound {
		
		SupportDAO dao = new SupportDAOImpl();
		dao.logIn(username,password);
		
	}

	@Override
	public void viewStatus() throws SomethingWentWrong {
		
		SupportDAO dao = new SupportDAOImpl();
		List<Issues> list = dao.viewIssue();
		for(Issues i : list) {
			if(i.getCustomer().getId() == LoggedIn.userid) {
				System.out.println(i.getId()+" "+i.getIssue()+" "+i.getStatus());
			}
		}
	}


	@Override
	public void viewIssue() throws SomethingWentWrong{
		
		SupportDAO dao = new SupportDAOImpl();
		
		List<Issues> list = dao.viewIssue();
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		if(list.size()>0) {
			
			
		for(Issues i : list) {
			if(i.getCsr()==null) {
			System.out.println("Issue id : "+i.getId()+ "  |  " + "Customer_id : " + i.getCustomer().getId()+ "  |  "  + "Issue : " + i.getIssue()+ "  |  "  + "Status : " + i.getStatus()+ "  |  "  +"Assigned : "+ "Not Yet");
				
			}
			else {
			System.out.println("Issue id : "+i.getId()+ "  |  "  + "Customer_id : " + i.getCustomer().getId() + "  |  " + "Issue : " + i.getIssue()+ "  |  "  + "Status : " + i.getStatus()+ "  |  "  +"Assigned : "+ i.getCsr().getId());
			
			}
		  }
			
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		
	}

	@Override
	public void assignToSelf() throws SomethingWentWrong {
		
		SupportDAO dao = new SupportDAOImpl();
		dao.assignToSelf();
		
	}

	@Override
	public void assignToOther(int id) throws SomethingWentWrong {
		
		SupportDAO dao = new SupportDAOImpl();
		dao.assignToOther(id);
		
	}

	@Override
	public void replyToIssue(int id, Scanner sc) throws SomethingWentWrong {
		
		SupportDAO dao = new SupportDAOImpl();
		List<Issues> list = dao.viewIssue();
		
		for(Issues i : list) {
			if(i.getCsr() != null && i.getCsr().getId() == LoggedIn.userid && i.getStatus() == Status.OPEN) {
				System.out.println(i.getId() + "  " + i.getIssue());
			}
		}
		
		System.out.println("Entery issue id to reply");
		id = sc.nextInt();
		
		sc.nextLine();
		System.out.println("Enter reply for issue");
		String reply = sc.nextLine();
		
		dao.replyToIssue(id,reply);
		
	}


	@Override
	public void closeIssue(int id) throws SomethingWentWrong {
		
		SupportDAO dao = new SupportDAOImpl();
		List<Issues> list = dao.viewIssue();
		
		for(Issues i: list) {
			if(i.getCsr()!= null && i.getCsr().getId() == LoggedIn.userid && i.getStatus() == Status.OPEN) {
				System.out.println("=====================================================");
				System.out.println(i.getId() + "  "+ i.getIssue());
				System.out.println("=====================================================");
			}
		}
		dao.closeIssue(id);
		
	}

	@Override
	public void viewFeedback() throws SomethingWentWrong,NoRecordFound {
		
		SupportDAO dao = new SupportDAOImpl();
		List<Feedback> list = dao.viewFeedback();
		
//		if(list.size()>0) {
//			for(Feedback i: list) {
//				if(i != null && i.getFeedIssue() != null && i.getFeedIssue().getCsr()!= null && i.getFeedIssue().getCsr().getId() == LoggedIn.userid) {
//					
//					System.out.println("=====================================================");
//					System.out.println("Issue : "+i.getFeedIssue()+" Message : "+i.getMessage());
//					System.out.println("=====================================================");
//					
//				}
//			}
//		}
		
		if(list.size()>0) {
			
			for(Feedback i: list) {
				if(i.getFeedIssue()==null) {
					System.out.println("=====================================================");
					System.out.println("Issue_id : "+ i.getFeedIssue().getId()+" Feedback : "+ i.getMessage());
					System.out.println("=====================================================");
				}
			}
		}
		
		
		
	}

	@Override
	public void deleteAccount() throws SomethingWentWrong, NoRecordFound {
		
		SupportDAO dao = new SupportDAOImpl();
		dao.deleteAccount();
		
	}

	@Override
	public void deleteIssue(int id) throws SomethingWentWrong, NoRecordFound {
		
		SupportDAO dao = new SupportDAOImpl();
		dao.deleteIssue(id);
		
	}

}
