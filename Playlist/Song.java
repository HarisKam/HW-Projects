/*
 * Name: Haris Kamran
 * ID#: 112786164
 * Recitation 07
 */

/**
 * The Song class which contains information for a song.
 * @author haris
 *
 */
public class Song 
{
	
	/**
	 * The name of the song.
	 */
	private String name;
	
	/**
	 * The artist of the song.
	 */
	private String artist;
	
	/**
	 * The album of the song.
	 */
	private String album;
	
	/**
	 * The length (in seconds) of the song.
	 */
	private int length;
	
	/**
	 * The default constructor for the Song Object.
	 */
	public Song()
	{
		
	}
	
	/**
	 * Other constructor for the Song Object.
	 * @param songName
	 * 		String that is assigned to name.
	 * @param songArtist
	 * 		String that is assigned to artist.
	 * @param songAlbum
	 * 		String that is assigned to album.
	 * @param songLength
	 * 		Int that is assigned to length.
	 */
	public Song(String songName, String songArtist, String songAlbum, int songLength)
	{
		name = songName;
		artist = songArtist;
		album = songAlbum;
		length = songLength;
	}
	
	/**
	 * getter method
	 * @return
	 * 		name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * getter method
	 * @return
	 * 		artist
	 */
	public String getArtist()
	{
		return this.artist;
	}
	
	/**
	 * getter method
	 * @return
	 * 		album
	 */
	public String getAlbum()
	{
		return this.album;
	}
	
	/**
	 * getter method
	 * @return
	 * 		length
	 */
	public int getLength()
	{
		return this.length;
	}
	
	/**
	 * setter method
	 * @param songName
	 * 		String for name
	 */
	public void setName(String songName)
	{
		name = songName;
	}
	
	/**
	 * setter method
	 * @param songArtist
	 * 		String for artist
	 */
	public void setArtist(String songArtist)
	{
		artist = songArtist;
	}
	
	/**
	 * setter method
	 * @param songAlbum
	 * 		String for album
	 */
	public void setAlbum(String songAlbum)
	{
		album = songAlbum;
	}

	/**
	 * setter method
	 * @param songLength
	 * 		int for length
	 */
	public void setLength(int songLength)
	{
		length = songLength;
	}
}

