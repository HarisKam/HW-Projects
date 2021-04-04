/*
Name: Haris Kamran
ID#: 112786164
Recitation 07
*/

import java.util.Comparator;

/**
 * The FollowingComparator class which is used to compare and sort the users by their following count.
 * @author haris
 *
 */
public class FollowingComparator implements Comparator<User>
{
	FollowGraph f;
	public FollowingComparator(FollowGraph b)
	{
		f = b;
	}
	public int compare(User u1, User u2)
	{
		u1.resetCount();
		u2.resetCount();
		for (int x = 0; x < f.getConnections().length; x++)
		{
			if (f.getConnections()[u1.getIndexPos()][x] == true)
			{
				u1.followingInc();
			}
			if (f.getConnections()[u2.getIndexPos()][x] == true)
			{
				u2.followingInc();
			}
		}
		if (u1.getFollowing() == u2.getFollowing())
		{
			return 0;
		}
		else if (u1.getFollowing() > u2.getFollowing())
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}
}
