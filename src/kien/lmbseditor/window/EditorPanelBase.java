package kien.lmbseditor.window;

import javax.swing.JPanel;

public abstract class EditorPanelBase extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Refresh the list when Preferences had changed.
	public abstract void refresh();
	
	// Update Painting.
	public abstract void update();
	
	// Commit Edit before saving.
	public abstract void updateEdit();
	
}
