/*
Name: Haris Kamran
ID#: 112786164
Recitation 07
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The FollowGraph class which contains a list of users.
 * @author haris
 *
 */
public class FollowGraph implements Serializable
{
	/**
	 * The list of users.
	 */
	private List<User> users;
	
	/**
	 * The maximum number of users.
	 */
	public static final int MAX_USERS = 100;
	
	/**
	 * boolean array which holds the connections between the users.
	 */
	private boolean[][] connections;
	
	/**
	 * default constructor which initializes a new list of users and the connections 2d array.
	 */
	public FollowGraph()
	{
		users = new ArrayList<User>();
		connections = new boolean[MAX_USERS][MAX_USERS];
	}
	
	/**
	 * The addUser method which adds a new user to the graph.
	 * @param userName
	 * 		The name of the user to be added
	 */
	public void addUser(String userName)
	{
		User u = new User(userName);
		users.add(u);
	}
	
	/**
	 * The addConnection method which adds a new connection between two existing users.
	 * @param userFrom
	 * 		The user who will be following
	 * @param userTo
	 * 		The user who will get followed.
	 */
	public void addConnection(String userFrom, String userTo)
	{
		int from = -1;
		int to = -1;
		
		for (int x = 0; x < users.size(); x++)
		{
			if (users.get(x).getUserName().equalsIgnoreCase(userFrom))
			{
				from = x;
			}
			if (users.get(x).getUserName().equalsIgnoreCase(userTo))
			{
				to = x;
			}
		}
		
		if ((from != -1) && (to != -1))
		{
			connections[from][to] = true;
		}
	}
	
	/**
	 * The removeConnection method which removes a connection between two existing users
	 * @param userFrom
	 * 		The user who is going to "unfollow"
	 * @param userTo
	 * 		The user who is getting "unfollowed"
	 */
	public void removeConnection(String userFrom, String userTo)
	{
		int from = -1;
		int to = -1;
		
		for (int x = 0; x < users.size(); x++)
		{
			if (users.get(x).getUserName().equalsIgnoreCase(userFrom))
			{
				from = x;
			}
			if (users.get(x).getUserName().equalsIgnoreCase(userTo))
			{
				to = x;
			}
		}
		
		if ((from != -1) && (to != -1))
		{
			connections[from][to] = false;
		}
	}
	
	/**
	 * The shortestPath method which finds the shortest path between two users.
	 * @param userFrom
	 * 		The source user
	 * @param userTo
	 * 		The destination user
	 * @return
	 * 		A string containing the shortest path between the two users and its distance.
	 */
	public String shortestPath(String userFrom, String userTo)
	{
		boolean[] visited = new boolean[users.size()];
		String[] path = new String[users.size()];
		for (int s = 0; s < path.length; s++)
		{
			path[s] = users.get(s).getUserName();
			visited[s] = false;
		}
		int counter = 0;
		int indexFrom = -1;
		int indexTo = -1;
		Double minNum = Double.POSITIVE_INFINITY;
		int min = indexFrom;
		Double[] dist = new Double[users.size()];
		for (int x = 0; x < dist.length; x++)
		{
			dist[x] = Double.POSITIVE_INFINITY;
		}
		for (int u = 0; u < users.size(); u++)
		{
			if (users.get(u).getUserName().equalsIgnoreCase(userFrom))
			{
				indexFrom = u;
			}
			if (users.get(u).getUserName().equalsIgnoreCase(userTo))
			{
				indexTo = u;
			}
		}
		if (indexFrom == -1 || indexTo == -1)
		{
			return "One of the specified user(s) was not found";
		}
		
		for (int c = 0; c < connections[indexFrom].length; c++)
		{
			if (connections[indexFrom][c] == true)
			{
				dist[c] = 1.0;
			}
		}
		dist[indexFrom] = 0.0;
		
		while (counter < users.size())
		{
			minNum = Double.POSITIVE_INFINITY;
			for (int d = 0; d < dist.length; d++)
			{
				if (dist[d] < minNum && visited[d] == false)
				{
					min = d;
					minNum = dist[d];
				}
 			}
			visited[min] = true;
			counter++;
			for (int c = 0; c < connections[min].length; c++)
			{
				if (connections[min][c] == true)
				{
					if (dist[min] + 1 < dist[c])
					{
						dist[c] = dist[min] + 1;
						path[c] = path[min] + " -> " + users.get(c).getUserName();
					}
				}
			}
		}
		if (dist[indexTo] == Double.POSITIVE_INFINITY)
		{
			return ("No path from source user to destination.");
		}
		return (dist[indexTo] + "###" + users.get(indexFrom).getUserName() + " -> " + path[indexTo]);
	}
	
