package source.swing_components;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import source.game.Cell;

/**
 * Panel in order to displays the cells of the game
 * @author William
 * @date 08/02/2020
 * @version 2.0
 */
public class CellPanel extends JPanel {
	/***********
	 * Private parameters
	 ***********/	
	private static final long serialVersionUID = 1L;
	private Cell cell;
	
	public CellPanel(final Cell INPUT_CELL) {
		cell = INPUT_CELL;
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		updateRenderer();
		addMouseListener(new CellMouseListener());
	}
	
	/***********
	 * Private functions
	 ***********/	
	private void updateRenderer() {
		if(cell.isAlive()) {
			setBackground(Color.BLUE);
		}
		else {
			setBackground(Color.WHITE);
		}
	}

	/***********
	 * Private class
	 ***********/	
	private class CellMouseListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			cell.setAlive(!cell.isAlive());
			updateRenderer();
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
