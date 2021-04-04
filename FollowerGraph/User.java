/*
Name: Haris Kamran
ID#: 112786164
Recitation 07
*/

/**
 * The User class which contains information for a User.
 * @author haris
 *
 */
public class User
{
	/**
	 * String containing name of the user
	 */
	private String userName;
	/**
	 * The user's position in the graph index
	 */
	private	int indexPos;	
	/**
	 * The amount of users in the graph.
	 */
	public static int userCount;
	/**
	 * The amount of followers the user has.
	 */
	private int followers = 0;
	/**
	 * The amount of users this user is following.
	 */
	private int following = 0;
	
	/**
	 * The constructor for User, which sets its name and its index position, and then increments user count.
	 * @param userName
	 * 		The String set as the user's name
	 */
	public User(String userName)
	{
		this.userName = userName;
		indexPos = userCount;
		userCount++;
	}
	
	/**
	 * getter method
	 * @return
	 * 		the number of users in the graph
	 */
	public int getUserCount()
	{
		return userCount;
	}
	
	/**
	 * getter method
	 * @return
	 * 		this user's name
	 */
	public String getUserName()
	{
		return this.userName;
	}
	
	/**
	 * getter method
	 * @return
	 * 		this user's index position
	 */
	public int getIndexPos()
	{
		return this.indexPos;
	}
	
	/**
	 * toString method which returns the name of the user.
	 */
	public String toString()
	{
		return ("Name: " + this.userName);
	}
	
	/**
	 * setter method
	 * @param userName
	 * 		String to be set as the user's name
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	/**
	 * getter method
	 * @return
	 * 		number of followers
	 */
	public int getFollowers()
	{
		return followers;
	}
	
	/**
	 * getter method
	 * @return
	 * 		number of users this user is following
	 */
	public int getFollowing()
	{
		return following;
	}
	
	public void resetCount()
	{
		followers = 0;
		following = 0;
	}
	public void followerInc()
	{
		this.followers++;
	}
	
	public void followingInc()
	{
		this.following++;
	}
}
