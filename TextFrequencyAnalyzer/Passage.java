/*
Name: Haris Kamran
ID#: 112786164
Recitation 07
*/

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The Passage class which contains information for words in a passage and the frequency of which they appear.
 * @author haris
 *
 */
public class Passage extends Hashtable<String, Integer>
{
	/**
	 * ArrayList which holds the stopWords
	 */
	private ArrayList<String> stopWords = new ArrayList<String>();
	
	/**
	 * title of the passage
	 */
	private String title;
	
	/**
	 * number of words in the passage
	 */
	private int wordCount;
	
	/**
	 * Hashtable which holds information for other passages and their similarity to this passage.
	 */
	private Hashtable<String, Double> similarTitles = new Hashtable<String, Double>();
	
	/**
	 * char [] of alphabets
	 */
	char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	/**
	 * Constructor with parameters for Passage
	 * @param title
	 * 		String for title
	 * @param file
	 * 		File used in parseFile
	 */
	public Passage(String title, File file)
	{
		this.title = title;
		this.parseFile(file);
	}
	
	/**
	 * Constructor with parameters (stopwords) for Passage
	 * @param title
	 * 		String for title
	 * @param file
	 * 		File used in parseFile
	 * @param stopFile
	 * 		File containing the stopWords
	 */
	public Passage(String title, File file, File stopFile)
	{
		this.title = title;
		this.setStopWords(stopFile);
		this.parseFile(file);
		
	}
	
