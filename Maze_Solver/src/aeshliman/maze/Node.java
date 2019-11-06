/*
 * Author: Isaac Aeshliman
 * Date: Nov 6, 2019
 * Description: A node in a maze data structure
 */
package aeshliman.maze;

public class Node<T>
{
	// Instance Variables
	private T data;
	private Node<T> north;
	private Node<T> east;
	private Node<T> south;
	private Node<T> west;
	
	
	
	// Constructors
	public Node()
	{
		this.data = null;
		this.north = null;
		this.east = null;
		this.south = null;
		this.west = null;
	}
	
	public Node(T data)
	{
		this.data = data;
		this.north = null;
		this.east = null;
		this.south = null;
		this.west = null;
	}
	
	
	
	// Getters
	public T getData()
	{
		return this.data;
	}
	
	public Node<T> getNorth()
	{
		return this.north;
	}
	
	public Node<T> getEast()
	{
		return this.east;
	}
	
	public Node<T> getSouth()
	{
		return this.south;
	}
	
	public Node<T> getWest()
	{
		return this.west;
	}
	
	
	
	// Setters
	public void setData(T data)
	{
		this.data = data;
	}
	
	public void setNorth(Node<T> node)
	{
		this.north = node;
	}
	
	public void setEast(Node<T> node)
	{
		this.east = node;
	}
	
	public void setSouth(Node<T> node)
	{
		this.south = node;
	}
	
	public void setWest(Node<T> node)
	{
		this.west = node;
	}
	
	
	
	// Operations
	
	
	
	// toString
	public String toString()
	{
		return this.data.toString();
	}
}
