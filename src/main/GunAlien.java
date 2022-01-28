package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class: GunAlien
 * 
 * @author A507 <br>
 *         Purpose: sub class of Alien that shoots bullets
 *
 */
public class GunAlien extends Alien {

	/**
	 * ensures: initializes gun alien based on given parameters
	 * 
	 * @param x
	 * @param y
	 */
	public GunAlien(int x, int y) { // when the gun alien bounces it changes to cyan color and shoots a bullet
		super(x, y);
		Color c = Color.RED;
		setColor(c);
	}

}
