package kien.lmbseditor.window;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import kien.lmbseditor.core.SkillMotionCommands;
import kien.lmbseditor.core.SkillMotionItemType;
import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import kien.lmbseditor.core.motion.SkillMotionCommandEndIf;
import kien.lmbseditor.core.motion.SkillMotionCommandIf;
import kien.lmbseditor.window.motion.MotionPropertyDialogBase;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SkillMotionDescriptionPanel extends JPanel {
	
	private ArrayList<SkillMotionCommandBase> commandsInListOrder;
	private JList<SkillMotionCommandBase> listCommand;
	private JComboBox<String> listMotionCommand;
	private DefaultComboBoxModel<String> listModelMotionCommand;
	private DefaultListModel<SkillMotionCommandBase> listModelCommand;
	private SkillMotionItemType contents;
	
	private int tabIndex;
	
	/**
	 * Create the panel.
	 */
	public SkillMotionDescriptionPanel(SkillMotionItemType source) {
		commandsInListOrder = new ArrayList<SkillMotionCommandBase>();
		contents = source;
		setLayout(new MigLayout("", "[growprio 80,grow 80][growprio 5,grow 5]", "[grow]"));
		
		listModelCommand = new DefaultListModel<SkillMotionCommandBase>();
		listCommand = new JList<SkillMotionCommandBase>(listModelCommand);
		listCommand.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(listCommand, "cell 0 0,grow");
		
		JPanel panel = new JPanel();
		add(panel, "cell 1 0,grow");
		panel.setLayout(new MigLayout("", "[grow]", "[28px][28px][28px][]"));
		
		listModelMotionCommand = new DefaultComboBoxModel<String>();
		listMotionCommand = new JComboBox<String>(listModelMotionCommand);
		panel.add(listMotionCommand, "cell 0 0,growx");
		
		JButton btnNewButton_1 = new JButton("Add Command");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SkillMotionDescriptionPanel.this.onAddCommand();
			}
		});
		panel.add(btnNewButton_1, "cell 0 1,growx");
		
		JButton btnNewButton_2 = new JButton("Edit Command");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SkillMotionDescriptionPanel.this.onEditCommand();
			}
		});
		panel.add(btnNewButton_2, "cell 0 2,growx");
		
		JButton btnNewButton = new JButton("Delete Command");
		btnNewButton.addActionListener(new ActionListener() {
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
				}
			}
			obj.setDepth(selected.getDepth());
			if (selected.getParent() != null) {
				selected.getParent().addChild(obj);
			} else {
				this.contents.list.add(index, obj);
			}
			this.initializeCommandList();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
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
		int index = listCommand.getSelectedIndex();
		if (index >= 0) {
			SkillMotionCommandBase obj = commandsInListOrder.get(index);
			if (obj.getParent() != null) {
				obj.getParent().removeChild(obj);
			} else {
				contents.list.remove(obj);
			}
			this.initializeCommandList();
		}
	}
	
	public void setTabIndex(int n) {
		tabIndex = n;
	}
	
}
