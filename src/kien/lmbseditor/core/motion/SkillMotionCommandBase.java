package kien.lmbseditor.core.motion;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialog;
import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
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
	public MotionPropertyDialog obtainDialog() {
		LinkedHashMap<String, Object> ptl = this.obtainPropertyTypeList();
		LinkedHashMap<String, Object> pl = this.obtainPropertyList();
		if (ptl != null && pl != null) {
			return new MotionPropertyDialog(ptl, pl);
		}
		return null;
	};
	
	// Return the LinkedHashMap that stores pairs of Property Name and type of property.
	public LinkedHashMap<String, Object> obtainPropertyTypeList() {
		try {
			InputStream stream = this.getClass().getResourceAsStream("property/" + this.typeName() + ".json");
			return JSON.decode(stream);
		} catch (Exception e) {
			return new LinkedHashMap<String, Object>();
		}
	};
	
	// Returns the LinkedHashMap that stores pairs of property Name and its value of command.
	public abstract LinkedHashMap<String, Object> obtainPropertyList();
	
	/**
	 * Get the string that will show up in the command list.
	 * 
	 * @return
	 */
	public String obtainCommandRepresentation() {
		return indentString() + "��" + this.commandListName();
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
	public void updateProperty(MotionPropertyDialog dialog) {
		try {
			this.updatePropertyFromMap(dialog.getData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};

	protected abstract void updatePropertyFromMap(LinkedHashMap<String, Object> data);

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
		for (int n = 0; n < this.getDepth(); n++) {
			ret += " * ";
		}
		return ret;
	}

	public void removeList(ArrayList<SkillMotionCommandBase> list) {
		list.remove(this);
	}

	/**
	 * Is this object available in the Command List for adding into motion by user.
	 * @return boolean
	 */
	public boolean includeAvailable() {
		return true;
	}

	/**
	 * Is this object included in JSON object generated by the motion.
	 * @return boolean
	 */
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
