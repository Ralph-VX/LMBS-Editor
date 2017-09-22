package kien.lmbseditor.window.motion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import net.arnx.jsonic.JSON;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class MotionPropertyDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private PropertyTable table;
	private LinkedHashMap<String, String> propertyList;
	private LinkedHashMap<String, Object> data;

	static final private String testJSON = "{\r\n" + "    \"prop1\" : \"string\",\r\n"
			+ "    \"prop2\" : \"integer\",\r\n" + "    \"prop3\" : \"text\"\r\n" + "}";

	static public void main(String[] args) {
		LinkedHashMap<String, Object> testData = new LinkedHashMap<String, Object>();
		testData.put("prop1", "string");
		testData.put("prop2", 10);
		testData.put("prop3", "this is what we can do to edit some scripts/nwith plenty of areas to screw up.");
		System.out.println(JSON.decode(testJSON).toString());
		MotionPropertyDialog diag = new MotionPropertyDialog(JSON.decode(testJSON), testData);
		diag.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		diag.setVisible(true);
		System.out.println(diag.data);
	}

	/**
	 * Create the dialog.
	 */
	public MotionPropertyDialog(LinkedHashMap<String, String> property, LinkedHashMap<String, Object> data) {
		propertyList = property;
		this.data = data;
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
				table = new PropertyTable();
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
		this.onCancel();
	};

	public void onCancel() {
		this.dispose();
	}

	public LinkedHashMap<String, Object> getData() {
		return data;
	}
	
	public class PropertyTable extends JTable {

		private static final long serialVersionUID = 1L;
		private LinkedHashMap<String, String> propertyList;
		private LinkedHashMap<String, Object> data;
		private DefaultTableColumnModel cm;
		private DefaultTableModel tm;

		@SuppressWarnings("unchecked")
		public PropertyTable() {
			this.propertyList = MotionPropertyDialog.this.propertyList;
			this.data = MotionPropertyDialog.this.data;
			this.setFillsViewportHeight(true);
			this.getTableHeader().setReorderingAllowed(false);
			cm = (DefaultTableColumnModel) this.getColumnModel();
			cm.addColumn(new TableColumn(0));
			cm.addColumn(new TableColumn(1));
			cm.getColumn(0).setHeaderValue("Name");
			cm.getColumn(0).setMaxWidth(75);
			cm.getColumn(0).setResizable(false);
			cm.getColumn(1).setHeaderValue("Value");
			cm.getColumn(1).setResizable(false);
			tm = (DefaultTableModel) this.getModel();
			Set<String> propNameList = propertyList.keySet();
			int row = 0;
			for (String name : propNameList) {
				Vector<Object> vec = tm.getDataVector();
				Vector<Object> vec2 = new Vector<Object>();
				vec2.add(name);
				vec2.add(data.get(name));
				vec.add(vec2);
				if (propertyList.get(name).equals("text")) {
					this.setRowHeight(row,120);
				}
				row++;
			}
			this.revalidate();
			System.out.println(tm.getDataVector());
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			if (column == 0) {
				return false;
			}
			return true;
		}

		@Override
		public TableCellRenderer getCellRenderer(int row, int column) {
			if (column == 0) {
				return super.getCellRenderer(row, column);
			} else {
				String name = (String) this.getValueAt(row, 0);
				String type = this.propertyList.get(name);
				if (type == null) {
					return super.getCellRenderer(row, column);
				} else if (type.equals("text")) {
					return new TextAreaRenderer();
				} else if (type.equals("boolean")) {
					return this.getDefaultRenderer(Boolean.class);
				} else {
					return super.getCellRenderer(row, column);
				}
			}
		}

		@Override
		public TableCellEditor getCellEditor(int row, int column) {
			if (column == 0) {
				return super.getCellEditor(row, column);
			} else {
				String name = (String) this.getValueAt(row, 0);
				String type = this.propertyList.get(name);
				if (type == null) {
					return super.getCellEditor(row, column);
				} else if (type.equals("text")) {
					return new TextAreaEditor();
				} else if (type.equals("boolean")) {
					return this.getDefaultEditor(Boolean.class);
				} else if (type.equals("float") || type.equals("integer")) {
					return new FormattedTextFieldEditor(type);
				} else {
					return super.getCellEditor(row, column);
				}
			}
		}
		
		@SuppressWarnings("unchecked")
		public LinkedHashMap<String, Object> getTableContents() {
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			Vector<Vector<Object>> table = tm.getDataVector();
			for (Vector<Object> row : table) {
				map.put((String) row.get(0), row.get(1));
			}
			return map;
		}
		
		// TextAreaRenderer from: http://esus.com/embedding-a-jtextarea-in-a-jtable-cell/
		class TextAreaRenderer extends JScrollPane implements TableCellRenderer {
			private static final long serialVersionUID = 1L;
			JTextArea textarea;

			public TextAreaRenderer() {
				textarea = new JTextArea();
				textarea.setLineWrap(true);
				textarea.setWrapStyleWord(true);
				//textarea.setBorder(new TitledBorder("This is a JTextArea"));
				getViewport().add(textarea);
			}

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (isSelected) {
					setForeground(table.getSelectionForeground());
					setBackground(table.getSelectionBackground());
					textarea.setForeground(table.getSelectionForeground());
					textarea.setBackground(table.getSelectionBackground());
				} else {
					setForeground(table.getForeground());
					setBackground(table.getBackground());
					textarea.setForeground(table.getForeground());
					textarea.setBackground(table.getBackground());
				}

				textarea.setText((String) value);
				textarea.setCaretPosition(0);
				return this;
			}
		}

		// TextAreaEditor from: http://esus.com/embedding-a-jtextarea-in-a-jtable-cell/
		class TextAreaEditor extends DefaultCellEditor {
			private static final long serialVersionUID = 1L;
			protected JScrollPane scrollpane;
			protected JTextArea textarea;

			public TextAreaEditor() {
				super(new JCheckBox());
				scrollpane = new JScrollPane();
				textarea = new JTextArea();
				textarea.setLineWrap(true);
				textarea.setWrapStyleWord(true);
				// textarea.setBorder(new TitledBorder("This is a JTextArea"));
				scrollpane.getViewport().add(textarea);
			}

			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				textarea.setText((String) value);

				return scrollpane;
			}

			public Object getCellEditorValue() {
				return textarea.getText();
			}
		}
		
		class FormattedTextFieldEditor extends DefaultCellEditor {
			
			private static final long serialVersionUID = 1L;
			protected JFormattedTextField jftf;
			
			public FormattedTextFieldEditor(String type) {
				super(new JCheckBox());
				if (type.equals("integer")) {
					DecimalFormat format = new DecimalFormat("##0");
					format.setMinimumFractionDigits(0);
					jftf = new JFormattedTextField(format);
					jftf.setColumns(10);
				} else if (type.equals("float")) {
					DecimalFormat format = new DecimalFormat("##0.###");
					format.setMinimumFractionDigits(0);
					jftf = new JFormattedTextField(format);
					jftf.setColumns(10);
				}
			}
			
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				jftf.setValue(value);

				return jftf;
			}
			
			public Object getCellEditorValue() {
				try {
					jftf.commitEdit();
				} catch (ParseException e) {
					
				}
				return jftf.getValue();
			}
		}

	}
}
