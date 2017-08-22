package kien.lmbseditor.window.skillmotion;

import java.awt.KeyboardFocusManager;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.TransferHandler;
import javax.swing.border.BevelBorder;

import kien.lmbseditor.core.SkillMotionCommands;
import kien.lmbseditor.core.SkillMotionItemType;
import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import kien.lmbseditor.window.EditorPanelBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import net.arnx.jsonic.JSON;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;

@SuppressWarnings("unused")
public class SkillMotionDescriptionPanel extends EditorPanelBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<SkillMotionCommandBase> commandsInListOrder;
	private JList<SkillMotionCommandBase> listCommand;
	private JComboBox<String> listMotionCommand;
	private DefaultComboBoxModel<String> listModelMotionCommand;
	private DefaultListModel<SkillMotionCommandBase> listModelCommand;
	private SkillMotionItemType contents;
	private SkillMotionTransferHandler smth; 
	
	private int tabIndex;
	
	/**
	 * Create the panel.
	 */
	public SkillMotionDescriptionPanel(SkillMotionItemType source) {
		commandsInListOrder = new ArrayList<SkillMotionCommandBase>();
		contents = source;
		setLayout(new MigLayout("", "[growprio 80,grow 80][growprio 5,grow 5]", "[grow]"));
		
		smth = new SkillMotionTransferHandler();
		listModelCommand = new DefaultListModel<SkillMotionCommandBase>();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		add(scrollPane, "cell 0 0,grow");
		listCommand = new JList<SkillMotionCommandBase>(listModelCommand);
		scrollPane.setViewportView(listCommand);
		listCommand.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listCommand.setCellRenderer(new SkillMotionCommandCellRenderer());
		listCommand.setTransferHandler(smth);
		
		ActionMap map = listCommand.getActionMap();
		map.put(SkillMotionTransferHandler.getCutAction().getValue(Action.NAME),
				SkillMotionTransferHandler.getCutAction());
		map.put(SkillMotionTransferHandler.getCopyAction().getValue(Action.NAME),
				SkillMotionTransferHandler.getCopyAction());
		map.put(SkillMotionTransferHandler.getPasteAction().getValue(Action.NAME),
				SkillMotionTransferHandler.getPasteAction());
		
		InputMap imap = listCommand.getInputMap(JComponent.WHEN_FOCUSED);
		imap.put(KeyStroke.getKeyStroke("ctrl X"), 
				SkillMotionTransferHandler.getCutAction().getValue(Action.NAME));
		imap.put(KeyStroke.getKeyStroke("ctrl C"), 
				SkillMotionTransferHandler.getCopyAction().getValue(Action.NAME));
		imap.put(KeyStroke.getKeyStroke("ctrl V"), 
				SkillMotionTransferHandler.getPasteAction().getValue(Action.NAME));
		
		JPanel panel = new JPanel();
		add(panel, "cell 1 0,grow");
		panel.setLayout(new MigLayout("", "[grow]", "[28px][28px][28px][]"));
		
		listModelMotionCommand = new DefaultComboBoxModel<String>();
		listMotionCommand = new JComboBox<String>(listModelMotionCommand);
		panel.add(listMotionCommand, "cell 0 0,growx");
		
		JButton btnNewButton_1 = new JButton("Add Command");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SkillMotionDescriptionPanel.this.onAddCommand();
			}
		});
		panel.add(btnNewButton_1, "cell 0 1,growx");
		
		JButton btnNewButton_2 = new JButton("Edit Command");
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SkillMotionDescriptionPanel.this.onEditCommand();
			}
		});
		panel.add(btnNewButton_2, "cell 0 2,growx");
		
		JButton btnNewButton = new JButton("Delete Command");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SkillMotionDescriptionPanel.this.onDeleteCommand();
			}
		});
		panel.add(btnNewButton, "cell 0 3,growx");
		
		refreshAvailableCommand();
		initializeCommandList();
	}
	
	public void initializeCommandList() {
		int i = listCommand.getSelectedIndex();
		commandsInListOrder.clear();
		for (SkillMotionCommandBase obj : contents.list) {
			obj.addList(commandsInListOrder);
		}
		refreshCommandList();
		if (i >= 0) {
			listCommand.setSelectedIndex(i);
		}
	}
	
	public void refreshCommandList() {
		int i = listCommand.getSelectedIndex();
		listModelCommand.clear();
		for (SkillMotionCommandBase obj : commandsInListOrder) {
			listModelCommand.addElement(obj);
		}
		if (i >= 0) {
			listCommand.setSelectedIndex(i);
		}
	}

	public void refreshContents() {
		
	}
	
	public void refreshAvailableCommand() {
		for (String commandName : SkillMotionCommands.motionNameToType.keySet()) { 
			listModelMotionCommand.addElement(commandName);
		}
	}
	
	public void onAddCommand() {
		int index = listCommand.getSelectedIndex();
		if (index == -1) {
			index = commandsInListOrder.size()-1;
		}
		try {
			SkillMotionCommandBase selected = commandsInListOrder.get(index);
			SkillMotionCommandBase obj = SkillMotionCommands.motionTypeToClass.get(SkillMotionCommands.motionNameToType.get(listMotionCommand.getItemAt(listMotionCommand.getSelectedIndex()))).newInstance();
			MotionPropertyDialogBase d = obj.obtainDialog();
			if (d != null) {
				d.setObject(obj);
				d.clearDirty();
				d.setVisible(true);
				if (d.isDirty()) {
					obj.updateProperty(d);
					obj.setDirty();
				} else {
					return;
				}
			}
			obj.setDepth(selected.getDepth());
			if (selected.getParent() != null) {
				selected.getParent().addChild(selected.getParent().getChildIndex(selected), obj);
			} else {
				this.contents.list.add(this.contents.list.indexOf(selected), obj);
			}
			this.initializeCommandList();
			int i = listModelCommand.indexOf(obj);
			listCommand.setSelectedIndex(index+1);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void onEditCommand() {
		int index = listCommand.getSelectedIndex();
		if (index >= 0) {
			SkillMotionCommandBase obj = listModelCommand.getElementAt(index);
			MotionPropertyDialogBase d = obj.obtainDialog();
			if (d != null) {
				d.setObject(obj);
				d.clearDirty();
				d.setVisible(true);
				if (d.isDirty()) {
					obj.setDirty();
					obj.updateProperty(d);
					listCommand.repaint();
				}
			}
		}
	}
	
	public void onDeleteCommand() {
		int[] indices = listCommand.getSelectedIndices();
		if (indices.length > 0) {
			ArrayList<SkillMotionCommandBase> list = new ArrayList<SkillMotionCommandBase>();
			for (int i : indices) {
				list.add(commandsInListOrder.get(i));
			}
			for (SkillMotionCommandBase obj : list) {
				if (obj.getParent() != null) {
					obj.getParent().removeChild(obj);
				} else {
					contents.list.remove(obj);
				}
			}
			this.initializeCommandList();
		}
	}
	
	public void setTabIndex(int n) {
		tabIndex = n;
	}

	@Override
	public void refresh() {
	}
	
	@Override
	public void update() {
		
	}
	
	private class SkillMotionTransferHandler extends TransferHandler {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getSourceActions(JComponent c) {
			return COPY_OR_MOVE;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public Transferable createTransferable(JComponent c) {
			int[] indices = listCommand.getSelectedIndices();
			JList<SkillMotionCommandBase> jlist = (JList<SkillMotionCommandBase>) c;
			SkillMotionDescriptionPanel panel = SkillMotionDescriptionPanel.this;
			ArrayList<SkillMotionCommandBase> list = new ArrayList<SkillMotionCommandBase>();
			for (int i : indices) {
				list.add(panel.commandsInListOrder.get(i));
			}
			if (list.size() > 0) {
				try {
					return new StringSelection(JSON.encode(list));
				} catch (Exception e) {
					return null;
				}
			}
			return null;
		}
		
		@Override
		protected void exportDone(JComponent c, Transferable t, int action) {
		    if (action == MOVE) {
		    	SkillMotionDescriptionPanel p = SkillMotionDescriptionPanel.this;
		    	p.onDeleteCommand();
		    }
		}
		
		@Override
		public boolean canImport(TransferHandler.TransferSupport support) {
			try {
				String s = (String)support.getTransferable().getTransferData(DataFlavor.stringFlavor);
				ArrayList<LinkedHashMap<String, Object>> list = JSON.decode(s);
				for (LinkedHashMap<String, Object> h : list) {
					SkillMotionCommands.motionTypeToClass.get((String)h.get("type")).newInstance().setProperty(h);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean importData(TransferHandler.TransferSupport support) {
			if (!canImport(support)) {
				return false;
			}
			try {
				JList<SkillMotionCommandBase> list = (JList<SkillMotionCommandBase>) support.getComponent();
				SkillMotionDescriptionPanel p = SkillMotionDescriptionPanel.this;
				String s = (String)support.getTransferable().getTransferData(DataFlavor.stringFlavor);
				ArrayList<LinkedHashMap<String, Object>> json = JSON.decode(s);
				int i = list.getSelectedIndex();
				if (i == -1) {
					i = list.getModel().getSize() - 1;
				}
				SkillMotionCommandBase selected = p.commandsInListOrder.get(i);
				for (LinkedHashMap<String,Object> h : json) {
					SkillMotionCommandBase obj = SkillMotionCommands.motionTypeToClass.get((String)h.get("type")).newInstance();
					obj.setProperty(h);
					obj.setDepth(selected.getDepth());
					if (selected.getParent() != null) {
						selected.getParent().addChild(selected.getParent().getChildIndex(selected), obj);
					} else {
						p.contents.list.add(p.contents.list.indexOf(selected), obj);
					}
				}
				p.initializeCommandList();
				p.listCommand.setSelectedIndex(p.commandsInListOrder.indexOf(selected));
				return true;
			}catch (Exception e){
				e.printStackTrace();
				return false;
			}
		}
	}
	
}
