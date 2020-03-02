package source;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Main class for the Game of Life.
 * Create the first window asking the number of rows and columns, then create the Board with
 * these values.
 * @author William Pouts
 * @date 01/03/2020
 * @version 1.0
 *
 */
public class StartGameOfLife extends JFrame {
	/***********
	 * Private parameters
	 ***********/
	private static final long serialVersionUID = 1L;
	
	private JTextField numRowTxtField;
	private JTextField numColRxtField;

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		StartGameOfLife game = new StartGameOfLife();
		game.setVisible(true);
	}

	/**
	 * Constructor of Game
	 */
	public StartGameOfLife() {
		//Create the Frame
		final String TITLE = "Starting Menu";
		setTitle(TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		final int DEFAULT_HIGH = 150;
		final int DEFAULT_WIDTH = DEFAULT_HIGH * 3;
		final Dimension DEFAULT_DIMENSION = new Dimension(DEFAULT_WIDTH, DEFAULT_HIGH);
		setSize(DEFAULT_DIMENSION);
		setMinimumSize(DEFAULT_DIMENSION);
		setResizable(false);
		
		//Set the layout
		final GridBagLayout LAYOUT = new GridBagLayout();
		setLayout(LAYOUT);
		
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		
		//Add the two texts
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		layoutConstraints.gridwidth = 1;
		layoutConstraints.gridheight = 1;
		layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
		layoutConstraints.ipadx = 1;
		layoutConstraints.ipady = 1;
		layoutConstraints.insets = new Insets(5, 5, 5, 5);
		layoutConstraints.anchor = GridBagConstraints.CENTER;
		layoutConstraints.weightx = 0;
		layoutConstraints.weighty = 0;
		final Font FONT = new Font("Consolas", Font.BOLD, 15);
		final String ROW_LABEL_TXT = "Number of rows ?";
		JLabel rowLabel = new JLabel(ROW_LABEL_TXT);
		rowLabel.setFont(FONT);
		getContentPane().add(rowLabel, layoutConstraints);
		
		layoutConstraints.gridy = 1;
		final String COLUMN_LABEL_TXT = "Number of columns ?";
		JLabel columnLabel = new JLabel(COLUMN_LABEL_TXT);
		columnLabel.setFont(FONT);
		getContentPane().add(columnLabel, layoutConstraints);
		
		//Add the two text fields
		layoutConstraints.gridx = 1;
		layoutConstraints.gridy = 0;
		layoutConstraints.insets = new Insets(5, 20, 5, 20);
		layoutConstraints.weightx = 1;
		numRowTxtField = new JTextField();
		getContentPane().add(numRowTxtField, layoutConstraints);
		
		layoutConstraints.gridy = 1;
		layoutConstraints.weightx = 1;
		numColRxtField = new JTextField();
		getContentPane().add(numColRxtField, layoutConstraints);
		
		//Add the apply button and its panel
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 2;
		layoutConstraints.gridwidth = 2;
		layoutConstraints.insets = new Insets(2, 2, 2, 2);
		layoutConstraints.weightx = 0;
		final JPanel BUTTON_PANEL = new JPanel();
		getContentPane().add(BUTTON_PANEL, layoutConstraints);		
		
		JButton applyButton = new JButton();
		final String BUTTON_TXT = "Apply";
		applyButton.setText(BUTTON_TXT);
		applyButton.addActionListener(new ApplyButtonListener());
		BUTTON_PANEL.add(applyButton);
		pack();
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/***********
	 * Private classes
	 ***********/
	private class ApplyButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				final int NUM_ROWS = Integer.parseInt(numRowTxtField.getText());
				final int NUM_COLUMNS = Integer.parseInt(numColRxtField.getText());
				new BoardFrame(NUM_ROWS, NUM_COLUMNS);
				dispose();
			}
			catch (NumberFormatException error) {
				System.out.println("One or more of the input parameters are not numbers (" + numRowTxtField.getText() + ", " + numColRxtField.getText() + "), try again with correct values");
			}
			catch (IllegalArgumentException error) {
				System.out.println("One or more of the input parameters are not strict positive values (" + numRowTxtField.getText() + ", " + numColRxtField.getText() + "), try again with correct only > 0 values");
			}
		}
	}
}
