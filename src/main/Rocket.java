package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Dimension2D;
import java.util.ArrayList;

/**
 * Class: Rocket
 * 
 * @author A507 <br>
 *         Purpose: Rocket object in the game that is required to be completed
 *         to reach the next level
 *
 */
public class Rocket extends MovingObjects {
	private static int x = 350;
	private static int y = 550 - 155;
	private static int size = 35;
	private static int speed = 6;
	private static Color color = Color.green;
	public int number = 0;

	/**
	 * ensures: initializes Rocket object
	 */
	public Rocket() {
		super(x, y, size / 4, speed, color);
	}

	/**
	 * ensures: draw object onto graphics
	 * 
	 * @param g
	 */
	public void drawOn(Graphics2D g) {
		g.drawRect(this.centerX - size, this.centerY, size, 105);
		for (int i = 0; i < number; i++) {
			g.fillRect(this.centerX - size, this.centerY + 70 - i * 35, size, size);
		}

	}

	/**
	 * ensures: returns whether or not the rocket has left the top of the screen and
	 * "reached next level"
	 * 
	 * @return boolean true if reached next level, false otherwise
	 */
	public boolean reachNextLevel() {
		return this.centerY + 100 < 0;
	}

	/**
	 * ensures: updates rocket position based on rocket speed
	 * 
	 * @param Rspeed
	 */
	public void update(int Rspeed) {
		this.centerY = centerY + Rspeed;
	}

	/**
	 * ensures: sets number to parameter n
	 * 
	 * @param n
	 */
	public void setnumber(int n) {
		this.number = n;
	}

	/**
	 * ensures: sets current y position to given parameter y
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.centerY = y;
	}
}
