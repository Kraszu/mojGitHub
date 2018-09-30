package model;

public class OrkBoyz {



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


	public OrkBoyz(String UnitName, int j, int k, int l, int m, int n, int o, int p, int q, int r, int u)
		{
			this.UnitName = UnitName;
			this.UnitValue = 7;
			this.WS = 4;
			this.BS = 4;
			this.S = 4;
			this.T = 4;
			this.W = 1;
			this.I = 4;
			this.A = 1;
			this.LD = 8;
			this.Sv = 3;
		}


	public String getUnitName() 
	{
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
		return UnitName ;
		
	}
}
