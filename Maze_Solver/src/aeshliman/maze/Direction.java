/**
 * Author: Isaac Aeshliman
 * Date: Mar 4, 2021
 * Description:
 *
 * TODO: 
 */

package aeshliman.maze;

public enum Direction
{
	NORTH(0),
	EAST(1),
	SOUTH(2),
	WEST(3);
	
	public final int value;
	private Direction(int value) { this.value = value; }
}
