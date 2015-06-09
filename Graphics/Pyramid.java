import acm.graphics.*;
import acm.program.GraphicsProgram;

/**
 * PyramidProgram: Make a pyramid of Bricks
 * 
 * @author Sneha Parihar, MSE, 30.10.2014
 */
public class Pyramid extends GraphicsProgram {

	final int BRICK_WIDTH = 30;
	final int BRICK_HEIGHT = 12;
	final int BRICKS_IN_BASE = 14;

	public void run() {
		
		int x = 0;
		int startOnX = 0;
		int y = BRICK_HEIGHT*BRICKS_IN_BASE;
		
		for(int level = BRICKS_IN_BASE; level > 0; level--)
		{			
			for(int i = 0; i < level ; i++) 
			{
				GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);				
				add(brick,x,y);
				x += BRICK_WIDTH;
			}
			startOnX += (BRICK_WIDTH/2);
			x = startOnX;
			y = y - BRICK_HEIGHT;			
		}
	}
}
