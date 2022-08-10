/*
Author: Amirreza Pazira UCID:30133500
Program: Fight Simulator (Object-Oriented Programming)

Features: Up to 100 rounds of simulation - There's an attacker which can attack aimed at low,medium,high
user can enter the percentage of each attack type - There's a defender that will try to block the attacks -
The defender is intelligent it will learn from first few rounds and it will use the data to block the future attacks 

Limitations: The actual attack types probabilities will differ from the user's input due to randomness, but it will be very close -
The defender is not that "Intelligent" it wont block the attacks everytime after the learning rounds - 
There's only 100 rounds of simulation - The defender's defense type can't be changed by the user -
There's only 3 type of attacks  

Version: 5-Feb-2021
*/
// Importing Random class to generate random number
import java.util.Random;

public class Defender
{
	// Variable for the number of rounds
	private int rounds;
	// Variables for storing number of attacks for each type
	private float attackLow;
	private float attackMed;
	private float attackHigh;
	// Variable for storing the bounds of each defense type after calculations based on the attack data after getting rounded
	private float low;
	private float med;
	private float high;
	// Variable for storing the bounds of each defense type after calculations based on the attack data
	private float newLow;
	private float newMed;
	private float newHigh;
	// Variable for the random number generation range
	private final int MAX = 9;
	// Variables for the number of each defense type
	private float defLow = 0;
	private float defMed = 0;
	private float defHigh = 0;
	// Variables for defense type storage and attack type
	private String defType;
	private String att;
	// Variable for storing number of blocked attacks
	private int block;
	// Variable for storing number of successful attacks
	private int hit;
	// Variable for storing number of passed rounds to determine when the defender stops learning
	private int round;
	// Variable for start of learning round
	private float third;
	// Constructor for storing the number of rounds and calculating the learning round
	public Defender(int newRounds){
		rounds = newRounds;
		third = rounds/3;
	}

	// Features: gets a parameter and based on that it will generate a defense type if the numbers of rounds passed
	// is below the learning round default percentages will be used to get defense types 
	// if it's above learning round it will get new percentages based on previous attack types and it will generate
	// defense types based on the new percentages here the defender is intelligent
	// Limitations: need a parameter and there's still randomness involved so it's not intelligent in sense of it will block everything
	public void defend(String newAttack)
	{
		int fight;
		Random num = new Random();
		// If statemnt when the passed rounds is below the learning round
		// Here it will use default percentages for defense type probabilities
		if (round <= third){
			low = 3;
			med = 6;
			high = 9;
			fight = num.nextInt(MAX) + 1;
			// If the random int is below this number the defense is aimed low
			if (fight <= low){
				defType = "Low";
				defLow++;
			}
			// If the random int is below this number and above the previous number the defense is aimed medium height
			if ((fight > low) && (fight <= med)){
				defType = "Med";
				defMed++;
			}
			// If the random int is below this number and above the previous number the defense is aimed high
			if ((fight > med) && (fight <= high)){
				defType = "High";
				defHigh++;
			}
		}
		// If statement when the passed rounds is above the learning round
		// the body of the method is the same as above but with new percentages for defense types based on
		// previous attack types
		if (round > third){
			// Here getStat method will be called with attack type variable for calculations of new percentages
			// Also the getRound method will be called to round the numbers
			newLow = getProb(attackLow);
			newMed = getProb(attackMed);
			newHigh = getProb(attackHigh);
			low = newLow;
			med = newMed + low;
			high = newHigh + med;
			low = getRound(low);
			med = getRound(med);
			high = getRound(high);

      		fight = num.nextInt(MAX) + 1;
			if (fight <= low){
				defType = "Low";
				defLow++;
			}
			if ((fight > low) && (fight <= med)){
				defType = "Med";
				defMed++;
			}
			if ((fight > med) && (fight <= high)){
				defType = "High";
				defHigh++;
			}

		}

		
	}
	// Features: it will get each attack type percentages as a parameter and it will calculate the percentages of each defense
	// type so it can display all of it with at the end
	// Limitations: needs 3 parameters to start
	public void results(float atLow, float atMed, float atHigh)
	{
		// Variables for storing the statistics 
		float statLow;
		float statMed;
		float statHigh;
		float statLowAttack;
		float statMedAttack;
		float statHighAttack;
		System.out.println("--------------");
		System.out.println();
		System.out.println("Summary of Battle");
		System.out.println();
		// Calculating Defense types and attack types percentages
		statLow = (defLow/rounds)*100;
		statMed = (defMed/rounds)*100;
		statHigh = (defHigh/rounds)*100;
		statLowAttack = (attackLow/rounds)*100;
		statMedAttack = (attackMed/rounds)*100;
		statHighAttack = (attackHigh/rounds)*100;
		System.out.println("Total Hits: " + hit + " Total Blocks: " + block);
		System.out.println();
		System.out.print("User's Stats:");
		System.out.print(String.format(" Low: " + "%.2f",atLow));
		System.out.print("%");
		System.out.print(String.format(" Medium: " + "%.2f",atMed));
		System.out.print("%");
		System.out.print(String.format(" High: " + "%.2f",atHigh));
		System.out.println("%");
		System.out.print("Attacker's Stats");
		System.out.print(String.format(" Low: " + "%.2f",statLowAttack));
		System.out.print("%");
		System.out.print(String.format(" Medium: " + "%.1f",statMedAttack));
		System.out.print("%");
		System.out.print(String.format(" High: " + "%.1f",statHighAttack));
		System.out.println("%");
		System.out.print("Defender's Stats:");
		System.out.print(String.format(" Low: " + "%.2f",statLow));
		System.out.print("%");
		System.out.print(String.format(" Medium: " + "%.2f",statMed));
		System.out.print("%");
		System.out.print(String.format(" High: " + "%.2f",statHigh));
		System.out.println("%");
	}
	// Features: adding one to each attack type variable based on the attackers attack type in the parameter
	// also adding one round to number of rounds passed variable
	// Limitations: needs a parameter
	public void statAttack(String atType){
		round++;
		if (atType == "Low")
			attackLow++;
		if (atType == "Med")
			attackMed++;
		if (atType == "High")
			attackHigh++;
	}
	// Features: gets the attack type as a parameter and if it's same as defense type the attack is blocked
	// and if its not the attack is successful also it will print these results for each round
	// Limitations: needs a parameter
	public void statDefend(String attType){
		String action;
		if (attType == defType){
			action = " Blocked";
			block++;
		}
		else{
			action = " Hit";
			hit++;
		}
		System.out.println("Round " + round +"...     " + " Attacker: " + attType + "    Defender: " + defType + " Result " + action);
	}
	//Features: getting the bounds for each defense type by calculating the correct range between 1-9
	//based on the numbers of each attack type before learning round
	//Limitations: needs a parameter
	//Returns the calculated number as a float
	public float getProb(float attack){
		float tempFirst;
		float tempSecond;
		int tempThird;
		tempFirst = (attack/round)*100;
		tempSecond = (tempFirst/100)*9;
		if (tempFirst == 0)
			tempSecond = 0;

        return (tempSecond);
    }
    // Features: rounds the number passed to it when called and returns it as a int
	// Limitations: needs a parameter
    public int getRound(float number){
		float tempFirst = number;
		int tempSecond;
		tempSecond = (int) tempFirst;
		if ( tempFirst - tempSecond >= .5 ) 
        	tempSecond = tempSecond + 1;
        return (tempSecond);
	}
}
