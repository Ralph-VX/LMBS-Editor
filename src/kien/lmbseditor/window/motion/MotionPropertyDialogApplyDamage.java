package kien.lmbseditor.window.motion;

import java.awt.geom.Point2D;
import java.text.DecimalFormat;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import kien.lmbseditor.core.motion.SkillMotionCommandApplyDamage;
import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import net.miginfocom.swing.MigLayout;

public class MotionPropertyDialogApplyDamage extends MotionPropertyDialogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFormattedTextField damageTextField;
	private JFormattedTextField knockbackXTextField;
	private JFormattedTextField knockbackYTextField;
	public double damage;
	public Point2D.Double knockback;
	public int knockdir;
	private JCheckBox knockbackInvertedCheckBox;


	/**
	 * Create the dialog.
	 */
	public MotionPropertyDialogApplyDamage() {
		super();
		knockback = new Point2D.Double();
		setTitle("Apply Damage");
		setBounds(100, 100, 298, 160);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][]"));
		DecimalFormat f = new DecimalFormat("0.##");
		f.setMinimumFractionDigits(0);
		{
			JLabel lblNewLabel = new JLabel("Damage");
			lblNewLabel.setToolTipText("percentage of damage applied to target");
			contentPanel.add(lblNewLabel, "cell 0 0,alignx trailing");
		}
		{
			damageTextField = new JFormattedTextField(f);
			damageTextField.setToolTipText("percentage of damage applied to target");
			contentPanel.add(damageTextField, "cell 1 0,growx");
			damageTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Knockback X");
			lblNewLabel_1.setToolTipText("Knockback power in x-direction");
			contentPanel.add(lblNewLabel_1, "cell 0 1,alignx trailing");
		}
		{
			knockbackXTextField = new JFormattedTextField(f);
			knockbackXTextField.setToolTipText("Knockback power in x-direction");
			contentPanel.add(knockbackXTextField, "flowx,cell 1 1,growx");
			knockbackXTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Knockback Y");
			lblNewLabel_2.setToolTipText("Knockback power in y-direction");
			contentPanel.add(lblNewLabel_2, "cell 1 1");
		}
		{
			knockbackYTextField = new JFormattedTextField(f);
			knockbackYTextField.setToolTipText("Knockback power in y-direction");
			contentPanel.add(knockbackYTextField, "cell 1 1");
			knockbackYTextField.setColumns(10);
		}
		{
			knockbackInvertedCheckBox = new JCheckBox("Knockback Inverted");
			knockbackInvertedCheckBox.setToolTipText("Will the knockback become opposide");
			contentPanel.add(knockbackInvertedCheckBox, "cell 1 2");
		}
	}
	
	@Override
	public void onOk() {
		this.damage = ((Number)this.damageTextField.getValue()).doubleValue() / 100;
		this.knockback.x = ((Number)this.knockbackXTextField.getValue()).doubleValue();
		this.knockback.y = ((Number)this.knockbackYTextField.getValue()).doubleValue();
		this.knockdir = this.knockbackInvertedCheckBox.isSelected() ? 1 : 0;
		this.setDirty();
		this.onCancel();
	}

	@Override
	public void setObject(SkillMotionCommandBase object) {
		SkillMotionCommandApplyDamage src = (SkillMotionCommandApplyDamage)object;
		this.damage = src.damage * 100;
		this.knockback = src.knockback;
		this.knockdir = src.knockdir;
		this.damageTextField.setValue(damage);
		this.knockbackXTextField.setValue(knockback.x);
		this.knockbackYTextField.setValue(knockback.y);
		this.knockbackInvertedCheckBox.setSelected(knockdir > 0);
	}
	
	

}
