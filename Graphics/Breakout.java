import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Breakout: Write a GraphicsProgram that draws the bricks and the paddle for
 * Breakout.
 * 
 * @author Sneha Parihar WS2014 23.11.2014
 */
public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;
	
	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;
	
	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	
	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;
	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;
	/** Paddle move speed */
	private static final int PADDLE_SPEED = 10;
	
	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;
	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;
	/** Separation between bricks */
	private static final int BRICK_SEP = 4;
	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1)* BRICK_SEP)/ NBRICKS_PER_ROW;
	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;
	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Animation delay or pause time between ball moves */
	private static final int DELAY = 50;

	/** Number of turns */
	private static final int NTURNS = 3;
	
	/** Declare the local variables */
	private GRect paddle;
	private GOval ball;
	private int life = 0;
	private int countOfBricks = NBRICK_ROWS * NBRICKS_PER_ROW;
	
	/** Ball Velocity */
	private double xVel = 5.0;
	private double yVel = 15.0;

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		setupGame();
		playGame();
	}

	/** Makes the setup for the game */
	private void setupGame() {
		setupBall();
		setupPaddle();
		setupBricks();
		addMouseListeners();
	}

	/** Makes the setup of the ball */
	private void setupBall() {
		drawBall();
	}

	/** Ball object is created on the screen */
	private void drawBall() {
		if (ball == null) {
			ball = new GOval(WIDTH / 2, HEIGHT / 2, BALL_RADIUS, BALL_RADIUS);
			ball.setFilled(true);
			add(ball);
		}
	}

	/** Makes the setup of the paddle */
	private void setupPaddle() {
		drawPaddle();
	}

	/** Paddle object is created on the screen */
	private void drawPaddle() {
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		add(paddle, WIDTH / 2, HEIGHT - PADDLE_Y_OFFSET);
		paddle.setFilled(true);
	}

	/** Makes the setup of the bricks */
	private void setupBricks() {
		drawBricks();
	}

	/** Brick layout is created on the screen */
	private void drawBricks() {
		int x = 0;
		int y = 0;
		for (int level = 1; level <= NBRICK_ROWS; level++) {
			y += BRICK_SEP;
			for (int i = 1; i <= NBRICKS_PER_ROW; i++) {
				x += BRICK_SEP;
				GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				add(brick, x, y);
				setColorToBrick(brick, level);
				x += BRICK_WIDTH;
			}
			x = 0;
			y += BRICK_HEIGHT;
		}
	}

	/** Bricks are coloured in a pattern */
	private void setColorToBrick(GRect brick, int level) {
		brick.setFilled(true);
		if (level == 1 || level == 2) {
			brick.setColor(Color.RED);
		} else if (level == 3 || level == 4) {
			brick.setColor(Color.ORANGE);
		} else if (level == 5 || level == 6) {
			brick.setColor(Color.YELLOW);
		} else if (level == 7 || level == 8) {
			brick.setColor(Color.GREEN);
		} else if (level == 9 || level == 10) {
			brick.setColor(Color.CYAN);
		}
	}

	/** Games begins! */
	private void playGame() {
		while (!gameOver()) {
			bounceBall();
		}
	}

	/** 
	 * Ball starts bouncing
	 * On every bounce from the paddle it ball collide with a brick and destroy it
	 * If the ball does not land on the paddle, then life is lost
	 * */
	private void bounceBall() {
		if (ball != null) {
			moveBall();			
			checkForCollision();
			checkOffScreen();
			pause(DELAY);
		}
	}

	/** Update and move ball */
	private void moveBall() {
		ball.move(xVel, yVel);
	}

	/**
	 * Determine if collision with floor, update velocities and location as
	 * appropriate.
	 */
	private void checkForCollision() {
		GPoint ballPosition = ball.getBounds().getLocation();
		GObject objCollider = findCollidingObject(ballPosition);
		if (objCollider != null) {
			if (objCollider == paddle) {
				collidedWithPaddle();
			} else {
				collidedWithBrick(objCollider);
			}
		}
	}

	/**
	 * @param ballPosition - position of the ball
	 * @return objCollider - colliding object with ball is returned
	 */
	private GObject findCollidingObject(GPoint ballPosition) {
		GObject objCollider = getElementAt(ballPosition);
		if (objCollider == null) {
			objCollider = getElementAt(ball.getX() + (2 * BALL_RADIUS),	ball.getY());
			if (objCollider == null) {
				objCollider = getElementAt(ball.getX(), ball.getY()	+ (2 * BALL_RADIUS));
				if (objCollider == null) {
					objCollider = getElementAt(ball.getX() + (2 * BALL_RADIUS),	ball.getY() + (2 * BALL_RADIUS));
					
				}	
			}
		}
		return objCollider;
	}

	/**
	 * ball has collided with the paddle, thus it will bounce back
	 */
	private void collidedWithPaddle() {
		yVel = -yVel;
	}

	/**
	 * ball has collided with the brick, thus the brick will be removed
	 */
	private void collidedWithBrick(GObject objBrick) {
		remove(objBrick);
		yVel = -yVel;
		countOfBricks--;
	}

	/**
	 * determines if ball has moved of screen, if it has, removes ball, sets
	 * variable to null
	 */
	private void checkOffScreen() {
		if (ball.getX() <= 0) {
			xVel = -xVel;
		} else if ((ball.getX() + BALL_RADIUS) >= WIDTH) {
			xVel = -xVel;
		}	
		if ((ball.getY() + BALL_RADIUS) <= 0) {
			yVel = -yVel;
		} else if ((ball.getY() + (BALL_RADIUS * 2)) >= HEIGHT) {
			remove(ball);
			ball = null;
			life++;
		}
	}
	
	/**
	 * on the mouse screen movement, paddle moves left or right
	 */
	public void mouseMoved(MouseEvent e) {
		double paddleLoc = paddle.getX();
		double mouseLoc = e.getX();
		if ((mouseLoc > paddleLoc) & ((paddleLoc + PADDLE_WIDTH) < WIDTH)) {
			paddle.move(PADDLE_SPEED, 0);
		}
		if ((mouseLoc < paddleLoc)) {
			paddle.move(-PADDLE_SPEED, 0);
		}
	}
	
	/**
	 * determines if game is over -- true if either all the lives are gone 
	 * or all the bricks are destroyed
	 */
	private boolean gameOver() {		
		if (ball == null) {
			if (life < NTURNS) {
				setupBall();
			} else {
				println("All lives are gone!! Game is over!!");
				println("Bricks scored: " + (100 - countOfBricks));
				return true;
			}
		} else if (countOfBricks == 0) {
			println("All bricks are destroyed!! You win!!");
		}
		return false;
	}
}
