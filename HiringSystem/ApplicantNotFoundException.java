/*
Name: Haris Kamran
ID#: 112786164
Recitation: Monday 11 am
*/
/**
 * Class that extends the Exception Class. Thrown when there is no applicant in the HiringTable that matches
 * the name of the applicant the user is searching for.
 * @author haris
 *
 */
public class ApplicantNotFoundException extends Exception
{
	public ApplicantNotFoundException(String s)
	{
		super(s);
	}
}
