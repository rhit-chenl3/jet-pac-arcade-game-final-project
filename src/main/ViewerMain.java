package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Class: ViewerMain
 * 
 * @author A507 <br>
 *         Purpose: Main class for running and viewing the game.
 *
 */

public class ViewerMain extends JComponent {
	public static final Dimension GAME_VIEWER_SIZE = new Dimension(500, 560);
	private static final int DELAY = 50;

	/**
	 * ensures: constructs the first level
	 */
	public ViewerMain() {
		this.constructLevel(1);
	}

	/**
	 * ensures: creates a new ViewerMain to run
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ViewerMain();
	}

	/**
	 * Constructs and displays the JFrame which displays ViewerMain via the Level
	 * class.
	 * 
	 * 
	 * @param level
	 */

	public void constructLevel(int level) {

		JFrame frame = new JFrame();
		frame.setSize(GAME_VIEWER_SIZE);
		Level L = new Level(level, frame.getSize());
		KeyboardListener k = new KeyboardListener(L);

		L.setFocusable(true);
		L.addKeyListener(k);
		frame.setTitle("ArcadeGame!");
		frame.add(L);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Timer to update and repaint the viewer;
		Timer t = new Timer(DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				L.update();
				L.repaint();

			}
		});

		// Starts the simulator
		t.start();
	}

}