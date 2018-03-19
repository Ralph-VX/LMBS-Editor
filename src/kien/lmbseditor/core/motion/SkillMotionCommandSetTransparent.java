package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

public class SkillMotionCommandSetTransparent extends SkillMotionCommandBase {

	public final String type = "settransparent";
	public boolean value;
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) {
		value = ((Boolean)list.get("value"));
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ": " + value;
	}

	@Override
	public String commandListName() {
		return "Set Transparent";
	}

	@Override
	public String typeName() {
		return type;
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("value", this.value);
		return map;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		this.setProperty(data);
	}
}
