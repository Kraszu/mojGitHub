package view;


import controller.RaceController;
import model.Cars;


public class RaceView 
{
	private RaceController raceController;
	
	
	public RaceView()
		{
			this.raceController = new RaceController();	
		}
	
	public void startTheRace()
	
		{
			this.raceController.loadFromFile();
			this.raceController.setCarNumbers();
			this.raceController.setDrivers();
			Cars winner = this.raceController.race();
			
				System.out.println();
		    	System.out.println("THE WINNER IS: "+ winner.getDriverName() +" !!!");
				System.out.println("With a stunning race time:"+ raceController.getRaceTime());
				System.out.println("Distance:"+winner.getDriverDistance());
				System.out.println();
				System.out.println("List of all competitors:");
				
			
			for(int b =0; b < raceController.getRacemModel().getCompetitors().size(); b++)
				{
					Cars looser = raceController.getRacemModel().getCompetitors().get(b);
							System.out.println();
							System.out.print("Driver Name: "+looser.getDriverName());
							System.out.println(" Distance: "+looser.getDriverDistance());
				}
				
			this.raceController.saveTheResult();
		
		}
	
}
