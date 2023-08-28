package com.masai;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
      Scanner sc = new Scanner(System.in); 
    	int choice;
    	
    	do {
    		System.out.println("*******************************************************");
    		System.out.println("*******************************************************");
    		System.out.println("**            Press 1 Register Customer              **");
        	System.out.println("**            Press 2 Login Customer                 **");
        	System.out.println("**            Press 3 Register Representative        **");
        	System.out.println("**            Press 4 Login Representative           **");
        	System.out.println("**            Press 0 Logout                         **");
        	System.out.println("*******************************************************");
        	System.out.println("*******************************************************");
        	
        	System.out.println("Enter your choice : ");
        	choice = sc.nextInt();
        	
        	switch(choice) {
        	case 1:
        		CustomerUI.addCustomer(sc);
        		break;
        	case 2:
        		CustomerUI.loginCustomer(sc);
        		break;
        	case 3:
        		SupportUI.addCSR(sc);
        		break;
        	case 4: 
        		SupportUI.loginCSR(sc);
        		break;
        	case 0:
        		System.out.println("****************************************************");
        		System.out.println("             Thankyou");
        		System.out.println("****************************************************");
        		break;
        	default:
        		System.out.println("****************************************************");
        		System.out.println("             Invalid choice");
        		System.out.println("****************************************************");
        		
        	}
    	}while(choice!=0);
    }

	
}
