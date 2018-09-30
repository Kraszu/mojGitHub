package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.studentController;

public class StudentsView {
	//access to controller 
		private studentController sController;
		// open scanner
		static Scanner in = new Scanner (System.in);
		// constructor
		public StudentsView(){
			// this.sController connect this class with controller where is the program "engine"
			this.sController = new studentController();
		
		}
		
		
		// method menu provides interaction with user
		
		public static int menuOption()
		{
			int option = 0;
		System.out.println("------------------------------------");
		System.out.println("	   Plese select Option			");
		System.out.println("------------------------------------");
		System.out.println("1. Add Student");
		System.out.println("2. Remove Student");
		System.out.println("3. Display all Students with grades");
		System.out.println("4. Exit");
		boolean selected = false;
		
		while (selected == false)// loop will continue until user would pick one of the 4 available options
		{
			
			
			try // by using try and catch i want to eliminate threat of incorrect input
			{
				option = in.nextInt();
				in.nextLine();
				if ((option == 1) || (option == 2) || (option == 3))
					selected = true;
				else if ((option == 4))
					System.out.println("Goodbye!");
				
				else
					System.out.println("Sorry but you have to choose an option between 1 and 4");
				
				
			}
			catch (InputMismatchException e) 
			{
				System.out.println("Sorry you did not enter a valid option");
				
				in.next();
			}		
			
		}
				
		return option;
		}
		// "board control" for the program 
		public void louncher(){
			boolean finish = false;
			if (finish == false){
				int option = menuOption();
				//using the switch method i deal with each case depends of user choice
				switch (option){
		
				case 1:				
					this.sController.process();
					break;
				case 2:
					this.sController.processRmStudent();
					break;
				case 3:
					this.sController.markRangeGrade();
					break;
				case 4:
					// case 4 terminate the program
					System.exit(0);
				default: 
					finish = true;
					break;
				}
				louncher();
			}
		}
}
