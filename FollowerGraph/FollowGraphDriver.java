/*
Name: Haris Kamran
ID#: 112786164
Recitation 07
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

/**
 * The FollowGraphDriver class that contains the main method. Allows the user to interact with a FollowGraph and perform actions through the menu.
 * @author haris
 *
 */
public class FollowGraphDriver 
{

	public static void main(String[] args) 
	{
		/**
		 * The FollowGraph that actions are performed with
		 */
		FollowGraph f = new FollowGraph();
		Scanner scan = new Scanner(System.in);
		
		/**
		 * String containing userInput for actions
		 */
		String userInput;
		/**
		 * String containing userInput for names.
		 */
		String stringInput;
		/**
		 * boolean that tells the program to continue operating.
		 */
		boolean run = true;
		
		while (run)
		{
			System.out.println("\n(U) Add User\n(C) Add Connection\n(AU) Load all Users\n(AC) Load all Connections\n(P) Print all Users"
			+  "\n(L) Print all Loops\n(RU) Remove User\n(RC) Remove Connection\n(SP) Find Shortest Path\n(AP) Find All Paths\n(Q) Quit\n");
			String destUser = "";
			String srcUser = "";
			userInput = "";
			stringInput = "";
			while (userInput.equals(""))
			{
				System.out.print("Enter a selection: ");
				userInput = scan.nextLine();
			}
			if (userInput.equalsIgnoreCase("U"))
			{
				while (stringInput.equals(""))
				{
					System.out.print("Please enter the name of the user: ");
					stringInput = scan.nextLine();
				}
				f.addUser(stringInput);
			}
			if (userInput.equalsIgnoreCase("C"))
			{
				while (srcUser.equals(""))
				{
					System.out.print("Please enter the source of the connection to add");
					srcUser = scan.nextLine();
				}
				while (destUser.equals(""))
				{
					System.out.print("Please enter the destination of the connection to add");
					destUser = scan.nextLine();
				}
				f.addConnection(srcUser, destUser);
			}
			if (userInput.equalsIgnoreCase("AU"))
			{
				System.out.print("Enter the file name: ");
				stringInput = scan.nextLine();
				f.loadAllUsers(stringInput);
			}
			if (userInput.equalsIgnoreCase("AC"))
			{
				System.out.print("Enter the file name: ");
				stringInput = scan.nextLine();
				f.loadAllConnections(stringInput);
			}
			if (userInput.equalsIgnoreCase("P"))
			{
				userInput = "";
				while (userInput.equals(""))
				{
					System.out.println("(SA) Sort Users by Name\r\n" + 
							"    (SB) Sort Users by Number of Followers\r\n" + 
							"    (SC) Sort Users by Number of Following\r\n" + 
							"    (Q) Quit");
					System.out.println("Enter a selection: ");
					userInput = scan.nextLine();
				}
				if (userInput.equalsIgnoreCase("SA"))
				{
					f.printAllUsers(new NameComparator());
				}
				if (userInput.equalsIgnoreCase("SB"))
				{
					f.printAllUsers(new FollowersComparator(f));
				}
				if (userInput.equalsIgnoreCase("SC"))
				{
					f.printAllUsers(new FollowingComparator(f));
				}
			}
			if (userInput.equalsIgnoreCase("L"))
			{
				List<String> list = f.findAllLoops();
				System.out.println("There are " + list.size() + " loops");
				for (int x = 0; x < list.size(); x++)
				{
					System.out.println(list.get(x));
				}
			}
			if (userInput.equalsIgnoreCase("RU"))
			{
				while (stringInput.equals(""))
				{
					System.out.print("Please enter the user to remove: ");
					stringInput = scan.nextLine();
				}
			}
			if (userInput.equalsIgnoreCase("RC"))
			{
				while (srcUser.equals(""))
				{
					System.out.print("Please enter the source of the connection to remove");
					srcUser = scan.nextLine();
				}
				while (destUser.equals(""))
				{
					System.out.print("Please enter the destination of the connection to remove");
					destUser = scan.nextLine();
				}
				f.removeConnection(srcUser, destUser);
			}
			if (userInput.equalsIgnoreCase("SP"))
			{
				while (srcUser.equals(""))
				{
					System.out.print("Please enter the desired source: ");
					srcUser = scan.nextLine();
				}
				while (destUser.equals(""))
				{
					System.out.print("Please enter the desired destination: ");
					destUser = scan.nextLine();
				}
				System.out.println(f.shortestPath(srcUser, destUser));
			}
			if (userInput.equalsIgnoreCase("AP"))
			{
				while (srcUser.equals(""))
				{
					System.out.print("Please enter the desired source: ");
					srcUser = scan.nextLine();
				}
				while (destUser.equals(""))
				{
					System.out.print("Please enter the desired destination: ");
					destUser = scan.nextLine();
				}
				List<String> l = f.allPaths(srcUser, destUser);
				if (l != null)
				{
					System.out.println("There are a total of " + l.size() + " paths.");
					for (String s : l)
					{
						System.out.println(l);
					}
				}
			}
			if (userInput.equalsIgnoreCase("Q"))
			{
				run = false;
				System.out.println("Program terminating...");
			}
		}
	}

}
