package kien.lmbseditor.window.motion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import kien.lmbseditor.core.motion.SkillMotionCommandChangePose;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MotionPropertyDialogChangePose extends MotionPropertyDialogBase {

	/**
	 * Create the dialog.
	 */
	
	private boolean dirty;
	public String result;
	public JTextField textField;
	public JLabel labelTitle;
	
	public MotionPropertyDialogChangePose() {
		setTitle("Change Pose");
		setBounds(100, 100, 208, 98);
		{
			labelTitle = new JLabel("Pose Name");
			labelTitle.setToolTipText("Name of the new pose");
			contentPanel.add(labelTitle);
		}
		{
			textField = new JTextField();
			textField.setToolTipText("Name of the new pose");
			contentPanel.add(textField);
			textField.setColumns(10);
		}
	}

	@Override
	public void setObject(SkillMotionCommandBase object) {
		SkillMotionCommandChangePose o = (SkillMotionCommandChangePose)object;
		this.textField.setText(o.name);
		this.result = o.name;
	}

	public void onOk() {
		this.result = this.textField.getText();
		this.setDirty();
		this.onCancel();
	}

}
