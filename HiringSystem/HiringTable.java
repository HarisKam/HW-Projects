/*
Name: Haris Kamran
ID#: 112786164
Recitation: Monday 11 am
*/

/**
 * A class that contains an array of Applicants called data. 
 * @author haris
 *
 */
public class HiringTable
{
	/**
	 * The maximum number of skills an applicant may have. 
	 */
	final static int MAX_SKILLS = 3;
	
	/**
	 * THe maximum number of companies an applicant may have.
	 */
	final static int MAX_COMPANIES = 3;
	
	/**
	 * The maximum number of applicants that can be contained in Applicant[] data.
	 */
	final static int MAX_APPLICANTS = 50;
	
	/**
	 * The current number of applicants in Applicant[] data.
	 */
	public int numApplicants = 0;
	
	/**
	 * The array of Applicants
	 */
	public Applicant[] data;
	
	/**
	 * The constructor for HiringTable.
	 * Creates an Applicant[] called data with a length equal to MAX_APPLICANTS.
	 */
	public HiringTable()
	{
		data = new Applicant[MAX_APPLICANTS];
	}

	/**
	 * A method that returns the number of Applicants in the HiringTable.
	 * @return
	 * 		numApplicants
	 */
	public int size()
	{
		return numApplicants;
	}
	
	/**
	 * A method that creates a new Applicant and adds it to the next empty space in data.
	 * @param newApplicant
	 * 		The Applicant that will be added to data.
	 * @throws FullTableException
	 * 		Indicates that there is no space in data for a new Applicant.
	 */
	public void addApplicant(Applicant newApplicant) throws FullTableException
	{
		if (numApplicants >= MAX_APPLICANTS)
		{
			throw new FullTableException("error");
		}
		data[numApplicants] = new Applicant();
		numApplicants++;
	}
	
	/**
	 * A method that searches data for an Applicant whose applicantName is equal to name, and removes it from data.
	 * All other Applicants after the removed Applicant are moved one space up, so there are no empty spaces in between.
	 * @param name
	 * 		The name of the Applicant the user wants to remove.
	 * @throws ApplicantNotFoundException
	 * 		Indicates that no Applicant in data has an applicantName equal to name.
	 */		
	public void removeApplicant(String name) throws ApplicantNotFoundException
	{
		int c = numApplicants;
		for (int x = 0; x < numApplicants; x++)
		{
			if (data[x].getApplicantName().equals(name))
			{
				for (int a = x; a < numApplicants; a++)
				{
					data[a] = data[a+1];
				}
				numApplicants--;
			}
		}
		if (c == numApplicants)
			throw new ApplicantNotFoundException("error");
	}
	
	/**
	 * Searches data for an Applicant with an applicantName equal to name.
	 * @param name
	 * 		The name of the Applicant to be returned.
	 * @return
	 * 		The Applicant with an applicantName equal to name.
	 * @throws ApplicantNotFoundException
	 * 		Indicates that no Applicant with the specified name was found in data.
	 */
	public Applicant getApplicant(String name) throws ApplicantNotFoundException
	{

		for (int x = 0; x < numApplicants; x++)
		{
			if (data[x].getApplicantName().equals(name))
			{
				return data[x];
			}
		}
		throw new ApplicantNotFoundException("error");
	}
	
	/**
	 * Searches table.data[] for Applicants that match the parameters specified. Prints all Applicants that qualify.
	 * @param table
	 * 		The HiringTable that will be searched.
	 * @param company
	 * 		The company that will be filtered for.
	 * @param skill
	 * 		The skill that will be filtered for.
	 * @param college
	 * 		The college that will be filtered for.
	 * @param GPA
	 * 		The minimum GPA that will be filtered for.
	 */
	public static void refineSearch(HiringTable table, String company, String skill, String college, double GPA)
	{
		System.out.println(String.format("%-30s%-15s%-10s%-18s%s", "Company Name", "Applicant", "GPA", "College", "Skills"));
		
		for (int d = 0; d < 100; d++)
		{
			System.out.print("-");
		}
			System.out.println();
		
		int counter = 0;
		if (!(company.equals("")))
			counter++; 
		if (!(skill.equals("")))
			counter++; 
		if (!(college.equals("")))
			counter++; 
		if (GPA != 0)
			counter++; 
		
		for (int x = 0; x < table.numApplicants; x++)
		{
			int check = 0;
			for (int c = 0; c < table.data[x].numCompanies; c++)
			{
				if (table.data[x].getCompanyName()[c].equals(company))
				{
					check++;
				}
			}
			for (int s = 0; s < table.data[x].numSkills; s++)
			{
				
				if (table.data[x].getApplicantSkills()[s].equals(skill))
				{
					check++;
				}
			}
				if (table.data[x].getApplicantCollege().equals(college))
				{
					check++;
				}
				if (table.data[x].getApplicantGPA() >= GPA)
				{
					if (GPA == 0) 
					{			
					}
					else check++;
				}
				if (check >= counter)
				{
					System.out.println(table.data[x].toString());
				}
			}
		}
	
	/**
	 * A method that creates a deep clone of the HiringTable, creating a clone of all Applicants for the data[] of the clone.
	 */
	public Object clone()
	{
		HiringTable a = new HiringTable();
		for (int c = 0; c < this.numApplicants; c++)
		{
			a.data[c] = (Applicant) this.data[c].clone();
		}
		a.numApplicants = this.numApplicants;
		return a;
	}
	
	/**
	 * Prints the information for each Applicant in HiringTable.data[].
	 */
	public void printApplicantTable()
	{
		System.out.println(String.format("%-30s%-15s%-10s%-18s%s", "Company Name", "Applicant", "GPA", "College", "Skills"));
		for (int d = 0; d < 100; d++)
		{
			System.out.print("-");
		}
		System.out.println();
		for (int a = 0; a < this.numApplicants; a++)
		{
			System.out.println(this.data[a].toString());
		}
	}
}

