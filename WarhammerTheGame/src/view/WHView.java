package view;

import controller.WHController;


import java.util.Scanner;

public class WHView 
{
	
		private WHController WHController;
		static Scanner sc = new Scanner(System.in);
		
		
		public WHView()
			{
				this.WHController = new WHController();
				
			}
		
		public static int chooseSide()
		{
			int option = 0;
		System.out.println("------------------------------------");
		System.out.println("	   Choose a Side				");
		System.out.println("------------------------------------");
		System.out.println("1. Space Marines");
		System.out.println("2. Orks");
		boolean selected = false;
		
		while (selected == false)
		{
			
			System.out.println("Please enter an option");
			try 
			{
				option = sc.nextInt();
				sc.nextLine();
				if ((option == 1) || (option == 2))
					selected = true;
				else
					System.out.println("Sorry but the option must be 1 or 2");
				
			}
			catch (Exception e) 
			{
				System.out.println("Sorry you did not enter a valid option");
				
				sc.next();
			}		
			
		}
				
		return option;
		}
		
		
		
		public void gameLouncher()
		
		{
			
			this.WHController.setPlayerName();
			
			boolean finish = false;
			if (finish == false)
			{
				for (int i = 0; i < 1; i++)
					System.out.println();
				int option = chooseSide();
				switch (option)
				{
				case 1 :
					
					
					System.out.println("Start of Game Turn 1");
					System.out.println("Start of "+controller.WHController.PLAYER_NAME+"'s Turn 1");
					
					System.out.println();
 			this.WHController.setAdiitionalSquadSize();
			this.WHController.AddUnit();
			this.WHController.setAdiitionalSquad2Size();
			this.WHController.AddUnit2();
			this.WHController.setAdiitionalSquad3Size();			
			this.WHController.AddUnit3();
			
			this.WHController.setSmLimit();
			this.WHController.setSmLimit();
			this.WHController.test();
			System.out.println("Start of Game Turn 1");
			System.out.println("Start of "+controller.WHController.PLAYER_NAME+"'s Turn 1");
			this.WHController.setMove();
            this.WHController.shootingPhaseStarterForSM();
            this.WHController.AssaultPhaseStarterForSM();
            this.WHController.endTurnSM();
     
					
					
					
					
			break;
				case 2 :
					
					System.out.println("Start of Game Turn 1");
					System.out.println("Start of "+controller.WHController.PLAYER_NAME+"'s Turn 1");
					
					
					this.WHController.setAdiitionalBoyz();
					this.WHController.AddBoyz();
					this.WHController.setAdiitionalBoyz2();
					this.WHController.AddBoyz2();
					this.WHController.setAdiitionalBoyz3();
					this.WHController.AddBoyz3();
					this.WHController.setOrkLimit();
					this.WHController.setOrkLimit();
					//this.WHController.testOrk();
					System.out.println("Start of Game Turn 1");
					System.out.println("Start of "+controller.WHController.PLAYER_NAME+"'s Turn 1");
					this.WHController.setMove();		           
					this.WHController.shootingPhaseStarterForOrks();
					this.WHController.AssaultPhaseStarterForOrks();
					this.WHController.endTurnOrks();
				
					
					break;
					
				default: 
					finish = true;
					break;
				}
			}
			
		}
		

}
