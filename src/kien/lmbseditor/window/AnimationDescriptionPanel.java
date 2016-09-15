package kien.lmbseditor.window;

import javax.swing.JPanel;

import kien.lmbseditor.core.AnimationItemType;
import kien.lmbseditor.core.AnimationObject;
import kien.lmbseditor.core.EditorProperty;
import kien.lmbseditor.mv.Animation;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.text.NumberFormatter;

import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class AnimationDescriptionPanel extends JPanel {

	private AnimationItemType contents;
	private int curFrame;
	private int maxFrame;
	private Animation animation;
	private DefaultListModel<String> timingListContent;
	private boolean inited;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5249160481137789181L;

	private JFormattedTextField timingRectX;

	private JFormattedTextField timingRectY;

	private JFormattedTextField timingRectWidth;

	private JFormattedTextField timingRectHeight;

	private JFormattedTextField timingDuration;

	private JFormattedTextField timingDamage;
	private JComboBox<String> animationList;
	private JLabel frameShowingLable;
	private JList<String> timingList;
	private JFormattedTextField timingKnockbackX;
	private JFormattedTextField timingKnockbackY;
	private JCheckBox timingKnockbackInvert;
	private AnimationContent animationContent;
	private JButton buttonPrevFrame;
	private JButton buttonNextFrame;
	private JButton buttonNewTiming;
	private JButton buttonDeleteTiming;
	private int tabIndex;

	/**
	 * Create the panel.
	 */
	public AnimationDescriptionPanel() {
		this.inited = false;
		this.animation = null;
		this.contents = null;
		this.curFrame = -1;
		this.maxFrame = -1;
		
		setLayout(new MigLayout("", "[100px][30px][80px][grow]", "[19px][19px][19px][19px][19][19][19][19][19][19][19][19][19][19][grow][19]"));
		
		JLabel lblAnimation = new JLabel("Animation Target");
		add(lblAnimation, "cell 0 0,aligny bottom");
		
		animationList = new JComboBox<String>();
		animationList.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				AnimationDescriptionPanel.this.onAnimationListChange();
			}
		});
		add(animationList, "cell 0 1,growx,aligny top");
		this.refreshAnimationList();
		
		animationContent = new AnimationContent();
		animationContent.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(animationContent, "cell 3 1 1 15,grow");
		
		JLabel lblFrames = new JLabel("Frames");
		add(lblFrames, "cell 0 2");
		
		frameShowingLable = new JLabel("Not Selected");
		add(frameShowingLable, "cell 0 3");
		
		JPanel panel = new JPanel();
		add(panel, "cell 1 3,alignx center,aligny center");
		
		buttonPrevFrame = new JButton("<");
		buttonPrevFrame.setEnabled(false);
		buttonPrevFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnimationDescriptionPanel.this.onPreviousFrame();
			}
		});
		panel.add(buttonPrevFrame);
		
		buttonNextFrame = new JButton(">");
		buttonNextFrame.setEnabled(false);
		buttonNextFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnimationDescriptionPanel.this.onNextFrame();
			}
		});
		panel.add(buttonNextFrame);
		
		JLabel lblTimings = new JLabel("Timings");
		add(lblTimings, "cell 0 4");
		
		JLabel lblNewLabel_1 = new JLabel("Properties");
		add(lblNewLabel_1, "cell 1 4 2 1");
		
		timingListContent = new DefaultListModel<String>();
		timingList = new JList<String>(timingListContent);
		timingList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				AnimationDescriptionPanel.this.onTimingListChange();
			}
		});
		timingList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(timingList, "cell 0 5 1 10,grow");
		
		JLabel lblX = new JLabel("x");
		add(lblX, "cell 1 5,alignx trailing");
		
		DecimalFormat format = new DecimalFormat("0.###");
		FocusAdapter f = new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				AnimationDescriptionPanel.this.onTimingInputLostFocus();
			}
		};
		KeyAdapter k = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					AnimationDescriptionPanel.this.requestFocusInWindow();
				}
			}
		};
		timingRectX = new JFormattedTextField(format);
		timingRectX.addKeyListener(k);
		timingRectX.addFocusListener(f);
		add(timingRectX, "cell 2 5,growx");
		
		JLabel lblY = new JLabel("y");
		add(lblY, "cell 1 6,alignx trailing");
		
		timingRectY = new JFormattedTextField(format);
		timingRectY.addFocusListener(f);
		timingRectY.addKeyListener(k);
		add(timingRectY, "cell 2 6,growx");
		
		JLabel lblWidth = new JLabel("width");
		add(lblWidth, "cell 1 7,alignx trailing");
		
		timingRectWidth = new JFormattedTextField(format);
		timingRectWidth.addFocusListener(f);
		timingRectWidth.addKeyListener(k);
		add(timingRectWidth, "cell 2 7,growx");
		
		JLabel lblHeight = new JLabel("height");
		add(lblHeight, "cell 1 8,alignx trailing");
		
		timingRectHeight = new JFormattedTextField(format);
		timingRectHeight.addFocusListener(f);
		timingRectHeight.addKeyListener(k);
		add(timingRectHeight, "cell 2 8,growx");
		
		JLabel lblNewLabel_2 = new JLabel("duration");
		add(lblNewLabel_2, "cell 1 9,alignx trailing");
		
		timingDuration = new JFormattedTextField(format);
		timingDuration.addFocusListener(f);
		timingDuration.addKeyListener(k);
		add(timingDuration, "cell 2 9,growx");
		
		JLabel lblDamagePercentage = new JLabel("Damage %");
		add(lblDamagePercentage, "cell 1 10,alignx trailing");
		
		timingDamage = new JFormattedTextField(format);
		timingDamage.addFocusListener(f);
		timingDamage.addKeyListener(k);
		add(timingDamage, "cell 2 10,growx");
		
		JLabel lblKnockbackX = new JLabel("Knockback x");
		add(lblKnockbackX, "cell 1 11,alignx trailing");
		
		timingKnockbackX = new JFormattedTextField(format);
		timingKnockbackX.addFocusListener(f);
		timingKnockbackX.addKeyListener(k);
		add(timingKnockbackX, "cell 2 11,growx");
		
		JLabel lblKnockbackY = new JLabel("Knockback y");
		add(lblKnockbackY, "cell 1 12,alignx trailing");
		
		timingKnockbackY = new JFormattedTextField(format);
		timingKnockbackY.addFocusListener(f);
		timingKnockbackY.addKeyListener(k);
		add(timingKnockbackY, "cell 2 12,growx");
		
		JLabel lblKnockbackBack = new JLabel("Invert Direction");
		add(lblKnockbackBack, "cell 1 13,alignx right");
		
		timingKnockbackInvert = new JCheckBox("Invert");
		timingKnockbackInvert.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				AnimationDescriptionPanel.this.onTimingInputLostFocus();
			}
		});
		add(timingKnockbackInvert, "cell 2 13");
		
		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 0 15,grow");
		
		buttonNewTiming = new JButton("New");
		buttonNewTiming.setEnabled(false);
		buttonNewTiming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnimationDescriptionPanel.this.onTimingNew();
			}
		});
		panel_1.add(buttonNewTiming);
		
		buttonDeleteTiming = new JButton("Delete");
		buttonDeleteTiming.setEnabled(false);
		buttonDeleteTiming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnimationDescriptionPanel.this.onTimingDelete();
			}
		});
		panel_1.add(buttonDeleteTiming);
		
		this.inited = true;
	}
	
	public void setContent(AnimationItemType item) {
		this.contents = item;
		this.refreshComponentContent();
	}

	private void clearRectContents() {
		this.timingDamage.setText("");
		this.timingDuration.setText("");
		this.timingRectX.setText("");
		this.timingRectY.setText("");
		this.timingRectWidth.setText("");
		this.timingRectHeight.setText("");
		this.timingKnockbackX.setText("");
		this.timingKnockbackY.setText("");
		this.timingKnockbackInvert.setSelected(false);
		this.buttonDeleteTiming.setEnabled(false);
	}
	
	/**
	 * Load datas for initialization.
	 */
	
	private void refreshComponentContent() {
		this.timingListContent.removeAllElements();
		this.clearRectContents();
		if (this.animation != null) {
			this.frameShowingLable.setText((curFrame+1) + "/" + maxFrame);
			if (this.contents != null) {
				ArrayList<AnimationObject> list = contents.data.get(curFrame);
				if (list != null) {
					for (int n = 0; n < list.size(); n++) {
						this.timingListContent.addElement("Rect" + (n+1));
					}
				}
			}
		}
	}
	
	private void refreshAnimationPanel() {
		this.animationContent.clear();
		if (this.animation != null) {
			this.animationContent.setAnimation(this.animation);
			this.animationContent.setFrame(this.curFrame);
			this.animationContent.setRects(contents.data.get(this.curFrame));
		}
	}
	
	private void refreshAnimationList() {
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
	
	private void onAnimationListChange() {
		int index = animationList.getSelectedIndex();
		if (this.inited && index >= 0) {
			this.animation = EditorProperty.animations.get(index);
			this.refreshAnimationPanel();
			if (this.animation != null) {
				this.animation = EditorProperty.animations.get(index);
				this.curFrame = 0;
				this.maxFrame = animation.frames.size();
				this.buttonNextFrame.setEnabled(true);
				this.buttonPrevFrame.setEnabled(false);
				this.buttonNewTiming.setEnabled(true);
				this.buttonDeleteTiming.setEnabled(false);
			} else {
				this.frameShowingLable.setText("Not Selected");
				this.buttonNextFrame.setEnabled(false);
				this.buttonPrevFrame.setEnabled(false);
				this.curFrame = -1;
				this.maxFrame = -1;
				this.buttonNewTiming.setEnabled(false);
				this.buttonDeleteTiming.setEnabled(false);
			}
			this.refreshComponentContent();
		}
	}
	
	private void onNextFrame() {
		if (this.curFrame != -1 && this.maxFrame != -1) {
			this.curFrame++;
			if (this.curFrame >= this.maxFrame) {
				this.curFrame = this.maxFrame - 1;
			} else {
				this.refreshComponentContent();
				this.buttonPrevFrame.setEnabled(true);
				this.animationContent.setFrame(this.curFrame);
				this.animationContent.setRects(this.contents.data.get(this.curFrame));
				if (this.curFrame == this.maxFrame - 1) {
					this.buttonNextFrame.setEnabled(false);
				}
			}
		}
	}
	
	private void onPreviousFrame() {
		if (this.curFrame != -1 && this.maxFrame != -1) {
			this.curFrame--;
			if (this.curFrame < 0) {
				this.curFrame = 0;
			} else {
				this.refreshComponentContent();
				this.buttonNextFrame.setEnabled(true);
				this.animationContent.setFrame(this.curFrame);
				this.animationContent.setRects(this.contents.data.get(this.curFrame));
				if (this.curFrame == 0) {
					this.buttonPrevFrame.setEnabled(false);
				}
			}
		}
	}
	
	private void onTimingListChange() {
		ArrayList<AnimationObject> aos = contents.data.get(this.curFrame);
		if (aos != null && this.timingList.getSelectedIndex() >= 0) {
			AnimationObject ao = aos.get(this.timingList.getSelectedIndex());
			if (ao != null) {
				this.timingDamage.setText(Float.toString(ao.damagePer));
				this.timingDuration.setText(Integer.toString(ao.dur));
				this.timingRectX.setText(Float.toString(ao.rect.x));
				this.timingRectY.setText(Float.toString(ao.rect.y));
				this.timingRectWidth.setText(Float.toString(ao.rect.width));
				this.timingRectHeight.setText(Float.toString(ao.rect.height));
				this.timingKnockbackX.setText(Float.toString(ao.knockback.x));
				this.timingKnockbackY.setText(Float.toString(ao.knockback.y));
				this.timingKnockbackInvert.setSelected(ao.knockdir > 0);
				this.buttonDeleteTiming.setEnabled(true);
				return;
			}
		}
		this.clearRectContents();
	}
	
	private void onTimingInputLostFocus() {
		if (this.curFrame >= 0 && this.timingList.getSelectedIndex() != -1) {
			contents.updateData(
					this.curFrame, 
					this.timingList.getSelectedIndex(), 
					Float.parseFloat(this.timingRectX.getText()), 
					Float.parseFloat(this.timingRectY.getText()),
					Float.parseFloat(this.timingRectWidth.getText()), 
					Float.parseFloat(this.timingRectHeight.getText()), 
					Integer.parseInt(this.timingDuration.getText()), 
					Float.parseFloat(this.timingDamage.getText()),
					Float.parseFloat(this.timingKnockbackX.getText()), 
					Float.parseFloat(this.timingKnockbackY.getText()), 
					this.timingKnockbackInvert.isSelected());
			this.animationContent.setRects(contents.data.get(this.curFrame));
		}
	}
	
	private void onTimingNew() {
		if (this.animation != null) {
			this.contents.newData(this.curFrame);
			this.refreshComponentContent();
			this.refreshAnimationPanel();
			this.timingList.setSelectedIndex(this.contents.data.get(this.curFrame).size()-1);
		}
	}
	
	private void onTimingDelete() {
		if (this.animation != null && this.curFrame >= 0 && this.timingList.getSelectedIndex() >= 0) {
			this.contents.deleteData(curFrame, this.timingList.getSelectedIndex());
			this.refreshComponentContent();
			this.refreshAnimationPanel();
		}
	}

	public void setTabIndex(int n) {
		this.tabIndex = n;
	}
	
	
	
}
