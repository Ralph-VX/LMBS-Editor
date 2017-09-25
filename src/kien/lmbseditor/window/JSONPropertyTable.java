package kien.lmbseditor.window.skillmotion;

import java.awt.Component;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;


public class JSONPropertyTable extends JTable {

	private static final long serialVersionUID = 1L;
	private LinkedHashMap<String, Object> propertyList;
	private LinkedHashMap<String, Object> data;
	private DefaultTableColumnModel cm;
	private DefaultTableModel tm;

	@SuppressWarnings("unchecked")
	public JSONPropertyTable(LinkedHashMap<String, Object> property, LinkedHashMap<String, Object> data) {
		this.propertyList = property;
		this.data = data;
		this.setFillsViewportHeight(true);
		this.getTableHeader().setReorderingAllowed(false);
		tm = (DefaultTableModel) this.getModel();
		tm.setColumnCount(2);
		Set<String> propNameList = propertyList.keySet();
		int row = 0;
		for (String name : propNameList) {
			Vector<Object> vec = tm.getDataVector();
			Vector<Object> vec2 = new Vector<Object>();
			vec2.add(name);
			vec2.add((Object)data.get(name));
			tm.addRow(vec2);
			if (getPropertyType(name).equals("text")) {
				this.setRowHeight(row, 120);
			}
			row++;
		}
		cm = (DefaultTableColumnModel) this.getColumnModel();
		cm.getColumn(0).setHeaderValue("Name");
		cm.getColumn(0).setMaxWidth(75);
		cm.getColumn(0).setResizable(false);
		cm.getColumn(1).setHeaderValue("Value");
		cm.getColumn(1).setResizable(false);
		this.revalidate();
	}

	@SuppressWarnings("unchecked")
	private String getPropertyType(String name) {
		try {
			Object obj = propertyList.get(name);
			if (obj instanceof String) {
				return ((String)obj).toLowerCase();
			} else if (obj instanceof LinkedHashMap) {
				LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)obj;
				if (map.get("type") != null) {
					return ((String) map.get("type")).toLowerCase();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
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
			String type = getPropertyType(name);
			System.out.println(type);
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
			String type = getPropertyType(name);
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

	// TextAreaRenderer from:
	// http://esus.com/embedding-a-jtextarea-in-a-jtable-cell/
	class TextAreaRenderer extends JScrollPane implements TableCellRenderer {
		private static final long serialVersionUID = 1L;
		JTextArea textarea;

		public TextAreaRenderer() {
			textarea = new JTextArea();
			textarea.setLineWrap(true);
			textarea.setWrapStyleWord(true);
			// textarea.setBorder(new TitledBorder("This is a JTextArea"));
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