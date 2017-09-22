package kien.lmbseditor.core.motion;

import java.awt.Color;
import java.util.LinkedHashMap;

public class SkillMotionCommandElse extends SkillMotionCommandBase {
	
	public final String type = "else";
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
	}

	@Override
	public String commandListName() {
		return "Else";
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

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		return null;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
	}
}
