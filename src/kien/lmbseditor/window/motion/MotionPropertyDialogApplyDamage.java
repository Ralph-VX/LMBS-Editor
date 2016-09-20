package kien.lmbseditor.window.motion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kien.lmbseditor.core.motion.SkillMotionCommandApplyDamage;
import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 298, 141);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][]"));
		DecimalFormat f = new DecimalFormat("0.##");
		{
			JLabel lblNewLabel = new JLabel("Damage");
			contentPanel.add(lblNewLabel, "cell 0 0,alignx trailing");
		}
		{
			damageTextField = new JFormattedTextField(f);
			contentPanel.add(damageTextField, "cell 1 0,growx");
			damageTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Knockback X");
			contentPanel.add(lblNewLabel_1, "cell 0 1,alignx trailing");
		}
		{
			knockbackXTextField = new JFormattedTextField(f);
			contentPanel.add(knockbackXTextField, "flowx,cell 1 1,growx");
			knockbackXTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Knockback Y");
			contentPanel.add(lblNewLabel_2, "cell 1 1");
		}
		{
			knockbackYTextField = new JFormattedTextField(f);
			contentPanel.add(knockbackYTextField, "cell 1 1");
			knockbackYTextField.setColumns(10);
		}
		{
			knockbackInvertedCheckBox = new JCheckBox("Knockback Inverted");
			contentPanel.add(knockbackInvertedCheckBox, "cell 1 2");
		}
	}
	
	@Override
	public void onOk() {
		this.damage = ((Number)this.damageTextField.getValue()).doubleValue();
		this.knockback.x = ((Number)this.knockbackXTextField.getValue()).doubleValue();
		this.knockback.y = ((Number)this.knockbackYTextField.getValue()).doubleValue();
		this.knockdir = this.knockbackInvertedCheckBox.isSelected() ? 1 : 0;
		this.setDirty();
		this.onCancel();
	}

	@Override
	public void setObject(SkillMotionCommandBase object) {
		SkillMotionCommandApplyDamage src = (SkillMotionCommandApplyDamage)object;
		this.damage = src.damage;
		this.knockback = src.knockback;
		this.knockdir = src.knockdir;
		this.damageTextField.setValue(damage);
		this.knockbackXTextField.setValue(knockback.x);
		this.knockbackYTextField.setValue(knockback.y);
		this.knockbackInvertedCheckBox.setSelected(knockdir > 0);
	}
	
	

}
