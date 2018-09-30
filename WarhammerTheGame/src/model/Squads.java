package model;

public class Squads 
{
	private String UnitName;
	private int UnitValue;
	private int WS; // Weapon Skill
	private int BS; // Ballistic Skill
	private int S; // Strength
	private int T; //Toughness
	private int W; // Wounds
	private int I; // Initiative
	private int A; // Attacks 
	private int LD; // Leadership
	private int Sv; // Saving 


	public Squads(String UnitName,int UnitValue, int WS, int BS, int S, int T, int W, int I, int A, int LD, int Sv )
		{
			this.UnitName = UnitName;
			this.UnitValue = UnitValue;
			this.WS = WS;
			this.BS = BS;
			this.S = S;
			this.T = T;
			this.W = W;
			this.I = I;
			this.A = A;
			this.LD = LD;
			this.Sv = Sv;
			
		}


	public String getUnitName() {
		return UnitName;
	}


	public void setUnitName(String unitName) {
		this.UnitName = unitName;
	}


	public int getUnitValue() {
		return UnitValue;
	}


	public void setUnitValue(int unitValue) {
		this.UnitValue = unitValue;
	}


	public int getWS() {
		return WS;
	}


	public void setWS(int wS) {
		this.WS = wS;
	}


	public int getBS() {
		return BS;
	}


	public void setBS(int bS) {
		this.BS = bS;
	}


	public int getS() {
		return S;
	}


	public void setS(int s) {
		this.S = s;
	}


	public int getT() {
		return T;
	}


	public void setT(int t) {
		this.T = t;
	}


	public int getW() {
		return W;
	}


	public void setW(int w) {
		this.W = w;
	}


	public int getI() {
		return I;
	}


	public void setI(int i) {
		this.I = i;
	}


	public int getA() {
		return A;
	}


	public void setA(int a) {
		this.A = a;
	}


	public int getLD() {
		return LD;
	}


	public void setLD(int lD) {
		this.LD = lD;
	}


	public int getSv() {
		return Sv;
	}


	public void setSv(int sv) {
		this.Sv = sv;
	}
	public String toString()
	{
		return UnitName +" " + this.A;
		
	}

}