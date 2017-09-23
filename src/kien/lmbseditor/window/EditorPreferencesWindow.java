package kien.lmbseditor.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import kien.lmbseditor.core.EditorProperty;
import kien.util.KienLogger;
import net.miginfocom.swing.MigLayout;
import javax.swing.JCheckBox;

public class EditorPreferencesWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1765234494439106077L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JCheckBox useMinifiedJsonCheckBox;

	/**
	 * Create the dialog.
	 */
	public EditorPreferencesWindow() {
		setModal(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Preferences");
		setBounds(new Rectangle(0, 0, 450, 200));
		setResizable(false);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel
				.setLayout(new MigLayout("", "[70px][70px][30px][70px][30px][70px][75px]", "[1px][21px][1px][21px]"));
		{
			JLabel lblNewLabel = new JLabel("Project Directory");
			contentPanel.add(lblNewLabel, "cell 0 0,alignx left,aligny bottom");
		}
		{
			textField = new JTextField();
			textField.setText(EditorProperty.projectDirectory);
			textField.setEditable(false);
			contentPanel.add(textField, "cell 0 1 6 1,growx,aligny center");
			textField.setColumns(10);
		}
		{
			JButton btnNewButton = new JButton("...");
			btnNewButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					EditorPreferencesWindow.this.onDirectorySetting();
				}
			});
			contentPanel.add(btnNewButton, "cell 6 1,growx,aligny top");
		}
		{
			useMinifiedJsonCheckBox = new JCheckBox("Use Minified Json");
			useMinifiedJsonCheckBox.setToolTipText("When checked, saved json files will be minified.");
			useMinifiedJsonCheckBox.setSelected(EditorProperty.useMinifiedJson);
			contentPanel.add(useMinifiedJsonCheckBox, "cell 0 3");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");

				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						EditorPreferencesWindow.this.onOk();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						EditorPreferencesWindow.this.onCancel();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void onDirectorySetting() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int ret = jfc.showOpenDialog(this);
		if (ret == -1) {
			KienLogger.logger.severe("Error had occured while choosing directory");
			System.exit(0);
		} else if (ret == 1) {
			return;
		} else {
			File f = jfc.getSelectedFile();
			if (f.exists() && f.isDirectory()) {
				File project = new File(f.getAbsolutePath() + "\\Game.rpgproject");
				if (project.exists()) {
					textField.setText(f.getAbsolutePath());
					return;
				}
			}
			JOptionPane.showConfirmDialog(this, "Not a valid RPG Maker MV project directory.", "Error",
					JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}

	private void onOk() {
		if (!EditorPreferencesWindow.this.textField.getText().equals(EditorProperty.projectDirectory)) {
			EditorProperty.setProjectDirectory(this.textField.getText());
		}
		EditorProperty.useMinifiedJson = this.useMinifiedJsonCheckBox.isSelected();
		this.onCancel();
	}

	private void onCancel() {
		this.setVisible(false);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

}
