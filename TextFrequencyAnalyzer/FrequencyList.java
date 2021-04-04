/*
Name: Haris Kamran
ID#: 112786164
Recitation 07
*/

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The FrequencyList class which contains information to determine how many times a word appears in a passage.
 * @author haris
 *
 */
public class FrequencyList
{
	/**
	 * The word that this FL refers to
	 */
	private String word;
	
	/**
	 * The ArrayList which holds values for occurrences of the word
	 */
	private ArrayList <Integer> frequencies = new ArrayList<Integer>();
	
	/**
	 * The HashTable which holds the passage titles and their index in frequencies arraylist.
	 */
	private Hashtable<String, Integer> passageIndices = new Hashtable<String, Integer>();
	
	/**
	 * FrequencyList constructor which creates a frequencyList given a word and an arraylist of passages.
	 * @param word
	 * @param passages
	 */
	public FrequencyList(String word, ArrayList<Passage> passages)
	{
		for (int x = 0; x < passages.size(); x++)
		{
			if (passages.get(x).get(word) != null)
			{
				passageIndices.put(passages.get(x).getTitle(), frequencies.size());
				frequencies.add(passages.get(x).get(word));
			}
		}
	}
	
	/**
	 * Adds a passage to FL and updates values accordingly.
	 * @param p
	 * 		The passage to be added.
	 */
	public void addPassage(Passage p)
	{
		if (word != null)
		{
			if (p.get(word) != null)
			{
				passageIndices.put(p.getTitle(), frequencies.size());
				frequencies.add(p.get(word));
			}
		}
	}
	
	/**
	 * getter method
	 * @param p
	 * 		The passage to find the frequency of word
	 * @return
	 * 		the frequency of word in passage p
	 */
	public int getFrequency(Passage p)
	{
		if (passageIndices.get(p.getTitle()) == null)
		{
			return 0;
		}
		return frequencies.get(passageIndices.get(p.getTitle()));
	}
	
	/**
	 * getter method
	 * @return
	 * 		The word of this FL.
	 */
	public String getWord()
	{
		return this.word;
	}
}
