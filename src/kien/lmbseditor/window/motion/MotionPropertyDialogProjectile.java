package kien.lmbseditor.window.motion;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;

import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import kien.lmbseditor.core.motion.SkillMotionCommandProjectile;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MotionPropertyDialogProjectile extends MotionPropertyDialogBase {

	public String classname;
	public String parameters;
	private JTextField classnameField;
	private JTextField parametersField;
	
	public MotionPropertyDialogProjectile() {
		setBounds(100, 100, 450, 170);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		JLabel lblNewLabel = new JLabel("classname");
		lblNewLabel.setToolTipText("class name of the projectile class");
		contentPanel.add(lblNewLabel, "cell 0 0,alignx trailing");
		
		classnameField = new JTextField();
		contentPanel.add(classnameField, "cell 1 0,growx");
		classnameField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("parameters");
		lblNewLabel_1.setToolTipText("parameter of the projectile, comma separated");
		contentPanel.add(lblNewLabel_1, "cell 0 1,alignx trailing");
		
		parametersField = new JTextField();
		contentPanel.add(parametersField, "cell 1 1,growx");
		parametersField.setColumns(10);

	}

	@Override
	public void setObject(SkillMotionCommandBase object) {
		SkillMotionCommandProjectile obj = (SkillMotionCommandProjectile)object;
		classnameField.setText(obj.classname);
		parametersField.setText(obj.parameters);
	}

	@Override
	public void onOk() {
		classname = classnameField.getText();
		parameters = parametersField.getText();
		this.setDirty();
		this.onCancel();
	}

}
