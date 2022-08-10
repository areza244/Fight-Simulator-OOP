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
// import Scanner class to get user's input
import java.util.Scanner;
// main class for starting the simulation and to communicate between the other two classes
public class Manager
{
	public static void main(String[] args) {
		// Variables for storing the number of rounds and the attack type and the attack types percentages
		int rounds;
		float low;
		float med;
		float high;
		float sum;
		String response;
		Scanner in = new Scanner(System.in);
		// User input for number of rounds
		System.out.print("Enter number of rounds: ");
		rounds = in.nextInt();
		// If the number of rounds is out of this range the default number of 10 will be used
		if ((rounds < 1) || (rounds > 100))
			rounds = 10;
		// Creating objects and instances of the other two classes (Attacker and Defender)
		Attacker att = new Attacker(rounds);
		Defender def = new Defender(rounds);
		// Getting the sum of user's percentages 
		sum = att.getPercent();
		System.out.println();
		System.out.println("    Fight!  ");
		System.out.println("--------------");
		// For loop for the simulation
		int i;
		for (i = 1; i < rounds + 1; i++)
		{
		// Calling the getAttack method and passing the sum variable then storing the return value which is the attack type
			response = att.getAttack(sum);
		// Calling statAttack method to pass it the attack type so that the defender class can learn from these data later on
			def.statAttack(response);
		// Calling defend method and passing the attack type to generate defense type
			def.defend(response);
		// Calling the statDefend method to see if the attack is blocked or not and display the results
			def.statDefend(response);
		}
		// Getting each attack type percentages and passing them to results method in defender class to display them at the end
		low = att.getLow();
		med = att.getMed();
		high = att.getHigh();
		def.results(low,med,high);
	}
}