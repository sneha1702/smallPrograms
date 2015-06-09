import java.awt.Color;

import acm.graphics.*;
import acm.program.GraphicsProgram;

/**
 * OlympicRings: Write a GraphicsProgram that draws the Olympic Rings.
 * 
 * @author Sneha Parihar, MSE, 02.11.2014
 */
public class OlympicRings extends GraphicsProgram {
	
	// Declaration of constants
	private static final int outerRadius = 50;
	private static final int innerRadius = 40;	
	private final int ringOffset = (outerRadius*2) + 10;
	private final int arcPosStart = 280;
	private final int arcNegStart = 190;
	private final int arcSweep = 90;
	private final int arcAngleOffset = 3;
	
	// This function is used to draw a ring with the given paramters
	private GOval drawRing( GOval ring ,int radius, Color ringColor, int OffsetX, int OffsetY) {
		double x = (getWidth() / 2);
		double y = (getHeight() / 2);
		ring = new GOval(((x + OffsetX) - radius), ((y + OffsetY) - radius) , (2 * radius), (2 * radius));
		ring.setColor(ringColor);
		ring.setFilled(true);
		add(ring);		
		return ring;
	}
	
	// This function is used to draw an arc with the given parameters
	private GArc drawArc( GArc arc ,int radius, Color arcColor, int offsetX, int offsetY, int arcStart,int offsetSweep) {
		double x = (getWidth() / 2);
		double y = (getHeight() / 2);
		arc = new GArc(((x + offsetX) - radius), ((y + offsetY) - radius) , (2 * radius), (2 * radius), arcStart, arcSweep-offsetSweep);
		arc.setColor(arcColor);
		add(arc);		
		return arc;
	}

	public void run() {
		paintRings();		
		paintIntersection();			
	}

	//This method is used to draw the five olympic rings
	private void paintRings() {
		GOval ring1 = null;
		drawRing(ring1,outerRadius,Color.BLUE,0,0);
		GOval innerRing1 = null;
		drawRing(innerRing1,innerRadius,Color.WHITE,0,0);
		
		GOval ring2 = null;
		drawRing(ring2,outerRadius,Color.BLACK,ringOffset,0);
		GOval innerRing2 = null;
		drawRing(innerRing2,innerRadius,Color.WHITE,ringOffset,0);
		
		GOval ring3 = null;
		drawRing(ring3,outerRadius,Color.RED,ringOffset*2,0);
		GOval innerRing3 = null;
		drawRing(innerRing3,innerRadius,Color.WHITE,ringOffset*2,0);
		
		GOval ring4 = null;
		drawRing(ring4,outerRadius,Color.YELLOW,outerRadius,outerRadius);
		GOval innerRing4 = null;
		drawRing(innerRing4,innerRadius,Color.WHITE,outerRadius,outerRadius);
		
		GOval ring5 = null;
		drawRing(ring5,outerRadius,Color.GREEN,(ringOffset + outerRadius),outerRadius);
		GOval innerRing5 = null;
		drawRing(innerRing5,innerRadius,Color.WHITE,(ringOffset + outerRadius),outerRadius);		
	
	}
	
	// This method is used to draw the intersection of the rings
	private void paintIntersection() {
		int arcAngle;
		for(int p = outerRadius; p > innerRadius; p--)
		{
			arcAngle = arcPosStart + arcAngleOffset;
			
			GArc arc1 = null;
			drawArc(arc1, p, Color.BLUE, 0, 0, arcAngle,0);
			
			GArc arc3 = null;
			drawArc(arc3, p, Color.BLACK, ringOffset, 0, arcAngle,0);
		}
		arcAngle = 0;
		for(int p = outerRadius; p > innerRadius; p--)
		{
			arcAngle = arcNegStart + arcAngleOffset;
			
			GArc arc2 = null;
			drawArc(arc2, p, Color.BLACK, ringOffset, 0, arcAngle,15);
			
			GArc arc4 = null;
			drawArc(arc4, p, Color.RED, ringOffset*2, 0, arcAngle,0);
		}
	}
}
