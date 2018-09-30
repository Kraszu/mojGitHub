package model;

import java.util.ArrayList;


public class WHModel 
{
	
	private ArrayList<Squads> SquadMember;
	private ArrayList<Squads> SquadMember2;
	private ArrayList<Squads> SquadMember3;
	private ArrayList<OrkBoyz> Boyz;
	private ArrayList<OrkBoyz> Boyz2;
	private ArrayList<OrkBoyz> Boyz3;
	
		public WHModel()
	{
		this.SquadMember = new ArrayList<Squads>();
		this.SquadMember.add(new Squads("Marine 1", 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		this.SquadMember.add(new Squads("Marine 2", 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		this.SquadMember.add(new Squads("Marine 3", 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		this.SquadMember.add(new Squads("Marine 4", 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		this.SquadMember.add(new Squads("Marine 5", 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		this.SquadMember2 = new ArrayList<Squads>();
		this.SquadMember2.add(new Squads("Marine 1", 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		this.SquadMember2.add(new Squads("Marine 2", 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		this.SquadMember2.add(new Squads("Marine 3", 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		this.SquadMember2.add(new Squads("Marine 4", 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		this.SquadMember2.add(new Squads("Marine 5", 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		this.SquadMember3 = new ArrayList<Squads>();
		this.SquadMember3.add(new Squads("Marine 1", 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		this.SquadMember3.add(new Squads("Marine 2", 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		this.SquadMember3.add(new Squads("Marine 3", 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		this.SquadMember3.add(new Squads("Marine 4", 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		this.SquadMember3.add(new Squads("Marine 5", 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		this.Boyz = new ArrayList<OrkBoyz> ();
		this.Boyz.add(new OrkBoyz( "Ork 1", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz.add(new OrkBoyz( "Ork 2", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz.add(new OrkBoyz( "Ork 3", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz.add(new OrkBoyz( "Ork 4", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz.add(new OrkBoyz( "Ork 5", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz.add(new OrkBoyz( "Ork 6", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz.add(new OrkBoyz( "Ork 7", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz.add(new OrkBoyz( "Ork 8", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz.add(new OrkBoyz( "Ork 9", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz.add(new OrkBoyz( "Ork 10", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz2 = new ArrayList<OrkBoyz> ();
		this.Boyz2.add(new OrkBoyz( "Ork 1", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz2.add(new OrkBoyz( "Ork 2", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz2.add(new OrkBoyz( "Ork 3", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz2.add(new OrkBoyz( "Ork 4", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz2.add(new OrkBoyz( "Ork 5", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz2.add(new OrkBoyz( "Ork 6", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz2.add(new OrkBoyz( "Ork 7", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz2.add(new OrkBoyz( "Ork 8", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz2.add(new OrkBoyz( "Ork 9", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz2.add(new OrkBoyz( "Ork 10", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz3 = new ArrayList<OrkBoyz> ();
		this.Boyz3.add(new OrkBoyz( "Ork 1", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz3.add(new OrkBoyz( "Ork 2", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz3.add(new OrkBoyz( "Ork 3", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz3.add(new OrkBoyz( "Ork 4", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz3.add(new OrkBoyz( "Ork 5", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz3.add(new OrkBoyz( "Ork 6", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz3.add(new OrkBoyz( "Ork 7", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz3.add(new OrkBoyz( "Ork 8", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz3.add(new OrkBoyz( "Ork 9", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		this.Boyz3.add(new OrkBoyz( "Ork 10", 6, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		
	}

	public ArrayList<OrkBoyz> getBoyz() {
		return Boyz;
	}

	public void setBoyz(ArrayList<OrkBoyz> boyz) {
		Boyz = new ArrayList<OrkBoyz> ();
	}

	public ArrayList<OrkBoyz> getBoyz2() {
		return Boyz2;
	}

	public void setBoyz2(ArrayList<OrkBoyz> boyz2) {
		Boyz2 = new ArrayList<OrkBoyz> ();
	}

	public ArrayList<OrkBoyz> getBoyz3() {
		return Boyz3;
	}

	public void setBoyz3(ArrayList<OrkBoyz> boyz3) {
		Boyz3 = new ArrayList<OrkBoyz> ();
	}

	public ArrayList<Squads> getSquadMember3() {
		return SquadMember3;
	}

	public void setSquadMember3(ArrayList<Squads> squadMember3) {
		SquadMember3 = new  ArrayList<Squads>();
	}

	public ArrayList<Squads> getSquadMember2() {
		return SquadMember2;
	}

	public void setSquadMember2(ArrayList<Squads> squadMember2) {
		SquadMember2 = new  ArrayList<Squads>();
	}

	public ArrayList<Squads> getSquadMember() 
	{
		return SquadMember;
	}

	public void setSquadMember() 
	{
		this.SquadMember = new  ArrayList<Squads>();
	}
	
	public void addSquadMemeber(int index,Squads unit)
	{
		this.SquadMember.add(unit);
		
	}
	
	public void addSquadMemeber2(int index,Squads unit)
	{
		this.SquadMember2.add(unit);
		
	}
	public void addSquadMemeber3(int index,Squads unit)
	{
		this.SquadMember3.add(unit);
		
	}
	public void addOrkBoyz(int index, OrkBoyz boyz)
	{
		this.Boyz.add(boyz);
	}
	public void addOrkBoyz2(int index, OrkBoyz boyz)
	{
		this.Boyz2.add(boyz);
	}
	public void addOrkBoyz3(int index, OrkBoyz boyz)
	{
		this.Boyz3.add(boyz);
	}
	
}
