package main;

import java.awt.Color;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;

/**
 * Class:RocketPiece
 * 
 * @author A507 <br>
 *         Purpose: sub class of movingObject and it has its own parameters
 */
public class RocketPiece extends MovingObjects {
	public static final int ROCKET_SIZE = 35;
	public static final Color ROCKET_COLOR = Color.LIGHT_GRAY;
	public static final int ROCKET_FALL_SPEED = 6;
	public Rectangle2D rect = new Rectangle2D.Double();

	/**
	 * 
	 * @param centerX
	 * @param centerY
	 */
	public RocketPiece(int centerX, int centerY) {
		super(centerX, centerY, ROCKET_SIZE, ROCKET_FALL_SPEED, ROCKET_COLOR);
		this.velX = 0;
		this.velY = ROCKET_FALL_SPEED;
	}

	/**
	 * rewrite the update method of MovingObjects to make it adept rectangular
	 * object
	 * 
	 * @param dim
	 */
	@Override
	public void update(Dimension2D dim) {
		centerX += velX;
		centerY += velY;
		this.rect.setRect(this.centerX - ROCKET_SIZE / 2, this.centerY - ROCKET_SIZE / 2, ROCKET_SIZE, ROCKET_SIZE);
	}

	/**
	 * make rocket piece fall
	 */
	public void fall() {
		this.velY = 6;
	}

}
