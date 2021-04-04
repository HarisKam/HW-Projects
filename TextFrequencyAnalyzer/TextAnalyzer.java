/*
Name: Haris Kamran
ID#: 112786164
Recitation 07
*/

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TextAnalyzer class which compares multiple texts to check whether they have the same author or not.
 * @author haris
 *
 */
public class TextAnalyzer 
{
	/**
	 * FrequencyTable which holds frequencylists for the passages.
	 */
	private FrequencyTable f;
	
	public static void main(String[] args)
	{
		
		/**
		 * An ArrayList that holds the passages contained within each file.
		 */
		ArrayList<Passage> passages = new ArrayList<Passage>();
		
		Scanner scan = new Scanner(System.in);
		
		/**
		 * String which holds the name for user entered directory.
		 */
		String directory = "";
		
		while (directory.equals(""))
		{
			System.out.println("Enter the directory of a folder of text files: ");
			directory = scan.nextLine();
		}
		File[] directoryOfFiles = new File(directory).listFiles();
		File stopWords = new File(directory + "StopWords.txt");
		
		for (File s : directoryOfFiles)
		{
			if (s.getName().equalsIgnoreCase("StopWords.txt"))
			{
				stopWords = s;
			}
		}
		
		for(File i : directoryOfFiles)
		{
			if (!(i.getName().equalsIgnoreCase("StopWords.txt")))
			{
				Passage p = new Passage(i.getName(), i, stopWords);
				passages.add(p);
			}
		}
		for (int x = 0; x < passages.size(); x++)
		{
			for (int y = 0; y < passages.size(); y++)
			{
				if (x != y)
				{
					Passage.cosineSimilarity(passages.get(x), passages.get(y));
				}
			}
		}
		System.out.println("Text (title)                  | Similarities (%)\n");
		for (int r = 0; r < passages.size(); r++)
		{
			System.out.println(passages.get(r).toString());
		}
		System.out.println("Suspected Texts with Same Authors\n----------------------------------------------------------------------------");
		for (int d = 0; d < passages.size(); d++)
		{
			for (double cos : passages.get(d).getSimilarTitles().values())
			{
				if (cos >= .60)
				{
	
				}
			}
		}
	}
	

}
