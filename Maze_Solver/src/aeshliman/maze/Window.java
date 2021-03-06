package aeshliman.maze;

import java.io.File;
import java.util.Stack;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * Author: Isaac Aeshliman
 * Date: Mar 4, 2021
 * Description:
 *
 * TODO: 
 */

public class Window
{
	// Instance Variables
	protected JFrame window;
	private Maze maze;
	private JLabel[][] grid;
	private JPanel mazePanel;

	/**
	 * Create the application.
	 */
	public Window(int width, int height)
	{
		initialize(width, height);
		initializeGrid();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int width, int height)
	{
		window = new JFrame();
		window.setTitle("Maze Solver");
		window.setBounds(100, 100, 720, 405);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel controlPanel = new JPanel();
		window.getContentPane().add(controlPanel, BorderLayout.NORTH);
		
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fc = new JFileChooser(new File("."));
				if(fc.showOpenDialog(window)==JFileChooser.APPROVE_OPTION)
				{ 
					maze.loadMaze(fc.getSelectedFile());
					grid = null;
					mazePanel.removeAll();
					initializeGrid();
					paint(mazePanel);
					
				}
				else System.err.println("Invalid file " + fc.getName());
			}
		});
		loadButton.setPreferredSize(new Dimension(100, 25));
		controlPanel.add(loadButton);
		
		JButton solveButton = new JButton("Solve");
		solveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Stack<Node> path = maze.solve();
				int count = 1;
				while(!path.isEmpty())
				{
					Node node = path.pop();
					if(maze.getStart()==node) continue;
					if(maze.getFinish()==node) continue;
					grid[node.getRow()][node.getCol()].setText(Integer.toString(count++));	
				}
			}
		});
		solveButton.setPreferredSize(new Dimension(100, 25));
		controlPanel.add(solveButton);
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				grid = null;
				mazePanel.removeAll();
				initializeGrid();
				paint(mazePanel);
			}
		});
		resetButton.setPreferredSize(new Dimension(100, 25));
		controlPanel.add(resetButton);
		
		maze = new Maze();
		
		mazePanel = new JPanel();
		window.getContentPane().add(mazePanel, BorderLayout.CENTER);
		mazePanel.setLayout(new GridLayout(maze.getRows(), maze.getCols(), 0, 0));
	}
	
	private void initializeGrid()
	{
		grid = new JLabel[maze.getRows()][maze.getCols()];
		mazePanel.setLayout(new GridLayout(maze.getRows(), maze.getCols(), 0, 0));
		for(int i=0; i<grid.length; i++)
		{
			for(int j=0; j<grid[i].length; j++)
			{
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(1, 1, 0, 0));
				panel.setBorder(new LineBorder(Color.BLACK));
				mazePanel.add(panel);
				JLabel label = new JLabel("X");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(label);
				grid[i][j] = label;
			}
		}
		
		for(Node node : maze.allNodes())
		{
			if(maze.getStart()==node) grid[node.getRow()][node.getCol()].setText("S");
			else if(maze.getFinish()==node) grid[node.getRow()][node.getCol()].setText("F");
			else grid[node.getRow()][node.getCol()].setText(" ");
		}
	}
	
	private void paint(JComponent JComponent)
	{
		JComponent.revalidate();
		JComponent.repaint();
	}
}
