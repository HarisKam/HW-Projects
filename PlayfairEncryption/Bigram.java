/*
 * Name: Haris Kamran
 * ID#: 112786164
 * Recitation 07
 */

/**
 * Bigram class which contains two characters
 * @author haris
 *
 */
public class Bigram 
{
	/**
	 * The first character in the Bigram
	 */
	private char first;
	
	/**
	 * The second character in the Bigram
	 */
	private char second;
	
	/**
	 * Default constructor for the Bigram
	 */
	public Bigram()
	{
		
	}
	
	/**
	 * Constructor with variables for the bigram
	 * @param first
	 * 		Character to be set to first
	 * @param second
	 * 		Character to be set to second
	 */
	public Bigram(char first, char second)
	{
		this.first = first;
		this.second = second;
	}
	
	/**
	 * toString method, which returns both of the characters of the bigram in a concatenated String.
	 */
	public String toString()
	{
		String s = Character.toString(first) + Character.toString(second);
		return s;
	}
	
	/**
	 * setter method
	 * @param first
	 * 		the character to be set to first
	 */
	public void setFirst(char first)
	{
		this.first = first;
	}
	
	/**
	 * setter method
	 * @param second
	 * 		the character to be set to second
	 */
	public void setSecond(char second)
	{
		this.second = second;
	}
	
	/**
	 * getter method
	 * @return
	 * 		the first character of the bigram
	 */
	public char getFirst()
	{
		return this.first;
	}
	
	/**
	 * getter method
	 * @return
	 * 		the second character of the bigram
	 */
	public char getSecond()
	{
		return this.second;
	}
	
}
