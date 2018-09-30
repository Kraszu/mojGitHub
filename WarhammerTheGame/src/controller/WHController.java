package controller;

import java.util.Random;
import java.util.Scanner;

import model.OrkBoyz;
import model.Squads;
import model.WHModel;

public class WHController {
	public static String PLAYER_NAME = "";
	private static final int MAX_POINT = 400;

	private Scanner in;
	private WHModel warhammer;
	private int adiitionalsquadSize;
	private int additionalSquad2Size;
	private int additionalSquad3Size;
	private int additionalBoyz;
	private int additionalBoyz2;
	private int additionalBoyz3;
	private int[] distance = { 36 };
	private int move;
	private int SmLimit;
	private int orkLimit;

	public WHController() {
		in = new Scanner(System.in);
		this.warhammer = new WHModel();
		this.adiitionalsquadSize = 0;
		this.additionalSquad2Size = 0;
		this.additionalSquad3Size = 0;
		this.additionalBoyz = 0;
		this.additionalBoyz2 = 0;
		this.additionalBoyz3 = 0;
		this.SmLimit = 0;
		this.orkLimit = 0;
		this.move = 0;
	}

	public int getOrkLimit() {
		return orkLimit;
	}

	public String setPlayerName() {
		System.out.println("Please enter your name...");
		return PLAYER_NAME = in.next();
	}

	public WHModel getWHModel() {
		return warhammer;
	}

	// DIE ROLL
	public int dieRoll() {
		return (int) (Math.random() * 6) + 1;
	}

	public void setOrkLimit() {

		int sum4 = 0;
		int sum5 = 0;
		int sum6 = 0;
		int orkSum = 0;

		for (OrkBoyz orks1 : this.warhammer.getBoyz()) {
			sum4 = +orks1.getUnitValue() * (this.warhammer.getBoyz().size());
		}

		for (OrkBoyz orks2 : this.warhammer.getBoyz2()) {
			sum5 = +orks2.getUnitValue() * (this.warhammer.getBoyz2().size());
		}

		for (OrkBoyz orks3 : this.warhammer.getBoyz3()) {
			sum6 = +orks3.getUnitValue() * (this.warhammer.getBoyz3().size());
		}

		orkSum = +sum4 + sum5 + sum6;
		this.orkLimit = +MAX_POINT - orkSum;

		if (this.SmLimit <= 0) {
			for (int z = 0; z < this.warhammer.getBoyz3().size(); z++)
				deadOkrsGroup3();
			System.out.println(
					"Sorry you heave reach the points limit, you can not add so many units. Please try diferrent qty ");
			System.out.println("\nPress any key to continue");
			in.nextLine();
			setAdiitionalBoyz3();
			AddBoyz3();
		}
	}

	public void setOrkLimit_Auto() {

		int sum4 = 0;
		int sum5 = 0;
		int sum6 = 0;
		int orkSum = 0;

		for (OrkBoyz orks1 : this.warhammer.getBoyz()) {
			sum4 = +orks1.getUnitValue() * (this.warhammer.getBoyz().size());
		}

		for (OrkBoyz orks2 : this.warhammer.getBoyz2()) {
			sum5 = +orks2.getUnitValue() * (this.warhammer.getBoyz2().size());
		}

		for (OrkBoyz orks3 : this.warhammer.getBoyz3()) {
			sum6 = +orks3.getUnitValue() * (this.warhammer.getBoyz3().size());
		}

		orkSum = +sum4 + sum5 + sum6;
		this.orkLimit = +MAX_POINT - orkSum;

		if (this.SmLimit <= 0) {
			for (int z = 0; z < this.warhammer.getBoyz3().size(); z++)
				deadOkrsGroup3();

			setAdiitionalBoyz3_Auto();
			AddBoyz3();
		}

	}

	public void setSmLimit() {
		int sum1 = 0;
		int sum2 = 0;
		int sum3 = 0;
		int smSum = 0;
		for (Squads squad1 : this.warhammer.getSquadMember()) {
			sum1 = +squad1.getUnitValue() * (this.warhammer.getSquadMember().size());
		}

		for (Squads squad2 : this.warhammer.getSquadMember2()) {
			sum2 = +squad2.getUnitValue() * (this.warhammer.getSquadMember2().size());
		}

		for (Squads squad3 : this.warhammer.getSquadMember3()) {
			sum3 = +squad3.getUnitValue() * (this.warhammer.getSquadMember3().size());
		}
		smSum = +sum1 + sum2 + sum3;

		this.SmLimit = +MAX_POINT - smSum;

		if (this.SmLimit <= 0) {
			for (int z = 0; z < this.warhammer.getSquadMember3().size(); z++)
				unitToRemoveSquadGamma();

			System.out.println(
					"Sorry you heave reach the points limit, you can not add so many units. Please try diferrent qty ");
			System.out.println("\nPress any key to continue");
			in.nextLine();
			setAdiitionalSquad3Size();
			AddUnit3();
		}

		if (smSum == 0) {

		}
	}

