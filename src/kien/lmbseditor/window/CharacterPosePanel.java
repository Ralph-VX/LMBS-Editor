package kien.lmbseditor.window;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListModel;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;

import kien.lmbseditor.core.CharacterPose;
import kien.lmbseditor.core.CharacterSet;
import kien.lmbseditor.core.EditorProperty;
import kien.lmbseditor.core.PoseFrameProperty;
import kien.lmbseditor.core.PoseProperty;
import kien.util.Util;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class CharacterPosePanel extends JPanel {
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
		panel_2.setLayout(new MigLayout("", "[60px,grow][60,grow][60,grow]", "[13px][][][][][]"));
		
		JLabel lblNameLabe = new JLabel("Max Frames");
		panel_2.add(lblNameLabe, "cell 0 0,alignx left,aligny top");

		DecimalFormat format = new DecimalFormat("0");
		
		JLabel lblCurrentFrame = new JLabel("Current Frame");
		panel_2.add(lblCurrentFrame, "cell 1 0");
		maxFrameTextField = new JFormattedTextField(format);
		
		panel_2.add(maxFrameTextField, "cell 0 1,growx");
		
		sliderCurrentFrame = new JSlider();
		sliderCurrentFrame.setPaintLabels(true);
		panel_2.add(sliderCurrentFrame, "cell 1 1");
		
		labelCurrentFrame = new JLabel("New label");
		panel_2.add(labelCurrentFrame, "cell 2 1");
		
		JLabel lblCharacterWidth = new JLabel("Character Width");
		panel_2.add(lblCharacterWidth, "cell 0 2");
		
		JLabel lblCharacterHeight = new JLabel("Character Height");
		panel_2.add(lblCharacterHeight, "cell 1 2");
		
		characterWidthTextField = new JFormattedTextField(format);
		panel_2.add(characterWidthTextField, "cell 0 3,growx");
		
		characterHeightTextField = new JFormattedTextField(format);
		panel_2.add(characterHeightTextField, "cell 1 3,growx");
		
		buttonLooping = new JCheckBox("Pose Looping");
		panel_2.add(buttonLooping, "cell 2 3");
		
		JLabel lblWeaponX = new JLabel("Weapon X");
		panel_2.add(lblWeaponX, "cell 0 4");
		
		JLabel lblWeaponY = new JLabel("Weapon Y");
		panel_2.add(lblWeaponY, "cell 1 4");
		
		JLabel lblWeaponAngle = new JLabel("Weapon Angle");
		panel_2.add(lblWeaponAngle, "cell 2 4");
		
		weaponXTextField = new JFormattedTextField(format);
		panel_2.add(weaponXTextField, "cell 0 5,growx");
		
		weaponYTextField = new JFormattedTextField(format);
		panel_2.add(weaponYTextField, "cell 1 5,growx");
		
		weaponAngleTextField = new JFormattedTextField(format);
		panel_2.add(weaponAngleTextField, "cell 2 5,growx");
		
		listModelCharacter = new DefaultListModel<String>();
		listCharacter = new JList<String>(listModelCharacter);
		listCharacter.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				CharacterPosePanel.this.onCharacterListSelectedChange();
			}
		});
		listCharacter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(listCharacter, "cell 0 1,grow");
		
		listModelPose = new DefaultListModel<String>();
		listPose = new JList<String>(listModelPose);
		listPose.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				CharacterPosePanel.this.onPoseListSectedChange();
			}
		});
		listPose.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(listPose, "cell 1 1,grow");
		
		this.refreshCharacterList();
		this.refreshPoseList();
		this.refreshProperties();
	}
	
	
	private void clearProperties() {
		labelCurrentFrame.setText("Not Selected");
		sliderCurrentFrame.setMinimum(0);
		sliderCurrentFrame.setValue(0);
		sliderCurrentFrame.setMaximum(1);
		characterWidthTextField.setText("");
		characterHeightTextField.setText("");
		weaponXTextField.setText("");
		weaponYTextField.setText("");
		weaponAngleTextField.setText("");
		buttonLooping.setSelected(false);
		sliderCurrentFrame.setEnabled(false);
		characterWidthTextField.setEnabled(false);
		characterHeightTextField.setEnabled(false);
		weaponXTextField.setEnabled(false);
		weaponYTextField.setEnabled(false);
		weaponAngleTextField.setEnabled(false);
		buttonLooping.setEnabled(false);
	}
	
	private void clearPosePanel() {
		posePanel.clear();
	}
	
	private void refreshCharacterList() {
		listModelCharacter.clear();
		characterIndexToName.clear();
		for (String name : EditorProperty.characterList.lists.keySet()){
			characterIndexToName.add(name);
			listModelCharacter.addElement((EditorProperty.characterList.lists.get(name).isDirty() ? "*" : "") + name);
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

	private void refreshProperties() {
		if (currentPose != null) {
			sliderCurrentFrame.setEnabled(true);
			characterWidthTextField.setEnabled(true);
			characterHeightTextField.setEnabled(true);
			weaponXTextField.setEnabled(true);
			weaponYTextField.setEnabled(true);
			weaponAngleTextField.setEnabled(true);
			buttonLooping.setEnabled(true);
			this.sliderCurrentFrame.setMinimum(0);
			this.sliderCurrentFrame.setMaximum(currentPose.property.frameCount);
			this.sliderCurrentFrame.setValue(this.frameIndex);
			labelCurrentFrame.setText("Current: " + Integer.toString(this.frameIndex));
			this.buttonLooping.setSelected(currentPose.property.loop);
			PoseFrameProperty frame = currentPose.property.frames.get(this.frameIndex);
			this.characterWidthTextField.setText(Integer.toString(frame.width));
			this.characterHeightTextField.setText(Integer.toString(frame.height));
			this.weaponXTextField.setText(Integer.toString(frame.weaponX));
			this.weaponYTextField.setText(Integer.toString(frame.weaponY));
			this.weaponAngleTextField.setText(Integer.toString(frame.weaponAngle));
			String lastchar = posePanel.charactername;
			String lastpose = posePanel.posename;
			posePanel.charactername = this.currentCharacter.characterName;
			posePanel.maxFrame = currentPose.property.frameCount;
			posePanel.curFrame = this.frameIndex;
			posePanel.posename = currentPose.poseName;
			if (lastchar != posePanel.charactername || lastpose != posePanel.posename){
				posePanel.loadImage(currentPose.poseFile);
			}
			
			posePanel.repaint();
			
		} else {
			this.clearPosePanel();
			this.clearProperties();
			posePanel.repaint();
		}
	}
	
	private void onCharacterListSelectedChange() {
		int num = listCharacter.getSelectedIndex();
		String character = characterIndexToName.get(num);
		this.currentCharacter = EditorProperty.characterList.lists.get(character);
		this.currentPose = null;
		this.frameIndex = -1;
		this.refreshPoseList();
		this.refreshProperties();
	}
	
	private void onPoseListSectedChange() {
		int num = listPose.getSelectedIndex();
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
	
	public void setTabIndex(int n) {
		this.tabIndex = n;
	}
}
