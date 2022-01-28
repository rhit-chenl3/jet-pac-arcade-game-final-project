package main;

import java.awt.Color;
import java.awt.geom.Dimension2D;

/**
 * Class: Bullet
 * 
 * @author A507 <br>
 *         Purpose: Bullet class with its own parameters and methods to then
 *         call MovingObjects super class
 *
 */
public class Bullet extends MovingObjects {
	public static final int BULLET_RADIUS = 6;
	public static final int BULLET_SPEED = 7;
//	public static final Color BULLET_COLOR = Color.YELLOW;

	/**
	 * ensures: initializes bullet with given parameters
	 * 
	 * @param centerX
	 * @param centerY
	 * @param color
	 */
	public Bullet(int centerX, int centerY, Color color) { // hero bullets will be BLACK, alien's will be cyan
		super(centerX, centerY, BULLET_RADIUS, BULLET_SPEED, color);
		this.velY = 0;
		this.velX = BULLET_SPEED;
	}

	/**
	 * ensures: updates position of bullet
	 */
	@Override
	public void update(Dimension2D dim) {
		centerX += velX;
		centerY += velY;

	}

	/**
	 * ensures: tells whether or not bullet is off screen
	 * 
	 * @param dim
	 * @return boolean for if the bullet is off screen
	 */
	public boolean offScreen(Dimension2D dim) {
		return this.centerX > dim.getWidth() || this.centerX < 0;
	}

	/**
	 * ensures: makes bullets travel vertically for higher difficulty levels
	 */
	public void level2Direction() {
		if (this.centerY > 280) {
			this.velX = 0;
			this.velY = -BULLET_SPEED;
		} else {
			this.velX = 0;
			this.velY = BULLET_SPEED;
		}
	}

	/**
	 * ensures: corrects direction of bullet to move in the same direction as given
	 * MovingObject's motion
	 * 
	 * @param other
	 */
	public void correctDirection(MovingObjects other) {
		if (other.velX > 0 && this.velX < 0) {
			this.velX = -this.velX;
		} else if (this.velX > 0 && other.velX < 0) {
			this.velX = -this.velX;
		} else if (other.velX == 0) {
			if (other.centerX > 250) {
				this.velX = -BULLET_SPEED;
			} else {
				this.velX = BULLET_SPEED;
			}
		}
	}

}
