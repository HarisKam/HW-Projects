/*
 * Name: Haris Kamran
 * ID#: 112786164
 * Recitation 07
 */
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;

/**
 * The SongLinkedList class which creates a playlist using a doubly linked list of SongNodes.
 * @author haris
 *
 */

public class SongLinkedList 
{
	/**
	 * The end of the list.
	 */
	private SongNode tail;
	
	/**
	 * The beginning of the list.
	 */
	private SongNode head;
	
	/**
	 * The cursor which is used to traverse the list and add/remove SongNodes.
	 */
	private SongNode cursor;
	
	/**
	 * The number of Songs in the list.
	 */
	private int size;
	
	/**
	 * Clip that plays the audio from the .wav file.
	 */
	public Clip c;
	
	/**
	 * Variable used to keep track of whether a song is currently playing.
	 */
	int audioPlay = 0;
	
	/**
	 * Constructor for the SongLinkedList, initializes size to 0, head/tail/cursor to null.
	 */
	public SongLinkedList() 
	{
		tail = null;
		head = null;
		cursor = null;
		size = 0;
	}
	
	/**
	 * A method for playing audio of a song in the playlist.
	 * Searches the playlist for a songNode with a name that corresponds.
	 * If a corresponding songNode is found, it searches for a .wav file with the same name, and plays it.
	 * If no corresponding songNode is found, it prints that no song in the playlist has the corresponding name.
	 * @param name
	 * 		Name of the song the user wants to play.
	 * @throws IllegalArgumentException
	 * 		Indicates that there is no .wav file for the song.
	 */		
	public void play(String name) throws IllegalArgumentException
	{
		int count = 0;
		SongNode nodePtr = head;
		while (nodePtr != null)
		{
			if(nodePtr.getData().getName().equals(name))
				{
					cursor = nodePtr;
					count++;
					try 
					{
						AudioInputStream AIS = AudioSystem.getAudioInputStream(new File(name + ".wav"));
						if (audioPlay != 0)
						{
							c.stop();
							audioPlay = 0;
						}
						c = AudioSystem.getClip();
						c.open(AIS);
						c.setFramePosition(0);
			            c.start(); 
			            audioPlay++;
			            System.out.println("'" + name + "'" + " by " + nodePtr.getData().getArtist() + " is now playing.");
					}
					catch (Exception ex) 
					{
						System.out.println("The .wav file for '" + name + "' could not be found.");
					}
					break;
				}
			nodePtr = nodePtr.getNext();
		}
		if (count == 0)
			System.out.println("'" + name + "' does not correspond to a song in the playlist.");
	}
	
	/**
	 * A method for moving the cursor forwards.
	 * If the cursor is not null (The playlist is not empty), 
	 *   and cursor.getNext() is not null (The cursor is not at the end of the list), advances the cursor to the next SongNode.
	 */
	public void cursorForwards()
	{
		if ((cursor != null) && (!(cursor.getNext()==null)))
		{
			cursor = cursor.getNext();
			System.out.println("Cursor moved to next song.");
		}
		else
		{
			System.out.println("Already at end of playlist.");
		}	
	}
	
	/**
	 * A method for moving the cursor backwards.
	 * If the cursor is not null, and cursor.getPrev() is not null (The cursor is not at the beginning of the list),
	 *   moves the cursor to the previous SongNode.
	 */
	public void cursorBackwards()
	{
		if ((cursor != null) && (!(cursor.getPrev()==null)))
		{
			cursor = cursor.getPrev();
			System.out.println("Cursor moved to previous song.");
		}
		else
		{
			System.out.println("Already at beginning of playlist.");
		}	
	}
	
	/**
	 * A method that inserts a SongNode after the cursor's position, and then moves the cursor forward to that new SongNode.
	 * @param newSong
	 * 		The Song which the user wants to add to the playlist.
	 */
	public void insertAfterCursor(Song newSong)
	{
		SongNode s = new SongNode();
		s.setData(newSong);
		if (size == 0 || cursor == null)
		{
			head = s;		
		}		
		else if (cursor.getNext()==null)
		{
			tail = s;
			s.setPrev(cursor);
			cursor.setNext(s);			
		}
		else
		{
			s.setNext(cursor.getNext());
			s.setPrev(cursor);
			cursor.getNext().setPrev(s);
			cursor.setNext(s);
		}
		cursor = s;
		size++;
	}
	
