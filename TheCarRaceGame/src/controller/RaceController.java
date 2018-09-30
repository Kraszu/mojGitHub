package controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import model.Cars;
import model.RaceModel;



	public class RaceController 
	{
		private Scanner in;
		private RaceModel racemModel;
		
		private int numOfDrivers;
		private int raceTime;
		
		
		public RaceController()
			{
				in = new Scanner (System.in);
				this.racemModel = new RaceModel();
				
				this.numOfDrivers = 0;
				this.raceTime = 0;				
			}
		
		public RaceModel getRacemModel() 
			{
				return racemModel;
			}
		
		public int getRaceTime()
			{
				return raceTime;
			}
		
		
		public void setCarNumbers()
			{
				System.out.println("Please enter the number of cars..");
				this.numOfDrivers = in.nextInt();
				in.nextLine();
				this.racemModel.setCompetitors();
			}
		
		public void setDrivers()
			{
				for (int w=0; w < this.numOfDrivers; w++)
				{
					this.racemModel.addCompetitor(w, new Cars(setDriverName(w),0));
				}
			}
		
		public String setDriverName (int w)
			{
				System.out.println("Please enter the name of the driver "+(w+1));
				String driverName;
				driverName = in.nextLine();
				return driverName;
			}
		

		
		public Cars race()
			{
				while (this.raceTime <= 10000)
				{
					for(int x = 0; x < this.racemModel.getCompetitors().size();x++)
						{
							Cars car = this.racemModel.getCompetitors().get(x);
							car.setDriverDistance(car.getDriverDistance() + randomInt(50,1));
							this.raceTime++;
							if (car.getDriverDistance()>= 10000)
								{
									return car;
								}
							
						}
				}
				return null;
			}
		
		
		public static int randomInt(int Min, int Max)
	       {
			return(int) (Math.random()*(Max - Min) +Min);
	            
	       }
		

		public void saveTheResult()
			{
				try
				{
					File resultFile = new File ("C:\\Temp\\Result.txt");
						if (!resultFile.exists())
							{
								resultFile.createNewFile();
							}
						
						FileWriter resultWriter = new FileWriter(resultFile);
						Calendar raceDate = Calendar.getInstance();
						DateFormat formatter = new SimpleDateFormat("dd/MM/YYYY HH:mm");
						String dateOfTheLastRace = formatter.format(raceDate.getTime());
						resultWriter.write("the race results from" + " " + dateOfTheLastRace+ " are: " ); 

						for(Cars car : this.racemModel.getCompetitors())
							{
								resultWriter.write("\n");
								resultWriter.write(car.getDriverName()+ " " + car.getDriverDistance());
								
							}
						resultWriter.close();
						
				}
				catch(Exception ex)
					{
						System.out.println(ex.getMessage());
					}
			}
		
		
		public void loadFromFile()
			{
				try
					{
						File resultFile = new File ("C:\\Temp\\Result.txt");
						if (!resultFile.exists())
							{
								resultFile.createNewFile();
							}
						FileReader resultReader = new FileReader(resultFile);
						BufferedReader buffReader = new BufferedReader(resultReader);
						
						String lineFromFile = buffReader.readLine();
						
						while(lineFromFile !=null)
							{						
								
								System.out.println(lineFromFile);
								
							lineFromFile = buffReader.readLine();
							}
						buffReader.close();
					}
				
					catch(Exception ex)
						{
							System.out.println(ex.getMessage());
						}
			}
}

