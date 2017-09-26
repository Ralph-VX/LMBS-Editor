package kien.lmbseditor.window.animation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kien.lmbseditor.window.JSONPropertyTable;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import net.miginfocom.swing.MigLayout;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class AnimationTimingDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private LinkedHashMap<String, LinkedHashMap<String, Object>> datas;
	private final ButtonGroup buttonGroupTimingType = new ButtonGroup();
	private JSONPropertyTable propertyTable;

	/**
	 * Create the dialog.
	 */
	public AnimationTimingDialog() {
		datas.put("damage", new LinkedHashMap<String, Object>());
		datas.put("projectile", new LinkedHashMap<String, Object>());
		setModal(true);
		setBounds(100, 100, 450, 403);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[33%,grow][33%,grow][33%,grow]", "[][grow]"));
		{
			JRadioButton rdbtnNewRadioButton = new JRadioButton("Damage");
			buttonGroupTimingType.add(rdbtnNewRadioButton);
			contentPanel.add(rdbtnNewRadioButton, "cell 0 0");
		}
		{
			JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Projectile");
			buttonGroupTimingType.add(rdbtnNewRadioButton_1);
			contentPanel.add(rdbtnNewRadioButton_1, "cell 1 0");
		}
		{
			propertyTable = new JSONPropertyTable();
			contentPanel.add(propertyTable, "cell 0 1 3 1,grow");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void setData(String name, LinkedHashMap<String, Object> data) {
		this.datas.put(name, data);
		this.checkButton(name);
		this.refreshTableContent();
	}
	
	private void refreshTableContent() {
		String name = this.getSelectedButtonName();
		if (name != null) {
			try {
				this.propertyTable.setData(JSON.decode(this.getClass().getResourceAsStream("animationTiming" + name + ".json")), datas.get(name));
			} catch (JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void checkButton(String name) {
        for (Enumeration<AbstractButton> buttons = buttonGroupTimingType.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            button.setSelected(button.getText().equals(name));
        }
	}
	
	private String getSelectedButtonName() {
        for (Enumeration<AbstractButton> buttons = buttonGroupTimingType.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText().toLowerCase();
            }
        }
        return null;
	}
}
