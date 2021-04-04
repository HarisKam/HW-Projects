/*
Name: Haris Kamran
ID#: 112786164
Recitation: Monday 11 am
*/
import java.util.Scanner;

/**
 * The HiringSystem class which runs the main method, a user-input driven menu which creates
 *  a HiringTable and allows the user to perform actions through scanner inputs.
 * These actions include: 
 * Adding an Applicant
 * Removing an Applicant
 * Getting Information of an Applicant
 * Printing the Hiring Table
 * Filtering the Hiring Table for certain parameters
 * Getting how many Applicants are in the Hiring Table
 * Creating a Backup of the current Hiring Table
 * Comparing the current Hiring Table to the backup to check for equality
 * Reverting the current Hiring Table to the backup
 * Quitting the program
 * 
 * @author haris
 *
 */
public class HiringSystem
{
	public static void main(String[] args) 
	{
		/**
		 * Stores the user input for an applicant's GPA
		 */
		String gpaInput = "";
		
		/**
		 * Stores the user input for menu actions
		 */
		String userInput = "";
		
		/**
		 * Stores the user input for a company name
		 */
		String companyName = "";
		
		/**
		 * Stores the user input for an applicant name
		 */
		String applicantName = "";
		
		/**
		 * Stores the applicant's GPA as a double to be used in methods
		 */
		double applicantGPA = -1;
		
		/**
		 * Stores the user input for an applicant's college
		 */
		String applicantCollege = "";
		
		/**
		 * Stores the user input for an applicant's skill
		 */
		String skillName = "";
	
		
		/**
		 * The new HiringTable
		 */
		HiringTable h = new HiringTable();
		
		/**
		 * A backup HiringTable
		 */
		HiringTable backup = new HiringTable();
		
		Scanner scan = new Scanner(System.in);
		
		/**
		 * Variable the determines whether the menu should keep running or terminate
		 */
		int run = 0;
		
		/**
		 * Prints the possible user inputs and their function
		 */
		System.out.println("(A)   Add Applicant\n(R)   Remove Applicant\n"
				+ "(G)   Get Applicant\n(P)   Print List\n"
				+ "(RS)  Refine Search\n(S)   Size\n(B)   Backup\n"
				+ "(CB)  Compare Backup\n(RB)  Revert Backup\n(Q)   Quit\n");
		
		while (run == 0)
		{
			System.out.print("Please enter a command: ");
			userInput = scan.nextLine().toUpperCase();
			
			/**
			 * Add Applicant Operation
			 */
			if (userInput.equals("A"))
				{
					Applicant a = new Applicant();
					try
					{		
						h.addApplicant(a);
					}
					catch (FullTableException e)
					{
						System.out.println("There is no more room in the Hiring Table for new Applicants.\n");
						continue;	
					}
					
					System.out.print("Enter Applicant Name: ");
					applicantName = scan.nextLine();
					h.data[h.numApplicants-1].setApplicantName(applicantName);
					
					applicantGPA = -1;
					while(applicantGPA < 0 || applicantGPA > 4)
					{
						System.out.print("Enter Applicant GPA: ");
						try 
						{
							gpaInput = scan.nextLine();
							applicantGPA = Double.parseDouble(gpaInput);
							h.data[h.numApplicants-1].setApplicantGPA(applicantGPA);
						}
						catch (NegativeGPAException e) 
						{
							System.out.println("GPA cannot be a negative number, please enter a new GPA.");
						}
						catch (HighGPAException e)
						{
							System.out.println("GPA cannot be greater than 4.0, please enter a new GPA.");
						}
						catch (NumberFormatException e)
						{
							System.out.println("Please enter a valid input for GPA.");
						}
					}
					
					
					System.out.print("Enter Applicant College: ");
					applicantCollege = scan.nextLine();	
					h.data[h.numApplicants-1].setApplicantCollege(applicantCollege);
					
					while (h.data[h.numApplicants-1].numCompanies < HiringTable.MAX_COMPANIES)
					{
						System.out.print("Enter up to " + 
						  (HiringTable.MAX_COMPANIES - h.data[h.numApplicants-1].numCompanies) + " Companies: ");
						companyName = scan.nextLine();
						if (companyName.equals(""))
						{
							if(h.data[h.numApplicants-1].numCompanies > 0)
							{
								break;
							}
							else continue;							
						}
						h.data[h.numApplicants-1].setCompanyName(companyName);						
					}
					
					while (h.data[h.numApplicants-1].numSkills < HiringTable.MAX_SKILLS)
					{
						System.out.print("Enter up to " + 
						  (HiringTable.MAX_SKILLS - h.data[h.numApplicants-1].numSkills) + " Skills: ");
						skillName = scan.nextLine();
						if (skillName.equals(""))
						{
							if(h.data[h.numApplicants-1].numSkills > 0)
								{
									break;
								}
							else continue;
						}
						h.data[h.numApplicants-1].setApplicantSkills(skillName);
					}
					applicantGPA = -1;
					System.out.println("Applicant " + h.data[h.numApplicants-1].getApplicantName() 
					  + " successfully added to the hiring system.\n");
				}
					
			/**
			 * Remove Applicant Operation
			 */
			 if (userInput.equalsIgnoreCase("R"))
			 {
				System.out.print("Enter the name of the Applicant to remove: ");
				try 
				{
					applicantName = scan.nextLine();
					h.removeApplicant(applicantName);
					System.out.println("Applicant " + applicantName 
					  + " has been succesfully removed from the hiring system.\n");						
				}
				catch (ApplicantNotFoundException e)
				{
					System.out.println("Applicant with the corresponding name was not found.\n");
				}					
			}	
			 
			 /**
			  * Get Applicant Operation
			  */
			 if (userInput.equalsIgnoreCase("G"))
			 {
				 System.out.print("Enter Applicant Name: ");
				 try
				 {
				 applicantName = scan.nextLine();
				 h.getApplicant(applicantName).updateSkillsandCompany();
				 System.out.println("\nApplicant Name: " + h.getApplicant(applicantName).getApplicantName());
				 System.out.println("Applicant Applying From: " + h.getApplicant(applicantName).getCompanyName()[0]);
				 System.out.println("Applicant GPA: " + h.getApplicant(applicantName).getApplicantGPA());
				 System.out.println("Applicant College: " + h.getApplicant(applicantName).getApplicantCollege());
				 System.out.println("Applicant Skills: " + h.getApplicant(applicantName).skillString + "\n");
				 }
				 catch (ApplicantNotFoundException e)
				 {
					 System.out.println("Applicant with the corresponding name was not found.\n");
				 }
			 }
			 
			 /**
			  * Print Hiring Table Operation
			  */
			 if (userInput.equalsIgnoreCase("P"))
			 {
				 System.out.println();
				 h.printApplicantTable();
				 System.out.println(); 
			 }
			 
			 /**
			  * Refine Search Operation
			  */
			 if (userInput.equalsIgnoreCase("RS"))
			 {
				 applicantGPA = -1;
				 System.out.print("Enter a company to filter for: ");
				 companyName = scan.nextLine();
				 System.out.print("Enter a skill to filter for: ");
				 skillName = scan.nextLine();
				 System.out.print("Enter a college to filter for: ");
				 applicantCollege = scan.nextLine();
				 innerloop: while (applicantGPA == -1) 
				 {
					System.out.print("Enter a minimum GPA to filter for: ");
				 	gpaInput = scan.nextLine();
				 	if (gpaInput.equals(""))
				 	{
				 		applicantGPA = 0;
				 	}
				 	else 
				 	{
				 		try
				 		{
				 			applicantGPA = Double.parseDouble(gpaInput);
				 		}
				 		catch (NumberFormatException e)
				 		{
				 			System.out.println("Please enter a valid input for GPA, or press [Enter] to skip.");
				 			continue innerloop;
				 		}
				 	}			 
				 	HiringTable.refineSearch(h, companyName, skillName, applicantCollege, applicantGPA);
				 }
			 }
			 
			 /**
			  * Size Operation
			  */
			 if(userInput.equalsIgnoreCase("S"))
			 {
				 System.out.println("There are " + h.size() + " applicants in the hiring system.\n");
			 }
			 
			 /**
			  * Create Backup Operation
			  */
			 if (userInput.equalsIgnoreCase("B"))
			 {
				 backup = (HiringTable) h.clone();
				 System.out.println("Backup list successfully created.\n");
			 }
			 
			 outerloop: if (userInput.equalsIgnoreCase("CB"))
			 {
				if (h.numApplicants == backup.numApplicants)
				{
					for (int x = 0; x < h.numApplicants; x++)
					{
						if (!(h.data[x].equals((Applicant)(backup.data[x]))))
						{
							System.out.println("Current list is not the same as the backup copy.\n" + x);
							break outerloop;
						}
					}
					System.out.println("Current list is the same as the backup copy.\n");
				}
				else System.out.println("Current list is not the same as the backup copy.\n");
			 }
			 
			 /**
			  * Revert Current List to Backup List Operation
			  */
			 if (userInput.equalsIgnoreCase("RB"))
			 {
				 h = (HiringTable) backup.clone();
				 System.out.println("Successfully reverted to the backup copy.\n");
			 }
			 
			 /**
			  * Quits the Menu
			  */
			 if (userInput.equalsIgnoreCase("Q"))
			 {
				 run++;
				 System.out.println("Quitting Program...");
			 }
		}
	}
}

				

