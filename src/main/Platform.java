package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Class: Platform
 * 
 * @author A507 <br>
 *         Purpose: The platform class that will scan the text files to
 *         determine platform locations.
 * 
 */
public class Platform {
	private static final int HEIGHT = 20;
	private static final int WIDTH = 100;

	private Color color;
	private int x;
	private int y;
	public Rectangle2D r = new Rectangle2D.Double();

	/**
	 * ensures: platform object is initialized with given parameters
	 * 
	 * @param x
	 * @param y
	 * @param color
	 */
	public Platform(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;

	}

	/**
	 * ensures: Draws object onto graphics2D
	 * 
	 * @param g2
	 */
	public void drawOn(Graphics2D g2) {
		// TODO: Draw the platform (a rectangle)
		g2.setColor(this.color);
		this.r.setRect(this.x, this.y, WIDTH, HEIGHT);
		g2.fill(this.r);
		g2.draw(this.r);
	}

	/**
	 * @return center x value
	 */
	public int getX() {
		return this.x + WIDTH / 2;
	}

	/**
	 * @return center y value
	 */
	public int getY() {
		return this.y + HEIGHT / 2;
	}

	/**
	 * @return width
	 */
	public int getWidth() {
		return WIDTH;
	}

	/**
	 * @return height
	 */
	public int getHeight() {
		return HEIGHT;
	}

	/**
	 * @return y value
	 */
	public int getCornerX() {
		return this.x;
	}

	/**
	 * @return x value
	 */
	public int getCornerY() {
		return this.y;
	}
}
