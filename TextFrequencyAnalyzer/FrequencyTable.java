/*
Name: Haris Kamran
ID#: 112786164
Recitation 07
*/

import java.util.ArrayList;
import java.util.Set;

/**
 * The FrequencyTable class which implements an ArrayList of FrequencyLists
 * @author haris
 *
 */
public class FrequencyTable extends ArrayList<FrequencyList>
{
	/**
	 * builds a FT given an arraylist of passages
	 * @param passages
	 * 		the arraylist of passages
	 * @return
	 * 		the new FT
	 */
	public static FrequencyTable buildTable(ArrayList <Passage> passages)
	{
		FrequencyTable f = new FrequencyTable();
		for (int x = 0; x < passages.size(); x++)
		{
			Set<String> currentWordList = passages.get(x).keySet();
			for (String word: currentWordList)
			{
				boolean isNew = true;
				for (int a = 0; a < f.size(); a++)
				{
					if (word.equalsIgnoreCase(f.get(x).getWord()))
					{
						isNew = false;
					}
				}
				if (isNew)
				{
					FrequencyList w = new FrequencyList(word, passages);
					f.add(w);
				}
			}
			
		}
		return f;
	}
	
	/**
	 * Adds a passage to the FT and updates values accordingly
	 * @param p
	 * 		the passage to be added
	 * @throws IllegalArgumentException
	 * 		If the given passage is null or empty.
	 */
	public void addPassage(Passage p) throws IllegalArgumentException
	{
		Set<String> currentWordList = p.keySet();
		for (String word: currentWordList)
		{
			for (int x = 0; x < this.size(); x++)
			{
				if (word.equalsIgnoreCase(this.get(x).getWord()))
				{
					this.get(x).addPassage(p);
				}
				else
				{
					ArrayList<Passage> pList = new ArrayList<Passage>();
					FrequencyList w = new FrequencyList(word, pList);
					w.addPassage(p);
					this.add(w);
				}
			}
		}
	}
	
	/**
	 * getter method for frequency of a word in a passage
	 * @param word
	 * 		The word
	 * @param p
	 * 		the passage
	 * @return
	 * 		the frequency of the word in the passage
	 */
	public int getFrequency(String word, Passage p)
	{
		for (int x = 0; x < this.size(); x++)
		{
			if (this.get(x).getWord().equalsIgnoreCase(word))
			{
				return this.get(x).getFrequency(p);
			}
		}
		return 0;
	}
}
