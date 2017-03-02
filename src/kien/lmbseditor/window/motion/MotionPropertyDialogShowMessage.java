package kien.lmbseditor.window.motion;

import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import kien.lmbseditor.core.motion.SkillMotionCommandShowMessage;
import net.miginfocom.swing.MigLayout;

public class MotionPropertyDialogShowMessage extends MotionPropertyDialogBase {
	
	private static final long serialVersionUID = -504979773795691396L;
	public int channel;
	public String string;
	private JFormattedTextField channelField;
	private JTextField stringField;
	
	public MotionPropertyDialogShowMessage() {
		setTitle("Show Message");
		
		DecimalFormat format = new DecimalFormat("##0");
		format.setMinimumFractionDigits(0);
		format.setMaximumFractionDigits(0);
		
		setBounds(100, 100, 450, 170);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		JLabel lblNewLabel = new JLabel("channel");
		lblNewLabel.setToolTipText("class name of the projectile class");
		contentPanel.add(lblNewLabel, "cell 0 0,alignx trailing");
		
		channelField = new JFormattedTextField(format);
		contentPanel.add(channelField, "cell 1 0,growx");
		channelField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Text");
		lblNewLabel_1.setToolTipText("parameter of the projectile, comma separated");
		contentPanel.add(lblNewLabel_1, "cell 0 1,alignx trailing");
		
		stringField = new JTextField();
		contentPanel.add(stringField, "cell 1 1,growx");
		stringField.setColumns(10);

	}

	@Override
	public void setObject(SkillMotionCommandBase object) {
		SkillMotionCommandShowMessage obj = (SkillMotionCommandShowMessage)object;
		channelField.setValue(obj.channel);
		stringField.setText(obj.string);
	}

	@Override
	public void onOk() {
		channel = ((Number)channelField.getValue()).intValue();
		string = stringField.getText();
		this.setDirty();
		this.onCancel();
	}

}
