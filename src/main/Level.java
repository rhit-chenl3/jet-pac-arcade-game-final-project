package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JComponent;

/**
 * Class: Level
 * 
 * @author A507 <br>
 *         Purpose: Will draw on the scanned platforms and MovingObjects as well
 *         as Hero.
 *
 */
public class Level extends JComponent {

	private Graphics2D g2;
	private Random rand = new Random();
	private int level;
	private Hero hero = new Hero();
	private Rocket rocket = new Rocket();
	private Scorecard scorecard = new Scorecard(4, 5);
	private Selectpage page;
	public boolean isSelected = false;
	public boolean collisionDown;
	public boolean collisionUp;
	public boolean collisionLeft;
	public boolean collisionRight;
	public boolean rocketpiecefall = false;
	public String VerticalDirection = "";
	public String HorizontalDirection = "";
	public String filename;

	public ArrayList<Alien> aliens = new ArrayList<Alien>();
	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public ArrayList<RocketPiece> rocketPieces = new ArrayList<RocketPiece>();

	public ArrayList<Platform> setup = new ArrayList<Platform>();
	private Dimension framesize;

	/**
	 * ensures: initializes level with given parameters
	 * 
	 * @param level
	 * @param frameSize
	 */
	public Level(int level, Dimension frameSize) {
		this.level = level;
		this.page = new Selectpage(3, frameSize);
		this.framesize = frameSize;
		filename = "Level" + (this.level) + ".txt";
	}

	/**
	 * Draws the game components
	 * 
	 * @param graphics
	 */
	@Override
	protected void paintComponent(Graphics graphics) {

		super.paintComponent(graphics);

		this.g2 = (Graphics2D) graphics;

		if (this.hero.isAlive()) {
			hero.drawOn(this.g2);
		} else {
			this.gameOver();
		}

		if (page.getIndex() == 0) {
			filename = "Level1.txt";
		} else if (page.getIndex() == 1) {
			filename = "Level2.txt";
		} else {
			filename = "Level3.txt";
		}

		if (rocket.reachNextLevel()) { // clear all things on current level and then initialize next level
			this.level++;
			filename = "Level" + (this.level) + ".txt";
			this.initializeLevel();
		}

		// draws the platforms from the list
		for (Platform p : this.setup) {
			p.drawOn(this.g2);
		}
		// draws the aliens from the list
		for (Alien a : this.aliens) {
			a.drawOn(this.g2);
		}

		// draws the bullets from the list
		for (Bullet b : this.bullets) {
			b.drawOn(this.g2);
		}

		// draws the rocket pieces from the list
		for (RocketPiece r : this.rocketPieces) {
			r.drawOn(this.g2);
		}

		scorecard.drawOn(this.g2);
		rocket.drawOn(this.g2);

		if (!isSelected) {
			page.drawOn(this.g2);
		}

	}

	/**
	 * ensures: sets current level to given parameter level
	 * 
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * ensures: adds aliens equal to given parameter count
	 * 
	 * @param count
	 */
	public void addAliens(int count) {
		for (int i = 0; i < count; i++) {
			this.aliens.add(new Alien(rand.nextInt(300) + 100, rand.nextInt(200)));
		}
	}

	/**
	 * ensures: adds gun aliens equal to given parameter count
	 * 
	 * @param count
	 */
	public void addGunAlien(int count) {
		for (int i = 0; i < count; i++) {
			this.aliens.add(new GunAlien(rand.nextInt(500), rand.nextInt(200)));
		}
	}

//	/**
//	 * ensures: adds bullets equal to given parameter count
//	 * 
//	 * @param x
//	 * @param y
//	 * @param color
//	 * @param count
//	 */
//	public void addBullet(int x, int y, Color color, int count) {
//		for (int i = 0; i < count; i++) {
//			this.bullets.add(new Bullet(x, y, color));
//		}
//	}

