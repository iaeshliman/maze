/*
 * Author: Isaac Aeshliman
 * Date: Nov 6, 2019
 * Description: Manages the maze data structure
 */
package aeshliman.maze;

public class Maze<T>
{
	// Instance Variables
	private Node<T> corner;
	private Node<T> start;
	private Node<T> end;
	
	
	
	// Constructor
	public Maze()
	{
		this.corner = null;
		this.start = null;
		this.end = null;
	}
	
	
	
	// Getters
	public Node<T> getCorner()
	{
		return this.corner;
	}
	
	public Node<T> getStart()
	{
		return this.start;
	}
	
	public Node<T> getEnd()
	{
		return this.end;
	}
	
	
	
	// Setters
	public void setCorner(Node<T> node)
	{
		this.corner = node;
	}
	
	public void setStart(Node<T> node)
	{
		this.start = node;
	}
	
	public void setEnd(Node<T> node)
	{
		this.end = node;
	}
	
	
	
	// Operations
	
	
	
	
}
