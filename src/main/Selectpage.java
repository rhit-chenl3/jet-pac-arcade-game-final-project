package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Class: Selectpage
 * 
 * @author A507 <br>
 *         Purpose: Main menu page of game allowing level selection and
 *         instructions
 *
 */
public class Selectpage {

	private int high = 10;
	private int x = 0;
	private int y = 0;
	private int length = 50;
	private int number = 0;
	private int index = 0;
	private Color color = new Color(255, 255, 255);
	private Color boxColor = new Color(0, 0, 0);
	private Color selectedColor = new Color(0, 173, 181);

	/**
	 * ensures: initializes object based on given parameters
	 * 
	 * @param number
	 * @param frameSize
	 */
	public Selectpage(int number, Dimension frameSize) {

		this.number = number;
		this.high = (int) frameSize.getHeight() / 11;
		this.length = (int) frameSize.getWidth() / 3;
		this.x = (int) frameSize.getWidth() / 2 - length / 2;
		this.y = (int) frameSize.getHeight() / (number * 2) + 50;

	}

	/**
	 * ensures: draw object onto graphics
	 * 
	 * @param g
	 */
	public void drawOn(Graphics2D g) {

		ArrayList<Rectangle> boxs = new ArrayList<Rectangle>();
		Rectangle box = new Rectangle(0, 0, 10000, 10000);

		g.setColor(color);
		g.fill(box);
		g.setColor(boxColor);

		Font menuFont = new Font("SansSerif", Font.PLAIN, 40);
		Font levelFont = new Font("SansSerif", Font.PLAIN, 20);
		Font helpFont = new Font("SansSerif", Font.PLAIN, 16);
		String menu = "A507 ARCADE GAME";
		String help = "'d' and 'u' to navigate and 'enter' to select";
		String help2 = "gameplay: arrow keys to move, space to shoot and 'f' to drop objects";
		g.setFont(menuFont);
		g.drawString(menu, 50, 50);
		g.setFont(helpFont);
		g.drawString(help, 100, 450);
		g.drawString(help2, 10, 500);

		for (int i = 0; i < number; i++) {
			String level = "Level " + (i + 1);
			g.setFont(levelFont);
			g.drawString(level, x + (length / 3), y + i * 100 - 5);
			boxs.add(new Rectangle(x, y + i * 100, length, high));
		}

		for (int i = 0; i < boxs.size(); i++) {
			g.setColor(boxColor);
			if (this.index == i) {
				g.setColor(this.selectedColor);
			}
			g.fill(boxs.get(i));
		}

	}

	/**
	 * ensures: changes current index
	 * 
	 * @param x
	 */
	public void changeIndex(int x) {
		this.index = this.index + x;
		if (this.index < 0) {
			this.index = this.number - 1;
		}

		if (this.index >= this.number) {
			this.index = 0;
		}
	}

	/**
	 * ensures: returns index
	 * 
	 * @return index
	 */
	public int getIndex() {
		return this.index;
	}
}
