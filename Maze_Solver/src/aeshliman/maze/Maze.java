/**
 * Author: Isaac Aeshliman
 * Date: Mar 4, 2021
 * Description:
 *
 * TODO: 
 */

package aeshliman.maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Maze
{
	// Instance Variables
	private int cols;
	private int rows;
	private Node start;
	private Node finish;
	
	// Constructor
	public Maze()
	{
		// Set maze size and initialize start and finish nodes
		cols = 5;
		rows = 5;
		start = new Node(1,0);
		finish = new Node(2,4);
		
		// Initialize all other nodes
		Node n1 = new Node(1,1);
		Node n2 = new Node(2,1);
		Node n3 = new Node(3,1);
		Node n4 = new Node(4,1);
		Node n5 = new Node(0,2);
		Node n6 = new Node(1,2);
		Node n7 = new Node(4,2);
		Node n8 = new Node(1,3);
		Node n9 = new Node(3,3);
		Node n10 = new Node(4,3);
		Node n11 = new Node(1,4);
		
		// Sets node adjacencies
		start.setSouth(n1);
		n1.setNorth(start);
		n1.setEast(n2);
		n1.setSouth(n6);
		n2.setEast(n3);
		n2.setWest(n1);
		n3.setEast(n4);
		n3.setWest(n2);
		n4.setSouth(n7);
		n4.setWest(n3);
		n5.setEast(n6);
		n6.setNorth(n1);
		n6.setSouth(n8);
		n6.setWest(n5);
		n7.setNorth(n4);
		n7.setSouth(n10);
		n8.setNorth(n6);
		n8.setSouth(n11);
		n9.setEast(n10);
		n10.setNorth(n7);
		n10.setWest(n9);
		n11.setNorth(n8);
		n11.setEast(finish);
		finish.setWest(n11);
		
		n1.setWest(finish);
	}
	
	// Getters and Setters
	public int getCols() { return this.cols; }
	public int getRows() { return this.rows; }
	public Node getStart() { return this.start; }
	public Node getFinish() { return this.finish; }
	
	// Operations
	public Stack<Node> solve()
	{
		HashSet<Node> visited = new HashSet<Node>();
		visited.add(start);
		return start.solve(visited, finish);
	}
	
	public HashSet<Node> allNodes()
	{
		// Initialize queue and visited set with start node
		Queue<Node> queue = new LinkedList<Node>();
		HashSet<Node> visited = new HashSet<Node>();
		queue.add(start);
		visited.add(start);
		
		// Visit each node in the maze according to breadth first algorithm
		while(!queue.isEmpty())
		{
			Node current = queue.poll();
			for(Node node : current.getAdjacent())
			{
				if(node!=null&&!visited.contains(node))
				{
					queue.add(node);
					visited.add(node);
				}
			}
		}
		return visited;
	}
	
	public String breadthFirst()
	{
		// Initialize queue and visted set with start node
		Queue<Node> queue = new LinkedList<Node>();
		HashSet<Node> visited = new HashSet<Node>();
		queue.add(start);
		visited.add(start);
		
		// Visit each node in the maze according to breadth first algorithm
		String result = "";
		int count = 1;
		while(!queue.isEmpty())
		{
			Node current = queue.poll();
			for(Node node : current.getAdjacent())
			{
				if(node!=null&&!visited.contains(node))
				{
					queue.add(node);
					visited.add(node);
				}
			}
			result += count++ + ": " + current + "\n";
		}
		return result;
	}
	
	public void loadMaze(File file)
	{
		try(Scanner scan = new Scanner(file);)
		{
			int startX = scan.nextInt();
			int startY = scan.nextInt();
			int endX = scan.nextInt();
			int endY = scan.nextInt();
			scan.nextLine();
			ArrayList<ArrayList<Node>> grid = new ArrayList<ArrayList<Node>>();
			int row = 0;
			while(scan.hasNext())
			{
				grid.add(new ArrayList<Node>());
				String lineStr = scan.nextLine();
				int lineLength = lineStr.length();
				int[] lineSplit = new int[lineLength];
				int line = Integer.parseInt(lineStr);
				for(int i=lineLength-1; i>=0; i--)
				{
					lineSplit[i] = line%10;
					line /= 10;
				}
				for(int i=0; i<lineSplit.length; i++)
				{
					if(lineSplit[i]==1) grid.get(row).add(i,new Node(i,row));
					else grid.get(row).add(i,null);
				}
				row++;
			}
			
			for(int i=0; i<grid.size(); i++) // Rows
			{
				for(int j=0; j<grid.get(i).size(); j++) // Columns
				{
					Node node = grid.get(i).get(j);
					if(node!=null)
					{
						try
						{
							Node adjacent = grid.get(i-1).get(j);
							if(adjacent!=null) { node.setNorth(adjacent); }
						}
						catch(IndexOutOfBoundsException e) {  }
						
						try
						{
							Node adjacent = grid.get(i).get(j+1);
							if(adjacent!=null) { node.setEast(adjacent); }
						}
						catch(IndexOutOfBoundsException e) {  }
						
						try
						{
							Node adjacent = grid.get(i+1).get(j);
							if(adjacent!=null) { node.setSouth(adjacent); }
						}
						catch(IndexOutOfBoundsException e) {  }
						
						try
						{
							Node adjacent = grid.get(i).get(j-1);
							if(adjacent!=null) { node.setWest(adjacent); }
						}
						catch(IndexOutOfBoundsException e) {  }
					}
				}
			}
			
			start = grid.get(startY).get(startX);
			finish = grid.get(endY).get(endX);
		}
		catch(FileNotFoundException e) { e.printStackTrace(); }
	}
	
	// toString
	public String toString()
	{
		return breadthFirst().trim();
	}
	
}
