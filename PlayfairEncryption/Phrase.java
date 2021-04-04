/*
 * Name: Haris Kamran
 * ID#: 112786164
 * Recitation 07
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * The Phrase class which implements a Queue of Bigrams using a Linked List
 * @author haris
 *
 */
public class Phrase extends LinkedList<Bigram> implements Queue<Bigram>
{
	/**
	 * Enqueue method, which adds a Bigram to the end of the Queue
	 * @param b
	 */
	public void enqueue(Bigram b)
	{
		this.add(b);
	}
	
	/**
	 * Dequeue method, which removes a Bigram from the front of the queue and returns it
	 * @return
	 * 		The bigram at the front of the queue
	 */
	public Bigram dequeue()
	{
		return this.remove();
	}
	
	/**
	 * Peek method, which returns the Bigram at the front of the queue
	 */
	public Bigram peek()
	{
		return this.peekFirst();
	}
	
	/**
	 * Builds a new Phrase from a String. First, gets all letters from the string, converting 'j' to 'i'.
	 * Then creates Bigrams from the String, handling cases for duplicates. 
	 * Enqueues each Bigram that is created into the Phrase, then returns the Phrase.
	 * @param s
	 * 		The String to build the Phrase from.
	 * @return
	 * 		The newly created Phrase.
	 */
	public static Phrase buildFromStringforEnc(String s)
	{
		Phrase p = new Phrase();
		if (s.equals("") || s == null)
		{
			return null;
		}
		String newPhrase = "";
		for (int x = 0; x < s.length(); x++)
		{
			if (Character.isLetter(s.charAt(x)))
			{
				char currentChar = s.charAt(x);
				if (currentChar == 'j')
				{
					currentChar = 'i';
				}
				newPhrase += currentChar;
			}
		}
		newPhrase = newPhrase.toUpperCase();
		
		if (newPhrase.length() == 0)
		{
			return null;
		}
		
		for (int counter = 0; counter < newPhrase.length(); counter+= 2)
		{
			Bigram b = new Bigram();
			b.setFirst(newPhrase.charAt(counter));
			if (counter+1 == newPhrase.length())
			{
				b.setSecond('X');
			}
			else
			{
				if (newPhrase.charAt(counter) == newPhrase.charAt(counter+1))
				{
					b.setSecond('X');
					counter--;
				}
				else
				{
					b.setSecond(newPhrase.charAt(counter+1));
				}
			}
			p.enqueue(b);
		}
		return p;
	}
	
	/**
	 * The encryption method which takes a Phrase and encrypts it based on the given key, returning the encrypted Phrase.
	 * @param key
	 * 		The Key Table used to encrypt the Phrase
	 * @return
	 * 		The encrypted phrase
	 * @throws IllegalArgumentException
	 * 		Indicates that the Key Table is null.
	 */
	public Phrase encrypt(KeyTable key) throws IllegalArgumentException
	{
		if (key == null)
		{
			throw new IllegalArgumentException();
		}
		Phrase encryptedPhrase = new Phrase();
		while (!(this.isEmpty()))
		{
			Bigram b = this.dequeue();
			if (key.findRow(b.getFirst()) == key.findRow(b.getSecond()))
			{
				Bigram e = this.encryptSameRow(key, b, key.findRow(b.getFirst()));
				encryptedPhrase.enqueue(e);
			}
			else if (key.findCol(b.getFirst()) == key.findCol(b.getSecond()))
			{
				Bigram e = this.encryptSameCol(key, b, key.findCol(b.getFirst()));
				encryptedPhrase.enqueue(e);
			}
			else
			{
				Bigram e = this.encryptDiagonal(key, b);
				encryptedPhrase.enqueue(e);
			}
		}
		return encryptedPhrase;
	}
	
	/**
	 * The encryption method called when characters of the bigram are on the same row. Sets the characters equal to the next value in the row, 
	 * 	wrapping around if necessary.
	 * @param keyT
	 * 		The KeyTable used for encryption
	 * @param b
	 * 		The bigram to be encrypted
	 * @param row
	 * 		The row which the letters of the bigram are in
	 * @return
	 * 		The encrypted bigram
	 */
	public Bigram encryptSameRow(KeyTable keyT, Bigram b, int row)
	{
		Bigram a = new Bigram();
		if (keyT.findCol(b.getFirst()) == keyT.key[0].length-1)
		{
			a.setFirst(keyT.key[row][0]);
		}
		else
		{
			a.setFirst(keyT.key[row][keyT.findCol(b.getFirst())+1]);
		}
		if (keyT.findCol(b.getSecond()) == keyT.key[0].length-1)
		{
			a.setSecond(keyT.key[row][0]);
		}
		else
		{
			a.setSecond(keyT.key[row][keyT.findCol(b.getSecond())+1]);
		}
		return a;
	}
	
