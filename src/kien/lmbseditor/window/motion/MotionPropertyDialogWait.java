package kien.lmbseditor.window.motion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import kien.lmbseditor.core.motion.SkillMotionCommandWait;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

public class MotionPropertyDialogWait extends MotionPropertyDialogBase {

	public JFormattedTextField textField;
	public int dur;
	public JLabel labelTitle;
	
	/**
	 * Create the dialog.
	 */
	public MotionPropertyDialogWait() {
		DecimalFormat format = new DecimalFormat("##0");
		format.setMinimumFractionDigits(0);
		format.setMaximumFractionDigits(0);
		setTitle("Wait");
		setBounds(100, 100, 259, 133);
		{
			labelTitle = new JLabel("Duration");
			labelTitle.setToolTipText("Amount of frames the process will pause");
			contentPanel.add(labelTitle);
		}
		{
			textField = new JFormattedTextField();
			textField.setToolTipText("Amount of frames the process will pause");
			contentPanel.add(textField);
			textField.setColumns(10);
		}
	}

	@Override
	public void setObject(SkillMotionCommandBase object) {
		SkillMotionCommandWait src = (SkillMotionCommandWait)object;
		this.dur = src.dur;
		this.textField.setValue(dur);
	}

	@Override
	public void onOk() {
		this.dur = ((Number)textField.getValue()).intValue();
		this.setDirty();
		this.onCancel();
	}

}
