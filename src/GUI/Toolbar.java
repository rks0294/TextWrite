package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar implements ActionListener{
	private JToolBar toolbar;
	private TextPanel textPanel;
	private JButton normalButton, saveButton, saveAsButton, openButton, boldButton, italicButton, underlineButton;
	private ToolbarListener listener;
	public Toolbar(){
		setFloatable(false);
		setBorder(BorderFactory.createEtchedBorder());

		openButton = new JButton();
		openButton.setIcon(Utils.createIcon("/images/open.gif"));
		openButton.setToolTipText("Open");
		
		saveButton = new JButton();
		saveButton.setIcon(Utils.createIcon("/images/save.gif"));
		saveButton.setToolTipText("Save");
		
		saveAsButton = new JButton();
		saveAsButton.setIcon(Utils.createIcon("/images/saveAs.gif"));
		saveAsButton.setToolTipText("Save As..");
		
		normalButton = new JButton();
		normalButton.setIcon(Utils.createIcon("/images/normal.gif"));
		normalButton.setToolTipText("Plain");
		
		boldButton = new JButton();
		boldButton.setIcon(Utils.createIcon("/images/bold.gif"));
		boldButton.setToolTipText("Bold");

		italicButton = new JButton();
		italicButton.setIcon(Utils.createIcon("/images/italic.gif"));
		italicButton.setToolTipText("Italic");

		underlineButton = new JButton();
		underlineButton.setIcon(Utils.createIcon("/images/underline.gif"));
		underlineButton.setToolTipText("Underline");
		
		openButton.addActionListener(this);
		saveButton.addActionListener(this);
		saveAsButton.addActionListener(this);
		normalButton.addActionListener(this);
		boldButton.addActionListener(this);
		italicButton.addActionListener(this);
		underlineButton.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(openButton);
		add(saveButton);
		add(saveAsButton);
		addSeparator(new Dimension(10, 10));
		add(normalButton);
		add(boldButton);
		add(italicButton);
		add(underlineButton);
	
	}
	public void setToolbarListener(ToolbarListener listener){
		this.listener = listener;
	}
	public void actionPerformed(ActionEvent event) {
		if((JButton)event.getSource() == openButton){
			if (listener != null) {
				listener.openEventOccured();
				System.out.println("save");
			}
		}else if(event.getSource() == saveButton){
			if (listener != null) {
				listener.saveEventOccured();
				System.out.println("save");
			}
		}else if(event.getSource() == saveAsButton){
			if (listener != null) {
				listener.saveAsEventOccured();
				System.out.println("save");
			}
		}else if(event.getSource() == boldButton){
			if (listener != null) {
				listener.boldEventOccured();
			}
		}else if(event.getSource() == italicButton){
			if (listener != null) {
				listener.italicEventOccured();
			}
		}else if(event.getSource() == underlineButton){
			if (listener != null) {
				listener.underlineEventOccured();
			}
		}else if(event.getSource() == normalButton){
			if (listener != null) {
				listener.normalEventOccured();
			}
		}
	}
	public void setTextPanel(TextPanel textPanel){
		this.textPanel = textPanel;
	}
}
