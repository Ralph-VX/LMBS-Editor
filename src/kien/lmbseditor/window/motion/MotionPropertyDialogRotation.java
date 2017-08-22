package kien.lmbseditor.window.motion;

import java.text.DecimalFormat;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import kien.lmbseditor.core.motion.SkillMotionCommandRotation;
import net.miginfocom.swing.MigLayout;

public class MotionPropertyDialogRotation extends MotionPropertyDialogBase {
	private static final long serialVersionUID = 3292873801824570323L;
	
	protected JFormattedTextField rotationField;
	protected JFormattedTextField durationField;
	public int rotation;
	public int dir;
	public int dur;
	public int rounds;
	protected JCheckBox invertCheck;
	private JLabel lblNewLabel_1;
	protected JFormattedTextField roundField;
	
	
	/**
	 * Create the dialog.
	 */
	public MotionPropertyDialogRotation() {
		setTitle("Rotation");
		setBounds(100, 100, 352, 154);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		DecimalFormat format = new DecimalFormat("##0");
		format.setMinimumFractionDigits(0);
		
		JLabel lblNewLabel = new JLabel("rotation");
		lblNewLabel.setToolTipText("target angle of rotation");
		contentPanel.add(lblNewLabel, "cell 0 0,alignx trailing");
		
		rotationField = new JFormattedTextField(format);
		contentPanel.add(rotationField, "flowx,cell 1 0,growx");
		rotationField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("duration");
		lblNewLabel_2.setToolTipText("Amount of time this rotation will use");
		contentPanel.add(lblNewLabel_2, "cell 1 0,alignx trailing");
		
		durationField = new JFormattedTextField(format);
		durationField.setToolTipText("Amount of time this rotation will use");
		contentPanel.add(durationField, "cell 1 0,growx");
		
		lblNewLabel_1 = new JLabel("rounds");
		contentPanel.add(lblNewLabel_1, "cell 0 1,alignx left");
		
		roundField = new JFormattedTextField(format);
		roundField.setToolTipText("Amount of rounds this rotation will do.");
		contentPanel.add(roundField, "flowx,cell 1 1,growx");
		
		invertCheck = new JCheckBox("Invert Direction  ");
		invertCheck.setToolTipText("is this rotation is counter-clockwise or not");
		contentPanel.add(invertCheck, "cell 1 1");

	}

	@Override
	public void setObject(SkillMotionCommandBase object) {
		SkillMotionCommandRotation obj = (SkillMotionCommandRotation)object;
		rotationField.setValue(obj.rotation);
		durationField.setValue(obj.dur);
		invertCheck.setSelected(obj.dir > 0);
		roundField.setValue(obj.rounds);
		clearDirty();
	}

	@Override
	public void onOk() {
		rotation = ((Number)rotationField.getValue()).intValue();
		dur = ((Number)durationField.getValue()).intValue();
		dir = invertCheck.isSelected() ? 1 : 0;
		rounds = ((Number)roundField.getValue()).intValue();
		this.setDirty();
		this.onCancel();
	}

}