	/**
	 * method which goes through a given file and adds words to this Passage's HashTable.
	 * @param file
	 * 		the File to be parsed through
	 */
	public void parseFile (File file)
	{
		FileInputStream fis;
		try 
		{
			fis = new FileInputStream(file);
			InputStreamReader inStream = new InputStreamReader(fis);
			BufferedReader stdin = new BufferedReader(inStream);
			String line = stdin.readLine();
			
			while (line != null)
			{
				line = line.toLowerCase();
				for (int x = 0; x < line.length(); x++)
				{
					boolean remove = true;
					for (int y = 0; y < alphabet.length; y++)
					{
						if (line.charAt(x) == alphabet[y])
						{
							remove = false;
						}
					}
					if ((remove) && (!(line.substring(x, x+1).isBlank())))
					{
						line = line.substring(x+1);
						x = 0;
					}
				}	
				while (!(line.isBlank()))
				{
					if (line.indexOf(" ") == -1)
					{
						boolean valid = true;
						for (String stopWords : this.getStopWords())
						{
							if (line.equalsIgnoreCase(stopWords))
							{
								valid = false;
							}
						}
						if (valid)
						{
							if (this.get(line) != null)
							{
								this.put(line, this.get(line)+1);
							}
							else
							{
								this.put(line, 1);
							}
						}
						line = "";
					}
					for (int p = 0; p < line.length(); p++)
					{
						if (line.isBlank())
						{
							break;
						}
						if (line.substring(p, p+1).equals(" "))
						{
							boolean isValid = true;
							String word = line.substring (0, p);
							if (this.getStopWords() == null)
							{
								
							}
							for (String stopWords : this.getStopWords())
							{
								if (word.equalsIgnoreCase(stopWords))
								{
									isValid = false;
								}
							}
							if (isValid)
							{
								if (this.get(word) != null)
								{
									this.put(word, this.get(word)+1);
								}
								else
								{
									this.put(word, 1);
								}
							}
							line = line.substring(p+1);
							p = -1;
						}
					}
				}
				line = stdin.readLine();	
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: FILE NOT FOUND.");
		}
		catch (IOException e) 
		{
			System.out.println("ERROR.");
		}
	}

	/**
	 * Calculates the similarity between two passages.
	 * @param passage1
	 * 		the first passage
	 * @param passage2
	 * 		the second passage
	 * @return
	 * 		A double containing the similarity as a decimal percentage.
	 */
	public static double cosineSimilarity(Passage passage1, Passage passage2)
	{
		double uSum = 0;
		double vSum = 0;
		double topSum = 0;
		double cosine = 0; 
		ArrayList<String> wordList = new ArrayList<String>();
		for (String words : passage1.keySet())
		{
			wordList.add(words);
		}
		for (String f : passage2.keySet())
		{
			boolean newWord = true;
			for (String word : wordList)
			{
				if (f.equalsIgnoreCase(word))
				{
					newWord = false;
				}
			}
			if (newWord)
			{
				wordList.add(f);
			}
		}
		
		for (String words : wordList)
		{
			topSum += (passage1.getWordFrequency(words))*(passage2.getWordFrequency(words));
			uSum += Math.pow(passage1.getWordFrequency(words), 2);
			vSum += Math.pow(passage2.getWordFrequency(words), 2);
		}
		uSum = Math.sqrt(uSum);
		vSum = Math.sqrt(vSum);
		cosine = (topSum / (uSum*vSum));
		passage1.getSimilarTitles().put(passage2.getTitle(), cosine);
		passage2.getSimilarTitles().put(passage1.getTitle(), cosine);
		return cosine;
	}
	
	/**
	 * Returns the number of times the word appears / the wordCount
	 * @param word
	 * 		The word to find the frequency of
	 * @return
	 * 		The frequency of the word.
	 */
	public double getWordFrequency(String word)
	{
		double sum = 0;
		for (String s : this.keySet())
		{
			sum += this.get(s);
		}
		if (this.get(word) == null)
		{
			return 0;
		}
		return (this.get(word) / sum);
	}
	
	/**
	 * getter method
	 * @return
	 * 		this.keySet()
	 */
	public Set<String> getWords()
	{
		return this.keySet();
	}
	
	/**
	 * getter method
	 * @return
	 * 		title
	 */
	public String getTitle()
	{
		return this.title;
	}
	
	/**
	 * setter method
	 * @param title
	 * 		String to be set as title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	/**
	 * getter method
	 * @param word
	 * 		the given word
	 * @return
	 * 		the number of times (word) appears
	 */
	public int getWordCount(String word)
	{
		return this.get(word);
	}
	
	/**
	 * setter method
	 * @param wordCount
	 * 		the integer to be set as wordCount
	 */
	public void setWordCount(int wordCount)
	{
		this.wordCount = wordCount;
	}
	
	/**
	 * getter method
	 * @return
	 * 		the similarTitles Hashtable
	 */
	public Hashtable<String, Double> getSimilarTitles()
	{
		return this.similarTitles;
	}
	
	/**
	 * setter method
	 * @param h
	 * 		The hashtable to be set as similarTitles
	 */
	public void setSimilarTitles(Hashtable<String, Double> h)
	{
		this.similarTitles = h;
	}
	
	/**
	 * getter method
	 * @return
	 * 		the stopWords
	 */
	public ArrayList<String> getStopWords()
	{
		return this.stopWords;
		
	}
	
	/**
	 * setter method
	 * @param file
	 * 		The file containing the stop words
	 */
	public void setStopWords(File file)
	{
		try 
		{
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader inStream = new InputStreamReader(fis);
			BufferedReader stdin = new BufferedReader(inStream);
			String line = stdin.readLine();
			while (line != null)
			{
				this.stopWords.add(line);
				line = stdin.readLine();
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("ERROR: File not found.");
		} 
		catch (IOException e) 
		{
			System.out.println("ERROR");
		}
	}
	
	/**
	 * Prints a string representation of this Passage and its similartitles.
	 */
	public String toString()
	{
		String s = "----------------------------------------------------------------------------\n" + this.title;
		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<Double> frequencies = new ArrayList<Double>();
		for (String title : this.similarTitles.keySet())
		{
			titles.add(title);
		}
		for (double d : this.similarTitles.values())
		{
			frequencies.add(d);
		}
		for (int x = 0; x < this.similarTitles.size(); x++)
		{
			if (x == 0)
			{
				for (int b = this.title.length(); b < 30; b++)
				{
					s += " ";
				}
				s += "| ";
			}
			if (x >= 2 && x%2 == 0)
			{
				s += "\n\t\t\t      | ";
			}
			s += titles.get(x) + "(" + ((int)(frequencies.get(x)*100)) + "%), ";
		}
		
		return s;
	}
}
