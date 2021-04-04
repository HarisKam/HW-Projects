/*
Name: Haris Kamran
ID#: 112786164
Recitation 07
*/

import java.util.Comparator;

/**
 * The FollowersComparator class which is used to compare and sort the users by number of followers
 * @author haris
 *
 */
public class FollowersComparator implements Comparator<User>
{
	FollowGraph f;
	public FollowersComparator(FollowGraph b)
	{
		f = b;
	}
	public int compare(User u1, User u2)
	{
		u1.resetCount();
		u2.resetCount();
		for (int x = 0; x < f.getConnections().length; x++)
		{
			if (f.getConnections()[x][u1.getIndexPos()] == true)
			{
				u1.followerInc();;
			}
			if (f.getConnections()[x][u2.getIndexPos()] == true)
			{
				u2.followerInc();
			}
		}
		if (u1.getFollowers() == u2.getFollowers())
		{
			return 0;
		}
		else if (u1.getFollowers() > u2.getFollowers())
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}
}
