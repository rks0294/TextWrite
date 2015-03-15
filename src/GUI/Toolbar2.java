package GUI;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar2 extends JToolBar{
	private JToolBar toolbar;
	private JButton saveButton, saveAsButton, openButton;
	public Toolbar2(){

		setBorder(BorderFactory.createEtchedBorder());

		saveButton = new JButton();
		saveAsButton = new JButton();
		openButton = new JButton();
		
		setLayout(new BorderLayout());
		add(saveButton);
		add(saveAsButton);
		add(openButton);
	
	}
}
