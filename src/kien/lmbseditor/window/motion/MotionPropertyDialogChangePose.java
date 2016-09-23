package kien.lmbseditor.window.motion;

import javax.swing.JLabel;
import javax.swing.JTextField;

import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import kien.lmbseditor.core.motion.SkillMotionCommandChangePose;

public class MotionPropertyDialogChangePose extends MotionPropertyDialogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5407845095800179918L;
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

	@Override
	public void onOk() {
		this.result = this.textField.getText();
		this.setDirty();
		this.onCancel();
	}

}