	/**
	 * ensures: adds rocketpieces equal to given parameter count
	 * 
	 * @param count
	 */
	public void addRocketPiece(int count) {
		for (int i = 0; i < count; i++) {
			this.rocketPieces.add(new RocketPiece(200 * i + rand.nextInt(90) + 10, 0));
		}
	}

	/**
	 * ensures: clears all game objects and resets hero and rocket parameters
	 */
	public void clearAll() {
		this.aliens.clear();
		this.bullets.clear();
		this.rocketPieces.clear();
		this.setup.clear();
		rocket.setY(400);
		hero.resetRocketPiece();
		hero.resetHeroLives();
		rocket.setnumber(0);
	}

	/**
	 * ensures: updates all game simulation objects
	 * 
	 */
	public void update() {

		ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
		ArrayList<Alien> aliensToRemove = new ArrayList<Alien>();
		ArrayList<RocketPiece> piecesToRemove = new ArrayList<RocketPiece>();

		for (Alien a : this.aliens) {
			a.update(this.getSize());

			if (a.overlapsWith(hero)) {
				this.hero.getHit();
				aliensToRemove.add(a);
			}
		}

		for (RocketPiece r : this.rocketPieces) {
			r.update(this.getSize());

			if (r.overlapsWith(hero)) {
				// if(this.hero.getNumOfRocketPieces() == 0)
				if (this.rocketpiecefall == true) {
					r.fall();
				}
				// this.hero.pickUpRocketPiece();
				// piecesToRemove.add(r);
				if (this.rocketpiecefall == false) {
					r.centerX = hero.centerX + r.radius / 2;
					r.centerY = hero.centerY + hero.radius;
				}
			}

			for (Platform p : this.setup) {
				if (r.insideBox(p.r)) {
					r.stopMoving();
				}
			}
		}

		for (Bullet b : this.bullets) {
			b.update(this.getSize());

			for (Alien a1 : this.aliens) {
				if (b.overlapsWith(a1) && b.getColor() == Color.BLACK) {
					aliensToRemove.add(a1);
					bulletsToRemove.add(b);
				}
			}
			for (Bullet b1 : this.bullets) {
				if (b1.overlapsWith(b) && b != b1) {
					bulletsToRemove.add(b1);
					bulletsToRemove.add(b);
				}
			}
			if (b.offScreen(framesize) || b.isHit) { // check if bullet off screen or hit and don't add to list if so
				bulletsToRemove.add(b);
			}
			if (b.overlapsWith(hero) && b.getColor() == Color.CYAN) {
				this.hero.getHit();
				bulletsToRemove.add(b);
			}
		}

		// check if alien is bumping into each other and call bounce on it if so
		for (Alien a1 : this.aliens) {
			for (Alien a2 : this.aliens) {
				if (a1 != a2 && a1.overlapsWith(a2)) {
					a1.bounce(a2);
					if (this.level != 1) {
						a1.shootMoreBullets(bullets);
					}
				}
			}
		}

		for (Alien a1 : this.aliens) {
			for (Platform p1 : this.setup) {
				if (a1.insideBox(p1.r)) {
					a1.bouncePlatform(p1);
					if (this.level != 1) {
						a1.shootMoreBullets(bullets);
					} else {
						a1.shootBullet(this.bullets);
					}
				}
			}
		}

		for (Platform p : this.setup) {
			if (hero.collisionDown(p)) {
				this.collisionDown = false;
				break;
			} else {
				this.collisionDown = true;
			}

			if (hero.collisionUp(p)) {
				this.collisionUp = false;
				break;
			} else {
				this.collisionUp = true;
			}

		}

		for (Platform p : this.setup) {
			if (hero.collisionLeft(p)) {
				this.collisionLeft = false;
				break;
			} else {
				this.collisionLeft = true;
			}
		}

		for (Platform p : this.setup) {
			if (hero.collisionRight(p)) {
				this.collisionRight = false;
				break;
			} else {
				this.collisionRight = true;
			}

		}

		for (RocketPiece r : rocketPieces) {

			if (r.overlapsWith(rocket)) {

				piecesToRemove.add(r);
				rocket.setnumber(4 - rocketPieces.size());
				this.hero.pickUpRocketPiece();
				// System.out.println(rocket.number);

			}

		}

		// check if hero bullets hit alien and call alien death on it if so

		// check if alien bullets hit hero and call hero hit on it if so

		// check if hero bumps into rocket parts and call collect on it if so

		// check if anything needs to be removed
		for (Alien alien : aliensToRemove) {
			this.aliens.remove(alien);
		}
		for (Bullet bullet : bulletsToRemove) {
			this.bullets.remove(bullet);
		}
		for (RocketPiece piece : piecesToRemove) {
			this.rocketPieces.remove(piece);
		}

		this.scorecard.update(hero.getLives(), hero.getNumOfRocketPieces());

		switch (this.VerticalDirection) {
		case "UP":
			if (this.collisionUp) {
				this.hero.updatePosition(0, -5);
			}
			break;
		case "DOWN":
			if (this.collisionDown) {
				this.hero.updatePosition(0, 5);
			}
			break;
		default:
			if (this.collisionDown) {
				this.hero.updatePosition(0, 3);
			}
			break;
		}

		switch (this.HorizontalDirection) {
		case "LEFT":
			if (this.collisionLeft) {
				this.hero.updatePosition(-5, 0);
			}
			break;
		case "RIGHT":
			if (this.collisionRight) {
				this.hero.updatePosition(5, 0);
			}
			break;
		default:
			this.hero.updatePosition(0, 0);
		}

		if (hero.getNumOfRocketPieces() == 3) {
			rocket.update(-6);
			hero.dead();
		}

	}

