package kien.lmbseditor.core.motion;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import kien.lmbseditor.core.SkillMotionCommands;
import net.arnx.jsonic.JSONHint;

public class SkillMotionCommandIf extends SkillMotionCommandBase {

	public final String type = "if";
	public String expression;
	public ArrayList<SkillMotionCommandBase> list;

	public SkillMotionCommandIf() {
		expression = "";
		list = new ArrayList<SkillMotionCommandBase>();
		list.add(new SkillMotionCommandEndIf(this));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setProperty(LinkedHashMap<String, Object> in) {
		this.expression = (String) in.get("expression");
		list = SkillMotionCommands.convertJsonToObjectList((ArrayList<LinkedHashMap<String, Object>>) in.get("list"));
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
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ":" + expression + ", then: ";
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

	@Override
	public void clearDirty() {
		super.clearDirty();
		for (SkillMotionCommandBase i : list) {
			i.clearDirty();
		}
	}

	@Override
	public String commandListName() {
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
			i.setDepth(depth + 1);
		}
	}

	@Override
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

	@Override
	public void addChild(int index, SkillMotionCommandBase child) {
		if (child.parent == this) {
			return;
		}
		if (child.parent != null) {
			child.parent.removeChild(child);
		}
		this.list.add(index, child);
		child.setParent(this);
		child.setDepth(this.depth + 1);
	}

	@Override
	public int getChildIndex(SkillMotionCommandBase child) {
		return this.list.indexOf(child);
	}

	@Override
	public void removeChild(SkillMotionCommandBase child) {
		if (child.typeName() == "endif") {
			return;
		}
		this.list.remove(child);
	}

	@Override
	public Color commandColor() {
		return Color.BLUE;
	}
	
	private boolean hasElse() {
		for (SkillMotionCommandBase child : this.list) {
			if (child.typeName().equals("else")) {
				return true;
			}
		}
		return false;
	}
	
	private int endIfIndex() {
		int index = 0;
		for (SkillMotionCommandBase child : this.list) {
			if (child.typeName().equals("endif")) {
				return index;
			}
			index++;
		}
		return -1;
	}

	private int elseIndex() {
		int index = 0;
		for (SkillMotionCommandBase child : this.list) {
			if (child.typeName().equals("else")) {
				return index;
			}
			index++;
		}
		return -1;
	}
	
	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("else", this.hasElse());
		map.put("expression", this.expression);
		return map;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		this.expression = (String) data.get("expression");
		boolean bool = (boolean)data.get("else");
		if (bool != this.hasElse()) {
			if (bool) {
				int endifindex = this.endIfIndex();
				this.list.add(endifindex, new SkillMotionCommandElse(this));
			} else {
				int endifindex = this.endIfIndex();
				int elseindex = this.elseIndex();
				while (elseindex != endifindex) {
					this.list.remove(elseindex);
					endifindex = this.endIfIndex();
				}
			}
		}
	}
}
