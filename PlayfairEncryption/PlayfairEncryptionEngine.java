/*
 * Name: Haris Kamran
 * ID#: 112786164
 * Recitation 07
 */

import java.util.Scanner;

/**
 * The PlayfairEncryptionEngine class containing the main method, which implements a user-driven menu for the program.
 * The program first prompts the user for a key phrase to build a new Key Table from, if no input is given, creates a default Key Table.
 * The program allows the user to change the current key, print the current key, enter a plaintext input and receive an encrypted input,
 *   enter an encrypted input and receive a plaintext input, and quit the program.
 * @author haris
 *
 */
public class PlayfairEncryptionEngine 
{
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		
		/**
		 * The string which holds the user input for the menu.
		 */
		String userInput = "";
		
		/**
		 * The string which holds the user input for the key phrase.
		 */
		String keyphrase = "";
		
		/**
		 * The string which holds the user input for phrases to be encrypted or decrypted.
		 */
		String phrase = "";
		
		/**
		 * The KeyTable which stores the created Key Tables.
		 */
		KeyTable k = new KeyTable();
		
		/**
		 * The Phrase which stores the created phrases.
		 */
		Phrase p = new Phrase();
		
		/**
		 * The boolean which determines if the program should continue running.
		 */
		boolean run = true;
		
		System.out.print("Enter key phrase: ");
		keyphrase = scan.nextLine();
		k = KeyTable.buildFromString(keyphrase);
		System.out.println("Generation Success!\n");
		
		while (run)
		{
			System.out.println("\nMenu:\n(CK) - Change Key\n(PK) - Print Key\n(EN) - Encrypt\n(DE) - Decrypt\n(Q) - Quit");
			System.out.print("\nPlease select an option: ");
			userInput = scan.nextLine();
			while (userInput.equals(""))
			{
				System.out.print("Please enter a valid option: ");
				userInput = scan.nextLine();
			}
			
			/**
			 * Change Key Operation
			 */
			if (userInput.equalsIgnoreCase("CK"))
			{
				System.out.print("Enter new key phrase: ");
				keyphrase = scan.nextLine();
				k = KeyTable.buildFromString(keyphrase);
				System.out.println("Generation Success!");
			}
			
			/**
			 * Print Key Operation
			 */
			if (userInput.equalsIgnoreCase("PK"))
			{
				System.out.println(k.toString());
			}
			
			/**
			 * Encrypt Phrase Operation
			 */
			if (userInput.equalsIgnoreCase("EN"))
			{
				System.out.print("Please enter a phrase to encrypt: ");
				phrase = scan.nextLine();
				if (phrase.equals(""))
				{
					System.out.println("Phrase is blank.");
				}
				else
				{
					p = Phrase.buildFromStringforEnc(phrase);
					System.out.println("Encrypted text is: " + p.encrypt(k).toString());	
				}
			}
			
			/**
			 * Decrypt Phrase Operation
			 */
			if (userInput.equalsIgnoreCase("DE"))
			{
				System.out.print("Please enter a phrase to decrypt: ");
				phrase = scan.nextLine();
				if (phrase.equals(""))
				{
					System.out.println("Phrase is blank.");
				}
				else
				{
					p = Phrase.buildFromStringforEnc(phrase);
					System.out.println("Decrypted text is: " + p.decrypt(k).toString());
				}
			}
			
			/**
			 * Quit Program 
			 */
			if (userInput.equalsIgnoreCase("Q"))
			{
				run = false;
				System.out.println("\nProgram terminating....");
			}

		}
		
	}

}
