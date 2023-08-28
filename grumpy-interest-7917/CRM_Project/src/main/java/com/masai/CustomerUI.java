package com.masai;

import java.util.Scanner;

import com.masai.entity.Customer;
import com.masai.exception.DuplicateDataException;
import com.masai.exception.NoRecordFound;
import com.masai.exception.SomethingWentWrong;
import com.masai.service.CustomerService;
import com.masai.service.CustomerServiceImpl;
import com.masai.service.SupportService;
import com.masai.service.SupportServiceImpl;

public class CustomerUI {

	public static void addCustomer(Scanner sc) {
		
		System.out.println("Enter email");
		String email = sc.next();
		
		System.out.println("Enter username");
		String username = sc.next();
		
		System.out.println("Enter password");
		String password = sc.next();
		
		Customer customer = new Customer(email, username, password);
		CustomerService service = new CustomerServiceImpl();
		
		try {
			service.addCustomer(customer);
			System.out.println("=====================================================");
			System.out.println("        Customer registered successfully");
			System.out.println("=====================================================");
		} catch (DuplicateDataException | SomethingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
	}

	public static void loginCustomer(Scanner sc) {
		
		System.out.println("Enter username");
		String username = sc.next();
		
		System.out.println("Enter password");
		String password = sc.next();
		
		CustomerService service = new CustomerServiceImpl();
		
		try {
			service.logIn(username,password);
			customerMenu(sc);
		} catch (NoRecordFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	private static void customerMenu(Scanner sc) {
		// TODO Auto-generated method stub
		
		int choice;
		do {
			System.out.println("=====================================================");
			System.out.println("| Press 1 to Raise Issue                            |");
			System.out.println("| Press 2 to view issue status                      |");
			System.out.println("| Press 3 to provide feedback                       |");
			System.out.println("| Press 4 to delete account                         |");
			System.out.println("| Press 0 to log_out                                |");
			System.out.println("=====================================================");
			
			System.out.println("Enter yuour choice as customer : ");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				raiseIssue(sc);
				break;
			case 2:
				viewStatus();
				break;
			case 3:
				giveFeedback();
				break;
			case 4:
				deleteAccount(sc);
				break;
			case 0:
				System.out.println("=====================================================");
        		System.out.println("              Thankyou visit again");
        		System.out.println("=====================================================");
        		break;
        	default:
        		System.out.println("*****************************************************");
        		System.out.println("     Please enter valid choice");
        		System.out.println("*****************************************************");
			}
		}while(choice!=0);
	}

	private static void giveFeedback() {
		
		CustomerService service = new CustomerServiceImpl();
		
		try {
			service.giveFeedback();
			System.out.println("=====================================================");
			System.out.println("                 Fedback is recorded");
			System.out.println("=====================================================");
		} catch (SomethingWentWrong e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
	}

	private static void viewStatus() {
		
		SupportService service = new SupportServiceImpl();
		try {
			service.viewStatus();
			System.out.println("============Issue status================");
		} catch (SomethingWentWrong e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void raiseIssue(Scanner sc) {
		
		System.out.println("Enter issue");
		sc.nextLine();
		String issue = sc.nextLine();
		
		CustomerService service = new CustomerServiceImpl();
		
		try {
			service.raiseIssue(issue);
			System.out.println("===========Issue is raised===============");
		} catch (SomethingWentWrong e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
	}

	private static void deleteAccount(Scanner sc) {
		
	
			try {
				CustomerService service = new CustomerServiceImpl();
				service.deleteAccount();
				System.out.println("=====================================================");
				System.out.println("         Account is deleted successfully");
				System.out.println("=====================================================");
			}catch(SomethingWentWrong e) {
				e.getMessage();
			}
		}
		
		
	}
	
	