	public void setSmLimit_Auto() {
		int sum1 = 0;
		int sum2 = 0;
		int sum3 = 0;
		int smSum = 0;
		for (Squads squad1 : this.warhammer.getSquadMember()) {
			sum1 = +squad1.getUnitValue() * (this.warhammer.getSquadMember().size());
		}

		for (Squads squad2 : this.warhammer.getSquadMember2()) {
			sum2 = +squad2.getUnitValue() * (this.warhammer.getSquadMember2().size());
		}

		for (Squads squad3 : this.warhammer.getSquadMember3()) {
			sum3 = +squad3.getUnitValue() * (this.warhammer.getSquadMember3().size());
		}
		smSum = +sum1 + sum2 + sum3;

		this.SmLimit = +MAX_POINT - smSum;

		if (this.SmLimit <= 0) {
			for (int z = 0; z < this.warhammer.getSquadMember3().size(); z++)
				unitToRemoveSquadGamma();

			setAdiitionalSquad3Size();
			AddUnit3();
		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------------------------------
	/// ADDING MARINES TO SQUADS
	public int setAdiitionalSquadSize()

	{

		int yesno = 0;
		System.out.println("------------------------------------------------");
		System.out.println(" Do you whish add Marines to your Alfa Squad?	");
		System.out.println("------------------------------------------------");
		System.out.println("1. Yes");
		System.out.println("2. No");
		boolean selected = false;

		while (selected == false) {

			try {

				yesno = in.nextInt();
				in.nextLine();
				if ((yesno == 1) || (yesno == 2))
					selected = true;
				else
					System.out.println("Sorry but the option must be 1 or 2");

			} catch (Exception e) {
				System.out.println("Sorry you did not enter a valid option");
				in.next();
			}

		}

		if (yesno == 1)

		{

			System.out.println("You can add from 1 to 5 Marines");

			this.adiitionalsquadSize = in.nextInt();
			in.nextLine();

			try {
				if ((this.adiitionalsquadSize == 1)) {
					System.out.println("One Marine was added to your Alfa Squad");
					System.out.println();

				} else if ((this.adiitionalsquadSize >= 0) && (this.adiitionalsquadSize <= 5)
						&& (this.adiitionalsquadSize != 1)) {
					System.out.println("you added " + this.adiitionalsquadSize + " Marines to your Alfa Squad");
					System.out.println();

				} else {

					System.out.println("Sorry but the option must be 0..5");
					System.out.println();
					setAdiitionalSquadSize();
				}

			} catch (Exception e) {
				in.next();
				System.out.println("Sorry you did not enter a valid option");
				System.out.println();

			}
		}

		return adiitionalsquadSize;
	}

	public void AddUnit() {

		for (int w = 0; w < adiitionalsquadSize; w++) {
			this.warhammer.addSquadMemeber(w, new Squads(setUnitName(w), 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		}
	}

	public String setUnitName(int w)

	{
		String unitName = "Marine" + (5 + w + 1);

		return unitName;
	}

	public int getAdiitionalSquadSize() {
		return adiitionalsquadSize;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public int setAdiitionalSquad2Size()

	{

		int yesno = 0;
		System.out.println("------------------------------------------------");
		System.out.println(" Do you whish add Marines to your Beta Squad?	");
		System.out.println("------------------------------------------------");
		System.out.println("1. Yes");
		System.out.println("2. No");
		boolean selected = false;

		while (selected == false) {

			try {

				yesno = in.nextInt();
				in.nextLine();
				if ((yesno == 1) || (yesno == 2))
					selected = true;
				else
					System.out.println("Sorry but the option must be 1 or 2");

			} catch (Exception e) {
				System.out.println("Sorry you did not enter a valid option");
				in.next();
			}

		}

		if (yesno == 1)

		{

			System.out.println("You can add from 1 to 5 Marines");

			this.additionalSquad2Size = in.nextInt();
			in.nextLine();

			try {
				if ((this.getAdiitionalSquad2Size() == 1)) {
					System.out.println("One Marine was added to your Beta Squad");
					System.out.println();
				} else if ((this.additionalSquad2Size >= 0) && (this.additionalSquad2Size <= 5)
						&& (this.additionalSquad2Size != 1)) {
					System.out.println("you added " + this.additionalSquad2Size + " Marines to your Beta Squad");
					System.out.println();
				} else {
					System.out.println("Sorry but the option must be 0..5");
					System.out.println();
					setAdiitionalSquad2Size();
				}

			} catch (Exception e) {
				System.out.println("Sorry you did not enter a valid option");
				System.out.println();
				in.next();
			}
		}

		return this.additionalSquad2Size;
	}

	public void AddUnit2() {

		for (int w = 0; w < this.additionalSquad2Size; w++) {
			this.warhammer.addSquadMemeber2(w, new Squads(setUnitName2(w), 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		}
	}

	public String setUnitName2(int w)

	{
		String unitName = "Marine" + (5 + w + 1);

		return unitName;

	}

	public int getAdiitionalSquad2Size() {
		return additionalSquad2Size;
	}
	// ------------------------------------------------------------------------------------------------------------------------------------

	public int setAdiitionalSquad3Size()

	{

		int yesno = 0;
		System.out.println("------------------------------------------------");
		System.out.println(" Do you whish add Marines to your Gamma Squad?	");
		System.out.println("------------------------------------------------");
		System.out.println("1. Yes");
		System.out.println("2. No");
		boolean selected = false;

		while (selected == false) {

			try {

				yesno = in.nextInt();
				in.nextLine();
				if ((yesno == 1) || (yesno == 2))
					selected = true;
				else
					System.out.println("Sorry but the option must be 1 or 2");

			} catch (Exception e) {
				System.out.println("Sorry you did not enter a valid option");
				in.next();
			}

		}

		if (yesno == 1)

		{

			System.out.println("You can add from 1 to 5 Marines");

			this.additionalSquad3Size = in.nextInt();
			in.nextLine();

			try {
				if ((this.getAdiitionalSquad3Size() == 1)) {
					System.out.println("One Marine was added to your Gamma Squad");
					System.out.println();
				} else if ((this.additionalSquad3Size >= 0) && (this.additionalSquad3Size <= 5)
						&& (this.additionalSquad3Size != 1)) {
					System.out.println("you added " + this.additionalSquad3Size + " Marines to your Gamma Squad");
					System.out.println();
				} else {
					System.out.println("Sorry but the option must be 0..5");
					System.out.println();
					setAdiitionalSquad3Size();

				}

			} catch (Exception e) {
				System.out.println("Sorry you did not enter a valid option");
				System.out.println();
				in.next();

			}
		}

		return this.additionalSquad3Size;
	}

	public void AddUnit3() {

		for (int w = 0; w < this.additionalSquad3Size; w++) {
			this.warhammer.addSquadMemeber3(w, new Squads(setUnitName2(w), 14, 4, 4, 4, 4, 1, 4, 1, 8, 3));
		}
	}

	public String setUnitName3(int w)

	{
		String unitName = "Marine" + (5 + w + 1);

		return unitName;

	}

	public int getAdiitionalSquad3Size() {
		return additionalSquad3Size;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------------------------------
	/// ADDING ORK BOYZ
	public int setAdiitionalBoyz()

	{

		int yesno = 0;
		System.out.println("-------------------------------------------------");
		System.out.println("Do you whish add more orks to your first OrkBoyz?");
		System.out.println("-------------------------------------------------");
		System.out.println("1. Yes");
		System.out.println("2. No");
		boolean selected = false;

		while (selected == false) {

			try {

				yesno = in.nextInt();
				in.nextLine();
				if ((yesno == 1) || (yesno == 2))
					selected = true;
				else
					System.out.println("Sorry but the option must be 1 or 2");

			} catch (Exception e) {
				System.out.println("Sorry you did not enter a valid option");
				in.next();
			}

		}

		if (yesno == 1)

		{
			System.out.println("You can add from 1 to 10 Boyz");

			this.additionalBoyz = in.nextInt();
			in.nextLine();

			try {
				if ((this.additionalBoyz == 1)) {
					System.out.println("One Ork was added to your first OrkBoyz");
					System.out.println();

				} else if ((this.additionalBoyz >= 0) && (this.additionalBoyz <= 10) && (this.additionalBoyz != 1)) {
					System.out.println("you added " + this.additionalBoyz + " Ork to your first OrkBoyz");
					System.out.println();

				} else {
					System.out.println("Sorry but the option must be 0..10");
					System.out.println();
					setAdiitionalBoyz();
				}

			} catch (Exception e) {
				System.out.println("Sorry you did not enter a valid option");
				System.out.println();
				in.next();

			}
		}

		return additionalBoyz;
	}

	public void AddBoyz() {

		for (int w = 0; w < additionalBoyz; w++) {
			this.warhammer.addOrkBoyz(w, new OrkBoyz(setOrkName(w), 7, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		}
	}

	public String setOrkName(int w)

	{
		String unitName = "Ork" + (10 + w + 1);

		return unitName;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public int setAdiitionalBoyz2()

	{

		int yesno = 0;
		System.out.println("--------------------------------------------------");
		System.out.println("Do you whish add more orks to your second OrkBoyz?");
		System.out.println("--------------------------------------------------");
		System.out.println("1. Yes");
		System.out.println("2. No");
		boolean selected = false;

		while (selected == false) {

			try {

				yesno = in.nextInt();
				in.nextLine();
				if ((yesno == 1) || (yesno == 2))
					selected = true;
				else
					System.out.println("Sorry but the option must be 1 or 2");

			} catch (Exception e) {
				System.out.println("Sorry you did not enter a valid option");
				in.next();
			}

		}

		if (yesno == 1)

		{
			System.out.println("You can add from 1 to 10 Boyz");

			this.additionalBoyz2 = in.nextInt();
			in.nextLine();

			try {
				if ((this.additionalBoyz2 == 1)) {
					System.out.println("One Ork was added to your second OrkBoyz");
					System.out.println();

				} else if ((this.additionalBoyz2 >= 0) && (this.additionalBoyz2 <= 10) && (this.additionalBoyz2 != 1)) {
					System.out.println("you added " + this.additionalBoyz2 + " Ork to your second OrkBoyz");
					System.out.println();

				} else {
					System.out.println("Sorry but the option must be 0..10");
					System.out.println();
					setAdiitionalBoyz2();
				}

			} catch (Exception e) {
				System.out.println("Sorry you did not enter a valid option");
				System.out.println();
				in.next();

			}
		}

		return additionalBoyz2;
	}

	public void AddBoyz2() {

		for (int w = 0; w < additionalBoyz2; w++) {
			this.warhammer.addOrkBoyz2(w, new OrkBoyz(setOrkName2(w), 7, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		}
	}

	public String setOrkName2(int w)

	{
		String unitName = "Ork" + (10 + w + 1);

		return unitName;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public int setAdiitionalBoyz3()

	{

		int yesno = 0;
		System.out.println("-------------------------------------------------");
		System.out.println("Do you whish add more orks to your third OrkBoyz?");
		System.out.println("-------------------------------------------------");
		System.out.println("1. Yes");
		System.out.println("2. No");
		boolean selected = false;

		while (selected == false) {

			try {

				yesno = in.nextInt();
				in.nextLine();
				if ((yesno == 1) || (yesno == 2))
					selected = true;
				else
					System.out.println("Sorry but the option must be 1 or 2");

			} catch (Exception e) {
				System.out.println("Sorry you did not enter a valid option");
				in.next();
			}

		}

		if (yesno == 1)

		{
			System.out.println("You can add from 1 to 10 Boyz");

			this.additionalBoyz3 = in.nextInt();
			in.nextLine();

			try {
				if ((this.additionalBoyz3 == 1)) {
					System.out.println("One Ork was added to your third OrkBoyz");
					System.out.println();

				} else if ((this.additionalBoyz3 >= 0) && (this.additionalBoyz3 <= 10) && (this.additionalBoyz3 != 1)) {
					System.out.println("you added " + this.additionalBoyz3 + " Ork to your third OrkBoyz");
					System.out.println();

				} else {
					System.out.println("Sorry but the option must be 0..10");
					System.out.println();
					setAdiitionalBoyz3();
				}

			} catch (Exception e) {
				System.out.println("Sorry you did not enter a valid option");
				System.out.println();
				in.next();
			}
		}

		return additionalBoyz3;
	}

	public void AddBoyz3() {

		for (int w = 0; w < additionalBoyz3; w++) {
			this.warhammer.addOrkBoyz3(w, new OrkBoyz(setOrkName3(w), 7, 4, 2, 3, 4, 1, 3, 2, 7, 6));
		}
	}

	public String setOrkName3(int w)

	{
		String unitName = "Ork" + (10 + w + 1);

		return unitName;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------------------------------
	public void test() {

		System.out.println(this.warhammer.getSquadMember());
		System.out.println(this.warhammer.getSquadMember2());
		System.out.println(this.warhammer.getSquadMember3());
	}

	public void testOrk() {
		System.out.println(this.warhammer.getBoyz());
		System.out.println(this.warhammer.getBoyz2());
		System.out.println(this.warhammer.getBoyz3());
	}
	// ------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------------------------------
	/// SHOOTING PHASE

	public int selectTarget() {
		int target = 0;

		System.out.println("1. first Orks band");
		System.out.println("2. second Orks band");
		System.out.println("3. third Orks band");
		boolean selected = false;

		while (selected == false) {

			System.out.println("Please enter an option");
			try {
				target = in.nextInt();
				in.nextLine();
				if ((target == 1) || (target == 2) || (target == 3))
					selected = true;
				else
					System.out.println("Sorry but the option must be 1 or 2 or 3");

			} catch (Exception e) {
				System.out.println("Sorry you did not enter a valid option");
				in.next();
			}
		}

		return target;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------

	public boolean chartToHit() {
		boolean hit = true;
		int roll = dieRoll();

		if (roll != 1) {

			if (roll >= 3)

			{
				hit = true;

				System.out.println(roll + " shot hit the target");
			} else {
				hit = false;
				System.out.println(roll + " shot miss the target");
			}

		} else {
			hit = false;
			System.out.println(roll + " shot miss the target");
		}

		return hit;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public boolean toWound()

	{
		boolean wound = true;
		int roll1 = dieRoll();

		if (chartToHit() == true) {
			if (roll1 >= 4)

			{
				wound = true;
				System.out.println(roll1 + " shot wound the target");
			} else {
				wound = false;
				System.out.println(roll1 + " shot did not wound the target");
			}

		} else {
			wound = false;
			System.out.println(roll1 + " shot miss the target");
		}
		return wound;

	}
	// ------------------------------------------------------------------------------------------------------------------------------------

	public void shootingPhaseStarterForSM() // rozwiazac problem dystansu dla
											// indywidualnych jednostek
	{
		for (int g = 0; g < distance.length; g++) {
			if (distance[g] <= 24)

				toSave();

			else if (distance[g] > 24)
				System.out.println("enemy is out of the shooting range!!");

		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void toSave()

	{

		boolean finish = false;
		System.out.println("--------------------------------------------------------");
		System.out.println(" Which greenskin band you want Alfa Squad to deal with? ");
		System.out.println("--------------------------------------------------------");
		{
			if (finish == false) {
				for (int i = 0; i < 1; i++)
					System.out.println();
				int target = selectTarget();

				int roll2 = dieRoll();
				// for (int z=0; z < this.warhammer.getSquadMember().size();z++)
				switch (target) {
				case 1: {
					if (deadOkrsGroup1() == false) {
						selectTarget();
					} else {

						for (int z = 0; z < this.warhammer.getSquadMember().size(); z++)

							if (toWound() == true) {
								if (roll2 >= 6) {
									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup1();

								}

							} else {
								System.out.println(" no effect");
							}

					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------

				case 2: {

					if (deadOkrsGroup2() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember().size(); z++)
							if (toWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup2();
								}

							} else {

								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------
				case 3: {
					if (deadOkrsGroup3() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember().size(); z++)
							if (toWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup3();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				default:
					finish = true;
					break;
				}
			}
		}
		// ------------------------------------------------------------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------------------------------------------------------------

		System.out.println("--------------------------------------------------------");
		System.out.println(" Which greenskin band you want Beta Squad to deal with? ");
		System.out.println("--------------------------------------------------------");
		{
			if (finish == false) {
				for (int i = 0; i < 1; i++)
					System.out.println();
				int target = selectTarget();

				int roll2 = dieRoll();
				// for (int z=0; z <
				// this.warhammer.getSquadMember2().size();z++)
				switch (target) {
				case 1: {
					if (deadOkrsGroup1() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember2().size(); z++)
							if (toWound() == true) {
								if (roll2 >= 6) {
									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup1();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------

				case 2: {
					if (deadOkrsGroup2() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember2().size(); z++)
							if (toWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup2();
								}

							} else {

								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------
				case 3: {
					if (deadOkrsGroup3() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember2().size(); z++)
							if (toWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup3();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				default:
					finish = true;
					break;
				}
			}
		}
		// ------------------------------------------------------------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------------------------------------------------------------
		System.out.println("---------------------------------------------------------");
		System.out.println(" Which greenskin band you want Gamma Squad to deal with? ");
		System.out.println("---------------------------------------------------------");

		{
			if (finish == false) {
				for (int i = 0; i < 1; i++)
					System.out.println();
				int target = selectTarget();

				int roll2 = dieRoll();
				// for (int z=0; z <
				// this.warhammer.getSquadMember3().size();z++)
				switch (target) {
				case 1: {
					if (deadOkrsGroup1() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember3().size(); z++)
							if (toWound() == true) {
								if (roll2 >= 6) {
									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup1();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------

				case 2: {
					if (deadOkrsGroup2() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember3().size(); z++)
							if (toWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup2();
								}

							} else {

								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------
				case 3: {
					if (deadOkrsGroup3() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember3().size(); z++)
							if (toWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup3();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				default:
					finish = true;
					break;
				}
			}
		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------------------------------

	public int selectTargetForOrk() {
		int targetForOrk = 0;

		System.out.println("1. Hummies Alfa");
		System.out.println("2. Hummies Beta");
		System.out.println("3. Hummies Gamma");
		boolean selected = false;

		while (selected == false) {

			System.out.println("Please enter an option");
			try {
				targetForOrk = in.nextInt();
				in.nextLine();
				if ((targetForOrk == 1) || (targetForOrk == 2) || (targetForOrk == 3))
					selected = true;
				else
					System.out.println("Sorry but the option must be 1 or 2 or 3");

			} catch (Exception e) {
				System.out.println("Sorry you did not enter a valid option");
				in.next();
			}
		}

		return targetForOrk;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------

	public boolean ToHitOrk() {
		boolean hit = true;
		int roll = dieRoll();

		if (roll != 1) {

			if (roll >= 5)

			{
				hit = true;

				System.out.println(roll + " shot hit the target");
			} else {
				hit = false;
				System.out.println(roll + " shot miss the target");
			}

		} else {
			hit = false;
			System.out.println(roll + " shot miss the target");
		}

		return hit;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public boolean toWoundOrk()

	{
		boolean wound = true;
		int roll1 = dieRoll();

		if (ToHitOrk() == true) {
			if (roll1 >= 4)

			{
				wound = true;
				System.out.println(roll1 + " shot wound the target");
			} else {
				wound = false;
				System.out.println(roll1 + " shot did not wound the target");
			}

		} else {
			wound = false;
			System.out.println(roll1 + " shot miss the target");
		}
		return wound;

	}
	// ------------------------------------------------------------------------------------------------------------------------------------

	public void shootingPhaseStarterForOrks() {
		for (int g = 0; g < distance.length; g++) {
			if (distance[g] <= 24)

				toSaveOrk();

			else if (distance[g] > 24)
				System.out.println("enemy is out of the shooting range!!");

			// setMove();
		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void toSaveOrk()

	{
		boolean finish = false;

		System.out.println("---------------------------------------------------------");
		System.out.println("   Which hummies you want us firs Boyz to crush he?      ");
		System.out.println("---------------------------------------------------------");

		if (finish == false) {
			for (int i = 0; i < 1; i++)
				System.out.println();
			int targetForOrk = selectTargetForOrk();

			int roll2 = dieRoll();
			// for (int z=0; z < this.warhammer.getBoyz().size()*2;z++)// two
			// attacks for Orks
			switch (targetForOrk)

			{
			case 1: {
				if (unitToRemoveSquadAlfa() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadAlfa();

							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 2: {
				if (unitToRemoveSquadBeta() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadBeta();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 3: {

				if (unitToRemoveSquadGamma() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadGamma();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}
			default:
				finish = true;
				break;
			}
		}

		System.out.println("---------------------------------------------------------");
		System.out.println("   Which hummies you want us second Boyz to crush hmmm?  ");
		System.out.println("---------------------------------------------------------");
		if (finish == false) {
			for (int i = 0; i < 1; i++)
				System.out.println();
			int targetForOrk = selectTargetForOrk();

			int roll2 = dieRoll();
			// for (int z=0; z < this.warhammer.getBoyz2().size()*2;z++)// two
			// attacks for Orks
			switch (targetForOrk)

			{
			case 1: {
				if (unitToRemoveSquadAlfa() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz2().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadAlfa();

							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 2: {
				if (unitToRemoveSquadBeta() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz2().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadBeta();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 3: {
				if (unitToRemoveSquadGamma() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz2().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadGamma();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}
			default:
				finish = true;
				break;
			}
		}
		System.out.println("---------------------------------------------------------");
		System.out.println("   Which hummies you want us third Boyz to crush like?   ");
		System.out.println("---------------------------------------------------------");

		if (finish == false) {
			for (int i = 0; i < 1; i++)
				System.out.println();
			int targetForOrk = selectTargetForOrk();

			int roll2 = dieRoll();
			// for (int z=0; z < this.warhammer.getBoyz3().size()*2;z++)// two
			// attacks for Orks
			switch (targetForOrk)

			{
			case 1: {
				if (unitToRemoveSquadAlfa() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz3().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadAlfa();

							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 2: {
				if (unitToRemoveSquadBeta() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz3().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadBeta();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 3: {
				if (unitToRemoveSquadGamma() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz3().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadGamma();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}
			default:
				finish = true;
				break;
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	//// MARINES CASULTIES //

	public boolean unitToRemoveSquadAlfa() {
		boolean casulties = false;
		int UnitToRemove = -1;
		for (Squads squads : this.warhammer.getSquadMember()) {
			{
				UnitToRemove = this.warhammer.getSquadMember().indexOf(squads);
			}
		}
		if (UnitToRemove != -1) {
			this.warhammer.getSquadMember().remove(UnitToRemove);
			casulties = true;
		} else {

			System.out.println("");
			System.out.println("Boss therez no hummie left to kill!");
			System.out.println("");
			casulties = false;
		}
		return casulties;

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public boolean unitToRemoveSquadBeta() {
		boolean casulties = false;
		int UnitToRemove = -1;
		for (Squads squads : this.warhammer.getSquadMember2()) {
			{
				UnitToRemove = this.warhammer.getSquadMember2().indexOf(squads);
			}
		}
		if (UnitToRemove != -1) {
			this.warhammer.getSquadMember2().remove(UnitToRemove);
			casulties = true;
		} else {
			System.out.println("");
			System.out.println("uhh, they'r all broken Boss!");
			System.out.println("");
			casulties = false;
		}
		return casulties;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public boolean unitToRemoveSquadGamma() {
		boolean casulties = false;
		int UnitToRemove = -1;
		for (Squads squads : this.warhammer.getSquadMember3()) {
			{
				UnitToRemove = this.warhammer.getSquadMember3().indexOf(squads);
			}
		}
		if (UnitToRemove != -1) {
			this.warhammer.getSquadMember3().remove(UnitToRemove);
			casulties = true;
		} else {
			System.out.println("");
			System.out.println("izz no fair! no more hummies to play!");
			System.out.println("");
			casulties = false;
		}
		return casulties;
	}
	// ------------------------------------------------------------------------------------------------------------------------------------
	/// ORK CASULTIES

	public boolean deadOkrsGroup1() {
		boolean casulties = false;
		int UnitToRemove = -1;
		for (OrkBoyz boyz : this.warhammer.getBoyz()) {
			{
				UnitToRemove = this.warhammer.getBoyz().indexOf(boyz);
			}
		}
		if (UnitToRemove != -1) {
			this.warhammer.getBoyz().remove(UnitToRemove);
			casulties = true;
		} else {
			System.out.println("");
			System.out.println("they all dead Sir!");
			System.out.println("");
			casulties = false;
		}
		return casulties;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public boolean deadOkrsGroup2() {
		boolean casulties = false;
		int UnitToRemove = -1;
		for (OrkBoyz boyz : this.warhammer.getBoyz2()) {
			{
				UnitToRemove = this.warhammer.getBoyz2().indexOf(boyz);
			}
		}
		if (UnitToRemove != -1) {
			this.warhammer.getBoyz2().remove(UnitToRemove);
			casulties = true;
		} else {
			System.out.println("");
			System.out.println("they done Sir!");
			System.out.println("");
			casulties = false;
		}
		return casulties;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public boolean deadOkrsGroup3() {
		boolean casulties = false;
		int UnitToRemove = -1;
		for (OrkBoyz boyz : this.warhammer.getBoyz3()) {
			{
				UnitToRemove = this.warhammer.getBoyz3().indexOf(boyz);
			}
		}
		if (UnitToRemove != -1) {
			this.warhammer.getBoyz3().remove(UnitToRemove);
			casulties = true;
		} else {
			System.out.println("");
			System.out.println("Job done Master" + PLAYER_NAME);
			System.out.println("");
			casulties = false;
		}
		return casulties;
	}
	// ------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------------------------------
	// MOVEMENT PHASE
	public int setMove() {
		for (int g = 0; g < distance.length; g++)
			if (distance[g] > 0)

			{
				System.out.println("please enter how far you wish to move. please choose between 0 and 6");

				this.move = in.nextInt();
				in.nextLine();
				try {

					if ((this.move >= 0) && (this.move <= 6)) {
						if ((distance[g] <= 0) || (distance[g] == 1)) {
							System.out.println("to close to move");
							System.out.println();
							setDistance();
						} else {
							System.out.println("You have moved " + this.move + "  towards your enemy");
							System.out.println();
							setDistance();
						}

					} else {
						System.out.println("Sorry but the option must be 0..6");
						System.out.println();
						setMove();
					}

				} catch (Exception e) {
					System.out.println("Sorry you did not enter a valid option");
					System.out.println();
					in.next();
				}
			}

			else {
				setDistance();
			}
		return move;

	}

	public int getMove() {
		return move;

	}
	// ------------------------------------------------------------------------------------------------------------------------------------

	public int[] getDistance() {
		return distance;
	}

	public int[] setDistance() {
		int result = 0;
		for (int a = 0; a < distance.length; a++) {

			result = distance[a] - this.move;
			if (result <= 0) {
				System.out.println("Sorry but you can not move that far");
				setMove();
				if (distance[a] == 1) {
					System.out.println("You are to close to move");
				}
			} else {
				distance[a] = +result;
				System.out.println("distance between your troops and enemy is: " + distance[a]);
			}
		}

		return distance;
	}

	// ASSAULT PHASE
	public void AssaultPhaseStarterForSM() {
		for (int g = 0; g < distance.length; g++) {
			if (distance[g] <= 6) {

				int charge = 0;
				System.out.println("------------------------------------------------");
				System.out.println(" Enemy is in the range! Do you whish to charge?	");
				System.out.println("------------------------------------------------");
				System.out.println("1. Yes");
				System.out.println("2. No");
				boolean selected = false;

				while (selected == false) {

					try {

						charge = in.nextInt();
						in.nextLine();
						if ((charge == 1) || (charge == 2))
							selected = true;
						else
							System.out.println("Sorry but the option must be 1 or 2");

					} catch (Exception e) {
						System.out.println("Sorry you did not enter a valid option");
						in.next();
					}

				}

				if (charge == 1)
					assaulSave();
			}

			else if (distance[g] > 6)
				System.out.println("enemy is out of the assault range!!");

			// setMove();
		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public boolean assaultToHitToAll() {
		boolean assaulthit = true;
		int roll = dieRoll();

		if (roll != 1) {

			if (roll >= 4)

			{
				assaulthit = true;

				System.out.println(roll + " shot hit the target");
			} else {
				assaulthit = false;
				System.out.println(roll + " shot miss the target");
			}

		} else {
			assaulthit = false;
			System.out.println(roll + " shot miss the target");
		}

		return assaulthit;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public boolean assaultWound()

	{
		boolean assaultwound = true;
		int roll1 = dieRoll();

		if (assaultToHitToAll() == true) {
			if (roll1 >= 4)

			{
				assaultwound = true;
				System.out.println(roll1 + " shot wound the target");
			} else {
				assaultwound = false;
				System.out.println(roll1 + " shot did not wound the target");
			}

		} else {
			assaultwound = false;
			System.out.println(roll1 + " shot miss the target");
		}
		return assaultwound;

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void assaulSave()

	{

		boolean finish = false;
		System.out.println("--------------------------------------------------------");
		System.out.println(" Which greenskin band you want Alfa Squad to deal with? ");
		System.out.println("--------------------------------------------------------");
		{
			if (finish == false) {
				for (int i = 0; i < 1; i++)
					System.out.println();
				int target = selectTarget();

				int roll2 = dieRoll();
				// for (int z=0; z < this.warhammer.getSquadMember().size();z++)
				switch (target) {
				case 1: {
					if (deadOkrsGroup1() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6) {
									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup1();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------

				case 2: {
					if (deadOkrsGroup2() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup2();
								}

							} else {

								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------
				case 3: {
					if (deadOkrsGroup3() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup3();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				default:
					finish = true;
					break;
				}
			}
		}
		// ------------------------------------------------------------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------------------------------------------------------------

		System.out.println("--------------------------------------------------------");
		System.out.println(" Which greenskin band you want Beta Squad to deal with? ");
		System.out.println("--------------------------------------------------------");
		{
			if (finish == false) {
				for (int i = 0; i < 1; i++)
					System.out.println();
				int target = selectTarget();

				int roll2 = dieRoll();
				// for (int z=0; z <
				// this.warhammer.getSquadMember2().size();z++)
				switch (target) {
				case 1: {
					if (deadOkrsGroup1() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember2().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6) {
									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup1();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------

				case 2: {
					if (deadOkrsGroup2() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember2().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup2();
								}

							} else {

								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------
				case 3: {
					if (deadOkrsGroup3() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember2().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup3();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				default:
					finish = true;
					break;
				}
			}
		}
		// ------------------------------------------------------------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------------------------------------------------------------
		System.out.println("---------------------------------------------------------");
		System.out.println(" Which greenskin band you want Gamma Squad to deal with? ");
		System.out.println("---------------------------------------------------------");

		{
			if (finish == false) {
				for (int i = 0; i < 1; i++)
					System.out.println();
				int target = selectTarget();

				int roll2 = dieRoll();
				// for (int z=0; z <
				// this.warhammer.getSquadMember3().size();z++)
				switch (target) {
				case 1: {
					if (deadOkrsGroup1() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember3().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6) {
									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup1();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------

				case 2: {
					if (deadOkrsGroup2() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember3().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup2();
								}

							} else {

								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------
				case 3: {
					if (deadOkrsGroup3() == false) {
						selectTarget();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember3().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup3();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				default:
					finish = true;
					break;
				}
			}
		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void AssaultPhaseStarterForOrks() {
		for (int g = 0; g < distance.length; g++) {
			if (distance[g] <= 6) {
				int charge = 0;
				System.out.println("------------------------------------------------");
				System.out.println(" Enemy is in the range! Do you whish to charge?	");
				System.out.println("------------------------------------------------");
				System.out.println("1. Yes");
				System.out.println("2. No");
				boolean selected = false;

				while (selected == false) {

					try {

						charge = in.nextInt();
						in.nextLine();
						if ((charge == 1) || (charge == 2))
							selected = true;
						else
							System.out.println("Sorry but the option must be 1 or 2");

					} catch (Exception e) {
						System.out.println("Sorry you did not enter a valid option");
						in.next();
					}

				}

				if (charge == 1)
					assaultSaveOrk();

			}

			else if (distance[g] > 6)
				System.out.println("enemy is out of the assault range!!");

			// setMove();
		}

	}

	public void assaultSaveOrk()

	{
		boolean finish = false;

		System.out.println("---------------------------------------------------------");
		System.out.println("   Which hummies you want us firs Boyz to crush he?      ");
		System.out.println("---------------------------------------------------------");

		if (finish == false) {
			for (int i = 0; i < 1; i++)
				System.out.println();
			int targetForOrk = selectTargetForOrk();

			int roll2 = dieRoll();
			// for (int z=0; z < this.warhammer.getBoyz().size()*2;z++)// two
			// attacks for Orks
			switch (targetForOrk)

			{
			case 1: {
				if (unitToRemoveSquadAlfa() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadAlfa();

							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 2: {
				if (unitToRemoveSquadBeta() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadBeta();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 3: {
				if (unitToRemoveSquadGamma() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadGamma();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}
			default:
				finish = true;
				break;
			}
		}

		System.out.println("---------------------------------------------------------");
		System.out.println("   Which hummies you want us second Boyz to crush hmmm?  ");
		System.out.println("---------------------------------------------------------");
		if (finish == false) {
			for (int i = 0; i < 1; i++)
				System.out.println();
			int targetForOrk = selectTargetForOrk();

			int roll2 = dieRoll();
			// for (int z=0; z < this.warhammer.getBoyz2().size()*2;z++)// two
			// attacks for Orks
			switch (targetForOrk)

			{
			case 1: {
				if (unitToRemoveSquadAlfa() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz2().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadAlfa();

							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 2: {
				if (unitToRemoveSquadBeta() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz2().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadBeta();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 3: {
				if (unitToRemoveSquadGamma() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz2().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadGamma();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}
			default:
				finish = true;
				break;
			}
		}
		System.out.println("---------------------------------------------------------");
		System.out.println("   Which hummies you want us third Boyz to crush like?   ");
		System.out.println("---------------------------------------------------------");

		if (finish == false) {
			for (int i = 0; i < 1; i++)
				System.out.println();
			int targetForOrk = selectTargetForOrk();

			int roll2 = dieRoll();
			// for (int z=0; z < this.warhammer.getBoyz3().size()*2;z++)// two
			// attacks for Orks
			switch (targetForOrk)

			{
			case 1: {
				if (unitToRemoveSquadAlfa() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz3().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadAlfa();

							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 2: {
				if (unitToRemoveSquadBeta() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz3().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadBeta();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 3: {
				if (unitToRemoveSquadGamma() == false) {
					selectTargetForOrk();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz3().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadGamma();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}
			default:
				finish = true;
				break;
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------------

	public void endTurnSM() {
		System.out.println("");
		System.out.println("the end of " + PLAYER_NAME + "'s Turn 1");

		setAdiitionalBoyz_Auto();
		AddBoyz();
		setAdiitionalBoyz2_Auto();
		AddBoyz2();
		setAdiitionalBoyz3_Auto();
		AddBoyz3();
		setOrkLimit_Auto();
		setOrkLimit_Auto();
		System.out.println("");
		System.out.println("Press any key to start Computer Turn 1");
		in.nextLine();
		setMove_Auto();
		shootingPhaseStarterForOrks_Auto();
		AssaultPhaseStarterForOrks_Auto();
		System.out.println("");
		System.out.println("the end of computer Turn 1");
		System.out.println("");
		System.out.println("the end of Game Turn 1");
		System.out.println("\nPress any key to continue");
		in.nextLine();

		System.out.println("");
		end_AutoTurnOrks();
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void endTurnOrks() {
		System.out.println("");
		System.out.println("the end of " + PLAYER_NAME + "'s Turn 1");

		setAdiitionalSquadSize_Auto();
		AddUnit();
		setAdiitionalSquad2Size_Auto();
		AddUnit2();
		setAdiitionalSquad3Size_Auto();
		AddUnit3();
		setSmLimit_Auto();
		setSmLimit_Auto();
		System.out.println("");
		System.out.println("Press any key to start Computer Turn 1");
		in.nextLine();
		setMove_Auto();
		shootingPhaseStarterForSM_Auto();
		AssaultPhaseStarterForSM_Auto();
		System.out.println("");
		System.out.println("the end of computer Turn 1");
		System.out.println("");
		System.out.println("the end of Game Turn 1");
		System.out.println("");
		System.out.println("Press any key to continue");
		in.nextLine();
		end_AutoTurnSM();
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void endTurnSM2() {
		System.out.println("");
		System.out.println("Press any key to start Computer Turn 2");
		in.nextLine();
		setMove_Auto();
		shootingPhaseStarterForOrks_Auto();
		AssaultPhaseStarterForOrks_Auto();
		System.out.println("");
		System.out.println("the end of computer Turn 2");
		System.out.println("");
		System.out.println("the end of Game Turn 2");
		System.out.println("");
		System.out.println("Press any key to continue");
		in.nextLine();
		end_AutoTurnOrks2();

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void endTurnOrks2() {
		System.out.println("");
		System.out.println("Press any key to start Computer Turn 2");
		in.nextLine();
		setMove_Auto();
		shootingPhaseStarterForSM_Auto();
		AssaultPhaseStarterForSM_Auto();
		System.out.println("");
		System.out.println("the end of computer Turn 2");
		System.out.println("");
		System.out.println("the end of Game Turn 2");
		System.out.println("");
		System.out.println("Press any key to continue");
		in.nextLine();
		end_AutoTurnSM2();
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void endTurnSM3() {
		System.out.println("");
		System.out.println("Press any key to start Computer Turn 3");
		in.nextLine();
		setMove_Auto();
		shootingPhaseStarterForOrks_Auto();
		AssaultPhaseStarterForOrks_Auto();
		System.out.println("");
		System.out.println("the end of computer Turn 3");
		System.out.println("");
		System.out.println("the end of Game Turn 3");
		System.out.println("");
		System.out.println("Press any key to continue");
		in.nextLine();
		end_AutoTurnOrks3();

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void endTurnOrks3() {
		System.out.println("");
		System.out.println("Press any key to start Computer Turn 3");
		in.nextLine();
		setMove_Auto();
		shootingPhaseStarterForSM_Auto();
		AssaultPhaseStarterForSM_Auto();
		System.out.println("");
		System.out.println("the end of computer Turn 3");
		System.out.println("");
		System.out.println("the end of Game Turn 3");
		System.out.println("");
		System.out.println("Press any key to continue");
		in.nextLine();
		end_AutoTurnSM3();
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void endTurnSM4() {
		System.out.println("");
		System.out.println("Press any key to start Computer Turn 4");
		in.nextLine();
		setMove_Auto();
		shootingPhaseStarterForOrks_Auto();
		AssaultPhaseStarterForOrks_Auto();
		System.out.println("");
		System.out.println("the end of computer Turn 4");
		System.out.println("");
		System.out.println("the end of Game Turn 4 ");
		System.out.println("");
		System.out.println("Press any key to continue");
		in.nextLine();
		END_THE_GAME();

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void endTurnOrks4() {
		System.out.println("");
		System.out.println("Press any key to start Computer Turn 4");
		in.nextLine();
		setMove_Auto();
		shootingPhaseStarterForSM_Auto();
		AssaultPhaseStarterForSM_Auto();
		System.out.println("");
		System.out.println("the end of computer Turn 4");
		System.out.println("");
		System.out.println("the end of Game Turn 4");
		System.out.println("");
		System.out.println("Press any key to continue");
		in.nextLine();
		END_THE_GAME();

	}
	// ------------------------------------------------------------------------------------------------------------------------------------
	// public void endTurnSM5()
	// {
	// System.out.println("");
	// System.out.println("Start of computer Turn 5");
	// System.out.println("");
	// setMove_Auto();
	// shootingPhaseStarterForOrks_Auto();
	// AssaultPhaseStarterForOrks_Auto();
	// System.out.println("");
	// System.out.println("the end of computer Turn 5");
	// System.out.println("");
	// System.out.println("the end of Game Turn 5 ");
	// System.out.println("");
	// END_THE_GAME();
	//
	// }
	//// ------------------------------------------------------------------------------------------------------------------------------------
	// public void endTurnOrks5()
	// {
	// System.out.println("");
	// System.out.println("Start of computer Turn 5");
	// System.out.println("");
	// setMove_Auto();
	// shootingPhaseStarterForSM_Auto();
	// AssaultPhaseStarterForSM_Auto();
	// System.out.println("");
	// System.out.println("the end of computer Turn 5");
	// System.out.println("");
	// System.out.println("the end of Game Turn 5");
	// System.out.println("");
	// END_THE_GAME();
	// }
	//

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/// ADDING MARINES TO SQUADS
	public int setAdiitionalSquadSize_Auto()

	{

		// int yesno = 0;
		// System.out.println("------------------------------------------------");
		// System.out.println(" Do you whish add Marines to your Alfa Squad? ");
		// System.out.println("------------------------------------------------");
		// System.out.println("1. Yes");
		// System.out.println("2. No");
		// boolean selected = false;
		//
		// while (selected == false)
		// {
		//
		//
		// try
		// {
		// int max = 2;
		// int min = 1;
		// Random rand = new Random();
		// yesno = rand.nextInt((max - min) + 1) + min;
		//
		// if ((yesno == 1) || (yesno == 2))
		// selected = true;
		// else
		// System.out.println("Sorry but the option must be 1 or 2");
		//
		// }
		// catch (Exception e)
		// {
		// System.out.println("Sorry you did not enter a valid option");
		// in.next();
		// }
		// return yesno;
		// }
		//
		// if(yesno ==1)
		//
		// {

		int max = 5;
		int min = 1;
		Random rand = new Random();

		this.adiitionalsquadSize = rand.nextInt((max - min) + 1) + min;

		try {
			if ((this.adiitionalsquadSize == 1)) {
				System.out.println("One Marine was added to Alfa Squad");
				System.out.println();

			} else if ((this.adiitionalsquadSize >= 0) && (this.adiitionalsquadSize <= 5)
					&& (this.adiitionalsquadSize != 1)) {
				System.out.println(this.adiitionalsquadSize + " Marines were added to Alfa Squad");
				System.out.println();

			}

		} catch (Exception e) {

			System.out.println();
			in.next();
		}

		return adiitionalsquadSize;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public int setAdiitionalSquad2Size_Auto()

	{

		// int yesno = 0;
		// System.out.println("------------------------------------------------");
		// System.out.println(" Do you whish add Marines to your Beta Squad? ");
		// System.out.println("------------------------------------------------");
		// System.out.println("1. Yes");
		// System.out.println("2. No");
		// boolean selected = false;
		//
		// while (selected == false)
		// {
		//
		//
		// try
		// {
		//
		// int max = 2;
		// int min = 1;
		// Random rand = new Random();
		// yesno = rand.nextInt((max - min) + 1) + min;
		// if ((yesno == 1) || (yesno == 2))
		// selected = true;
		// else
		// System.out.println("Sorry but the option must be 1 or 2");
		//
		// }
		// catch (Exception e)
		// {
		// System.out.println("Sorry you did not enter a valid option");
		// }
		// return yesno;
		// }
		//
		// if(yesno ==1)
		//
		// {

		int max = 5;
		int min = 1;
		Random rand = new Random();

		this.additionalSquad2Size = rand.nextInt((max - min) + 1) + min;
		in.nextLine();

		try {
			if ((this.adiitionalsquadSize == 1)) {
				System.out.println("One Marine was added to Beta Squad");
				System.out.println();
			} else if ((this.additionalSquad2Size >= 0) && (this.additionalSquad2Size <= 5)
					&& (this.additionalSquad2Size != 1)) {
				System.out.println(this.additionalSquad2Size + " Marines were addedto Beta Squad");
				System.out.println();
			}

		} catch (Exception e) {

			System.out.println();
			in.next();
		}

		return this.additionalSquad2Size;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------

	public int setAdiitionalSquad3Size_Auto()

	{

		// int yesno = 0;
		// System.out.println("------------------------------------------------");
		// System.out.println(" Do you whish add Marines to your Gamma Squad?
		// ");
		// System.out.println("------------------------------------------------");
		// System.out.println("1. Yes");
		// System.out.println("2. No");
		// boolean selected = false;
		//
		// while (selected == false)
		// {
		//
		//
		// try
		// {
		//
		// int max = 2;
		// int min = 1;
		// Random rand = new Random();
		// yesno = rand.nextInt((max - min) + 1) + min;
		// if ((yesno == 1) || (yesno == 2))
		// selected = true;
		// else
		// System.out.println("Sorry but the option must be 1 or 2");
		//
		// }
		// catch (Exception e)
		// {
		// System.out.println("Sorry you did not enter a valid option");
		// }
		// return yesno;
		// }
		//
		// if(yesno == 1)
		//
		// {

		int max = 5;
		int min = 1;
		Random rand = new Random();

		this.additionalSquad3Size = rand.nextInt((max - min) + 1) + min;
		in.nextLine();

		try {
			if ((this.additionalSquad3Size == 1)) {
				System.out.println("One Marine was added to  Gamma Squad");
				System.out.println();

			} else if ((this.additionalSquad3Size >= 0) && (this.additionalSquad3Size <= 5)
					&& (this.additionalSquad3Size != 1)) {
				System.out.println(this.additionalSquad3Size + " Marines were added to Gamma Squad");
				System.out.println();

			}

		} catch (Exception e) {

			System.out.println();
			in.next();
		}

		return this.additionalSquad3Size;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------------------------------
	/// ADDING ORK BOYZ
	public int setAdiitionalBoyz_Auto()

	{

		// int yesno = 0;
		// System.out.println("-------------------------------------------------");
		// System.out.println("Do you whish add more orks to your first
		// OrkBoyz?");
		// System.out.println("-------------------------------------------------");
		// System.out.println("1. Yes");
		// System.out.println("2. No");
		// boolean selected = false;
		//
		// while (selected == false)
		// {
		//
		//
		// try
		// {
		//
		// int max = 2;
		// int min = 1;
		// Random randi = new Random();
		// yesno = randi.nextInt((max - min) + 1) + min;
		// if ((yesno == 1) || (yesno == 2))
		// selected = true;
		// else
		// System.out.println("Sorry but the option must be 1 or 2");
		//
		// }
		// catch (Exception e)
		// {
		// System.out.println("Sorry you did not enter a valid option");
		// }
		//
		// }
		//
		// if(yesno ==1)
		//
		{

			int max = 10;
			int min = 1;
			Random randi = new Random();
			this.additionalBoyz = randi.nextInt((max - min) + 1) + min;
			in.nextLine();

			try {
				if ((this.additionalBoyz == 1)) {
					System.out.println("One Ork was added to  first OrkBoyz");
					System.out.println();

				} else if ((this.additionalBoyz >= 0) && (this.additionalBoyz <= 10) && (this.additionalBoyz != 1)) {
					System.out.println(this.additionalBoyz + " Orks were added to first OrkBoyz");
					System.out.println();

				}

			} catch (Exception e) {

				System.out.println();
				in.next();
			}
		}

		return additionalBoyz;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public int setAdiitionalBoyz2_Auto()

	{

		// int yesno = 0;
		// System.out.println("--------------------------------------------------");
		// System.out.println("Do you whish add more orks to your second
		// OrkBoyz?");
		// System.out.println("--------------------------------------------------");
		// System.out.println("1. Yes");
		// System.out.println("2. No");
		// boolean selected = false;
		//
		// while (selected == false)
		// {
		//
		//
		// try
		// {
		//
		// int max = 2;
		// int min = 1;
		// Random randi = new Random();
		// yesno = randi.nextInt((max - min) + 1) + min;
		// if ((yesno == 1) || (yesno == 2))
		// selected = true;
		// else
		// System.out.println("Sorry but the option must be 1 or 2");
		//
		// }
		// catch (Exception e)
		// {
		// System.out.println("Sorry you did not enter a valid option");
		// }
		//
		// }
		//
		// if(yesno ==1)
		//
		//
		{
			int max = 10;
			int min = 1;
			Random randi = new Random();
			this.additionalBoyz2 = randi.nextInt((max - min) + 1) + min;
			in.nextLine();

			try {
				if ((this.additionalBoyz2 == 1)) {
					System.out.println("One Ork was added to second OrkBoyz");
					System.out.println();

				} else if ((this.additionalBoyz2 >= 0) && (this.additionalBoyz2 <= 10) && (this.additionalBoyz2 != 1)) {
					System.out.println(this.additionalBoyz2 + " Orks were added to second OrkBoyz");
					System.out.println();

				}

			} catch (Exception e) {

				System.out.println();
				in.next();
			}
		}

		return additionalBoyz2;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public int setAdiitionalBoyz3_Auto()

	{

		// int yesno = 0;
		// System.out.println("-------------------------------------------------");
		// System.out.println("Do you whish add more orks to your third
		// OrkBoyz?");
		// System.out.println("-------------------------------------------------");
		// System.out.println("1. Yes");
		// System.out.println("2. No");
		// boolean selected = false;
		//
		// while (selected == false)
		// {
		//
		//
		// try
		// {
		//
		// int max = 2;
		// int min = 1;
		// Random randi = new Random();
		// yesno = randi.nextInt((max - min) + 1) + min;
		// if ((yesno == 1) || (yesno == 2))
		// selected = true;
		// else
		// System.out.println("Sorry but the option must be 1 or 2");
		//
		// }
		// catch (Exception e)
		// {
		// System.out.println("Sorry you did not enter a valid option");
		// }
		//
		// }
		//
		// if(yesno ==1)

		{

			int max = 10;
			int min = 1;
			Random randi = new Random();
			this.additionalBoyz3 = randi.nextInt((max - min) + 1) + min;

			try {
				if ((this.additionalBoyz3 == 1)) {
					System.out.println("One Ork was added to third OrkBoyz");
					System.out.println();

				} else if ((this.additionalBoyz3 >= 0) && (this.additionalBoyz3 <= 10) && (this.additionalBoyz3 != 1)) {
					System.out.println(this.additionalBoyz3 + " Orks were added to third OrkBoyz");
					System.out.println();

				}

			} catch (Exception e) {

				System.out.println();
				in.next();
			}
		}

		return additionalBoyz3;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------------------------------

	public int setMove_Auto() {

		for (int g = 0; g < distance.length; g++)
			if (distance[g] != 0) {

				int max = 6;
				int min = 1;
				Random randMove = new Random();
				this.move = randMove.nextInt((max - min) + 1) + min;

				try {

					if ((this.move >= 0) && (this.move <= 6)) {
						if ((distance[g] <= 0) || (distance[g] == 1)) {
							System.out.println("to close to move");
							System.out.println();
							setDistance_Auto();
						} else {

							System.out.println("You enemy moved " + this.move + "  towards you");
							System.out.println();
							setDistance_Auto();
						}

					} else {

						System.out.println();
						setMove_Auto();
					}

				} catch (Exception e) {

					System.out.println();
					in.next();

				}
			} else if (distance[g] <= 0) {

				setMove_Auto();
			} else {
				setDistance_Auto();
			}
		return move;
	}

	public int[] setDistance_Auto() {
		int result = 0;
		for (int a = 0; a < distance.length; a++) {

			result = distance[a] - this.move;
			if (result <= 0) {

				System.out.println("");
			}
			if (distance[a] == 1) {
				System.out.println("");
			} else {
				distance[a] = +result;
				System.out.println("distance between your troops and enemy is: " + distance[a]);
			}
		}

		return distance;
	}
	// ------------------------------------------------------------------------------------------------------------------------------------

	public int selectTarget_Auto() {
		int targetAuto = 0;

		// System.out.println("1. first Orks band");
		// System.out.println("2. second Orks band");
		// System.out.println("3. third Orks band");
		boolean selected = false;

		while (selected == false) {

			try {
				int max = 3;
				int min = 1;
				Random randTarg = new Random();
				targetAuto = randTarg.nextInt((max - min) + 1) + min;

				if ((targetAuto == 1) || (targetAuto == 2) || (targetAuto == 3))
					selected = true;

			} catch (Exception e) {
				in.next();
			}
		}

		return targetAuto;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void shootingPhaseStarterForSM_Auto() // rozwiazac problem dystansu
													// dla indywidualnych
													// jednostek
	{
		for (int g = 0; g < distance.length; g++) {
			if (distance[g] <= 24)

				toSave_Auto();

			else if (distance[g] > 24)
				System.out.println("enemy is out of the shooting range!!");

		}

	}
	// ------------------------------------------------------------------------------------------------------------------------------------

	public void toSave_Auto()

	{

		boolean finish = false;
		// System.out.println("--------------------------------------------------------");
		// System.out.println(" Which greenskin band you want Alfa Squad to deal
		// with? ");
		// System.out.println("--------------------------------------------------------");

		if (finish == false) {
			for (int i = 0; i < 1; i++)
				System.out.println();
			int targetAuto = selectTarget_Auto();

			int roll2 = dieRoll();
			// for (int z=0; z < this.warhammer.getSquadMember().size();z++)
			switch (targetAuto) {
			case 1: {
				if (deadOkrsGroup1() == false) {
					selectTarget_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getSquadMember().size(); z++)
						if (toWound() == true) {
							if (roll2 >= 6) {
								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								deadOkrsGroup1();
							}

						} else {
							System.out.println(" no effect");
						}
				}
				break;
			}
			// ------------------------------------------------------------------------------------------------------------------------------------

			case 2: {
				if (deadOkrsGroup2() == false) {
					selectTarget_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getSquadMember().size(); z++)
						if (toWound() == true) {
							if (roll2 >= 6)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								deadOkrsGroup2();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}
			// ------------------------------------------------------------------------------------------------------------------------------------
			case 3: {
				if (deadOkrsGroup3() == false) {
					selectTarget_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getSquadMember().size(); z++)
						if (toWound() == true) {
							if (roll2 >= 6)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								deadOkrsGroup3();
							}

						} else {
							System.out.println(" no effect");
						}
				}
				break;
			}
			default:
				finish = true;
				break;
			}
		}

		// ------------------------------------------------------------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------------------------------------------------------------

		// System.out.println("--------------------------------------------------------");
		// System.out.println(" Which greenskin band you want Beta Squad to deal
		// with? ");
		// System.out.println("--------------------------------------------------------");
		{
			if (finish == false) {
				for (int i = 0; i < 1; i++)
					System.out.println();
				int targetAuto = selectTarget_Auto();

				int roll2 = dieRoll();
				// for (int z=0; z <
				// this.warhammer.getSquadMember2().size();z++)
				switch (targetAuto) {
				case 1: {
					if (deadOkrsGroup1() == false) {
						selectTarget_Auto();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember2().size(); z++)
							if (toWound() == true) {
								if (roll2 >= 6) {
									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup1();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}

				// ------------------------------------------------------------------------------------------------------------------------------------

				case 2: {
					if (deadOkrsGroup2() == false) {
						selectTarget_Auto();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember2().size(); z++)
							if (toWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup2();
								}

							} else {

								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------
				case 3: {
					if (deadOkrsGroup3() == false) {
						selectTarget_Auto();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember2().size(); z++)
							if (toWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup3();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				default:
					finish = true;
					break;
				}
			}

			// ------------------------------------------------------------------------------------------------------------------------------------
			// ------------------------------------------------------------------------------------------------------------------------------------
			// System.out.println("---------------------------------------------------------");
			// System.out.println(" Which greenskin band you want Gamma Squad to
			// deal with? ");
			// System.out.println("---------------------------------------------------------");

			if (finish == false) {
				for (int i = 0; i < 1; i++)
					System.out.println();
				int targetAuto = selectTarget_Auto();

				int roll2 = dieRoll();
				// for (int z=0; z <
				// this.warhammer.getSquadMember3().size();z++)
				switch (targetAuto) {
				case 1: {
					if (deadOkrsGroup1() == false) {
						selectTarget_Auto();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember3().size(); z++)
							if (toWound() == true) {
								if (roll2 >= 6) {
									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup1();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------

				case 2: {
					if (deadOkrsGroup2() == false) {
						selectTarget_Auto();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember3().size(); z++)
							if (toWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup2();
								}

							} else {

								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------
				case 3: {
					if (deadOkrsGroup3() == false) {
						selectTarget_Auto();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember3().size(); z++)
							if (toWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup3();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				default:
					finish = true;
					break;
				}

			}

		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------------------------------
	public void shootingPhaseStarterForOrks_Auto() {
		for (int g = 0; g < distance.length; g++) {
			if (distance[g] <= 24)

				toSaveOrk_Auto();

			else if (distance[g] > 24)
				System.out.println("enemy is out of the shooting range!!");

			// setMove();
		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public int selectTargetForOrk_Auto() {
		int targetForOrk_Auto = 0;

		// System.out.println("1. Hummies Alfa");
		// System.out.println("2. Hummies Beta");
		// System.out.println("3. Hummies Gamma");
		boolean selected = false;

		while (selected == false) {

			try {
				int max = 3;
				int min = 1;
				Random randTargOrk = new Random();
				targetForOrk_Auto = randTargOrk.nextInt((max - min) + 1) + min;

				if ((targetForOrk_Auto == 1) || (targetForOrk_Auto == 2) || (targetForOrk_Auto == 3))
					selected = true;

			} catch (Exception e) {
				in.next();
			}
		}

		return targetForOrk_Auto;

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void toSaveOrk_Auto()

	{
		boolean finish = false;

		// System.out.println("---------------------------------------------------------");
		// System.out.println(" Which hummies you want us firs Boyz to crush he?
		// ");
		// System.out.println("---------------------------------------------------------");

		if (finish == false) {
			for (int i = 0; i < 1; i++)
				System.out.println();
			int targetForOrk = selectTargetForOrk_Auto();

			int roll2 = dieRoll();
			// for (int z=0; z < this.warhammer.getBoyz().size()*2;z++)// two
			// attacks for Orks
			switch (targetForOrk)

			{
			case 1: {
				if (unitToRemoveSquadAlfa() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadAlfa();

							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 2: {
				if (unitToRemoveSquadBeta() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadBeta();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 3: {
				if (unitToRemoveSquadGamma() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadGamma();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}
			default:
				finish = true;
				break;
			}
		}

		// System.out.println("---------------------------------------------------------");
		// System.out.println(" Which hummies you want us second Boyz to crush
		// hmmm? ");
		// System.out.println("---------------------------------------------------------");
		if (finish == false) {
			for (int i = 0; i < 1; i++)
				System.out.println();
			int targetForOrk = selectTargetForOrk_Auto();

			int roll2 = dieRoll();
			// for (int z=0; z < this.warhammer.getBoyz2().size()*2;z++)// two
			// attacks for Orks
			switch (targetForOrk)

			{
			case 1: {
				if (unitToRemoveSquadAlfa() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz2().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadAlfa();

							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;

			}

			case 2: {
				if (unitToRemoveSquadBeta() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz2().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadBeta();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 3: {
				if (unitToRemoveSquadGamma() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz2().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadGamma();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}
			default:
				finish = true;
				break;
			}
		}
		// System.out.println("---------------------------------------------------------");
		// System.out.println(" Which hummies you want us third Boyz to crush
		// like? ");
		// System.out.println("---------------------------------------------------------");

		if (finish == false) {
			for (int i = 0; i < 1; i++)
				System.out.println();
			int targetForOrk = selectTargetForOrk_Auto();

			int roll2 = dieRoll();
			// for (int z=0; z < this.warhammer.getBoyz3().size()*2;z++)// two
			// attacks for Orks
			switch (targetForOrk)

			{
			case 1: {
				if (unitToRemoveSquadAlfa() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz3().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadAlfa();

							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 2: {
				if (unitToRemoveSquadBeta() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz3().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadBeta();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 3: {
				if (unitToRemoveSquadGamma() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz3().size() * 2; z++)
						if (toWoundOrk() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadGamma();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}
			default:
				finish = true;
				break;
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// ASSAULT PHASE
	public void AssaultPhaseStarterForSM_Auto() {
		for (int g = 0; g < distance.length; g++) {
			if (distance[g] <= 6) {

				assaulSave_Auto();
			}

			else if (distance[g] > 6)
				System.out.println("enemy is out of the assault range!!");

		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void assaulSave_Auto()

	{

		boolean finish = false;
		// System.out.println("--------------------------------------------------------");
		// System.out.println(" Which greenskin band you want Alfa Squad to deal
		// with? ");
		// System.out.println("--------------------------------------------------------");
		{
			if (finish == false) {
				for (int i = 0; i < 1; i++)
					System.out.println();
				int target = selectTarget_Auto();

				int roll2 = dieRoll();
				// for (int z=0; z < this.warhammer.getSquadMember().size();z++)
				switch (target) {
				case 1: {
					if (deadOkrsGroup1() == false) {
						selectTarget_Auto();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6) {
									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup1();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------

				case 2: {
					if (deadOkrsGroup2() == false) {
						selectTarget_Auto();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup2();
								}

							} else {

								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------
				case 3: {
					if (deadOkrsGroup3() == false) {
						selectTarget_Auto();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup3();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				default:
					finish = true;
					break;
				}
			}
		}
		// ------------------------------------------------------------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------------------------------------------------------------

		// System.out.println("--------------------------------------------------------");
		// System.out.println(" Which greenskin band you want Beta Squad to deal
		// with? ");
		// System.out.println("--------------------------------------------------------");
		{
			if (finish == false) {
				for (int i = 0; i < 1; i++)
					System.out.println();
				int target = selectTarget_Auto();

				int roll2 = dieRoll();
				// for (int z=0; z <
				// this.warhammer.getSquadMember2().size();z++)
				switch (target) {
				case 1: {
					if (deadOkrsGroup1() == false) {
						selectTarget_Auto();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember2().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6) {
									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup1();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------

				case 2: {
					if (deadOkrsGroup2() == false) {
						selectTarget_Auto();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember2().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup2();
								}

							} else {

								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------
				case 3: {
					if (deadOkrsGroup3() == false) {
						selectTarget_Auto();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember2().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup3();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				default:
					finish = true;
					break;
				}
			}
		}
		// ------------------------------------------------------------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------------------------------------------------------------
		// System.out.println("---------------------------------------------------------");
		// System.out.println(" Which greenskin band you want Gamma Squad to
		// deal with? ");
		// System.out.println("---------------------------------------------------------");
		//

		{
			if (finish == false) {
				for (int i = 0; i < 1; i++)
					System.out.println();
				int target = selectTarget_Auto();

				int roll2 = dieRoll();
				// for (int z=0; z <
				// this.warhammer.getSquadMember3().size();z++)
				switch (target) {
				case 1: {
					if (deadOkrsGroup1() == false) {
						selectTarget_Auto();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember3().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6) {
									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup1();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------

				case 2: {
					if (deadOkrsGroup2() == false) {
						selectTarget_Auto();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember3().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup2();
								}

							} else {

								System.out.println(" no effect");
							}
					}
					break;
				}
				// ------------------------------------------------------------------------------------------------------------------------------------
				case 3: {
					if (deadOkrsGroup3() == false) {
						selectTarget_Auto();
					} else {
						for (int z = 0; z < this.warhammer.getSquadMember3().size(); z++)
							if (assaultWound() == true) {
								if (roll2 >= 6)

								{

									System.out.println(roll2 + " Armour save unit from any damage");
								} else {

									System.out.println(roll2 + " target inflicted the wound");
									deadOkrsGroup3();
								}

							} else {
								System.out.println(" no effect");
							}
					}
					break;
				}

				default:
					finish = true;
					break;
				}
			}
		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void AssaultPhaseStarterForOrks_Auto() {
		for (int g = 0; g < distance.length; g++) {
			if (distance[g] <= 6) {

				assaultSaveOrk_Auto();

			}

			else if (distance[g] > 6)
				System.out.println("enemy is out of the assault range!!");

			// setMove();
		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void assaultSaveOrk_Auto()

	{
		boolean finish = false;

		// System.out.println("---------------------------------------------------------");
		// System.out.println(" Which hummies you want us firs Boyz to crush he?
		// ");
		// System.out.println("---------------------------------------------------------");

		if (finish == false) {
			for (int i = 0; i < 1; i++)
				System.out.println();
			int targetForOrk = selectTargetForOrk_Auto();

			int roll2 = dieRoll();
			// for (int z=0; z < this.warhammer.getBoyz().size()*2;z++)// two
			// attacks for Orks
			switch (targetForOrk)

			{
			case 1: {
				if (unitToRemoveSquadAlfa() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadAlfa();

							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 2: {
				if (unitToRemoveSquadBeta() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadBeta();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 3: {
				if (unitToRemoveSquadAlfa() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadGamma();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}
			default:
				finish = true;
				break;
			}
		}

		// System.out.println("---------------------------------------------------------");
		// System.out.println(" Which hummies you want us second Boyz to crush
		// hmmm? ");
		// System.out.println("---------------------------------------------------------");
		if (finish == false) {
			for (int i = 0; i < 1; i++)
				System.out.println();
			int targetForOrk = selectTargetForOrk_Auto();

			int roll2 = dieRoll();
			// for (int z=0; z < this.warhammer.getBoyz2().size()*2;z++)// two
			// attacks for Orks
			switch (targetForOrk)

			{
			case 1: {
				if (unitToRemoveSquadAlfa() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz2().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadAlfa();

							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 2: {

				if (unitToRemoveSquadBeta() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz2().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadBeta();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 3: {
				if (unitToRemoveSquadGamma() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz2().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadGamma();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}
			default:
				finish = true;
				break;
			}
		}
		// System.out.println("---------------------------------------------------------");
		// System.out.println(" Which hummies you want us third Boyz to crush
		// like? ");
		// System.out.println("---------------------------------------------------------");

		if (finish == false) {
			for (int i = 0; i < 1; i++)
				System.out.println();
			int targetForOrk = selectTargetForOrk_Auto();

			int roll2 = dieRoll();
			// for (int z=0; z < this.warhammer.getBoyz3().size()*2;z++)// two
			// attacks for Orks
			switch (targetForOrk)

			{
			case 1: {
				if (unitToRemoveSquadAlfa() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz3().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadAlfa();

							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 2: {
				if (unitToRemoveSquadBeta() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz3().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadBeta();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}

			case 3: {
				if (unitToRemoveSquadGamma() == false) {
					selectTargetForOrk_Auto();
				} else {
					for (int z = 0; z < this.warhammer.getBoyz3().size() * 2; z++)
						if (assaultWound() == true) {
							if (roll2 >= 3)

							{

								System.out.println(roll2 + " Armour save unit from any damage");
							} else {

								System.out.println(roll2 + " target inflicted the wound");
								unitToRemoveSquadGamma();
							}

						} else {

							System.out.println(" no effect");
						}
				}
				break;
			}
			default:
				finish = true;
				break;
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------------------------------
	public void end_AutoTurnSM() {
		System.out.println("");
		System.out.println("Start of " + PLAYER_NAME + "'s Turn 2");
		System.out.println("");
		setMove();
		shootingPhaseStarterForOrks();
		AssaultPhaseStarterForOrks();
		System.out.println("");
		System.out.println("the end of " + PLAYER_NAME + "'s Turn 2");
		System.out.println("");

		endTurnOrks2();
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void end_AutoTurnOrks() {
		System.out.println("");
		System.out.println("Start of " + PLAYER_NAME + "'s Turn 2");
		System.out.println("");
		setMove();
		shootingPhaseStarterForSM();
		AssaultPhaseStarterForSM();
		System.out.println("");
		System.out.println("the end of " + PLAYER_NAME + "'s Turn 2");
		System.out.println("");
		endTurnSM2();
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void end_AutoTurnSM2() {
		System.out.println("");
		System.out.println("Start of " + PLAYER_NAME + "'s Turn 3");
		System.out.println("");
		setMove();
		shootingPhaseStarterForOrks();
		AssaultPhaseStarterForOrks();
		System.out.println("");
		System.out.println("the end of " + PLAYER_NAME + "'s Turn 3");
		System.out.println("");
		endTurnOrks3();
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void end_AutoTurnOrks2() {
		System.out.println("");
		System.out.println("Start of " + PLAYER_NAME + "'s Turn 3");
		System.out.println("");
		setMove();
		shootingPhaseStarterForSM();
		AssaultPhaseStarterForSM();
		System.out.println("");
		System.out.println("the end of " + PLAYER_NAME + "'s Turn 3");
		System.out.println("");
		endTurnSM3();
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void end_AutoTurnSM3() {
		System.out.println("");
		System.out.println("Start of " + PLAYER_NAME + "'s Turn 4");
		System.out.println("");
		setMove();
		shootingPhaseStarterForOrks();
		AssaultPhaseStarterForOrks();
		System.out.println("");
		System.out.println("the end of " + PLAYER_NAME + "'s Turn 4");
		System.out.println("");
		endTurnOrks4();
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	public void end_AutoTurnOrks3() {
		System.out.println("");
		System.out.println("Start of " + PLAYER_NAME + "'s Turn 4");
		System.out.println("");
		setMove();
		shootingPhaseStarterForSM();
		AssaultPhaseStarterForSM();
		System.out.println("");
		System.out.println("the end of " + PLAYER_NAME + "'s Turn 4");
		System.out.println("");
		endTurnSM4();
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// public void end_AutoTurnSM4()
	// {
	// System.out.println("");
	// System.out.println("Start of player Turn 5");
	// System.out.println("");
	// setMove();
	// shootingPhaseStarterForOrks();
	// AssaultPhaseStarterForOrks();
	// System.out.println("");
	// System.out.println("the end of player Turn 5");
	// System.out.println("");
	// endTurnOrks5();
	// }
	// ------------------------------------------------------------------------------------------------------------------------------------
	// public void end_AutoTurnOrks4()
	// {
	// System.out.println("");
	// System.out.println("Start of player Turn 5");
	// System.out.println("");
	// setMove();
	// shootingPhaseStarterForSM();
	// AssaultPhaseStarterForSM();
	// System.out.println("");
	// System.out.println("the end of player Turn 5");
	// System.out.println("");
	// endTurnSM5();
	// }
	// ------------------------------------------------------------------------------------------------------------------------------------
	public void END_THE_GAME() {
		System.out.println("");
		System.out.println("THE END OF WARHAMMER GAME");
		System.out.println("");
		test();
		testOrk();
		int sum1 = 0;
		int sum2 = 0;
		int sum3 = 0;
		int sum4 = 0;
		int sum5 = 0;
		int sum6 = 0;
		int orkSum = 0;
		int smSum = 0;

		for (Squads squad1 : this.warhammer.getSquadMember()) {
			sum1 = +squad1.getUnitValue() * (this.warhammer.getSquadMember().size());
		}

		for (Squads squad2 : this.warhammer.getSquadMember2()) {
			sum2 = +squad2.getUnitValue() * (this.warhammer.getSquadMember2().size());
		}

		for (Squads squad3 : this.warhammer.getSquadMember3()) {
			sum3 = +squad3.getUnitValue() * (this.warhammer.getSquadMember3().size());
		}

		for (OrkBoyz orks1 : this.warhammer.getBoyz()) {
			sum4 = +orks1.getUnitValue() * (this.warhammer.getBoyz().size());
		}

		for (OrkBoyz orks2 : this.warhammer.getBoyz2()) {
			sum5 = +orks2.getUnitValue() * (this.warhammer.getBoyz2().size());
		}

		for (OrkBoyz orks3 : this.warhammer.getBoyz3()) {
			sum6 = +orks3.getUnitValue() * (this.warhammer.getBoyz3().size());
		}

		orkSum = +sum4 + sum5 + sum6;
		smSum += sum1 + sum2 + sum3;

		if (orkSum > smSum) {
			System.out.println("");
			System.out.println("  With the score " + orkSum + " to " + smSum + " Ork have won!!!! WAAAGH!!!");
		}
		if (orkSum < smSum) {
			System.out.println("");
			System.out.println("  With the score " + smSum + " to " + orkSum
					+ " Space Marines have won!!!! For the Immortall Emperor!!!");
		}
		if (orkSum == smSum) {
			System.out.println("");
			System.out.println("  There is a draw: " + smSum + " " + orkSum);
		}
	}

	// C:\Users\Kraszu\workspace\Warhammer1\bin>java WarhammerMain
	// ------------------------------------------------------------------------------------------------------------------------------------
}
