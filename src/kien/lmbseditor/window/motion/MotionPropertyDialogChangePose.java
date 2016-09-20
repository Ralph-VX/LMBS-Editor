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
	private JTextField textField;
	
	public MotionPropertyDialogChangePose() {
		setTitle("Change Pose");
		setBounds(100, 100, 208, 98);
		{
			JLabel lblPoseName = new JLabel("Pose Name");
			contentPanel.add(lblPoseName);
		}
		{
			textField = new JTextField();
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
