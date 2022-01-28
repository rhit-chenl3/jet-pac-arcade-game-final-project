package main;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Class: Alien
 * 
 * @author A507 <br>
 *         Purpose: Alien class with its own parameters and methods to then call
 *         MovingObjects super class
 *
 */
public class Alien extends MovingObjects {
	public static final int ALIEN_RADIUS = 15;
	public static final int ALIEN_SPEED = 2;
	public static final Color ALIEN_COLOR = Color.GREEN;

	/**
	 * @param x
	 * @param y
	 */
	public Alien(int x, int y) {
		super(x, y, ALIEN_RADIUS, ALIEN_SPEED, ALIEN_COLOR);
	}

	/**
	 * ensures: Alien shoot basic bullet (left and right)
	 * 
	 * @param bullets
	 */
	public void shootBullet(ArrayList<Bullet> bullets) {
		if (this.getColor() == Color.RED) {
			centerX += velX;
			centerY += velY;
			Bullet newBullet = new Bullet(this.centerX, this.centerY, Color.CYAN);
			newBullet.correctDirection(this);
			bullets.add(newBullet);
		}

	}

	/**
	 * ensures: Alien shoots higher difficulty bullets (up and down)
	 * 
	 * @param bullets
	 */
	public void shootMoreBullets(ArrayList<Bullet> bullets) {

		if (this.getColor() == Color.RED) {
			centerX += velX;
			centerY += velY;
			Bullet newBullet = new Bullet(this.centerX, this.centerY, Color.CYAN);
			newBullet.level2Direction();
			bullets.add(newBullet);
		}
	}
}
