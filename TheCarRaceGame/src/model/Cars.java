package model;



public class Cars 
{
	private String driverName;
	private int driverDistance;
	

	
	
	public Cars(String driverName, int driverDistance)
		{
			this.driverName = driverName;
			this.driverDistance = driverDistance;		
		}
		
	
	public  void setDriverDistance(int driverDistance) 
		{
			this.driverDistance = driverDistance;		
		}
	
	
	public int getDriverDistance()
		{
			return driverDistance;
		}
	

	public String getDriverName() 
		{
			return driverName;
		}
	

	public void setDriverName(String driverName ) 
		{
			this.driverName = driverName;		
		}

	
	
	
}
	
	
	
	