	/**
	 * The allPaths method which returns a list containing all paths between the two users.
	 * @param userFrom
	 * 		The source user
	 * @param userTo
	 * 		The destination user
	 * @return
	 * 		List containing all paths between the users.
	 */
	public List<String> allPaths(String userFrom, String userTo)
	{
		   List<String> paths = new ArrayList<String>();
		   int indexFrom = -1;
		   int indexTo = -1;
		   for (int x = 0; x < users.size(); x++)
		   {
			   if (users.get(x).getUserName().equalsIgnoreCase(userFrom))
			   {
				   indexFrom = x;
			   }
			   if (users.get(x).getUserName().equalsIgnoreCase(userTo))
			   {
				   indexTo = x;
			   }
		   }
		   if (indexTo == -1 || indexFrom == -1)
		   {
			   System.out.println("One of the specified users was not found");
			   return null;
		   }
		   paths = this.graphTraversal(indexTo, indexFrom, new boolean[users.size()], "");
		   return paths;
	}

	/**
	 * helper method used to traverse graph
	 * @param target
	 * 		user being searched for
	 * @param origin
	 * 		origin user of this path
	 * @param oldvisited
	 * 		array of visited users
	 * @param path
	 * 		the current path
	 * @return
	 * 		an arraylist containing paths from the origin to the target.
	 */
	public ArrayList<String> graphTraversal(int target, int origin, boolean[] oldvisited, String path)
	{
		boolean[] visited = oldvisited;
		ArrayList<String> paths = new ArrayList<String>();
		ArrayList<String> options = new ArrayList<String>();
		if (!(path.equals("")))
		{
			path += " -> ";
		}
		path += users.get(origin).getUserName();
		visited[origin] = true;
		if (target == origin)
		{
			options.add(path);
		}
		for (int x = 0; x < users.size(); x++)
		{
			if (connections[origin][x] == true && visited[x] == false)
			{
				options = this.graphTraversal(target, x, visited, path);
					for (int b = 0; b < options.size(); b++)
					{
						paths.add(options.get(b));
					}
			}
		}
		if (options.size() > 0)
		{
			return paths;
		}
		return options;	
	}
	
	/**
	 * Prints all users in the graph and their number of followers/how many users they follow
	 * @param comp
	 * 		the comparator used to sort the user list.
	 */
	public void printAllUsers(Comparator comp)
	{
		List<User> sorted = users;
		sorted.sort(comp);
		System.out.println("User Name               Number of Followers      Number Following");
		for (User u : sorted)
		{
			System.out.println(String.format("%-20s%15d%25d", u.getUserName(), u.getFollowers(), u.getFollowing()));
		}
	}
	
	/**
	 * prints all followers of a user
	 * @param userName
	 * 		the specified user's name
	 */
	public void printAllFollowers(String userName)
	{
		int index = -1;
		for (int x = 0; x < users.size(); x++)
		{
			if (users.get(x).getUserName().equalsIgnoreCase(userName))
			{
				index = x;
			}
		}
		if (index == -1)
		{
			return;
		}
		for (int x = 0; x < connections.length; x++)
		{
			if (connections[x][index] == true)
			{
				System.out.println(users.get(x)); 
			}
		}
	}
	
