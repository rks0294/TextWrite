package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.undo.UndoManager;

public class mainFrame extends JFrame {
	private TextPanel textPanel;
	private Toolbar toolbar;
	private JFileChooser fileChooser;
	private PrinterJob job;
	private UndoUtility undoUtility;
	private UndoManager undoManager;
	private JMenuItem openMenuItem;

	private static int flag;

	public mainFrame() {
		super("TextWriteV2.0");
		setIconImage(new ImageIcon(System.class.getResource("/images/tw3.png"))
				.getImage());
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

		flag = 0;

		textPanel = new TextPanel();
		toolbar = new Toolbar();
		toolbar.setTextPanel(textPanel);
		fileChooser = new JFileChooser();
		undoUtility = new UndoUtility(textPanel);
		toolbar.setToolbarListener(new ToolbarListener() {

			public void saveEventOccured() {
				System.out.println("Inside save function");
				if (flag == 1) {
					File file = fileChooser.getSelectedFile();
					writer(file);
					JOptionPane.showMessageDialog(mainFrame.this,
							"File succesfully saved", "Confirm Exit",
							JOptionPane.OK_OPTION,
							Utils.createIcon("/images/icon.gif"));

				} else {
					System.out.println(flag);
					if (fileChooser.showSaveDialog(mainFrame.this) == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						flag = 1;
						writer(file);
					}
				}
			}

			public void openEventOccured() {
				if (fileChooser.showOpenDialog(mainFrame.this) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					flag = 1;
					reader(file);
				}
			}

			public void saveAsEventOccured() {
				if (fileChooser.showSaveDialog(mainFrame.this) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					if (file.exists()) {

						int action = JOptionPane.showConfirmDialog(
								mainFrame.this, "Replace existing file?");
						if (action == JOptionPane.YES_OPTION) {
							writer(file);
						}
					} else {
						writer(file);
					}

				}
			}

			public void boldEventOccured() {
				textPanel.getBoldFont();
			}

			public void italicEventOccured() {
				textPanel.getItalicFont();
			}

			public void underlineEventOccured() {
				textPanel.getUnderlineFont();
			}

			public void normalEventOccured() {
				textPanel.getNormalFont();

			}
		});
		setJMenuBar(createMenuBar());

		setLayout(new BorderLayout());

		add(textPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.PAGE_START);

		setVisible(true);
		setSize(700, 600);
		setMinimumSize(new Dimension(600, 450));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public JMenuBar createMenuBar() {

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		openMenuItem = new JMenuItem("Open");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		JMenuItem saveAsMenuItem = new JMenuItem("SaveAs..");
		JMenuItem printMenuItem = new JMenuItem("Print");
		JMenuItem exitMenuItem = new JMenuItem("Exit");

		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(printMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);

		fileMenu.setMnemonic(KeyEvent.VK_F);
		newMenuItem.setMnemonic(KeyEvent.VK_N);
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		openMenuItem.setMnemonic(KeyEvent.VK_O);
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		saveMenuItem.setMnemonic(KeyEvent.VK_S);
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		printMenuItem.setMnemonic(KeyEvent.VK_P);
		printMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));
		exitMenuItem.setMnemonic(KeyEvent.VK_X);
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.ALT_MASK));

		newMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);

				new mainFrame();

			}
		});
		openMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (fileChooser.showOpenDialog(mainFrame.this) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					flag = 1;
					reader(file);
				}
			}
		});

		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				System.out.println("Inside save function");
				if (flag == 1) {
					File file = fileChooser.getSelectedFile();
					writer(file);
					JOptionPane.showMessageDialog(mainFrame.this,
							"File succesfully saved", "Confirm Exit",
							JOptionPane.OK_OPTION,
							Utils.createIcon("/images/icon.gif"));

				} else {
					if (fileChooser.showSaveDialog(mainFrame.this) == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						flag = 1;
						writer(file);
					}
				}
			}
		});
		saveAsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (fileChooser.showSaveDialog(mainFrame.this) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					if (file.exists()) {
						int action = JOptionPane.showConfirmDialog(
								mainFrame.this, "Replace existing file?");
						if (action == JOptionPane.YES_OPTION) {
							writer(file);
						}
					} else {
						writer(file);
					}

				}

			}
		});
		printMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				job = PrinterJob.getPrinterJob();
				if (job.printDialog()) {
					try {
						job.print();
					} catch (PrinterException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int action = JOptionPane.showConfirmDialog(mainFrame.this,
						"Do you really want to exit the application?",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});

		JMenu editMenu = new JMenu("Edit");
		final JMenuItem undoMenuItem = new JMenuItem("Undo");
		final JMenuItem redoMenuItem = new JMenuItem("Redo");
		JMenuItem selectAllMenuItem = new JMenuItem("Select All");
		JMenuItem cutMenuItem = new JMenuItem("Cut");
		JMenuItem copyMenuItem = new JMenuItem("Copy");
		JMenuItem pasteMenuItem = new JMenuItem("Paste..");
		JMenuItem deleteMenuItem = new JMenuItem("Delete");
		JMenuItem timeAndDateMenuItem = new JMenuItem("Time/Date");

		editMenu.add(undoMenuItem);
		
		editMenu.add(redoMenuItem);
		editMenu.addSeparator();
		editMenu.add(selectAllMenuItem);
		editMenu.add(cutMenuItem);
		editMenu.add(copyMenuItem);
		editMenu.add(pasteMenuItem);
		editMenu.add(deleteMenuItem);
		editMenu.addSeparator();
		editMenu.add(timeAndDateMenuItem);

		editMenu.setMnemonic(KeyEvent.VK_E);
		undoMenuItem.setMnemonic(KeyEvent.VK_Z);
		undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				ActionEvent.CTRL_MASK));
		redoMenuItem.setMnemonic(KeyEvent.VK_Y);
		redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				ActionEvent.CTRL_MASK));
		selectAllMenuItem.setMnemonic(KeyEvent.VK_A);
		selectAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.CTRL_MASK));
		cutMenuItem.setMnemonic(KeyEvent.VK_X);
		cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		copyMenuItem.setMnemonic(KeyEvent.VK_C);
		copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		pasteMenuItem.setMnemonic(KeyEvent.VK_V);
		pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.CTRL_MASK));
		deleteMenuItem.setMnemonic(KeyEvent.VK_L);
		deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_DELETE, 0));
		timeAndDateMenuItem.setMnemonic(KeyEvent.VK_D);
		timeAndDateMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_D, ActionEvent.CTRL_MASK));

		undoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (undoUtility.canUndoMy()) {
					undoUtility.undoText();
				}
				undoMenuItem.setEnabled(undoUtility.canUndoMy());
				redoMenuItem.setEnabled(undoUtility.canRedoMy());
			}
		});
		redoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (undoUtility.canRedoMy()) {
					undoUtility.redoText();
				}
				undoMenuItem.setEnabled(undoUtility.canUndoMy());
				redoMenuItem.setEnabled(undoUtility.canRedoMy());
			}
		});
		selectAllMenuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				textPanel.selectAllText();
			}
		});
		cutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPanel.cutText();
			}
		});
		copyMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPanel.copyText();
			}
		});
		pasteMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPanel.pasteText();
			}
		});
		deleteMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPanel.deleteText();
			}
		});
		timeAndDateMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPanel.timeAndDate();
			}
		});
		JMenu formatMenu = new JMenu("Format");
		JMenuItem fontMenuItem = new JMenuItem("Font");

		fontMenuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser2 fontChooser = new JFileChooser2();
				int result = fontChooser.showDialog(mainFrame.this);
				if (result == JOptionPane.OK_OPTION) {
					Font font = fontChooser.getSelectedFont();
					textPanel.setFontType(font);
					System.out.println("Selected Font : " + font);
				}

			}
		});
		formatMenu.add(fontMenuItem);

		formatMenu.setMnemonic(KeyEvent.VK_R);
		fontMenuItem.setMnemonic(KeyEvent.VK_T);
		fontMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
				ActionEvent.CTRL_MASK));

		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutMenuItem = new JMenuItem("About TextWrite");
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane
						.showMessageDialog(
								mainFrame.this,
								"TextWrite is a simple yet more featured Graphical User Interface text editor based "
										+ "on Java Swing Framework. \n Version : 2.0 \n"
										+ "All rights reserved.",
								"Confirm Exit", JOptionPane.OK_OPTION,
								Utils.createIcon("/images/icon.gif"));
			}
		});
		helpMenu.add(aboutMenuItem);
		helpMenu.setMnemonic(KeyEvent.VK_H);
		aboutMenuItem.setMnemonic(KeyEvent.VK_A);
		aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.CTRL_MASK));

		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(formatMenu);
		menuBar.add(helpMenu);

		return menuBar;
	}

	public void writer(File file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(textPanel.getText());
			writer.close();
			setTitle("WriteText - " + file.getName());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void reader(File file) {
		try {
			BufferedReader input = new BufferedReader(new FileReader(file));
			textPanel.setText("");

			String line = input.readLine();
			while (line != null) {
				textPanel.appendText(line + "\n");
				line = input.readLine();
			}

			System.out.println("flag set to true");
			setTitle("WriteText - " + file.getName());
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}
