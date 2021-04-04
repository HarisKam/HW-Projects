/*
 * Name: Haris Kamran
 * ID#: 112786164
 * Recitation 07
 */

/**
 * The SongNode class which consists of a link to a previous SongNode, a link to the next SongNode, 
 *   and the Song contained within this SongNode.
 * @author haris
 *
 */
public class SongNode 
{
	/**
	 * Link to the previous SongNode.
	 */
	SongNode prev;
	
	/**
	 * Link to the next SongNode.
	 */
	SongNode next;
	
	/**
	 * The Song contained within this SongNode.
	 */
	Song data = new Song();
	
	/**
	 * The default constructor for the SongNode Object.
	 * Initializes next to null and prev to null.
	 */
	public SongNode()	
	{
		next = null;
		prev = null;
	}
	
	/**
	 * Other constructor for the SongNode Object.
	 * @param myPrev
	 * 		SongNode that is assigned to prev.
	 * @param myNext
	 * 		SongNode that is assigned to next.
	 * @param myData
	 * 		Song that is assigned to data.
	 */
	public SongNode(SongNode myPrev, SongNode myNext, Song myData)
	{
		prev = myPrev;
		next = myNext;
		data = myData;
	}
	
	/**
	 * getter method
	 * @return
	 * 		data
	 */
	public Song getData()
	{
		return this.data;
	}
	
	/**
	 * getter method
	 * @return
	 * 		prev
	 */
	public SongNode getPrev()
	{
		return this.prev;
	}
	
	/**
	 * getter method
	 * @return
	 * 		next
	 */
	public SongNode getNext()
	{
		return this.next;
	}
	
	/**
	 * setter method
	 * @param newData
	 * 		Song assigned to data.
	 */
	public void setData(Song newData)
	{
		data = newData;
	}
	
	/**
	 * setter method
	 * @param newPrev
	 * 		SongNode assigned to prev.
	 */
	public void setPrev(SongNode newPrev)
	{
		prev = newPrev;
	}
	
	/**
	 * setter method
	 * @param newNext
	 * 		SongNode assigned to next.
	 */
	public void setNext(SongNode newNext)
	{
		next = newNext;
	}
	
	
}
