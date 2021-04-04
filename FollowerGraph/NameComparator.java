/*
Name: Haris Kamran
ID#: 112786164
Recitation 07
*/

import java.util.Comparator;

/**
 * The NameComparator class which is used to compare and sort the users by name
 * @author haris
 *
 */
public class NameComparator implements Comparator<User>
{
	public int compare(User u1, User u2)
	{
		return (u1.getUserName().compareTo(u2.getUserName()));
	}
}
