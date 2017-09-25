package kien.lmbseditor.window.skillmotion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;

public class MotionPropertyDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JSONPropertyTable table;
	private LinkedHashMap<String, Object> propertyList;
	private LinkedHashMap<String, Object> data;
	private boolean dirty;

	/**
	 * Create the dialog.
	 */
	public MotionPropertyDialog(LinkedHashMap<String, Object> property, LinkedHashMap<String, Object> data) {
		propertyList = property;
		this.data = data;
		this.dirty = false;
		setModal(true);
		setBounds(100, 100, 500, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JSONPropertyTable(this.propertyList,this.data);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("OK")) {
			this.onOk();
		} else {
			this.onCancel();
		}
	}

	public void onOk() {
		this.data = this.table.getTableContents();
		this.setDirty();
		this.onCancel();
	};

	public void onCancel() {
		this.dispose();
	}

	public LinkedHashMap<String, Object> getData() {
		return data;
	}

	public boolean isDirty() {
		return dirty;
	}
	
	public void setDirty() {
		this.dirty = true;
	}
	
	public void clearDirty() {
		this.dirty = false;
	}

	
}
