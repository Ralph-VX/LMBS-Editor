package kien.lmbseditor.window.animation;

import kien.lmbseditor.core.EditorProperty;
import kien.lmbseditor.core.animation.AnimationLMBSTimingDamage;
import kien.lmbseditor.core.animation.AnimationLMBSProperty;
import kien.lmbseditor.core.animation.AnimationLMBSTimingBase;
import kien.lmbseditor.mv.Animation;
import kien.lmbseditor.window.EditorPanelBase;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.border.BevelBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class AnimationLMBSDescriptionPanel extends EditorPanelBase implements ActionListener, ItemListener {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField xValueTextField;
	private JTextField yValueTextField;
	private JComboBox<String> animationList;
	private JComboBox<String> xOriginList;
	private JComboBox<String> yOriginList;
	private AnimationContent animationContent;
	private JList<String> animationFrameList;
	private DefaultListModel<String> animationFrameListModel;
	private JList<AnimationLMBSTimingBase> animationTimingList;
	private DefaultListModel<AnimationLMBSTimingBase> animationTimingListModel;
	private int frameNumber;
	
	private AnimationLMBSProperty animation;
	private JFormattedTextField delayTextField;
	private JCheckBox mirrorCheck;
	private JCheckBox followCheck;
	public AnimationLMBSDescriptionPanel() {
		this.frameNumber = 0;
		
		setLayout(new MigLayout("", "[10%,grow][40%,grow][10%,grow][40%,grow]", "[][][][][][][grow]"));
		
		JLabel lblAnimation = new JLabel("Animation");
		add(lblAnimation, "cell 0 0,growx");
		
		animationList = new JComboBox<String>();
		animationList.addItemListener(this);
		this.refreshAnimationList();
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
		xOriginList.setModel(new DefaultComboBoxModel<String>(new String[] {"user", "target", "screen", "field"}));
		add(xOriginList, "cell 1 3,growx");
		
		yValueTextField = new JTextField();
		add(yValueTextField, "cell 2 3,growx");
		yValueTextField.setColumns(10);
		
		yOriginList = new JComboBox<String>();
		yOriginList.setModel(new DefaultComboBoxModel<String>(new String[] {"user", "target", "screen", "field"}));
		add(yOriginList, "cell 3 3,growx");

		JLabel lblDelay = new JLabel("Delay:");
		add(lblDelay, "flowx,cell 0 4,alignx left");

		DecimalFormat format = new DecimalFormat("0.###");
		format.setMinimumFractionDigits(0);
		delayTextField = new JFormattedTextField(format);
		add(delayTextField, "cell 1 4,growx");
		
		mirrorCheck = new JCheckBox("Mirror");
		add(mirrorCheck, "cell 2 4");
		
		followCheck = new JCheckBox("Follow Origin");
		add(followCheck, "cell 3 4");
		
		JSeparator separator = new JSeparator();
		add(separator, "cell 0 5 4 1");
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 6 2 1,grow");
		panel.setLayout(new MigLayout("", "[33%,grow][34%,grow][33%,grow]", "[30%,grow][60%,grow][10%,grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 0 3 1,grow");

		animationFrameListModel = new DefaultListModel<String>();
		animationFrameList = new JList<String>(animationFrameListModel);
		scrollPane.setViewportView(animationFrameList);
		
		JLabel lblNewLabel = new JLabel("Animation Frame:");
		scrollPane.setColumnHeaderView(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, "cell 0 1 3 1,grow");

		animationTimingListModel = new DefaultListModel<AnimationLMBSTimingBase>();
		animationTimingList = new JList<AnimationLMBSTimingBase>(animationTimingListModel);
		scrollPane_1.setViewportView(animationTimingList);
		
		JLabel lblTimings = new JLabel("Animation Timings:");
		scrollPane_1.setColumnHeaderView(lblTimings);
		
		JButton btnCreateTiming = new JButton("Create");
		btnCreateTiming.setActionCommand("create");
		btnCreateTiming.addActionListener(this);
		panel.add(btnCreateTiming, "cell 0 2,growx");
		
		JButton btnEditTiming = new JButton("Edit");
		btnEditTiming.setActionCommand("edit");
		btnEditTiming.addActionListener(this);
		panel.add(btnEditTiming, "cell 1 2,growx");
		
		JButton btnDeleteTiming = new JButton("Delete");
		btnDeleteTiming.setActionCommand("delete");
		btnDeleteTiming.addActionListener(this);
		panel.add(btnDeleteTiming, "cell 2 2,growx");
		
		animationContent = new AnimationContent();
		animationContent.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(animationContent, "cell 2 6 2 1,grow");
	}
	
	public void refreshAnimationList() {
		this.animationList.removeAllItems();
		this.animation = null;
		this.animationList.addItem("Select Animation");
		ArrayList<Animation> list = EditorProperty.animations;
		DecimalFormat format = new DecimalFormat("000");
		for (int n = 0; n < list.size(); n++) {
			Animation item = list.get(n);
			if (item != null) {
				this.animationList.addItem(format.format(n) + ":" + item.name);
			}
		}
	}
	
	public void refreshValues() {
		if (this.animation != null) {
			this.animationList.setSelectedIndex(this.animation.animationId);
			this.xOriginList.setSelectedItem(this.animation.x.origin);
			this.xValueTextField.setText(this.animation.x.getValue().toString());
			this.yOriginList.setSelectedItem(this.animation.y.origin);
			this.yValueTextField.setText(this.animation.y.getValue().toString());
			this.mirrorCheck.setSelected(this.animation.mirror);
			this.followCheck.setSelected(this.animation.follow);
		}
	}
	
	public void refreshAnimationFrameList() {
		this.animationFrameListModel.removeAllElements();
		if (this.animation != null) {
			Animation anim = EditorProperty.animations.get(this.animation.animationId);
			if (anim != null) {
				for (int i = 0; i < anim.frames.size(); i++) {
					this.animationFrameListModel.addElement(Integer.toString(i+1));
				}
			}
		}
	}
	
	public void refreshAnimationTimingList() {
		this.animationTimingListModel.removeAllElements();
		if (this.animation != null && this.frameNumber > 0) {
			ArrayList<AnimationLMBSTimingBase> arr = this.animation.timing;
			for (AnimationLMBSTimingBase timing : arr) {
				this.animationTimingListModel.addElement(timing);
			}
		}
	}
	
	public void onCreateTiming() {
		if (this.animation != null && this.frameNumber > 0) {
			AnimationTimingDialog d = new AnimationTimingDialog();
			d.setVisible(true);
			if (d.isDirty()) {
				try {
					AnimationLMBSTimingBase dret = AnimationLMBSProperty.timingTypeToClass.get(d.typeName).newInstance();
					this.animation.addTiming(dret);
					this.refreshAnimationTimingList();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void onEditTiming() {
		if (this.animation != null && this.frameNumber > 0) {
			AnimationLMBSTimingBase timing = this.animationTimingList.getSelectedValue();
			if (timing != null) {
				AnimationTimingDialog d = new AnimationTimingDialog();
				d.setData(AnimationLMBSProperty.classToTimingType.get(timing.getClass()), timing.obtainData());
				d.setVisible(true);
				if (d.isDirty()) {
					try {
						AnimationLMBSTimingBase dret = AnimationLMBSProperty.timingTypeToClass.get(d.typeName).newInstance();
						if (dret.getClass().equals(timing.getClass())) {
							this.animation.timing.set(this.animation.timing.indexOf(timing), dret);
						} else {
							this.animation.timing.remove(timing);
							this.animation.addTiming(dret);
						}
						this.refreshAnimationTimingList();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void onDeleteTiming() {
		if (this.animation != null && this.frameNumber > 0) {
			AnimationLMBSTimingBase timing = this.animationTimingList.getSelectedValue();
			if (timing != null) {
				this.animation.timing.remove(timing);
				this.refreshAnimationTimingList();
			}
		}
	}

	@Override
	public void refresh() {
	}

	@Override
	public void update() {
	}
	
	@Override
	public void updateEdit() {
		this.animation.x.origin = (String) this.xOriginList.getSelectedItem();
		this.animation.y.origin = (String) this.yOriginList.getSelectedItem();
		this.animation.x.setValue(this.xValueTextField.getText());
		this.animation.y.setValue(this.yValueTextField.getText());
		this.animation.delay = ((Number)this.delayTextField.getValue()).intValue();
		this.animation.follow = this.followCheck.isSelected();
		this.animation.mirror = this.mirrorCheck.isSelected();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("create")) {
			this.onCreateTiming();
		} else if (command.equals("edit")) {
			this.onEditTiming();
		} else if (command.equals("delete")) {
			this.onDeleteTiming();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (this.animation != null) {
			this.animation.animationId = this.animationList.getSelectedIndex();
			
		}
	}

}