	/**
	 * finds all loops in the graph and returns a String list of them.
	 * @return
	 * 		a list of strings containing all loops 
	 */
	public List<String> findAllLoops()
	{
		boolean[] visited = new boolean[users.size()];
		List<String> loops = new ArrayList<String>();
		List<String> temp = new ArrayList<String>();
		for (int x = 0; x < users.size(); x++)
		{
			temp = this.graphTraversal(x, x, visited, "");
			for (int y = 0; y < temp.size(); y++)
			{
				loops.add(temp.get(y));
			}
		}
		return loops;
		
	}
	
	/**
	 * method which prints all users this user is following
	 * @param userName
	 * 		name of the specified user.
	 */
	public void printAllFollowing(String userName)
	{
		int index = -1;
		for (int x = 0; x < users.size(); x++)
		{
			if (users.get(x).getUserName().equalsIgnoreCase(userName))
			{
				index = x;
			}
		}
		if (index == -1)
		{
			return;
		}
		for (int x = 0; x < connections.length; x++)
		{
			if (connections[index][x] == true)
			{
				System.out.println(users.get(x)); 
			}
		}
	}
	
	/**
	 * Parses a text file and adds new Users to the graph.
	 * @param fileName
	 * 		the name of the file to be parsed.
	 */
	public void loadAllUsers(String fileName)
	{
		FileInputStream fis;
		try 
		{
			fis = new FileInputStream(fileName);
			InputStreamReader inStream = new InputStreamReader(fis);
			BufferedReader stdin = new BufferedReader(inStream);
			String data = stdin.readLine();
			while (data != null)
			{
				User u = new User(data);
				users.add(u);
				System.out.println(data + " has been added");
				data = stdin.readLine();
			}
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: File not found.");
		} 
		catch (IOException e) 
		{
			System.out.println("ERROR");
		}			
	}
	
	/**
	 * Parses a text file and adds new connections
	 * @param fileName
	 * 		the name of the file to be parsed.
	 */
	public void loadAllConnections(String fileName)
	{
		FileInputStream fis;
		try 
		{
			fis = new FileInputStream(fileName);
			InputStreamReader inStream = new InputStreamReader(fis);
			BufferedReader stdin = new BufferedReader(inStream);
			String data = stdin.readLine();
			while (data != null)
			{
				String from = "";
				String to = "";
				int indexFrom = -1;
				int indexTo = -1;
				from = data.substring(0, data.indexOf(","));
				to = data.substring(data.indexOf(",")+2);
				for (int c = 0; c < users.size(); c++)
				{
					if (users.get(c).getUserName().equalsIgnoreCase(from))
					{
						indexFrom = c;
					}
					if (users.get(c).getUserName().equalsIgnoreCase(to))
					{
						indexTo = c;
					}
				}
				if (indexFrom == -1 || indexTo == -1)
				{
					System.out.println(data + " was not found");
					data = stdin.readLine();
				}
				else
				{
					connections[indexFrom][indexTo] = true;
					System.out.println(data + " added");
					data = stdin.readLine();
				}
			}
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: File not found.");
		} 
		catch (IOException e) 
		{
			System.out.println("ERROR");
		}	
	}
	
	/**
	 * method to remove a user, and all of their connections, from the graph.
	 * @param userName
	 * 		the name of the specified user.
	 */
	public void removeUser(String userName)
	{
		int index = -1;
		for (int x = 0; x < users.size(); x++)
		{
			if (users.get(x).getUserName().equalsIgnoreCase(userName))
			{
				index = x;
			}
		}
		if (index == -1)
		{
			System.out.println("User not found.");
			return;
		}
		users.remove(index);
		for (int c = 0; c < connections[index].length; c++)
		{
			connections[index][c] = false;
		}
		for (int r = 0; r < connections.length; r++)
		{
			connections[r][index] = false;
		}
	}
	
	/**
	 * getter method
	 * @return
	 * 		the connections[][] array.
	 */
	public boolean[][] getConnections()
	{
		return connections;
	}
}