	/**
	 * A method to remove whatever songNode the cursor is pointing to.
	 *   After the songNode is removed, advances the cursor forward, or, if cursor is at end of list, moves the cursor back.
	 * @return
	 * 		The song contained within the songNode that was removed from the playlist.
	 * @throws NullPointerException
	 * 		Indicates that there are no songs in the playlist (cursor is null).
	 */
	public Song removeCursor() throws NullPointerException
	{
		SongNode n = cursor;
		if (size == 1)
		{
			head = null;
			cursor = null;
			tail = null;
		}
		else if (cursor.getNext() != null)
		{
			if (cursor.getPrev() != null)
			{
			cursor.getNext().setPrev(cursor.getPrev());
			cursor.getPrev().setNext(cursor.getNext());
			}
			else
			{
				head = cursor.getNext();
				cursor.getNext().setPrev(null);
			}
			cursor = cursor.getNext();
		}
		else 
		{
			
			tail = cursor.getPrev();
			cursor = cursor.getPrev();
			cursor.setNext(null);
		}
		size--;
		return n.getData();		
	}
	
	/**
	 * A method for getting the size of the playlist.
	 * @return
	 * 		The variable size, which increments when a song is added and decrements when a song is removed.
	 */
	public int getSize()
	{
		return size;
	}
	
	/**
	 * A method for playing a random song in the playlist.
	 * @return
	 * 		The Song which was chosen to be played.
	 */
	public Song random()
	{
		this.play(this.randomSongNode().getData().getName());
		return cursor.getData();
	}
	
	/**
	 * A method for obtaining a random songNode in the playlist.
	 * @return
	 * 		The songNode which was chosen at random.
	 */
	public SongNode randomSongNode()
	{
		SongNode nodePtr = cursor;
		int n = (int)(Math.random()*this.getSize());
		for (int x = 0; x < n; x++)
		{
			if (cursor.getNext()!=null)
			{
				cursor = cursor.getNext();
			}
			
			else 
			{
				cursor = head;
			}
		}
		return cursor;
	}
	
	/**
	 * A method that randomly shuffles all the songs in the playlist.
	 */
	public void shuffle()
	{
		Song [] songArray = new Song[size];
		boolean check = true;
		if (size == 0)
		{
			System.out.println("Playlist is empty.");
			return;
		}
		Song s = cursor.getData();
		int orgSize = size;
		for (int x = 0; x < orgSize; x++)
		{
			SongNode nodePtr = head;
			int n = (int)(Math.random()*(size+1));
				while (n > 0)
				{
					if ((nodePtr != null) && (!(nodePtr.getNext()==null)))
					{
						
						nodePtr = nodePtr.getNext();
					}
					n--;
				}
				songArray[x] = nodePtr.getData();
				cursor = nodePtr;
				this.removeCursor();	
		}
		for (int n = 0; n < orgSize; n++)
		{
			this.insertAfterCursor(songArray[n]);
		}
		while (cursor.getData()!=s)
		{
			if (check && cursor.getNext()!=null)
			{
				cursor = cursor.getNext();
			}
			else
			{
				cursor = cursor.getPrev();
				check = false;
			}
			
		}
		System.out.println("Playlist shuffled.");
	}

	/**
	 * A method that creates a String containing a formatted version of the data for each Song in the playlist.
	 * @return
	 * 		The String containing the data for each Song.
	 */
	public String printSongs()
	{
		String s = new String("");
		if (cursor != null)
		{
			SongNode songPtr = head;
			for (int x = 0; x < size; x++)
			{
				s += (String.format("%-28s%-28s%-28s%d", songPtr.getData().getName(),
				  songPtr.getData().getArtist(), songPtr.getData().getAlbum(), songPtr.getData().getLength()));
				if (cursor == songPtr)
				{
					s += "   <-";
				}
				songPtr = songPtr.getNext();
				s+= "\n";
			}
		}
		return s;
	}
	
	/**
	 * A method that returns a String representation of the playlist, containing an organized version of each Song's data.
	 * @return
	 * 		The String representing the playlist.
	 */
	public String toString()
	{
		String n = ("Playlist:\n" + String.format("%-27s%-27s%-27s%s", "Song", "| Artist", "| Album", "| Length(s)" + 
				  "\n--------------------------------------------------------------------------------------------\n"));
		n += this.printSongs();
		return n;
	}
	
	/**
	 * A method that prints the playlist.
	 */
	public void printPlaylist()
	{
		System.out.println("Playlist:");
		System.out.println(String.format("%-27s%-27s%-27s%s", "Song", "| Artist", "| Album", "| Length(s)" + 
		  "\n--------------------------------------------------------------------------------------------" ));
		System.out.println(this.printSongs());
	}
	
	/**
	 * A method that deletes all songs in the playlist.
	 */
	public void deleteAll()
	{
		while (size > 0 )
		{
			this.removeCursor();
		}
	}
	
}
