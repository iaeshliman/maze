/**
 * Author: Isaac Aeshliman
 * Date: Feb 11, 2020
 * Description:
 *
 * TODO: 
 */

package aeshliman.maze;

import java.awt.EventQueue;

public class Driver
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Window window = new Window(960,540);
					window.window.setVisible(true);
				}
				catch(Exception e) {e .printStackTrace(); }
			}
		});
	}
}
