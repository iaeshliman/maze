/**
 * Author: Isaac Aeshliman
 * Date: Mar 4, 2021
 * Description:
 *
 * TODO: 
 */

package aeshliman.maze;

import java.util.HashSet;
import java.util.Stack;

public class Node
{	
	// Instance Variables
	private int col;
	private int row;
	private Node[] adjacent;
	
	// Constructors
	public Node(int col, int row)
	{
		this.col = col;
		this.row = row;
	}
	
	public Node(int col, int row, Node[] adjacent)
	{
		this.col = col;
		this.row = row;
		for(int i=0; i<adjacent.length; i++) this.adjacent[i] = adjacent[i];
	}
	
	{
		adjacent = new Node[Direction.values().length];
		adjacent[Direction.NORTH.value] = null;
		adjacent[Direction.EAST.value] = null;
		adjacent[Direction.SOUTH.value] = null;
		adjacent[Direction.WEST.value] = null;
	}
	
	// Getters and Setters
	public int getCol() { return this.col; }
	public int getRow() { return this.row; }
	public Node[] getAdjacent() { return adjacent; }
	public Node getNorth() { return adjacent[Direction.NORTH.value]; }
	public Node getEast() { return adjacent[Direction.EAST.value]; }
	public Node getSouth() { return adjacent[Direction.SOUTH.value]; }
	public Node getWest() { return adjacent[Direction.WEST.value]; }
	
	public void setNorth(Node node) { adjacent[Direction.NORTH.value] = node; }
	public void setEast(Node node) { adjacent[Direction.EAST.value] = node; }
	public void setSouth(Node node) { adjacent[Direction.SOUTH.value] = node; }
	public void setWest(Node node) { adjacent[Direction.WEST.value] = node; }
	
	// Operations
	public Stack<Node> solve(HashSet<Node> visited, Node goal) // solves depth first, need to fix for breadth first
	{
		// If the finish was found, add this node to the path and return
		if(this==goal)
		{
			Stack<Node> path = new Stack<Node>();
			path.add(this);
			return path;
		}
		// If there are no more nodes that can be visited return null
		else if(!this.canVisit(visited)) { return null; }
		// If there are more nodes to visit recursively visit each appending newly visited node
		else
		{
			Stack<Node> path = null;
			if(getNorth()!=null&&!visited.contains(getNorth())) // Visit north node
			{
				visited.add(getNorth());
				HashSet<Node> copy = new HashSet<Node>(visited);
				Stack<Node> temp = getNorth().solve(copy,goal); // Recursively visit every node depth first
				if(temp!=null) // If a path was found append this node and return path
				{
					if(path==null||temp.size()<path.size())
					{
						path = temp;
						path.add(this);
					}
				}
			}
			if(getEast()!=null&&!visited.contains(getEast())) // Visit east node
			{
				visited.add(getEast());
				HashSet<Node> copy = new HashSet<Node>(visited);
				Stack<Node> temp = getEast().solve(copy,goal);
				if(temp!=null)
				{
					if(path==null||temp.size()<path.size())
					{
						path = temp;
						path.add(this);
					}
				}
			}
			if(getSouth()!=null&&!visited.contains(getSouth())) // Visit south node
			{
				visited.add(getSouth());
				HashSet<Node> copy = new HashSet<Node>(visited);
				Stack<Node> temp = getSouth().solve(copy,goal);
				if(temp!=null)
				{
					if(path==null||temp.size()<path.size())
					{
						path = temp;
						path.add(this);
					}
				}
			}
			if(getWest()!=null&&!visited.contains(getWest())) // Visit west node
			{
				visited.add(getWest());
				HashSet<Node> copy = new HashSet<Node>(visited);
				Stack<Node> temp = getWest().solve(copy,goal);
				if(temp!=null)
				{
					if(path==null||temp.size()<path.size())
					{
						path = temp;
						path.add(this);
					}
				}
			}
			return path;
		}
	}
	
	public boolean canVisit(HashSet<Node> visited)
	{
		for(Node node : adjacent) { if(!visited.contains(node)) return true; }
		return false;
	}
	
	// toString
	public String toString()
	{
		return "Column " + col + " - Row " + row;
	}
}
