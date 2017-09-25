package kien.lmbseditor.window.animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kien.lmbseditor.core.AnimationItemType;
import kien.lmbseditor.core.EditorProperty;
import kien.lmbseditor.core.animation.AnimationLMBSDamageTiming;
import kien.lmbseditor.mv.Animation;
import kien.lmbseditor.window.EditorPanelBase;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("unused")
public class AnimationDescriptionPanel extends EditorPanelBase {

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
	public AnimationDescriptionPanel(AnimationItemType item) {
		this.contents = item;
		this.inited = false;
		this.animation = null;
		this.curFrame = -1;
		this.maxFrame = -1;
		
		setLayout(new MigLayout("", "[100px][30px][80px][grow]", "[19px][19px][19px][19px][19][19][19][19][19][19][19][19][19][19][grow][19]"));
		
		JLabel lblAnimation = new JLabel("Animation Target");
		add(lblAnimation, "cell 0 0,aligny bottom");
		
		animationList = new JComboBox<String>();
		animationList.addItemListener(new ItemListener() {
			@Override
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
			@Override
			public void actionPerformed(ActionEvent e) {
				AnimationDescriptionPanel.this.onPreviousFrame();
			}
		});
		panel.add(buttonPrevFrame);
		
		buttonNextFrame = new JButton(">");
		buttonNextFrame.setEnabled(false);
		buttonNextFrame.addActionListener(new ActionListener() {
			@Override
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
			@Override
			public void valueChanged(ListSelectionEvent e) {
				AnimationDescriptionPanel.this.onTimingListChange();
			}
		});
		timingList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(timingList, "cell 0 5 1 10,grow");
		
		JLabel lblX = new JLabel("x");
		add(lblX, "cell 1 5,alignx trailing");
		
		DecimalFormat format = new DecimalFormat("0.###");

		timingRectX = new JFormattedTextField(format);
		timingRectX.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				AnimationDescriptionPanel.this.onTimingRectXChange();
			}
			
		});
		add(timingRectX, "cell 2 5,growx");
		
		JLabel lblY = new JLabel("y");
		add(lblY, "cell 1 6,alignx trailing");
		
		timingRectY = new JFormattedTextField(format);
		timingRectY.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				AnimationDescriptionPanel.this.onTimingRectYChange();
			}
			
		});
		add(timingRectY, "cell 2 6,growx");
		
		JLabel lblWidth = new JLabel("width");
		add(lblWidth, "cell 1 7,alignx trailing");
		
		timingRectWidth = new JFormattedTextField(format);
		timingRectWidth.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				AnimationDescriptionPanel.this.onTimingRectWidthChange();
			}
			
		});
		add(timingRectWidth, "cell 2 7,growx");
		
		JLabel lblHeight = new JLabel("height");
		add(lblHeight, "cell 1 8,alignx trailing");
		
		timingRectHeight = new JFormattedTextField(format);
		timingRectHeight.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				AnimationDescriptionPanel.this.onTimingRectHeightChange();
			}
			
		});
		add(timingRectHeight, "cell 2 8,growx");
		
		JLabel lblNewLabel_2 = new JLabel("duration");
		add(lblNewLabel_2, "cell 1 9,alignx trailing");
		
		timingDuration = new JFormattedTextField(format);
		timingDuration.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				AnimationDescriptionPanel.this.onTimingDurationChange();
			}
			
		});
		add(timingDuration, "cell 2 9,growx");
		
		JLabel lblDamagePercentage = new JLabel("Damage %");
		add(lblDamagePercentage, "cell 1 10,alignx trailing");
		
		timingDamage = new JFormattedTextField(format);
		timingDamage.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				AnimationDescriptionPanel.this.onTimingDamageChange();
			}
			
		});
		add(timingDamage, "cell 2 10,growx");
		
		JLabel lblKnockbackX = new JLabel("Knockback x");
		add(lblKnockbackX, "cell 1 11,alignx trailing");
		
		timingKnockbackX = new JFormattedTextField(format);
		timingKnockbackX.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				AnimationDescriptionPanel.this.onTimingKnockbackXChange();
			}
			
		});
		add(timingKnockbackX, "cell 2 11,growx");
		
		JLabel lblKnockbackY = new JLabel("Knockback y");
		add(lblKnockbackY, "cell 1 12,alignx trailing");
		
		timingKnockbackY = new JFormattedTextField(format);
		timingKnockbackY.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				AnimationDescriptionPanel.this.onTimingKnockbackYChange();
			}
			
		});
		add(timingKnockbackY, "cell 2 12,growx");
		
		JLabel lblKnockbackBack = new JLabel("Invert Direction");
		add(lblKnockbackBack, "cell 1 13,alignx right");
		
		timingKnockbackInvert = new JCheckBox("Invert");
		timingKnockbackInvert.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				AnimationDescriptionPanel.this.onTimingKnockbackInvertChange();
			}
		});
		add(timingKnockbackInvert, "cell 2 13");
		
		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 0 15,grow");
		
		buttonNewTiming = new JButton("New");
		buttonNewTiming.setEnabled(false);
		buttonNewTiming.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AnimationDescriptionPanel.this.onTimingNew();
			}
		});
		panel_1.add(buttonNewTiming);
		
		buttonDeleteTiming = new JButton("Delete");
		buttonDeleteTiming.setEnabled(false);
		buttonDeleteTiming.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AnimationDescriptionPanel.this.onTimingDelete();
			}
		});
		panel_1.add(buttonDeleteTiming);

		this.inited = true;
		this.refreshComponentContent();
	}
	
	private void clearRectContents() {
		this.timingDamage.setValue(null);
		this.timingDuration.setValue(null);
		this.timingRectX.setValue(null);
		this.timingRectY.setValue(null);
		this.timingRectWidth.setValue(null);
		this.timingRectHeight.setValue(null);
		this.timingKnockbackX.setValue(null);
		this.timingKnockbackY.setValue(null);
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
				ArrayList<AnimationLMBSDamageTiming> list = contents.data.get(curFrame);
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
	
	private void onAnimationListChange() {
		int index = animationList.getSelectedIndex();
		if (this.inited && index >= 0) {
			if (this.timingList.getSelectedIndex() != -1) {
				this.saveCurrentContents();
			}
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
			if (this.timingList.getSelectedIndex() != -1) {
				this.saveCurrentContents();
			}
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
			if (this.timingList.getSelectedIndex() != -1) {
				this.saveCurrentContents();
			}
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
		ArrayList<AnimationLMBSDamageTiming> aos = contents.data.get(this.curFrame);
		if (aos != null && this.timingList.getSelectedIndex() >= 0) {
			AnimationLMBSDamageTiming ao = aos.get(this.timingList.getSelectedIndex());
			if (ao != null) {
				this.timingDamage.setValue(ao.damagePer);
				this.timingDuration.setValue(ao.dur);
				this.timingRectX.setValue(ao.rect.x);
				this.timingRectY.setValue(ao.rect.y);
				this.timingRectWidth.setValue(ao.rect.width);
				this.timingRectHeight.setValue(ao.rect.height);
				this.timingKnockbackX.setValue(ao.knockback.x);
				this.timingKnockbackY.setValue(ao.knockback.y);
				this.timingKnockbackInvert.setSelected(ao.knockdir > 0);
				this.buttonDeleteTiming.setEnabled(true);
				return;
			}
		}
		this.clearRectContents();
	}
	
	private void onTimingRectXChange() {
		if (this.curFrame >= 0 && this.timingList.getSelectedIndex() != -1) {
			if (this.timingRectX.getValue() != null) {
				double nv = (((Number)this.timingRectX.getValue()).doubleValue());
				if (nv != contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).rect.x) {
					contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).rect.x = nv;
					this.animationContent.setRects(contents.data.get(this.curFrame));
				}
			}
		}
	}
	private void onTimingRectYChange() {
		if (this.curFrame >= 0 && this.timingList.getSelectedIndex() != -1) {
			if (this.timingRectY.getValue() != null) {
				double nv = (((Number)this.timingRectY.getValue()).doubleValue());
				if (nv != contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).rect.y) {
					contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).rect.y = nv;
					this.animationContent.setRects(contents.data.get(this.curFrame));
				}
			}
		}
	}
	private void onTimingRectWidthChange() {
		if (this.curFrame >= 0 && this.timingList.getSelectedIndex() != -1) {
			if (this.timingRectWidth.getValue() != null) {
				double nv = (((Number)this.timingRectWidth.getValue()).doubleValue());
				if (nv != contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).rect.width) {
					contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).rect.width = nv;
					this.animationContent.setRects(contents.data.get(this.curFrame));
				}
			}
		}
	}
	private void onTimingRectHeightChange() {
		if (this.curFrame >= 0 && this.timingList.getSelectedIndex() != -1) {
			if (this.timingRectHeight.getValue() != null) {
				double nv = (((Number)this.timingRectHeight.getValue()).doubleValue());
				if (nv != contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).rect.height) {
					contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).rect.height = nv;
					this.animationContent.setRects(contents.data.get(this.curFrame));
				}
			}
		}
	}

	private void onTimingDamageChange() {
		if (this.curFrame >= 0 && this.timingList.getSelectedIndex() != -1) {
			if (this.timingDamage.getValue() != null) {
				double nv = (((Number)this.timingDamage.getValue()).doubleValue());
				if (nv != contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).damagePer) {
					contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).damagePer = nv;
				}
			}
		}
	}

	private void onTimingDurationChange() {
		if (this.curFrame >= 0 && this.timingList.getSelectedIndex() != -1) {
			if (this.timingDuration.getValue() != null) {
				int nv = (((Number)this.timingDuration.getValue()).intValue());
				if (nv != contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).dur) {
					contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).dur = nv;
				}
			}
		}
	}


	private void onTimingKnockbackXChange() {
		if (this.curFrame >= 0 && this.timingList.getSelectedIndex() != -1) {
			if (this.timingKnockbackX.getValue() != null) {
				double nv = (((Number)this.timingKnockbackX.getValue()).doubleValue());
				if (nv != contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).knockback.x) {
					contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).knockback.x = nv;
				}
			}
		}
	}

	private void onTimingKnockbackYChange() {
		if (this.curFrame >= 0 && this.timingList.getSelectedIndex() != -1) {
			if (this.timingKnockbackY.getValue() != null) {
				double nv = (((Number)this.timingKnockbackY.getValue()).doubleValue());
				if (nv != contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).knockback.y) {
					contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).knockback.y = nv;
				}
			}
		}
	}

	private void onTimingKnockbackInvertChange() {
		if (this.curFrame >= 0 && this.timingList.getSelectedIndex() != -1) {
			int nv = this.timingKnockbackInvert.isSelected() ? 1 : 0;
			if (contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).knockdir != nv) {
				contents.data.get(this.curFrame).get(this.timingList.getSelectedIndex()).knockdir = nv;
			}
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
			this.timingList.clearSelection();
			this.refreshComponentContent();
			this.refreshAnimationPanel();
		}
	}
	
	private void saveCurrentContents() {
		try {
			this.timingDamage.commitEdit();
			this.timingDuration.commitEdit();
			this.timingRectX.commitEdit();
			this.timingRectY.commitEdit();
			this.timingRectWidth.commitEdit();
			this.timingRectHeight.commitEdit();
			this.timingKnockbackX.commitEdit();
			this.timingKnockbackY.commitEdit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTabIndex(int n) {
		this.tabIndex = n;
	}

	@Override
	public void refresh() {
		this.refreshAnimationList();
		this.refreshAnimationPanel();
		this.refreshComponentContent();
	}
	
	@Override
	public void update() {
		this.refresh();
	}
	
}
