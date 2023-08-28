package com.masai;

import java.util.Scanner;

import com.masai.entity.SupportRepresentative;
import com.masai.exception.DuplicateDataException;
import com.masai.exception.NoRecordFound;
import com.masai.exception.SomethingWentWrong;
import com.masai.service.SupportService;
import com.masai.service.SupportServiceImpl;

public class SupportUI {

	public static void addCSR(Scanner sc) {
		
		System.out.println("Enter email");
		String email = sc.next();
		
		System.out.println("Enter username");
		String username = sc.next();
		
		System.out.println("Enter password");
		String password = sc.next();
		
		SupportRepresentative csr = new SupportRepresentative(email, username, password);
		
		SupportService service = new SupportServiceImpl();
		
		try {
			service.addCSR(csr);
			System.out.println("**********************************************");
			System.out.println("  Representative Registered successfully");
		}
		catch(DuplicateDataException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public static void loginCSR(Scanner sc) {
		
		System.out.println("Enter username");
		String username = sc.next();
		
		System.out.println("Enter password");
		String password = sc.next();
		
		SupportService service = new SupportServiceImpl();
		
		try {
			service.logIn(username,password);
			supportMenu(sc);
		}catch(NoRecordFound e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static void supportMenu(Scanner sc) {
		
		
		
		int choice;
		
		do {
			System.out.println("************************************");
			System.out.println("| Press 1  View issue              |");
			System.out.println("| Press 2  Assign Issue            |");
			System.out.println("| Press 3  Reply to Issue          |");
			System.out.println("| Press 4  Close the Issue         |");
			System.out.println("| Press 5  View feedback           |");
			System.out.println("| Perss 6  Delete Account          |");
			System.out.println("| Press 7  Delete Issue            |");
			System.out.println("| Press 0  Logout                  |");
			System.out.println("************************************");
			
			System.out.println("Enter your choice : ");
			choice = sc.nextInt();
			
			
			switch (choice) {
			case 1:
				viewIssue();
				break;
			case 2:
				assignIssue(sc);
				break;
			case 3:
				replyToIssues(sc);
				break;
			case 4:
				closeIssue(sc);
				break;
			case 5:
                viewFeedback();
				break;
			case 6:
				deleteAccount(sc);
				break;
			case 7:
				deleteIssue(sc);
				break;
			case 0:
				System.out.println("************************************");
				System.out.println("       Thankyou, visit again");
				System.out.println("************************************");
				break;
			default:
				System.out.println("*************************************");
				System.out.println("         Invalid input");
				System.out.println("*************************************");
				
			}
			
			
		}while(choice!=0);
		
	}

	private static void deleteIssue(Scanner sc) {
		
		try {
			
			System.out.println("Enter issue id to delete issue");
			int id = sc.nextInt();
			
			SupportService service = new SupportServiceImpl();
			service.deleteIssue(id);
			System.out.println("*******************************************************");
			System.out.println("          Issue is deleted successfully");
			System.out.println("*******************************************************");
		} catch (SomethingWentWrong | NoRecordFound e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}	
		
		
	}

	private static void viewIssue() {
		
		SupportService service = new SupportServiceImpl();
		try {
			System.out.println("*******************************************************");
			service.viewIssue();
			System.out.println("*******************************************************");
		} catch (SomethingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	private static void assignIssue(Scanner sc) {
		
		int choice;
		do {
			System.out.println("*********************************");
			System.out.println("| Press 1  Accept the issue       |");
			System.out.println("| Press 0  Exit form system     |");
			System.out.println("*********************************");
			System.out.println("Enter your choice to assign : ");
			
			
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				assignToSelf();
				break;
			case 2:
				//assignToOther(sc);
				break;
			case 0:
				System.out.println("***************************");
				System.out.println("         Thankyou");
				System.out.println("***************************");
				break;
			default:
				System.out.println("***************************");
				System.out.println("       Invalid input");
				System.out.println("***************************");
				
			}
			
		}while(choice!=0);
		
	}

	private static void assignToSelf() {
		
		SupportService service = new SupportServiceImpl();
		try {
			service.assignToSelf();
			System.out.println("*******************************************************");
			System.out.println("               Issue Accepted Successfully");
			System.out.println("*******************************************************");
		}catch(SomethingWentWrong e) {
			e.getMessage();
		}
		
	}
	
	private static void assignToOther(Scanner sc) {
		System.out.println("Enter id to assign : ");
		int id = sc.nextInt();
		
		SupportService service = new SupportServiceImpl();
		try {
			service.assignToOther(id);
			System.out.println("*******************************************************");
			System.out.println("     Issue assigned to entered id successfully");
			System.out.println("*******************************************************");
		}catch(SomethingWentWrong e) {
			e.getMessage();
		}
		
	}

	private static void replyToIssues(Scanner sc) {
		
		int id = 0;
		SupportService service = new SupportServiceImpl();
		
		try {
			service.replyToIssue(id,sc);
			System.out.println("*******************************************************");
			System.out.println("          Reply to the Issue is Done");
			System.out.println("*******************************************************"); 
		} catch (SomethingWentWrong e) {
			
			e.printStackTrace();
		}
		
	}
	
	private static void closeIssue(Scanner sc) {
		System.out.println("*******************************************************");
		System.out.println("Enter Issue id to Close the Issue");
		int id = sc.nextInt();
		SupportService service = new SupportServiceImpl();
		try {
			service.closeIssue(id);
			System.out.println("*******************************************************");
			System.out.println("                 Issue is closed");
			System.out.println("*******************************************************");
		} catch (SomethingWentWrong e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void viewFeedback() {
		
		SupportService service = new SupportServiceImpl();
		try {
			System.out.println("===================Feedback..........=====================");
			service.viewFeedback();
			System.out.println("================================================");
		} catch (SomethingWentWrong | NoRecordFound e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static void deleteAccount(Scanner sc) {
	

			try {
				SupportService service = new SupportServiceImpl();
				service.deleteAccount();
				System.out.println("*******************************************************");
				System.out.println("          Account Deleted successfully");
				System.out.println("*******************************************************");
			} catch (SomethingWentWrong | NoRecordFound e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}	
		
	}

}