	/**
	 * ensures: Sets boolean isSelected to true
	 */
	public void setSelected() {
		this.isSelected = true;
	}

	/**
	 * ensures: Selectpage index is changed
	 * 
	 * @param x
	 */
	public void changeIndex(int x) {
		setup.clear();
		page.changeIndex(x);
	}

	/**
	 * ensures: hero shoots bullets
	 */
	public void heroShoot() {
		hero.shooting(bullets);
	}

	/**
	 * ensures: returns filename
	 * 
	 * @return
	 */
	public String getLevel() {
		return filename;
	}

	/**
	 * ensures: Level is cleared and reinitialized.
	 */
	public void initializeLevel() {
		this.clearAll(); // clear the level first
		hero.reset();

		// scanner to read text file and figure out platform setup for the level and
		// store into a list.
		Scanner scanner = null;
		try {

			int linecount = 0;
			scanner = new Scanner(new File(filename));
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == 'o') {
						Platform platform = new Platform(i * 100, (linecount + 1) * 100, Color.darkGray);
						setup.add(platform);
					}
				}
				linecount++;
			}
		} catch (FileNotFoundException e) {
			this.clearAll();
			this.isSelected = false;
//			System.out.println("File not found");
//			e.printStackTrace();
			return;
		}

		if (filename.equals("Level1.txt")) {
			this.addAliens(5);
			this.addGunAlien(3);
			this.addRocketPiece(3);
		} else if (filename.equals("Level2.txt")) {
			this.addAliens(3);
			this.addGunAlien(5);
			this.addRocketPiece(3);
		} else {
			this.addGunAlien(8);
			this.addRocketPiece(3);
		}
	}

	/**
	 * ensures: Game over text and overlay are displayed to guide player
	 */
	public void gameOver() {
		hero.dead();
		int score = scorecard.getScore();
		Font gameOverFont = new Font("SansSerif", Font.PLAIN, 40);
		Font stringFont = new Font("SansSerif", Font.PLAIN, 20);
		String gameOverScore = "GAMEOVER: " + score;
		String help = "press 'x' to return to main menu";

		this.g2.setFont(gameOverFont);
		this.g2.drawString(gameOverScore, (int) framesize.getWidth() / 2 - 150, (int) framesize.getHeight() / 2);
		this.g2.setFont(stringFont);
		this.g2.drawString(help, (int) framesize.getWidth() / 2 - 140, (int) framesize.getHeight() / 2 + 60);
	}

}
