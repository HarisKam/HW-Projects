/*
 * Name: Haris Kamran
 * ID#: 112786164
 * Recitation 07
 */
import java.util.Scanner;

/**
 * The Player class which runs the main method, a user-input driven menu which creates a SongLinkedList,
 *   which functions as a playlist, and allows the user to perform actions through scanner inputs.
 * These actions include:
 * Adding a song to the playlist.
 * Moving the cursor to the next song.
 * Moving the cursor to the previous song.
 * Removing a song from the playlist.
 * Playing a song.
 * Clearing the playlist.
 * Shuffling the playlist.
 * Playing a random song.
 * Printing the playlist.
 * Getting the number of songs in the playlist.
 * Exiting the playlist.
 * @author haris
 *
 */
public class Player 
{

	public static void main(String[] args) 
	{
		/**
		 * The new SongLinkedList (AKA "playlist").
		 */
		SongLinkedList l = new SongLinkedList();
		
		/**
		 * String that stores user input for menu actions.
		 */
		String userInput = "";
		
		/**
		 * String that stores user input for the song's title.
		 */
		String songTitle = "";
		
		/**
		 * String that stores user input for the song's artist.
		 */
		String songArtist = "";
		
		/**
		 * String that stores user input for the song's album.
		 */
		String songAlbum = "";
		
		/**
		 * String that stores user input for the song's length.
		 */
		String lengthInput = "";
		
		/**
		 * Integer that stores user input for the song's length, converted from lengthInput using "Integer.parseInt(lengthInput)".
		 */
		int length = 0;
		
		Scanner scan = new Scanner(System.in);
		
		/**
		 * Variable that determines whether the program is to continue running or not.
		 */
		boolean run = true;
		
		System.out.println("Menu:" + "\n(A) Add Song to Playlist" 
		  + "\n(F) Go to Next Song" + "\n(B) Go to Previous Song" 
		  + "\n(R) Remove Song from Playlist" + "\n(L) Play a Song" 
	   	  + "\n(C) Clear the Playlist" + "\n(S) Shuffle Playlist" + "\n(Z) Random Song" 
		  + "\n(P) Print Playlist" + "\n(T) Get the total amount of songs in the playlist" 
		  + "\n(Q) Exit the playlist");
		
		while (run)
		{
			System.out.print("\nEnter an option: ");
			userInput = scan.nextLine();
		
			/**
			 * Operation for adding a song.
			 */
			if (userInput.equalsIgnoreCase("A"))
			{
				
				System.out.print("Enter song title: ");
				while (true) 
				{	
				songTitle = scan.nextLine();
				if (!(songTitle.equals("")))
						{
							break;
						}
				else
					System.out.print("Enter a valid name for song: ");
				}
				
				System.out.print("Enter artist(s) of the song: ");
				while (true) 
				{	
				songArtist = scan.nextLine();
				if (!(songArtist.equals("")))
						{
							break;
						}
				else
					System.out.print("Enter a valid name for artist(s): ");
				}
				
				System.out.print("Enter album: ");
				while (true) 
				{	
				songAlbum = scan.nextLine();
				if (!(songAlbum.equals("")))
						{
							break;
						}
				else
					System.out.print("Enter a valid name for album: ");
				}
				
				System.out.print("Enter length (in seconds) : ");
				while (true) 
				{
					try
					{
						lengthInput = scan.nextLine();
						length = Integer.parseInt(lengthInput);
						break;
					}
					catch (Exception e)
					{
						System.out.print("Please enter a valid number: ");
					}
				}
				Song s = new Song(songTitle, songArtist, songAlbum, length);
				l.insertAfterCursor(s);
				System.out.println("\n'" + songTitle + "'" + " by " + songArtist + " is added to your playlist.");		
			}
			
			/**
			 * Operation for playing a song.
			 */
			if (userInput.equalsIgnoreCase("L"))
			{
				
				System.out.print("Enter name of song to play: ");
				songTitle = scan.nextLine();
				l.play(songTitle);
			}
			
			if (userInput.equalsIgnoreCase("F"))
			{
				l.cursorForwards();
			}
			
			if (userInput.equalsIgnoreCase("B"))
			{
				l.cursorBackwards();
			}
			
			if (userInput.equalsIgnoreCase("P"))
			{
				System.out.println();
				l.printPlaylist();
			}
			
			if (userInput.equalsIgnoreCase("R"))
			{
				try
				{
					Song s = l.removeCursor();
					System.out.println(s.getName() + " by " + s.getArtist() + " has been removed from your playlist.");
				}
				catch (NullPointerException e)
				{
					System.out.println("Playlist is already empty.");
				}
			}
			
			/**
			 * Operation for clearing the playlist.
			 */
			if (userInput.equalsIgnoreCase("C"))
			{
				l.deleteAll();
				System.out.println("Playlist cleared.");
			}
			
			/**
			 * Operation for shuffling the playlist.
			 */
			if (userInput.equalsIgnoreCase("S"))
			{
				l.shuffle();
			}
			
			/**
			 * Operation for playing a random song in the playlist.
			 */
			if (userInput.equalsIgnoreCase("Z"))
			{
				if (l.getSize()!=0)
				{
					System.out.println("Playing a random song...");
					Song s = l.random();
				}
				else
				{
					System.out.println("The playlist is empty.");
				}
			}
			
			/**
			 * Operation for getting the number of songs in the playlist.
			 */
			if (userInput.equalsIgnoreCase("T"))
			{
				if (l.getSize()==0)
				{
					System.out.println("Your playlist is empty.");
				}
				else 
				{
					System.out.println("Your playlist contains " + l.getSize() + " songs.");
				}
			}
			
			/**
			 * Operation for exiting the playlist.
			 */
			if (userInput.equalsIgnoreCase("Q"))
			{
				run = false;
				System.out.println("Program terminated.");
			}
		}
	}

}
