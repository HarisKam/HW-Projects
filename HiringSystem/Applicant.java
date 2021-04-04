/*
Name: Haris Kamran
ID#: 112786164
Recitation: Monday 11 am
*/
import java.util.Scanner;

/**
 * The Applicant class which contains information for an Applicant.
 * @author haris
 *
 */
public class Applicant 
{
	Scanner scan = new Scanner(System.in);
	/**
	 * An array of Strings which contains the names of each of the Applicant's companies.
	 */
	private String[] companyName = new String[HiringTable.MAX_COMPANIES];
	
	/**
	 * The Applicant's name.
	 */
	private String applicantName;
	
	/**
	 * The GPA of the Applicant.
	 */
	private double applicantGPA;
	
	/**
	 * The Applicant's College.
	 */
	private String applicantCollege;
	
	/**
	 * An array of Strings containing the skills of the Applicant.
	 */
	private String[] applicantSkills = new String[HiringTable.MAX_SKILLS];

	/**
	 * Used to compile each String in companyName into one formatted String.
	 */
	public String compString = "";
	
	/**
	 * Used to compile each String in applicantSkills into one formatted String.
	 */
	public String skillString = "";
	
	/**
	 * The number of companies that the Applicant has.
	 */
	public int numCompanies = 0;
	
	/**
	 * The number of skills that the Applicant has.
	 */
	public int numSkills = 0;

	/**
	 * Default constructor for Applicant Object
	 */
	public Applicant()
	{
	
	}

	/**
	 * Other constructor for Applicant Object.
	 * @param compName
	 * 		String set to companyName
	 * @param appName
	 * 		String set to applicantName
	 * @param appGPA
	 * 		Double set to applicantGPA
	 * @param appCollege
	 * 		String set to applicantCollege
	 * @param appSkills
	 * 		String set to applicantSkills
	 */		
	public Applicant(String[] compName, String appName, double appGPA, String appCollege, String[] appSkills)
	{
		companyName = compName;
		applicantName = appName;
		applicantGPA = appGPA;
		applicantCollege = appCollege;
		applicantSkills = appSkills;
	}

	/**
	 * getter method 
	 * @return
	 * 		companyName
	 */
	public String[] getCompanyName()
	{
		return this.companyName;
	}

	/**
	 * getter method
	 * @return
	 * 		applicantName
	 */	
	public String getApplicantName()
	{
		return this.applicantName;
	}

	/**
	 * getter method
	 * @return
	 * 		applicantGPA
	 */
	public double getApplicantGPA()
	{
		return this.applicantGPA;
	}

	/**
	 * getter method
	 * @return
	 * 		applicantCollege
	 */
	public String getApplicantCollege()
	{
		return this.applicantCollege;
	}

	/**
	 * getter method
	 * @return
	 * 		applicantSkills
	 */
	public String[] getApplicantSkills()
	{
		return this.applicantSkills;
	}
	
	/**
	 * setter method
	 * @param name
	 * 		String for applicantName
	 */
	public void setApplicantName(String name)
	{
		this.applicantName = name;
	}
	
	/**
	 * setter method
	 * @param compName
	 * 		String added to companyName[]
	 */
	public void setCompanyName(String compName)
	{	
		this.companyName[this.numCompanies] = compName;
		this.numCompanies++;
	}

	/**
	 * setter method
	 * @param gpa
	 * 		Double for applicantGPA
	 * @throws HighGPAException
	 * 		Indicates that GPA is greater than 4.0
	 * @throws NegativeGPAException
	 * 		Indicates that GPA is less than 0
	 */
	public void setApplicantGPA(double gpa) throws HighGPAException, NegativeGPAException
	{
		if (gpa < 0)
		{
			throw new NegativeGPAException("error");
		}
		else if (gpa > 4)
		{
			throw new HighGPAException("error");
		}
		else this.applicantGPA = gpa;
	}

	/**
	 * setter method
	 * @param appCollege
	 * 		String for applicantCollege
	 */
	public void setApplicantCollege(String appCollege)
	{
		this.applicantCollege = appCollege;
	}

	/**
	 * setter method
	 * @param appSkills
	 * 		String added to applicantSkills[]
	 */
	public void setApplicantSkills(String appSkills)
	{
		this.applicantSkills[this.numSkills] = appSkills;
		this.numSkills++;
	}

	/**
	 * A method that creates and returns an Applicant with the same values as this one.
	 */
	public Object clone()
	{
		Applicant a = new Applicant(this.companyName, this.applicantName, this.applicantGPA, this.applicantCollege, this.applicantSkills);
		return a;
	}	

	/**
	 * A method that takes each String in companyName and adds it to the String compString, with a ", " in between each String.
	 * Also takes each String in applicantSkills and adds it to skillString, with a ", " in between each String.
	 */
	public void updateSkillsandCompany()
	{
		compString = "";
		skillString = "";
		for (int i = 0; i < this.companyName.length; i++)
		{
			if (this.companyName[i]!=null)
			{
				if(i > 0)
				{
					compString += ", ";
				}
				compString += (companyName[i]);
			}
		}
		for (int s = 0; s < applicantSkills.length; s++)
		{
			if (this.applicantSkills[s]!=null)
			{
				if(s > 0)
				{
					skillString += ", ";
				}
				skillString+=(applicantSkills[s]);
			}
		}
	}

	/**
	 * A method that returns a formatted String of the Applicant's variables.
	 */
	public String toString()
	{
		this.updateSkillsandCompany();
		return String.format("%-30s%-15s%-10.2f%-18s%s", this.compString, this.applicantName, this.applicantGPA, this.applicantCollege, this.skillString);	
	}
	
	/**
	 * A method that compares the Applicant with an Object obj to determine if they are equal.
	 * Return true if they are equal, else false.
	 */
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;
		if(!(obj instanceof Applicant))
			return false;
		Applicant b = (Applicant)obj;
		b.updateSkillsandCompany();
		this.updateSkillsandCompany();
		if (b.compString.equals(this.compString) && b.applicantName.equals(this.applicantName)
		  && (b.applicantGPA == this.applicantGPA) && (b.applicantCollege.equals(this.applicantCollege))
		  && b.skillString.equals(this.skillString))
			return true;
		return false;
	}
}







