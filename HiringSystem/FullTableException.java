/*
Name: Haris Kamran
ID#: 112786164
Recitation: Monday 11 am
*/
/**
 * Class that extends the Exception Class. Thrown when the user tries to add an Applicant to the HiringTable
 * while numApplicants >= MAX_APPLICANTS.
 * @author haris
 *
 */
public class FullTableException extends Exception
{
	public FullTableException (String s)
	{
		super(s);
	}
}
