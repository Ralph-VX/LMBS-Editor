package kien.lmbseditor.window.motion;

import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import kien.lmbseditor.core.motion.SkillMotionCommandMove;
import net.miginfocom.swing.MigLayout;

public class MotionPropertyDialogMove extends MotionPropertyDialogBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFormattedTextField xField;
	private JFormattedTextField yField;
	private JFormattedTextField durationField;
	public int dx;
	public int dy;
	public int dur;
	
	
	/**
	 * Create the dialog.
	 */
	public MotionPropertyDialogMove() {
		setTitle("Move");
		setBounds(100, 100, 352, 133);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		DecimalFormat format = new DecimalFormat("##0");
		format.setMinimumFractionDigits(0);
		
		JLabel lblNewLabel = new JLabel("x direction");
		lblNewLabel.setToolTipText("Amount of movement in x direction");
		contentPanel.add(lblNewLabel, "cell 0 0,alignx trailing");
		
		xField = new JFormattedTextField(format);
		contentPanel.add(xField, "flowx,cell 1 0,growx");
		xField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("y direction");
		lblNewLabel_1.setToolTipText("Amount of movement in y direction");
		contentPanel.add(lblNewLabel_1, "cell 1 0");
		
		yField = new JFormattedTextField(format);
		contentPanel.add(yField, "cell 1 0");
		yField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("duration");
		lblNewLabel_2.setToolTipText("Amount of time this movement will use");
		contentPanel.add(lblNewLabel_2, "cell 0 1,alignx trailing");
		
		durationField = new JFormattedTextField(format);
		contentPanel.add(durationField, "cell 1 1,growx");

	}

	@Override
	public void setObject(SkillMotionCommandBase object) {
		SkillMotionCommandMove obj = (SkillMotionCommandMove)object;
		dx = obj.dx;
		dy = obj.dy;
		dur = obj.dur;
		xField.setValue(dx);
		yField.setValue(dy);
		durationField.setValue(dur);
		clearDirty();
	}

	@Override
	public void onOk() {
		dx = ((Number)xField.getValue()).intValue();
		dy = ((Number)yField.getValue()).intValue();
		dur = ((Number)durationField.getValue()).intValue();
		this.setDirty();
		this.onCancel();
	}

}
