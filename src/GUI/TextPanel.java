package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.Document;

public class TextPanel extends JPanel {
	private JTextArea textArea;
	private boolean x = true;

	public TextPanel() {
		textArea = new JTextArea();
		Border outerBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3);
		Border innerBorder = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.WHITE);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		setLayout(new BorderLayout());
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}

	public String getText() {
		return textArea.getText();
	}

	public void setText(String string) {
		textArea.setText(string);
	}

	public void appendText(String string) {
		textArea.append(string);
	}

	public Document getDocument() {
		return textArea.getDocument();
	}

	public void selectAllText() {
		textArea.selectAll();
	}

	public void cutText() {
		textArea.cut();
	}

	public void copyText() {
		textArea.copy();
	}

	public void pasteText() {
		textArea.paste();
	}

	public void deleteText() {
		textArea.replaceSelection("");
		;
	}

	public void timeAndDate() {
		Date date = new Date();
		int pos = textArea.getCaretPosition();
		textArea.insert(date.toString(), pos);
	}

	public void setFontType(Font font) {
		textArea.setFont(font);
	}

	public void getNormalFont() {
		Font font = textArea.getFont();
		Font newFont = null;
		newFont = font.deriveFont(Font.PLAIN);
		textArea.setFont(newFont);
	}

	public void getBoldFont() {
		Font font = textArea.getFont();
		int style = font.getStyle();
		Font newFont = null;
		if (style == Font.PLAIN) {
			newFont = font.deriveFont(Font.BOLD);
		} else if (style == Font.ITALIC) {
			newFont = font.deriveFont(Font.ITALIC | Font.BOLD);
		}
		if (newFont != null) {
			textArea.setFont(newFont);
		}
	}

	public void getItalicFont() {
		Font font = textArea.getFont();
		int style = font.getStyle();
		Font newFont = null;
		if (style == Font.PLAIN) {
			newFont = font.deriveFont(Font.ITALIC);
		} else if (style == Font.BOLD) {
			newFont = font.deriveFont(Font.BOLD | Font.ITALIC);
		}
		if (newFont != null) {
			textArea.setFont(newFont);
		}
	}

	public void getUnderlineFont() {
		Font fd = textArea.getFont();
		final Map<TextAttribute, Object> fontAttribs = new HashMap<TextAttribute, Object>();
		if (fd.isItalic()) {
			fontAttribs.put(TextAttribute.POSTURE,
					TextAttribute.POSTURE_OBLIQUE);
		}
		if (fd.isBold()) {
			fontAttribs.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		}

		if (x) {
			fontAttribs
					.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			x = false;
		} else if (!x) {
			fontAttribs.put(TextAttribute.UNDERLINE, -1);
			x = true;
		}

		textArea.setFont(getFont().deriveFont(fontAttribs));
	}
}
