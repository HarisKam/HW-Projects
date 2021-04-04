/*
 * Name: Haris Kamran
 * ID#: 112786164
 * Recitation 07
 */

/**
 * The Key Table class which contains a key, a 5x5 matrix of characters.
 * @author haris
 *
 */
public class KeyTable
{
	/**
	 * The 2-D array of characters used as the key 
	 */
	char [][] key = new char [5][5];

	/**
	 * The Default Constructor for the Key Table
	 */
	public KeyTable()
	{
		
	}
	
	/**
	 * The constructor with variables for the Key Table
	 * @param key
	 * 		The 2-D array to be set as the key
	 */
	public KeyTable(char[][] key)
	{
		this.key = key;
	}
	
	/**
	 * A Static method which builds a new Key Table from a String keyphrase.
	 * Non-duplicate letters are taken from the keyphrase and inserted into the key, then the rest of the alphabet (excluding j) is inserted into the key.
	 * @param keyphrase
	 * 		The String used to create the new Key Table
	 * @return
	 * 		The newly created Key Table
	 * @throws IllegalArgumentException
	 * 		Indicates the the keyphrase is null
	 */
	public static KeyTable buildFromString(String keyphrase) throws IllegalArgumentException
	{
		if (keyphrase == null)
		{
			throw new IllegalArgumentException();
		}
		String newPhrase = "";
		for (int x = 0; x < keyphrase.length(); x++)
		{
			if (Character.isLetter(keyphrase.charAt(x)))
			{
				char currentChar = keyphrase.charAt(x);
				if (currentChar == 'j')
				{
					currentChar = 'i';
				}
				boolean unique = true;
				for (int a = 0; a < newPhrase.length(); a++)
				{
					if (newPhrase.charAt(a) == currentChar)
					{
						unique = false;
					}
				}
				if (unique)
				{
					newPhrase += currentChar;
				}
			}
		}
		
		newPhrase = newPhrase.toUpperCase();
		String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
		for (int l = 0; l < newPhrase.length(); l++)
		{
			if (alphabet.indexOf((newPhrase.charAt(l))) != -1)
			{
				alphabet = alphabet.substring(0, alphabet.indexOf(newPhrase.charAt(l)))
						+ alphabet.substring(alphabet.indexOf(newPhrase.charAt(l))+1);
			}
		}

		int counter = 0;
		int counter2 = 0;
		char [][] newKey = new char [5][5];
		
		for (int r = 0; r < newKey.length; r++)
		{
			for (int c = 0; c < newKey[r].length; c++)
			{
				if (newPhrase.length() <= counter)
				{
					newKey[r][c] = alphabet.charAt(counter2);
					counter2++;
				}
				else
				{
					newKey [r][c] = newPhrase.charAt(counter);
					counter++;
				}
			}
		}

		KeyTable newKT = new KeyTable(newKey);
		return newKT;
	}
	
	/**
	 * getter method
	 * @return
	 * 		the key of this Key Table
	 */
	public char[][] getKeyTable()
	{
		return this.key;
	}
	
	/**
	 * A method to find the row of a character in key
	 * @param c
	 * 		The character to be searched for
	 * @return
	 * 		The row in key in which the character is found
	 * @throws IllegalArgumentException
	 * 		Indicates the the character c is not a letter
	 */
	public int findRow(char c) throws IllegalArgumentException
	{
		if (Character.isLetter(c) == false)
		{
			throw new IllegalArgumentException();
		}
		for (int row = 0; row < key.length; row++)
		{
			for (int col = 0; col < key[row].length; col++)
			{
				if (key[row][col] == c)
				{
					return row;
				}
			}
		}
		return -1;
	}
	
	/**
	 * A method to find the column of a character in key
	 * @param c
	 * 		The character to be searched for
	 * @return
	 * 		The column in key in which the character is found
	 * @throws IllegalArgumentException
	 * 		Indicates the the character c is not a letter
	 */	public int findCol(char c) throws IllegalArgumentException
	{
		if (Character.isLetter(c) == false)
		{
			throw new IllegalArgumentException();
		}
		for (int row = 0; row < key.length; row++)
		{
			for (int col = 0; col < key[row].length; col++)
			{
				if (key[row][col] == c)
				{
					return col;
				}
			}
		}
		return -1;
	}	
	
	 /**
	  * The toString method which returns a String representation of the KeyTable, with spaces between each character of a row and a new line for each row.
	  */
	public String toString()
	{
		String printString = "\n";
		for (int row = 0; row < key.length; row++)
		{
			for (int col = 0; col < key[row].length; col++)
			{
				printString += key[row][col] + " ";
			}
			printString += "\n";
		}
		return printString;
	}
}
