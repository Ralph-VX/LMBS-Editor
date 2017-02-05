package kien.lmbseditor.window;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import kien.util.Util;

public class SkillMotionCommandCellRenderer extends JLabel implements ListCellRenderer<SkillMotionCommandBase> {
	
	private DefaultListCellRenderer generator;
	public static final Color defaultSelectedBackground = new Color(184,207,229);
	public static final Color defaultSelectedBorder = new Color(99,130,191);

	public SkillMotionCommandCellRenderer() {
		super();
		this.generator = new DefaultListCellRenderer();
		this.setOpaque(true);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends SkillMotionCommandBase> list,
			SkillMotionCommandBase value, int index, boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		
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
