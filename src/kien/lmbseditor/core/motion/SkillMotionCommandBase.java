package kien.lmbseditor.core.motion;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import net.arnx.jsonic.JSONHint;

public abstract class SkillMotionCommandBase {
	
	private boolean dirty;
	
	/**
	 * Retrieve information from json object.
	 * @param list the json object.
	 * @throws Exception 
	 */
	public abstract void setProperty(LinkedHashMap<String, Object> list) throws Exception;
	/**
	 * Get the panel to set the property of the motion.
	 * @return
	 */
	public abstract Class<? extends MotionPropertyDialogBase> obtainDialogClass();
	/**
	 * Get the string that will show up in the command list.
	 * @return
	 */
	public abstract String obtainCommandRepresentation();
	
	public String toString() {
		return this.obtainCommandRepresentation();
	}
	/**
	 * Update the information from the dialog.
	 * @param frame dialog created from the class returned by {@link obtainPanelClass}
	 */
	public abstract void updateProperty(MotionPropertyDialogBase dialog);
	
	@JSONHint(ignore=true)
	public boolean isDirty() {
		return this.dirty;
	}

	@JSONHint(ignore=true)
	public void setDirty() {
		this.dirty = true;
	}
	
	public void clearDirty() {
		this.dirty = false;
	}
	
	public void addList(ArrayList<SkillMotionCommandBase> list) {
		list.add(this);
	}
	
	public abstract String commandListName();
	
	public abstract String typeName();
	
}
