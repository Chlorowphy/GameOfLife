package source;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Cell object for the Board
 * A cell is either dead or alive
 * @author William Pouts
 * @date 01/03/2020
 * @version 1.0
 * 
 */
public class Cell extends JPanel {
	/***********
	 * Private parameters
	 ***********/
	private static final long serialVersionUID = 1L;
	private boolean isAlive = false;
	
	/**
	 * Constructor of Cell
	 */
	public Cell() {
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		updateRenderer();
		addMouseListener(new CellMouseListener());
	}
	
	/***********
	 * Public functions
	 ***********/
	public void setAlive(boolean alive) {
		isAlive = alive;
		updateRenderer();
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	/***********
	 * Private functions
	 ***********/
	private void updateRenderer() {
		if(isAlive) {
			setBackground(Color.BLUE);
		}
		else {
			setBackground(Color.WHITE);
		}
	}
	
	/***********
	 * Class functions
	 ***********/
	private class CellMouseListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent event) {
			setAlive(!isAlive);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		
	}
}
