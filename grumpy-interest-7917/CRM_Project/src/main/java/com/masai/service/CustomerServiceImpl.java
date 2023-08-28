package com.masai.service;

import java.util.List;
import java.util.Scanner;

import com.masai.dao.CustomerDAO;
import com.masai.dao.CustomerDAOImpl;
import com.masai.dao.SupportDAO;
import com.masai.dao.SupportDAOImpl;
import com.masai.entity.Customer;
import com.masai.entity.Issues;
import com.masai.entity.LoggedIn;
import com.masai.entity.Status;
import com.masai.exception.DuplicateDataException;
import com.masai.exception.NoRecordFound;
import com.masai.exception.SomethingWentWrong;

public class CustomerServiceImpl implements CustomerService{

	@Override
	public void addCustomer(Customer customer) throws DuplicateDataException,SomethingWentWrong {
		// TODO Auto-generated method stub
		CustomerDAO dao = new CustomerDAOImpl();
		dao.addCustomer(customer);
	}

	@Override
	public void logIn(String username, String password) throws NoRecordFound {
		
		CustomerDAO dao = new CustomerDAOImpl();
		dao.logIn(username,password);
		
	}

	@Override
	public void raiseIssue(String issue) throws SomethingWentWrong {
		CustomerDAO dao = new CustomerDAOImpl();
		dao.raiseIssue(issue);
		
	}

	@Override
	public void giveFeedback() throws SomethingWentWrong {
		
		Scanner sc = new Scanner(System.in);
		CustomerDAO custDAO = new CustomerDAOImpl();
		
		SupportDAO csrDAO = new SupportDAOImpl();
		
		List<Issues> list = csrDAO.viewIssue();
		
		for(Issues i : list) {
			if(i.getStatus().equals(Status.CLOSED) && i.getCustomer().getId() == LoggedIn.userid) {
				System.out.println("================================================");
				System.out.println("Issue id : "+i.getId()+"  |  "+"Issue : "+ i.getIssue()+"  |  "+"Reply : "+i.getReply());
			}
		}
		System.out.println("================================================");
		System.out.println("Select issue by id to provide feedback");
		int id = sc.nextInt();
		
		System.out.println("Provide your feedback");
		sc.nextLine();
		String feedback = sc.nextLine();
		
		System.out.println("Enter your rating (1 to 5)");
		int rating  = sc.nextInt();
		
		custDAO.giveFeedback(id,feedback,rating);
		
		
	}

	@Override
	public void deleteAccount() throws SomethingWentWrong {
		
		CustomerDAO dao = new CustomerDAOImpl();
		dao.deleteAccount();
		
	}

}
