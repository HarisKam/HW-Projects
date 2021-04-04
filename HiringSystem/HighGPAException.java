/*
Name: Haris Kamran
ID#: 112786164
Recitation: Monday 11 am
*/
/**
 * Class that extends the Exception Class. Thrown when the user inputs a GPA greater than 4.0.
 * @author haris
 *
 */
public class HighGPAException extends Exception
{
	public HighGPAException (String s)
	{
		super(s);
	}
}

