package kien.lmbseditor.core.motion;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import net.arnx.jsonic.JSONHint;

public abstract class SkillMotionCommandBase {

	private boolean dirty;
	protected int depth;
	protected SkillMotionCommandBase parent;

	/**
	 * Retrieve information from json object.
	 * 
	 * @param list
	 *            the json object.
	 * @throws Exception
	 */
	public abstract void setProperty(LinkedHashMap<String, Object> list) throws Exception;

	/**
	 * Get the panel to set the property of the motion.
	 * @return
	 */
	public abstract MotionPropertyDialogBase obtainDialog();

	/**
	 * Get the string that will show up in the command list.
	 * 
	 * @return
	 */
	public String obtainCommandRepresentation() {
		return indentString() + "Åü" + this.commandListName();
	};

	@Override
	public String toString() {
		return this.obtainCommandRepresentation();
	}
	
	
	public Color commandColor() {
		return Color.BLACK;
	}

	/**
	 * Update the information from the dialog.
	 * 
	 * @param frame
	 *            dialog created from the class returned by
	 *            {@link obtainPanelClass}
	 */
	public abstract void updateProperty(MotionPropertyDialogBase dialog);

	@JSONHint(ignore = true)
	public boolean isDirty() {
		return this.dirty;
	}

	@JSONHint(ignore = true)
	public void setDirty() {
		this.dirty = true;
	}

	public void clearDirty() {
		this.dirty = false;
	}

	public void addList(ArrayList<SkillMotionCommandBase> list) {
		list.add(this);
	}

	public void addList(int index, ArrayList<SkillMotionCommandBase> list) {
		list.add(index, this);
	}

	public abstract String commandListName();

	public abstract String typeName();

	@JSONHint(ignore = true)
	public void setDepth(int depth) {
		this.depth = depth;
	}

	@JSONHint(ignore = true)
	public int getDepth() {
		return depth;
	}

	@JSONHint(ignore = true)
	public void setParent(SkillMotionCommandBase p) {
		this.parent = p;
	}

	@JSONHint(ignore = true)
	public SkillMotionCommandBase getParent() {
		return this.parent;
	}

	protected String indentString() {
		String ret = "";
		for (int n = 0; n < depth; n++) {
			ret += " * ";
		}
		return ret;
	}

	public void removeList(ArrayList<SkillMotionCommandBase> list) {
		list.remove(this);
	}

	public boolean includeAvailable() {
		return true;
	}

	public boolean includeJSON() {
		return true;
	}
	
	public void addChild(SkillMotionCommandBase child) {
		
	}
	
	public void removeChild(SkillMotionCommandBase child) {
		
	}

	public void addChild(int index, SkillMotionCommandBase child) {
	}

	@JSONHint(ignore = true)
	public int getChildIndex(SkillMotionCommandBase child) {
		return -1;
	}
}
