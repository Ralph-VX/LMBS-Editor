package kien.lmbseditor.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kien.lmbseditor.core.CharacterPose;
import kien.lmbseditor.core.CharacterSet;
import kien.lmbseditor.core.EditorProperty;
import kien.lmbseditor.core.PoseFrameProperty;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;

public class CharacterPosePanel extends EditorPanelBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFormattedTextField maxFrameTextField;
	private JLabel labelCurrentFrame;
	private JFormattedTextField characterWidthTextField;
	private JFormattedTextField characterHeightTextField;
	private JFormattedTextField weaponXTextField;
	private JFormattedTextField weaponYTextField;
	private JFormattedTextField weaponAngleTextField;

	private int tabIndex;
	private CharacterPose currentPose;
	private CharacterSet currentCharacter;
	private JList<String> listCharacter;
	private JList<String> listPose;
	private int frameIndex;

	private DefaultListModel<String> listModelPose;
	private DefaultListModel<String> listModelCharacter;
	private JSlider sliderCurrentFrame;
	private PosePanel posePanel;
	private JCheckBox buttonLooping;
	private ArrayList<String> characterIndexToName;
	private ArrayList<String> poseIndexToName;
	private JCheckBox buttonHideWeapon;
	private JSlider sliderPreviewScale;
	private JLabel lblPreviewScale;
	private JButton buttonApplyAll;
	private JCheckBox buttonWeaponBack;

	/**
	 * Create the panel.
	 */
	public CharacterPosePanel() {
		characterIndexToName = new ArrayList<String>();
		poseIndexToName = new ArrayList<String>();
		currentPose = null;
		currentCharacter = null;
		frameIndex = -1;

		setLayout(new MigLayout("", "[100,growprio 20,grow][100,growprio 20,grow][grow]", "[20][grow]"));

		JLabel lblBattler = new JLabel("Character");
		add(lblBattler, "cell 0 0,alignx left");

		JLabel lblPose = new JLabel("Pose");
		add(lblPose, "cell 1 0");

		JPanel panel = new JPanel();
		add(panel, "cell 2 0 1 2,grow");
		panel.setLayout(new MigLayout("", "[grow]", "[grow 60][grow 40]"));

		posePanel = new PosePanel();
		posePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(posePanel, "cell 0 0,grow");

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, "cell 0 1,grow");
		panel_2.setLayout(new MigLayout("", "[60px,grow][60,grow][60,grow]", "[13px][][][][][][][]"));

		JLabel lblNameLabe = new JLabel("Max Frames");
		panel_2.add(lblNameLabe, "cell 0 0,alignx left,aligny top");

		DecimalFormat format = new DecimalFormat("0.##");
		format.setMinimumFractionDigits(0);

		JLabel lblCurrentFrame = new JLabel("Current Frame");
		panel_2.add(lblCurrentFrame, "cell 1 0");
		maxFrameTextField = new JFormattedTextField(format);
		maxFrameTextField.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				CharacterPosePanel.this.onMaxFrameChange();
			}
		});

		panel_2.add(maxFrameTextField, "cell 0 1,growx");

		sliderCurrentFrame = new JSlider();
		sliderCurrentFrame.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				CharacterPosePanel.this.onFrameSliderChange();
			}
		});
		sliderCurrentFrame.setPaintLabels(true);
		panel_2.add(sliderCurrentFrame, "cell 1 1");

		labelCurrentFrame = new JLabel("New label");
		panel_2.add(labelCurrentFrame, "cell 2 1");

		JLabel lblCharacterWidth = new JLabel("Character Width");
		panel_2.add(lblCharacterWidth, "cell 0 2");

		JLabel lblCharacterHeight = new JLabel("Character Height");
		panel_2.add(lblCharacterHeight, "cell 1 2");

		characterWidthTextField = new JFormattedTextField(format);
		characterWidthTextField.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				CharacterPosePanel.this.onCharacterWidthChange();
			}
		});
		panel_2.add(characterWidthTextField, "cell 0 3,growx");

		characterHeightTextField = new JFormattedTextField(format);
		characterHeightTextField.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				CharacterPosePanel.this.onCharacterHeightChange();
			}
		});
		panel_2.add(characterHeightTextField, "cell 1 3,growx");

		buttonLooping = new JCheckBox("Pose Looping");
		buttonLooping.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CharacterPosePanel.this.onLoopChanged();
			}
		});
		panel_2.add(buttonLooping, "cell 2 3");

		JLabel lblWeaponX = new JLabel("Weapon X");
		panel_2.add(lblWeaponX, "cell 0 4");

		JLabel lblWeaponY = new JLabel("Weapon Y");
		panel_2.add(lblWeaponY, "cell 1 4");

		JLabel lblWeaponAngle = new JLabel("Weapon Angle");
		panel_2.add(lblWeaponAngle, "cell 2 4");

		weaponXTextField = new JFormattedTextField(format);
		weaponXTextField.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				CharacterPosePanel.this.onWeaponXChange();
			}
		});
		panel_2.add(weaponXTextField, "cell 0 5,growx");

		weaponYTextField = new JFormattedTextField(format);
		weaponYTextField.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				CharacterPosePanel.this.onWeaponYChange();
			}
		});
		panel_2.add(weaponYTextField, "cell 1 5,growx");

		weaponAngleTextField = new JFormattedTextField(format);
		weaponAngleTextField.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				CharacterPosePanel.this.onWeaponAngleChange();
			}
		});
		panel_2.add(weaponAngleTextField, "cell 2 5,growx");
		
		buttonHideWeapon = new JCheckBox("Hide Weapon");
		buttonHideWeapon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CharacterPosePanel.this.onHideWeapon();
			}
		});
		
		sliderPreviewScale = new JSlider();
		panel_2.add(sliderPreviewScale, "cell 0 6");
		
		lblPreviewScale = new JLabel("Preview Scale:");
		sliderPreviewScale.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				posePanel.setScale(((double)sliderPreviewScale.getValue())/10);
				posePanel.repaint();
				lblPreviewScale.setText("Preview Scale: " + (double)sliderPreviewScale.getValue() * 10 + "%");
			}
		});
		sliderPreviewScale.setValue(10);
		panel_2.add(lblPreviewScale, "cell 1 6");
		panel_2.add(buttonHideWeapon, "cell 2 6");
		
		buttonApplyAll = new JButton("Apply to all frame");
		buttonApplyAll.setEnabled(false);
		buttonApplyAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CharacterPosePanel.this.onApplyToAll();
			}
		});
		panel_2.add(buttonApplyAll, "cell 0 7");
		
		buttonWeaponBack = new JCheckBox("Weapon Back");
		buttonWeaponBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CharacterPosePanel.this.onWeaponBack();
			}
		});
		panel_2.add(buttonWeaponBack, "cell 2 7");

		listModelCharacter = new DefaultListModel<String>();
		listCharacter = new JList<String>(listModelCharacter);
		listCharacter.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				CharacterPosePanel.this.onCharacterListSelectedChange();
			}
		});
		listCharacter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(listCharacter, "cell 0 1,grow");

		listModelPose = new DefaultListModel<String>();
		listPose = new JList<String>(listModelPose);
		listPose.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				CharacterPosePanel.this.onPoseListSelectedChange();
			}
		});
		listPose.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(listPose, "cell 1 1,grow");

		this.refreshCharacterList();
		this.refreshPoseList();
		this.refreshProperties();
	}

	private void clearProperties() {
		maxFrameTextField.setValue(0);
		labelCurrentFrame.setText("Not Selected");
		sliderCurrentFrame.setMinimum(0);
		sliderCurrentFrame.setValue(0);
		sliderCurrentFrame.setMaximum(1);
		characterWidthTextField.setValue(null);
		characterHeightTextField.setValue(null);
		weaponXTextField.setValue(null);
		weaponYTextField.setValue(null);
		weaponAngleTextField.setValue(null);
		buttonLooping.setSelected(false);
		buttonHideWeapon.setSelected(false);
		buttonWeaponBack.setSelected(false);
		maxFrameTextField.setEnabled(false);
		sliderCurrentFrame.setEnabled(false);
		characterWidthTextField.setEnabled(false);
		characterHeightTextField.setEnabled(false);
		weaponXTextField.setEnabled(false);
		weaponYTextField.setEnabled(false);
		weaponAngleTextField.setEnabled(false);
		buttonLooping.setEnabled(false);
		buttonHideWeapon.setEnabled(false);
		buttonWeaponBack.setEnabled(false);
		buttonApplyAll.setEnabled(false);
	}

	private void clearPosePanel() {
		posePanel.clear();
	}

	private void refreshCharacterList() {
		listModelCharacter.clear();
		characterIndexToName.clear();
		for (String name : EditorProperty.characterList.lists.keySet()) {
			characterIndexToName.add(name);
			listModelCharacter.addElement((EditorProperty.characterList.lists.get(name).isDirty() ? "*" : "") + name);
		}
	}

	private void updateCharacterList() {
		for (String name : EditorProperty.characterList.lists.keySet()) {
			int n = characterIndexToName.indexOf(name);
			if (n >= 0) {
				listModelCharacter.set(n, (EditorProperty.characterList.lists.get(name).isDirty() ? "*" : "") + name);
			} else {
				characterIndexToName.add(name);
				listModelCharacter.addElement(name);;
			}
		}
	}

	private void refreshPoseList() {
		listModelPose.clear();
		poseIndexToName.clear();
		if (currentCharacter != null) {
			Set<String> poses = currentCharacter.poses.keySet();
			for (String name : poses) {
				poseIndexToName.add(name);
				listModelPose.addElement((currentCharacter.poses.get(name).isDirty() ? "*" : "") + name);
			}

		}
	}

	private void updatePoseList() {
		if (currentCharacter != null) {
			for (String name : currentCharacter.poses.keySet()) {
				int n = poseIndexToName.indexOf(name);
				listModelPose.set(n, (currentCharacter.poses.get(name).isDirty() ? "*" : "") + name);
			}
		}
	}

	private void refreshProperties() {
		if (currentPose != null) {
			maxFrameTextField.setEnabled(true);
			sliderCurrentFrame.setEnabled(true);
			maxFrameTextField.setEnabled(true);
			characterWidthTextField.setEnabled(true);
			characterHeightTextField.setEnabled(true);
			weaponXTextField.setEnabled(true);
			weaponYTextField.setEnabled(true);
			weaponAngleTextField.setEnabled(true);
			buttonLooping.setEnabled(true);
			buttonHideWeapon.setEnabled(true);
			buttonWeaponBack.setEnabled(true);
			buttonApplyAll.setEnabled(true);
			maxFrameTextField.setValue(currentPose.property.frameCount);
			this.sliderCurrentFrame.setMinimum(0);
			this.sliderCurrentFrame.setMaximum(currentPose.property.frameCount - 1);
			this.sliderCurrentFrame.setValue(this.frameIndex);
			this.buttonLooping.setSelected(currentPose.property.loop);
			String lastchar = posePanel.charactername;
			String lastpose = posePanel.posename;
			posePanel.charactername = this.currentCharacter.characterName;
			posePanel.maxFrame = currentPose.property.frameCount;
			posePanel.curFrame = this.frameIndex;
			posePanel.posename = currentPose.poseName;
			if (lastchar != posePanel.charactername || lastpose != posePanel.posename) {
				posePanel.loadImage(currentPose.poseFile);
			}
			this.refreshFrameProperty();

		} else {
			this.clearPosePanel();
			this.clearProperties();
			posePanel.repaint();
		}
	}

	private void refreshFrameProperty() {
		if (currentPose != null) {
			PoseFrameProperty frame = currentPose.property.frames.get(this.frameIndex);
			labelCurrentFrame.setText("Current: " + Integer.toString(this.frameIndex+1) + "/" + currentPose.property.frameCount);
			this.characterWidthTextField.setValue(frame.width);
			this.characterHeightTextField.setValue(frame.height);
			this.weaponXTextField.setValue(frame.weaponX);
			this.weaponYTextField.setValue(frame.weaponY);
			this.weaponAngleTextField.setValue(frame.weaponAngle);
			this.buttonHideWeapon.setSelected(frame.hideWeapon);
			this.buttonWeaponBack.setSelected(frame.weaponBack);
			posePanel.curFrame = this.frameIndex;
			posePanel.rect.width = frame.width;
			posePanel.rect.height = frame.height;
			posePanel.weaponX = frame.weaponX;
			posePanel.weaponY = frame.weaponY;
			posePanel.weaponAngle = frame.weaponAngle;
			posePanel.hideWeapon = frame.hideWeapon;
			posePanel.weaponBack = frame.weaponBack;
			posePanel.repaint();
		}
	}

	private void updatePaintPanel() {
		if (currentPose != null) {
			PoseFrameProperty frame = currentPose.property.frames.get(this.frameIndex);
			posePanel.maxFrame = currentPose.property.frameCount;
			posePanel.curFrame = this.frameIndex;
			posePanel.rect.width = frame.width;
			posePanel.rect.height = frame.height;
			posePanel.weaponX = frame.weaponX;
			posePanel.weaponY = frame.weaponY;
			posePanel.weaponAngle = frame.weaponAngle;
			posePanel.hideWeapon = frame.hideWeapon;
			posePanel.weaponBack = frame.weaponBack;
			posePanel.repaint();
		}
	}

	private void onCharacterListSelectedChange() {
		if (this.currentPose != null) {
			//this.listCharacter.requestFocusInWindow();
			this.onPoseSelectedChangeSaveCurrent();
		}
		int num = listCharacter.getSelectedIndex();
		String character = characterIndexToName.get(num);
		this.currentCharacter = EditorProperty.characterList.lists.get(character);
		this.currentPose = null;
		this.frameIndex = -1;
		this.refreshPoseList();
		this.refreshProperties();
	}

	private void onPoseListSelectedChange() {
		int num = listPose.getSelectedIndex();
		if (this.currentPose != null) {
			//this.listPose.requestFocusInWindow();
			this.onPoseSelectedChangeSaveCurrent();
		}
		if (num >= 0) {
			String pose = poseIndexToName.get(num);
			this.currentPose = currentCharacter.poses.get(pose);
			this.frameIndex = 0;
		} else {
			this.currentPose = null;
			this.frameIndex = -1;
		}
		this.refreshProperties();
	}

	private void onFrameSliderChange() {
		this.frameIndex = this.sliderCurrentFrame.getValue();
		this.refreshFrameProperty();
	}

	public void setTabIndex(int n) {
		this.tabIndex = n;
	}

	private void onMaxFrameChange() {
		if (this.currentPose != null) {
			if (this.maxFrameTextField.getValue() != null) {
				int max = (((Number) this.maxFrameTextField.getValue()).intValue());
				if (max == 0) {
					this.maxFrameTextField.setValue(this.currentPose.property.frameCount);
					return;
				}
				if (max != this.currentPose.property.frameCount) {
					this.currentPose.property.setMaxFrame(max);
					if (this.frameIndex >= max) {
						this.frameIndex = 0;
						//this.refreshFrameProperty();
					}
					this.currentPose.property.setDirty();
					this.refreshProperties();
					this.updateCharacterList();
					this.updatePoseList();
					this.updatePaintPanel();
				}
			}
		}
	}

	private void onCharacterWidthChange() {
		if (this.currentPose != null) {
			if (this.characterWidthTextField.getValue() != null) {
				int nv = (((Number) this.characterWidthTextField.getValue()).intValue());
				if (nv != currentPose.property.frames.get(this.frameIndex).width) {
					currentPose.property.frames.get(this.frameIndex).width = nv;
					this.currentPose.property.setDirty();
					this.updateCharacterList();
					this.updatePoseList();
					this.updatePaintPanel();
				}
			}
		}
	}

	private void onCharacterHeightChange() {
		if (this.currentPose != null) {
			if (this.characterHeightTextField.getValue() != null) {
				int nv = (((Number) this.characterHeightTextField.getValue()).intValue());
				if (nv != currentPose.property.frames.get(this.frameIndex).height) {
					currentPose.property.frames.get(this.frameIndex).height = nv;
					this.currentPose.property.setDirty();
					this.updateCharacterList();
					this.updatePoseList();
					this.updatePaintPanel();
				}
			}
		}
	}

	private void onWeaponXChange() {
		if (this.currentPose != null) {
			if (this.weaponXTextField.getValue() != null) {
				int nv = (((Number) this.weaponXTextField.getValue()).intValue());
				if (nv != currentPose.property.frames.get(this.frameIndex).weaponX) {
					currentPose.property.frames.get(this.frameIndex).weaponX = nv;
					this.currentPose.property.setDirty();
					this.updateCharacterList();
					this.updatePoseList();
					this.updatePaintPanel();
				}
			}
		}
	}

	private void onWeaponYChange() {
		if (this.currentPose != null) {
			if (this.weaponYTextField.getValue() != null) {
				int nv = (((Number) this.weaponYTextField.getValue()).intValue());
				if (nv != currentPose.property.frames.get(this.frameIndex).weaponY) {
					currentPose.property.frames.get(this.frameIndex).weaponY = nv;
					this.currentPose.property.setDirty();
					this.updateCharacterList();
					this.updatePoseList();
					this.updatePaintPanel();
				}
			}
		}
	}

	private void onWeaponAngleChange() {
		if (this.currentPose != null) {
			if (this.weaponAngleTextField.getValue() != null) {
				int nv = (((Number) this.weaponAngleTextField.getValue()).intValue());
				if (nv != currentPose.property.frames.get(this.frameIndex).weaponAngle) {
					currentPose.property.frames.get(this.frameIndex).weaponAngle = nv;
					this.currentPose.property.setDirty();
					this.updateCharacterList();
					this.updatePoseList();
					this.updatePaintPanel();
				}
			}
		}
	}
	
	private void onHideWeapon() {
		if (this.currentPose != null) {
			if (this.currentPose.property.frames.get(this.frameIndex).hideWeapon != this.buttonHideWeapon.isSelected()) {
				this.currentPose.property.frames.get(this.frameIndex).hideWeapon = this.buttonHideWeapon.isSelected();
				this.currentPose.property.setDirty();
				this.updateCharacterList();
				this.updatePoseList();
				this.updatePaintPanel();
			}
		}
	}

	protected void onWeaponBack() {
		if (this.currentPose != null) {
			if (this.currentPose.property.frames.get(this.frameIndex).weaponBack != this.buttonWeaponBack.isSelected()) {
				this.currentPose.property.frames.get(this.frameIndex).weaponBack = this.buttonWeaponBack.isSelected();
				this.currentPose.property.setDirty();
				this.updateCharacterList();
				this.updatePoseList();
				this.updatePaintPanel();
			}
		}
	}
	
	protected void onLoopChanged() {
		if (this.currentPose != null) {
			if (this.currentPose.property.loop != this.buttonLooping.isSelected()) {
				this.currentPose.property.loop = this.buttonLooping.isSelected();
				this.currentPose.property.setDirty();
				this.updateCharacterList();
				this.updatePoseList();
			}
		}
	}

	private void onPoseSelectedChangeSaveCurrent() {
		int old = this.frameIndex;
		try {
			this.maxFrameTextField.commitEdit();
			if (old == this.frameIndex) {
				this.characterHeightTextField.commitEdit();
				this.characterWidthTextField.commitEdit();
				this.weaponAngleTextField.commitEdit();
				this.weaponXTextField.commitEdit();
				this.weaponYTextField.commitEdit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void onApplyToAll() {
		if (this.currentPose != null){
			PoseFrameProperty prop = this.currentPose.property.frames.get(this.frameIndex);
			for (PoseFrameProperty p : this.currentPose.property.frames) {
				p.overwrite(prop);
			}
		}
	}
	
	@Override
	public void refresh() {
		this.updateCharacterList();
		this.updatePoseList();
	}
}
