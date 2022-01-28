package main;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 * Class : Scorecard
 * 
 * @author A507 <br>
 *         Purpose:Show score at the top of window
 *
 */
public class Scorecard {

	private int heroLives;
	private int score = 0;
	private int packet;

	/**
	 * ensures: initializes a scorecard object
	 * 
	 * @param lives
	 * @param packet
	 */
	public Scorecard(int lives, int packet) {

		this.heroLives = lives;
		this.packet = packet;

	}

	/**
	 * ensures: draw object onto graphics
	 * 
	 * @param g
	 */
	public void drawOn(Graphics2D g) {

		ArrayList<Polygon> polygon = new ArrayList<Polygon>();
		ArrayList<Polygon> packet = new ArrayList<Polygon>();
		Font stringFont = new Font("SansSerif", Font.PLAIN, 25);
		String sc = "" + score;

		for (int i = 0; i < heroLives; i++) {

			int[] x = { 10 + i * 30, 30 + i * 30, 40 + i * 30, 20 + i * 30 };
			int[] y = { 15, 15, 25, 25 };

			polygon.add(new Polygon(x, y, 4));

		}

		for (int i = 0; i < this.packet; i++) {
			int[] packetX = { 460, 480, 480, 460 };
			int[] packetY = { 20 + i * 30, 20 + i * 30, 40 + i * 30, 40 + i * 30 };
			packet.add(new Polygon(packetX, packetY, 4));
		}

		for (Polygon p : polygon) {
			g.fillPolygon(p);
		}

		for (Polygon p : packet) {
			g.fillPolygon(p);
		}

		g.setFont(stringFont);
		g.drawString(sc, 220, 20);
	}

	/**
	 * ensures : update data and graph of scorecard
	 * 
	 * @param lives
	 * @param packet
	 */
	public void update(int lives, int packet) {
		this.heroLives = lives;
		this.packet = packet;
		this.score = packet * 50 + lives * 100;
	}

	/**
	 * ensures:return the data of score
	 * 
	 * @return
	 */
	public int getScore() {
		return this.score;
	}

}
