package GUI;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

public class UndoUtility {
	private TextPanel textPanel;
	UndoManager undomy = new UndoManager();

	public UndoUtility(TextPanel textPanel) {
		this.textPanel = textPanel;
		Document docx = textPanel.getDocument();
		docx.addUndoableEditListener(new UndoableEditListener() {

			public void undoableEditHappened(UndoableEditEvent event) {
				undomy.addEdit(event.getEdit());
			}
		});
	}

	public boolean canUndoMy() {
		if (undomy.canUndo()) {
			return true;
		} else {
			return false;
		}
	}

	public void undoText() {
		undomy.undo();
	}

	public boolean canRedoMy() {
		if (undomy.canUndo()) {
			return true;
		} else {
			return false;
		}
	}

	public void redoText() {
		undomy.redo();
	}
}
