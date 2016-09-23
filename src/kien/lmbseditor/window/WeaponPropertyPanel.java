package kien.lmbseditor.window;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JFormattedTextField;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kien.lmbseditor.core.EditorProperty;
import kien.lmbseditor.core.WeaponSet;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class WeaponPropertyPanel extends EditorPanelBase {
	
	private WeaponSet current;
	private DefaultListModel<String> listModelWeapon;
	private JList<String> listWeapon;
	private JFormattedTextField xField;
	private JFormattedTextField yField;
	private JFormattedTextField angleField;
	private WeaponImagePanel weaponImagePanel;
	private int setTabIndex;
	private ArrayList<String> indexToName;
	private JCheckBox chckbxNewCheckBox;
	
	/**
	 * Create the panel.
	 */
	public WeaponPropertyPanel() {
		setLayout(new MigLayout("", "[grow 40][grow 80]", "[grow]"));
		
		indexToName = new ArrayList<String>();
		listModelWeapon = new DefaultListModel<String>();
		listWeapon = new JList<String>(listModelWeapon);
		listWeapon.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				WeaponPropertyPanel.this.onWeaponListSelectedChange();
			}
		});
		listWeapon.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(listWeapon, "cell 0 0,grow");
		
		JPanel panel = new JPanel();
		add(panel, "cell 1 0,grow");
		panel.setLayout(new MigLayout("", "[grow]", "[grow 60][grow 30]"));
		
		weaponImagePanel = new WeaponImagePanel();
		panel.add(weaponImagePanel, "cell 0 0,grow");
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 0 1,grow");
		panel_1.setLayout(new MigLayout("", "[grow]", "[][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Weapon Origin x");
		lblNewLabel.setToolTipText("The coordinate from center of the image representing the point where character will hold this weapon");
		panel_1.add(lblNewLabel, "cell 0 0");
		
		DecimalFormat format = new DecimalFormat("##0");
		format.setMinimumFractionDigits(0);
		
		xField = new JFormattedTextField(format);
		xField.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				WeaponPropertyPanel.this.onXFieldChange();
			}
			
		});
		xField.setToolTipText("The coordinate from center of the image representing the point where character will hold this weapon");
		panel_1.add(xField, "cell 0 1,growx");
		
		JLabel lblNewLabel_1 = new JLabel("Weapon Origin y");
		lblNewLabel_1.setToolTipText("The coordinate from center of the image representing the point where character will hold this weapon");
		panel_1.add(lblNewLabel_1, "cell 0 2");
		
		yField = new JFormattedTextField(format);
		yField.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				WeaponPropertyPanel.this.onYFieldChange();
			}
			
		});
		yField.setToolTipText("The coordinate from center of the image representing the point where character will hold this weapon");
		panel_1.add(yField, "cell 0 3,growx");
		
		JLabel lblNewLabel_2 = new JLabel("Weapon default angle");
		lblNewLabel_2.setToolTipText("The angle need to rotate the weapon facing right horizontally");
		panel_1.add(lblNewLabel_2, "cell 0 4");
		
		angleField = new JFormattedTextField(format);
		angleField.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				WeaponPropertyPanel.this.onAngleFieldChange();
			}
			
		});
		angleField.setToolTipText("The angle need to rotate the weapon facing right horizontally");
		panel_1.add(angleField, "cell 0 5,growx");
		
		chckbxNewCheckBox = new JCheckBox("Show Ovarlay");
		chckbxNewCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				weaponImagePanel.showOverlay = ((JCheckBox)e.getSource()).isSelected();
				weaponImagePanel.repaint();
			}
		});
		chckbxNewCheckBox.setSelected(true);
		
		panel_1.add(chckbxNewCheckBox, "cell 0 6");
		
		this.refreshList();
		this.refreshProperty();
	}
	
	protected void onWeaponListSelectedChange() {
		this.refreshProperty();
	}

	private void refreshList() {
		String current = null;
		if (!indexToName.isEmpty()) {
			current = indexToName.get(listWeapon.getSelectedIndex());
		}
		listModelWeapon.clear();
		indexToName.clear();
		if (EditorProperty.weaponList != null) {
			for (String i : EditorProperty.weaponList.list.keySet()) {
				listModelWeapon.addElement((EditorProperty.weaponList.list.get(i).isDirty() ? "*" : "") + i);
				indexToName.add(i);
			}
			if (current != null) {
				listWeapon.setSelectedValue(current, true);
			}
		}
	}
	
	private void refreshProperty() {
		
		WeaponSet newItem;
		try {
			newItem = EditorProperty.weaponList.list.get(indexToName.get(listWeapon.getSelectedIndex()));
		} catch (Exception e) {
			newItem = null;
		}
		if (newItem != current) {
			updateValue();
			current = newItem;
			if (current != null) {
				xField.setValue(current.json.ox);
				yField.setValue(current.json.oy);
				angleField.setValue(current.json.angle);
				try {
					weaponImagePanel.img = ImageIO.read(current.imageFile);
					weaponImagePanel.angle = current.json.angle;
					weaponImagePanel.ox = current.json.ox;
					weaponImagePanel.oy = current.json.oy;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				weaponImagePanel.img = null;
				xField.setValue(null);
				yField.setValue(null);
				angleField.setValue(null);
			}
		}
	}
	
	private void updateValue() {
		if (this.current != null) {
			try {
				xField.commitEdit();
				yField.commitEdit();
				angleField.commitEdit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void onXFieldChange() {
		if (this.current != null) {
			int n = ((Number)xField.getValue()).intValue();
			if (n != current.json.ox) {
				current.json.ox = n;
				current.setDirty();
				weaponImagePanel.ox = n;
				weaponImagePanel.repaint();
			}
		}
	}
	
	private void onYFieldChange() {
		if (this.current != null) {
			int n = ((Number)yField.getValue()).intValue();
			if (n != current.json.oy) {
				current.json.oy = n;
				current.setDirty();
				weaponImagePanel.oy = n;
				weaponImagePanel.repaint();
			}
		}
	}
	
	private void onAngleFieldChange() {
		if (this.current != null) {
			int n = ((Number)angleField.getValue()).intValue();
			if (n != current.json.angle) {
				current.json.angle = n;
				current.setDirty();
				weaponImagePanel.angle = n;
				weaponImagePanel.repaint();
			}
		}
	}

	public void setTabIndex(int n2) {
		this.setTabIndex = n2;
	}

	@Override
	public void refresh() {
		this.refreshList();
	}
	
}