	/**
	 * The encryption method called when characters of a bigram are in the same column. Sets the characters equal to the next value in the column, 
	 * 	wrapping around if necessary.
	 * @param keyT
	 * 		The Key Table used for encryption
	 * @param b
	 * 		The bigram to be encrypted
	 * @param col
	 * 		The column which contains the letters of the bigram
	 * @return
	 * 		The encrypted bigram
	 */
	public Bigram encryptSameCol(KeyTable keyT, Bigram b, int col)
	{
		Bigram a = new Bigram();
		if (keyT.findRow(b.getFirst()) == keyT.key.length-1)
		{
			a.setFirst(keyT.key[0][col]);
		}
		else
		{
			a.setFirst(keyT.key[keyT.findRow(b.getFirst())+1][col]);
		}
		if (keyT.findRow(b.getSecond()) == keyT.key[0].length-1)
		{
			a.setSecond(keyT.key[0][col]);
		}
		else
		{
			a.setSecond(keyT.key[keyT.findRow(b.getSecond())+1][col]);
		}
		return a;
	}
	
	/**
	 * The encryption (and decryption) method called when characters of the bigram are not in the same row or column. 
	 * 	Sets the characters equal to the value in the same row, but column index of the other character in the Key Table.
	 * @param keyT
	 * 		The Key Table used for encryption
	 * @param b
	 * 		The bigram to be encrypted
	 * @return
	 * 		The encrypted bigram
	 */
	public Bigram encryptDiagonal(KeyTable keyT, Bigram b)
	{
		Bigram a = new Bigram();
		a.setFirst(keyT.key[keyT.findRow(b.getFirst())][keyT.findCol(b.getSecond())]);
		a.setSecond(keyT.key[keyT.findRow(b.getSecond())][keyT.findCol(b.getFirst())]);
		return a;
	}
	
	/**
	 * The decryption method which takes each Bigram of the Phrase and decrypts it, storing it in a new Phrase and returning that Phrase when finished.
	 * @param key
	 * 		The Key Table used for decryption
	 * @return
	 * 		The decrypted phrase
	 * @throws IllegalArgumentException
	 * 		Indicates that the Key Table is null
	 */
	public Phrase decrypt(KeyTable key) throws IllegalArgumentException
	{
		if (key == null)
		{
			throw new IllegalArgumentException();
		}
		Phrase decryptedPhrase = new Phrase();
		while (!(this.isEmpty()))
		{
			Bigram b = this.dequeue();
			if (key.findRow(b.getFirst()) == key.findRow(b.getSecond()))
			{
				Bigram e = this.decrpytSameRow(key, b, key.findRow(b.getFirst()));
				decryptedPhrase.enqueue(e);
			}
			else if (key.findCol(b.getFirst()) == key.findCol(b.getSecond()))
			{
				Bigram e = this.decrpytSameCol(key, b, key.findCol(b.getFirst()));
				decryptedPhrase.enqueue(e);
			}	
			else
			{
				Bigram e = this.encryptDiagonal(key, b);
				decryptedPhrase.enqueue(e);
			}
		}
		return decryptedPhrase;
	}
	
	/**
	 * Decryption method when characters of the bigram are on the same row.
	 * @param keyT
	 * 		The Key Table used for decryption
	 * @param b
	 * 		The bigram to be decrypted
	 * @param row
	 * 		The row which contains the characters of the bigram
	 * @return
	 */
	public Bigram decrpytSameRow(KeyTable keyT, Bigram b, int row)
	{
		Bigram a = new Bigram();
		if (keyT.findCol(b.getFirst()) == 0)
		{
			a.setFirst(keyT.key[row][keyT.key[row].length-1]);
		}
		else
		{
			a.setFirst(keyT.key[row][keyT.findCol(b.getFirst())-1]);
		}
		if (keyT.findCol(b.getSecond()) == 0)
		{
			a.setSecond(keyT.key[row][keyT.key[row].length-1]);
		}
		else
		{
			a.setSecond(keyT.key[row][keyT.findCol(b.getSecond())-1]);
		}
		return a;
	}
	
	/**
	 * The decryption method called when characters of a bigram are in the same column.
	 * @param keyT
	 * 		The Key Table used for decryption
	 * @param b
	 * 		The bigram to be decrypted
	 * @param col
	 * 		The column which contains the characters of the bigram
	 * @return
	 * 		The decrypted bigram
	 */
	public Bigram decrpytSameCol(KeyTable keyT, Bigram b, int col)
	{
		Bigram a = new Bigram();
		if (keyT.findRow(b.getFirst()) == 0)
		{
			a.setFirst(keyT.key[keyT.key.length-1][col]);
		}
		else
		{
			a.setFirst(keyT.key[keyT.findRow(b.getFirst())-1][col]);
		}
		if (keyT.findCol(b.getSecond()) == 0)
		{
			a.setSecond(keyT.key[keyT.key.length-1][col]);
		}
		else
		{
			a.setSecond(keyT.key[keyT.findRow(b.getSecond())-1][col]);
		}
		return a;
	}
	
	/**
	 * The toString method which returns a String representation of the phrase, containing the characters of all the Bigrams in the Phrase.
	 */
	public String toString()
	{
		String printString = "";
		Phrase f = new Phrase();
		while (!(this.isEmpty()))
		{
			Bigram temp = this.dequeue();
			printString += temp.toString();
			f.enqueue(temp);
		}
		while(!(f.isEmpty()))
		{
			this.enqueue(f.dequeue());
		}
		return printString;
	}	
}
