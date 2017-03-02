package kien.lmbseditor.window;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import kien.lmbseditor.core.motion.SkillMotionCommandBase;

public class SkillMotionCommandCellRenderer extends JLabel implements ListCellRenderer<SkillMotionCommandBase> {
	
	private static final long serialVersionUID = 7170180652543758298L;
	public static final Color defaultSelectedBackground = new Color(184,207,229);
	public static final Color defaultSelectedBorder = new Color(99,130,191);

	public SkillMotionCommandCellRenderer() {
		super();
		new DefaultListCellRenderer();
		this.setOpaque(true);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends SkillMotionCommandBase> list,
			SkillMotionCommandBase value, int index, boolean isSelected, boolean cellHasFocus) {
		
		this.setText(value.toString());
		this.setForeground(value.commandColor());
		if (isSelected) {
			this.setBackground(defaultSelectedBackground);
			this.setBorder(BorderFactory.createLineBorder(defaultSelectedBorder));
		} else {
			this.setBackground(Color.white);
			this.setBorder(null);
		}
		return this;
	}

}
