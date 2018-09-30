package model;

import java.util.ArrayList;



public class RaceModel 
{
	
	private ArrayList<Cars> competitors;
	
	
	
	
	public RaceModel()
		{
			this.competitors = new ArrayList<Cars>();		
		}	


	public ArrayList<Cars> getCompetitors() 
		{
			return competitors;
		}


	public void setCompetitors() 
		{
			this.competitors = new ArrayList<Cars>();
		}
	
	
	
	public void addCompetitor(int index, Cars car)
		{
			this.competitors.add(car);
		}
	
}
