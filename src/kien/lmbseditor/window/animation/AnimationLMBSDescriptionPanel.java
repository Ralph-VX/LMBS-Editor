package kien.lmbseditor.window.animation;

import kien.lmbseditor.core.animation.AnimationLMBSProperty;
import kien.lmbseditor.window.EditorPanelBase;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.BevelBorder;
import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import java.awt.Button;
import javax.swing.JButton;

public class AnimationLMBSDescriptionPanel extends EditorPanelBase {
	private JTextField xValueTextField;
	private JTextField yValueTextField;
	private JComboBox animationList;
	private JComboBox xOriginList;
	private JComboBox yOriginList;
	private AnimationContent animationContent;
	private JList animationFrameList;
	private JList AnimationTimingList;
	
	private AnimationLMBSProperty animation;
	public AnimationLMBSDescriptionPanel() {
		setLayout(new MigLayout("", "[10%,grow][40%,grow][10%,grow][40%,grow]", "[][][][][][][grow]"));
		
		JLabel lblAnimation = new JLabel("Animation");
		add(lblAnimation, "cell 0 0,growx");
		
		animationList = new JComboBox();
		add(animationList, "cell 0 1 4 1,growx");
		
		JLabel lblX = new JLabel("X:");
		add(lblX, "cell 0 2");
		
		JLabel lblXOrigin = new JLabel("X Origin:");
		add(lblXOrigin, "cell 1 2");
		
		JLabel lblY = new JLabel("Y:");
		add(lblY, "cell 2 2");
		
		JLabel lblYOrigin = new JLabel("Y Origin:");
		add(lblYOrigin, "cell 3 2");
		
		xValueTextField = new JTextField();
		add(xValueTextField, "cell 0 3,growx");
		xValueTextField.setColumns(10);
		
		xOriginList = new JComboBox<String>();
		xOriginList.setModel(new DefaultComboBoxModel(new String[] {"user", "target", "screen", "field"}));
		add(xOriginList, "cell 1 3,growx");
		
		yValueTextField = new JTextField();
		add(yValueTextField, "cell 2 3,growx");
		yValueTextField.setColumns(10);
		
		yOriginList = new JComboBox();
		yOriginList.setModel(new DefaultComboBoxModel(new String[] {"user", "target", "screen", "field"}));
		add(yOriginList, "cell 3 3,growx");
		
		JLabel lblDelay = new JLabel("Delay:");
		add(lblDelay, "flowx,cell 0 4,alignx left");
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		add(formattedTextField, "cell 1 4,growx");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Mirror");
		add(chckbxNewCheckBox, "cell 2 4");
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Follow Origin");
		add(chckbxNewCheckBox_1, "cell 3 4");
		
		JSeparator separator = new JSeparator();
		add(separator, "cell 0 5 4 1");
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 6 2 1,grow");
		panel.setLayout(new MigLayout("", "[33%,grow][34%,grow][33%,grow]", "[30%,grow][60%,grow][10%,grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 0 3 1,grow");
		
		animationFrameList = new JList();
		scrollPane.setViewportView(animationFrameList);
		
		JLabel lblNewLabel = new JLabel("Animation Frame:");
		scrollPane.setColumnHeaderView(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, "cell 0 1 3 1,grow");
		
		AnimationTimingList = new JList();
		scrollPane_1.setViewportView(AnimationTimingList);
		
		JLabel lblTimings = new JLabel("Animation Timings:");
		scrollPane_1.setColumnHeaderView(lblTimings);
		
		JButton btnCreateTiming = new JButton("Create");
		panel.add(btnCreateTiming, "cell 0 2,growx");
		
		JButton btnDeleteTiming = new JButton("Edit");
		panel.add(btnDeleteTiming, "cell 1 2,growx");
		
		JButton btnNewButton = new JButton("Delete");
		panel.add(btnNewButton, "cell 2 2,growx");
		
		animationContent = new AnimationContent();
		animationContent.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(animationContent, "cell 2 6 2 1,grow");
	}
	
	public void refreshValues() {
		if (this.animation != null) {
			
		}
	}

	@Override
	public void refresh() {
	}

	@Override
	public void update() {
	}

}
