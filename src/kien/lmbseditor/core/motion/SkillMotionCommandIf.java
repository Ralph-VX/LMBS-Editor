package kien.lmbseditor.core.motion;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import kien.lmbseditor.core.SkillMotionCommands;
import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogChangePose;
import net.arnx.jsonic.JSONHint;

public class SkillMotionCommandIf extends SkillMotionCommandBase {

	public final String type = "if";
	public String expression;
	public ArrayList<SkillMotionCommandBase> list;
	
	public SkillMotionCommandIf() {
		expression = "";
		list = new ArrayList<SkillMotionCommandBase>();
		list.add(new SkillMotionCommandEndIf());
		list.get(0).setParent(this);
	}
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> in) {
		this.expression = (String) in.get("expression");
		list = SkillMotionCommands.convertJsonToObjectList((ArrayList<LinkedHashMap<String, Object> >)in.get("list"));
		for (SkillMotionCommandBase item : list) {
			this.addChild(item);
		}
	}
	
	@Override
	public void addList(ArrayList<SkillMotionCommandBase> list) {
		list.add(this);
		for (SkillMotionCommandBase child : this.list) {
			child.addList(list);
		}
	}

	@Override
	public void addList(int index, ArrayList<SkillMotionCommandBase> list) {
		list.add(index, this);
		index++;
		for (SkillMotionCommandBase child : this.list) {
			child.addList(index, list);
			index++;
		}
	}
	@Override
	public MotionPropertyDialogBase obtainDialog() {
		MotionPropertyDialogChangePose d = new MotionPropertyDialogChangePose() {
			@Override
			public void setObject(SkillMotionCommandBase object) {
				SkillMotionCommandIf o = (SkillMotionCommandIf)object;
				this.textField.setText(o.expression);
				this.result = o.expression;
			}
		};
		// TODO Auto-generated method stub
		d.setTitle("If");
		d.labelTitle.setText("expression");
		d.labelTitle.setToolTipText("The expression this if statement will execute");
		d.textField.setToolTipText("The expression this if statement will execute");
		return d;
	}

	@Override
	public String obtainCommandRepresentation() {
		// TODO Auto-generated method stub
		return indentString() + "Åü" + commandListName() + " " + expression + ": ";
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		MotionPropertyDialogChangePose d = (MotionPropertyDialogChangePose)dialog;
		if (d.result != null) {
			expression = d.result;
		} else {
			expression = "";
		}
	}
	
	@Override
	public boolean isDirty() {
		if (super.isDirty()) {
			return true;
		}
		for (SkillMotionCommandBase i : list) {
			if (i.isDirty()) {
				return true;
			}
		}
		return false;
	}
	
	public void clearDirty() {
		super.clearDirty();
		for (SkillMotionCommandBase i : list) {
			i.clearDirty();
		}
	}

	@Override
	public String commandListName() {
		// TODO Auto-generated method stub
		return "If";
	}

	@Override
	public String typeName() {
		return type;
	}
	
	@Override
	public void removeList(ArrayList<SkillMotionCommandBase> list) {
		list.remove(this);
		for (SkillMotionCommandBase i : this.list) {
			i.removeList(list);
		}
	}
	
	@Override
	@JSONHint(ignore = true)
	public void setDepth(int depth) {
		super.setDepth(depth);
		for (SkillMotionCommandBase i : this.list) {
			i.setDepth(depth+1);
		}
	}
	
	public void addChild(SkillMotionCommandBase child) {
		if (child.parent == this) {
			return;
		}
		if (child.parent != null) {
			child.parent.removeChild(child);
		}
		this.list.add(child);
		child.setParent(this);
		child.setDepth(this.depth + 1);
	}
	
	public void removeChild(SkillMotionCommandBase child) {
		if (child.typeName() == "endif") {
			return;
		}
		this.list.remove(child);
	}
}
