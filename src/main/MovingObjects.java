package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;

/**
 * Class: MovingObjects
 * 
 * @author A507 <br>
 *         Purpose: Super class for all moving objects to remove code
 *         duplication and lower coupling.
 *
 */
public class MovingObjects {
	public int centerX, centerY, radius, velX, velY, moveSpeed;
	private Color color;
	public boolean isHit = false;

	/**
	 * ensures: initializes a moving object super class with given parameters
	 * 
	 * @param centerX
	 * @param centerY
	 * @param radius
	 * @param moveSpeed
	 * @param color
	 */
	public MovingObjects(int centerX, int centerY, int radius, int moveSpeed, Color color) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
		this.velX = (int) (moveSpeed - Math.random() * moveSpeed * 2);
		this.velY = (int) (moveSpeed - Math.random() * moveSpeed * 2);
		this.moveSpeed = moveSpeed;
		this.color = color;
		normalizeVelocity();
	}

	/**
	 * ensures: draw object onto graphics
	 * 
	 * @param g
	 */
	public void drawOn(Graphics2D g) {
		// avoid having to untranslate by mutating a copy of the graphics content
		g = (Graphics2D) g.create();
		g.setColor(this.color);
		g.translate(centerX - radius, centerY - radius);
		if (this.color == Color.LIGHT_GRAY) {
			g.fillRect(0, 0, radius, radius);
		} else {
			g.fillOval(0, 0, radius * 2, radius * 2);
		}
	}

	/**
	 * ensures: updates current object position
	 * 
	 * @param dim
	 */
	public void update(Dimension2D dim) {
		centerX += velX;
		centerY += velY;
		if (centerX > dim.getWidth() || centerX < 0) {
			centerX = (int) Math.min(Math.max(centerX, 0), dim.getWidth());
			velX = -velX;
		}
		if (centerY > dim.getHeight() || centerY < 0) {
			velY = -velY;
			centerY = (int) Math.min(Math.max(centerY, 0), dim.getHeight());
		}
	}

	/**
	 * ensures: Determine if two moving objects collided with each other (circular).
	 * 
	 * @param other
	 * @return boolean determining whether or not two objects are touching
	 */
	public boolean overlapsWith(MovingObjects other) { // used for circle objects
		int xDiff = centerX - (other.centerX);
		int yDiff = centerY - (other.centerY);
		int rxDiff = centerX - (other.centerX + other.radius / 2);
		int ryDiff = centerY - (other.centerY + other.radius / 2);
		double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
		double rdisance = Math.sqrt(rxDiff * rxDiff + ryDiff * ryDiff);
		if (other.color == Color.BLACK && this.color == Color.LIGHT_GRAY) {
			return this.radius / 2 + other.radius / 2 >= rdisance;
		} else if (other.color == Color.BLACK) {
			return this.radius + other.radius / 2 >= distance;
		}
		return this.radius + other.radius >= distance;
	}

	/**
	 * ensures: Determine if the moving object collided with given rectangle.
	 * 
	 * @param b
	 * @return boolean determining whether or not two objects are touching
	 */
	public boolean insideBox(Rectangle2D b) { // used for square objects
		if (this.color == Color.LIGHT_GRAY || this.color == Color.BLACK) {
			return b.intersects(centerX - radius, centerY - radius, radius, radius);
		}
		return b.intersects(centerX - radius, centerY - radius, radius * 2, radius * 2);
	}

	/**
	 * ensures: object bounces off platform objects and moves in opposite direction
	 * 
	 * @param other
	 */
	public void bouncePlatform(Platform other) {
		this.velX = this.centerX - other.getX();
		this.velY = this.centerY - other.getY();
		normalizeVelocity();
	}

	/**
	 * ensures: Move in opposite direction of collision with other moving thing.
	 * 
	 * @param other
	 */
	public void bounce(MovingObjects other) {
		this.velX = this.centerX - other.centerX;
		this.velY = this.centerY - other.centerY;
		normalizeVelocity();
	}

	/**
	 * ensures: object velocity is normalized
	 */
	public void normalizeVelocity() {
		// move down, right if velocity is set to zero
		if (this.velX == 0 && this.velY == 0) {
			this.velX = 1;
			this.velY = 1;
		}
		// normalize vector
		double vectorLength = Math.sqrt(velX * velX + velY * velY);
		this.velX = (int) (this.velX / vectorLength * moveSpeed * 2);
		this.velY = (int) (this.velY / vectorLength * moveSpeed * 2);
	}

	/**
	 * ensures: sets hit boolean for both objects to true
	 * 
	 * @param other
	 */
	public void isHit(MovingObjects other) {
		this.isHit = true;
		other.isHit = true;
	}

	/**
	 * ensures: stops the object from moving by zeroing velocities
	 */
	public void stopMoving() {
		this.velX = 0;
		this.velY = 0;
	}

	/**
	 * ensures: sets the color of current object
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * ensures: gets color of current object
	 * 
	 * @return color
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * ensures: get radius of current object
	 * 
	 * @return radius
	 */
	public int getRadius() {
		return this.radius;
	}

	/**
	 * ensures: get center position of x coordinate
	 * 
	 * @return centerX
	 */
	public int getCenterX() {
		return this.centerX;
	}

	/**
	 * ensures: get center position of y coordinate
	 * 
	 * @return centerY
	 */
	public int getCenterY() {
		return this.centerY;
	}
}
