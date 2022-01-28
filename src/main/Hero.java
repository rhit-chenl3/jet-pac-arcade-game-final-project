package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Class: Hero
 * 
 * @author A507 <br>
 *         Purpose: Hero class for hero methods and information
 *
 */
public class Hero extends MovingObjects {

	private int X;
	private int Y;
	private int Size;
	private int DEFAULT_X = 100;
	private int DEFAULT_Y = 350;
	private int DEFAULT_SIZE = 30;
	private Color color = Color.BLACK;
	private int lives = 4;
	private int numOfRocketPieces = 0;

	/**
	 * ensures: hero object is initialized and calls super class
	 */
	public Hero() {
		super(250, 250, 30, 0, Color.BLACK);
		this.X = this.DEFAULT_X;
		this.Y = this.DEFAULT_Y;
		this.Size = this.DEFAULT_SIZE;
		this.centerX = this.X + DEFAULT_SIZE / 2;
		this.centerY = this.Y + DEFAULT_SIZE / 2;
	}

	/**
	 * ensures: draw object onto graphics
	 * 
	 * @param g
	 */
	@Override
	public void drawOn(Graphics2D g) {
		g.drawRect(this.X, this.Y, this.Size, this.Size);

	}

	/**
	 * ensures: updates current x and y position
	 * 
	 * @param x
	 * @param y
	 */
	public void updatePosition(int x, int y) {
		this.velY = y;
		this.velX = x;
		this.X = this.X + velX;
		this.Y = this.Y + velY;
		this.centerX = this.X + DEFAULT_SIZE / 2;
		this.centerY = this.Y + DEFAULT_SIZE / 2;
	}

	/**
	 * ensures: return boolean if collides below platform
	 * 
	 * @param t
	 * @return boolean true if collides with platform, false otherwise
	 */
	public Boolean collisionDown(Platform t) {
		if ((X > t.getCornerX() - 4 & X < (t.getCornerX() + t.getWidth() - 4)
				|| (X + Size > t.getCornerX() + 4 && X + Size < t.getCornerX() + t.getWidth() + 4))) {
			if (Y + Size > t.getCornerY() && Y < t.getCornerY()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ensures: return boolean if collides on top of platform
	 * 
	 * @param t
	 * @return boolean true if collides with platform, false otherwise
	 */
	public Boolean collisionUp(Platform t) {
		if ((X > t.getCornerX() & X < (t.getCornerX() + t.getWidth())
				|| (X + Size > t.getCornerX() && X + Size < t.getCornerX() + t.getWidth()))) {
			if (Y < t.getCornerY() + t.getHeight() && Y > t.getCornerY()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ensures: return boolean if collides on left side of platform
	 * 
	 * @param t
	 * @return boolean true if collides with platform, false otherwise
	 */
	public Boolean collisionLeft(Platform t) {
		if ((Y > t.getCornerY() & Y < t.getCornerY() + t.getHeight())
				|| (Y + Size - 5 > t.getCornerY() && Y + Size < t.getCornerY() + t.getHeight())
				|| (Y < t.getCornerY() && Y + Size > t.getCornerY() + t.getHeight())) {
			if (X < t.getCornerX() + t.getWidth() && X + Size > t.getCornerX()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ensures: return boolean if collides on right side of platform
	 * 
	 * @param t
	 * @return boolean true if collides with platform, false otherwise
	 */
	public Boolean collisionRight(Platform t) {
		if ((Y > t.getCornerY() & Y < t.getCornerY() + t.getHeight())
				|| (Y + Size - 5 > t.getCornerY() && Y + Size < t.getCornerY() + t.getHeight())
				|| (Y < t.getCornerY() && Y + Size > t.getCornerY() + t.getHeight())) {
			if (X < t.getCornerX() + t.getWidth() && X + Size > t.getCornerX()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ensures: returns Y velocity
	 * 
	 * @return current Y velocity
	 */
	public int getYspeed() {
		return this.velY;
	}

	/**
	 * ensures: creates a new bullet to shoot
	 * 
	 * @param b
	 */
	public void shooting(ArrayList<Bullet> b) {
		Bullet newBullet = new Bullet(this.centerX, this.centerY, this.color);
		newBullet.correctDirection(this);
		b.add(newBullet);
	}

	/**
	 * ensures: decreases lives
	 */
	public void getHit() {
		this.lives--;
	}

	/**
	 * @return boolean true if hero lives more than 0, false otherwise
	 */
	public boolean isAlive() {
		return this.lives > 0;
	}

	/**
	 * ensures: increases number of rocket pieces on hero
	 */
	public void pickUpRocketPiece() {
		numOfRocketPieces++;
	}

	/**
	 * @return number of current rocket pieces held
	 */
	public int getNumOfRocketPieces() {
		return numOfRocketPieces;
	}

	/**
	 * @return number of lives
	 */
	public int getLives() {
		return this.lives;
	}

	/**
	 * reset number of held rocket pieces to 0
	 */
	public void resetRocketPiece() {
		this.numOfRocketPieces = 0;
	}

	/**
	 * ensures: reset hero lives back to 4
	 */
	public void resetHeroLives() {
		this.lives = 4;
	}

	/**
	 * ensures: when hero dies, move him off screen and reduce size
	 */
	public void dead() {
		this.centerX = 600;
		this.centerY = 600;
		this.Size = -1;
	}

	/**
	 * ensures: resets hero position and size
	 */
	public void reset() {
		this.X = this.DEFAULT_X;
		this.Y = this.DEFAULT_Y;
		this.Size = this.DEFAULT_SIZE;
	}

}
