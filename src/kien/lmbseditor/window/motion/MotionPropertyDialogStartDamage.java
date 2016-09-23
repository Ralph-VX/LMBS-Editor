package kien.lmbseditor.window.motion;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.JDialog;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import kien.lmbseditor.core.motion.SkillMotionCommandStartDamage;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;

public class MotionPropertyDialogStartDamage extends MotionPropertyDialogBase {
	private static final long serialVersionUID = 1L;
	private JFormattedTextField rectXTextField;
	private JFormattedTextField rectYTextField;
	private JFormattedTextField rectWTextField;
	private JFormattedTextField rectHTextField;
	private JFormattedTextField damageTextField;
	private JFormattedTextField knockXTextField;
	private JFormattedTextField knockYTextField;
	private JCheckBox invertKnock;
	public double rx;
	public double ry;
	public double rw;
	public double rh;
	public double kx;
	public double ky;
	public int ki;
	public double dm;

	/**
	 * Create the dialog.
	 */
	public MotionPropertyDialogStartDamage() {
		setTitle("Start Damage");
		setBounds(100, 100, 450, 188);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][][][]"));
		DecimalFormat format = new DecimalFormat("##0.###");
		format.setMinimumFractionDigits(0);
		
		JLabel lblNewLabel = new JLabel("Damage Rectangle");
		contentPanel.add(lblNewLabel, "cell 0 0");
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		contentPanel.add(panel, "cell 0 1,grow");
		panel.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		JLabel lblNewLabel_2 = new JLabel("x");
		lblNewLabel_2.setToolTipText("x-coordinate from origin of the damaging rect");
		panel.add(lblNewLabel_2, "cell 0 0,alignx trailing");
		
		rectXTextField = new JFormattedTextField();
		rectXTextField.setToolTipText("x-coordinate from origin of the damaging rect");
		panel.add(rectXTextField, "flowx,cell 1 0,growx");
		rectXTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("y");
		lblNewLabel_3.setToolTipText("y-coordinate from origin of the damaging rect");
		panel.add(lblNewLabel_3, "cell 1 0");
		
		rectYTextField = new JFormattedTextField();
		rectYTextField.setToolTipText("x-coordinate from origin of the damaging rect");
		panel.add(rectYTextField, "cell 1 0");
		rectYTextField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("width");
		lblNewLabel_4.setToolTipText("width from origin of the damaging rect");
		panel.add(lblNewLabel_4, "cell 1 0");
		
		rectWTextField = new JFormattedTextField();
		rectWTextField.setToolTipText("width from origin of the damaging rect");
		panel.add(rectWTextField, "cell 1 0");
		rectWTextField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("height");
		lblNewLabel_5.setToolTipText("height from origin of the damaging rect");
		panel.add(lblNewLabel_5, "cell 1 0");
		
		rectHTextField = new JFormattedTextField();
		rectHTextField.setToolTipText("height from origin of the damaging rect");
		panel.add(rectHTextField, "cell 1 0");
		rectHTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Damage");
		lblNewLabel_1.setToolTipText("percentage of damage applied to target");
		contentPanel.add(lblNewLabel_1, "flowx,cell 0 2");
		
		damageTextField = new JFormattedTextField();
		damageTextField.setToolTipText("percentage of damage applied to target");
		contentPanel.add(damageTextField, "cell 0 2");
		damageTextField.setColumns(10);
		
		invertKnock = new JCheckBox("Invert Knockback");
		invertKnock.setToolTipText("Will the knockback become opposide");
		contentPanel.add(invertKnock, "cell 0 2");
		
		JLabel lblNewLabel_6 = new JLabel("Knockback X");
		lblNewLabel_6.setToolTipText("Knockback power in x-direction");
		contentPanel.add(lblNewLabel_6, "flowx,cell 0 3");
		
		knockXTextField = new JFormattedTextField();
		knockXTextField.setToolTipText("Knockback power in x-direction");
		contentPanel.add(knockXTextField, "cell 0 3");
		knockXTextField.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Knockback Y");
		lblNewLabel_7.setToolTipText("Knockback power in y-direction");
		contentPanel.add(lblNewLabel_7, "cell 0 3");
		
		knockYTextField = new JFormattedTextField();
		knockYTextField.setToolTipText("Knockback power in y-direction");
		contentPanel.add(knockYTextField, "cell 0 3");
		knockYTextField.setColumns(10);

	}

	@Override
	public void setObject(SkillMotionCommandBase object) {
		// TODO Auto-generated method stub
		SkillMotionCommandStartDamage src = (SkillMotionCommandStartDamage)object;
		this.rectXTextField.setValue(src.rect.x);
		this.rectYTextField.setValue(src.rect.y);
		this.rectHTextField.setValue(src.rect.height);
		this.rectWTextField.setValue(src.rect.width);
		this.knockXTextField.setValue(src.knockback.x);
		this.knockYTextField.setValue(src.knockback.y);
		this.damageTextField.setValue(src.damage);
		this.invertKnock.setSelected(src.knockdir > 0);
	}

	@Override
	public void onOk() {
		rx = ((Number)this.rectXTextField.getValue()).doubleValue();
		ry = ((Number)this.rectYTextField.getValue()).doubleValue();
		rh = ((Number)this.rectHTextField.getValue()).doubleValue();
		rw = ((Number)this.rectWTextField.getValue()).doubleValue();
		kx = ((Number)this.knockXTextField.getValue()).doubleValue();
		ky = ((Number)this.knockXTextField.getValue()).doubleValue();
		dm = ((Number)this.damageTextField.getValue()).doubleValue();
		ki = this.invertKnock.isSelected() ? 1 : 0;
		this.setDirty();
		this.onCancel();
	}

}
