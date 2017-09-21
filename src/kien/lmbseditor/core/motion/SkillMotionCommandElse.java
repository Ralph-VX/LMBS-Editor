package kien.lmbseditor.core.motion;

import java.awt.Color;
import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;

public class SkillMotionCommandElse extends SkillMotionCommandBase {
	
	public final String type = "else";
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
	}

	@Override
	public MotionPropertyDialogBase obtainDialog() {
		return null;
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
	}

	@Override
	public String commandListName() {
		return null;
	}

	@Override
	public String typeName() {
		return type;
	}
	
	@Override
	public boolean includeAvailable() {
		return false;
	}
	
	protected String indentString() {
		String ret = "";
		for (int n = 0; n < this.getDepth()-1; n++) {
			ret += " * ";
		}
		return ret;
	}

	@Override
	public Color commandColor() {
		return Color.BLUE;
	}
}
