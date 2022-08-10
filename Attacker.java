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
// Importing Random and Scanner class to get user's input and to generate random number
import java.util.Random;
import java.util.Scanner;


public class Attacker
{
	// Variable for the number of rounds
	private int rounds;
	// Variables for the number of each attack type
	private float numLow;
	private float numMed;
	private float numHigh;
	// Variables for the number of each attack type probabilities after calculation
	private float newLow;
	private float newMed;
	private float newHigh;
	// Variables for the number of each attack type probabilities after getting rounded
	private float low;
	private float med;
	private float high;
	// variables for user's input for percentages of each attack type
	private float probLow;
	private float probMed;
	private float probHigh;
	// Variable for storing the attack type
	private String attackType;
	// Variable for the random number generation range
	private final int MAX = 9;
	// The constructor that will get the amount of simulation rounds
	public Attacker(int newRounds) { 
			rounds = newRounds;
	}
	// Method for getting the user's input for attack percentages that will return the sum of those number
	// Features: gets input and returns the sum the numbers and based on those numbers it will call another 
	// methods in this class to calculate the percentages and stores that value to variables to be used for 
	// getAttack method for generating attacks
	// Limitations: Doesn't take any parameters and is only used once for every test run
	// Returns the sum of user's inputs as a float
	public float getPercent(){
		float sum;
		Scanner in = new Scanner(System.in);
		// Getting user's input
		System.out.println("Enter percentages for the number of attacks that will be\n" + "directed: low, medium, high. The total of the three percentages \n" + "must sum to 100%");
		System.out.print("Percentage of low attacks: ");
		probLow = in.nextInt();
		System.out.print("Percentage of medium height attacks: ");
		probMed = in.nextInt();
		System.out.print("Percentage of high attacks: ");
		probHigh = in.nextInt();
		// Calling the getBound method to get the bounds for each attack type
		newLow = getBound(probLow);
		newMed = getBound(probMed);
		newHigh = getBound(probHigh);
		// Adding previous max bound to next max bound so that the last max bound is equal to MAX variable
		low = newLow;	
		med = newMed + low;
		high = newHigh + med;
		// Calling getRound method to round numbers
		low = getRound(low);
		med = getRound(med);
		high = getRound(high);

		// Getting the sum of user's inputs
		sum = probHigh+probMed+probLow;
		
		
		return (sum);
	}
	// Features: this method will generate random numbers between 1-20 then based on the values we get
	// from the getPercent method the method will decide which attack type to be used -
	// If the sum of percentages from the user's input is equal to 100 it will go through the 'if' statement
	// and it will use user's input but if it's not equal 100 it will go through the 'else' statement and use default values
	// Limitations: it needs a parameter to run 
	// Returns the attack type as a string
	public String getAttack(float prob){
		Random num = new Random();
		int fight;
		// If statement when prob or sum of all the user's input is equal to 100
		// it will use the user's percentages
		if ((prob) == 100){
			fight = num.nextInt(MAX) + 1;
			// If the random int is below this number the attack is aimed low
			if (fight <= low){
				numLow++;
				attackType = "Low";
			}
			// If the random int is below this number and above the previous number the attack is aimed medium height
			if ((fight > low) && (fight <= med)){
				numMed++;
				attackType = "Med";
			}
			// If the random int is below this number and above the previous number the attack is aimed high
			if ((fight > med) && (fight <= high)){
				numHigh++;
				attackType = "High";
			}

		}
		// Else statement when prob or sum of all the user's input is not equal to 100
		// the body of the method is the same as above but with default percentages for low, medium and high
		else{
			low = 3;
			med = 6;
			high = 9;
			fight = num.nextInt(MAX) + 1;
			if (fight <= low){
				numLow++;
				attackType = "Low";
			}
			if ((fight > low) && (fight <= med)){
				numMed++;
				attackType = "Med";
			}
			if ((fight > med) && (fight <= high)){
				numHigh++;
				attackType = "High";
			}
		}
		return (attackType);
	}
	// Features: Methods for returning the user's input so that the defender class can display it at the end
	// One method for each attack type
	public float getLow(){
			return (probLow);
	}
	public float getMed(){
			return(probMed);
	}
	public float getHigh(){
			return (probHigh);
	}
	//Features: getting the bounds for each attack type by calculating the correct range between 1-9
	// based on the percentages entered by the user
	//Limitations: needs a parameter
	//Returns the calculated number as a float
	public float getBound(float type){
		float tempFirst;
		tempFirst = (type/100)*9;
		if (type == 0)
			tempFirst = 0;
		return (tempFirst);

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