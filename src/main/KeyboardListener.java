package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class KeyboardListener
 * 
 * @author A507 <br>
 *         purpose: decide action based on input from keyboard
 */
public class KeyboardListener implements KeyListener {
	private Level L;
	private int x = 0;
	private int y = 0;

	public KeyboardListener(Level L) {
		this.L = L;
	}

	/**
	 * ensures: initializes key
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * ensures: when key pressed, decide a kind of movement
	 * 
	 * @param e
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (KeyEvent.VK_DOWN == e.getKeyCode() && L.collisionDown) {

			L.VerticalDirection = "DOWN";

		} else if (KeyEvent.VK_UP == e.getKeyCode() && L.collisionUp) {

			L.VerticalDirection = "UP";

		} else if (KeyEvent.VK_LEFT == e.getKeyCode() && L.collisionLeft) {

			L.HorizontalDirection = "LEFT";

		} else if (KeyEvent.VK_RIGHT == e.getKeyCode() && L.collisionRight) {

			L.HorizontalDirection = "RIGHT";

		} else if (KeyEvent.VK_ENTER == e.getKeyCode()) {
			L.setSelected();

		} else if (KeyEvent.VK_U == e.getKeyCode()) {
			if(!L.isSelected)
				L.changeIndex(-1);

		} else if (KeyEvent.VK_D == e.getKeyCode()) {
			if(!L.isSelected)
				L.changeIndex(1);

		} else if (KeyEvent.VK_SPACE == e.getKeyCode()) {
			L.heroShoot();

		} else if (KeyEvent.VK_F == e.getKeyCode()) {
			L.rocketpiecefall = true;
		}
		// System.out.println();

		// System.out.println(e.getKeyCode());

	}

	/**
	 * ensures: when key released decide to stop some movement
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (KeyEvent.VK_DOWN == e.getKeyCode()) {

		} else if (KeyEvent.VK_UP == e.getKeyCode()) {
			L.VerticalDirection = "";

		} else if (KeyEvent.VK_LEFT == e.getKeyCode()) {
			L.HorizontalDirection = "";

		} else if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
			L.HorizontalDirection = "";

		} else if (KeyEvent.VK_F == e.getKeyCode()) {
			L.rocketpiecefall = false;
		} else if (KeyEvent.VK_ENTER == e.getKeyCode()) {
			L.initializeLevel();
		} else if (KeyEvent.VK_X == e.getKeyCode()) {
			L.isSelected = false;
		}

	}

}
