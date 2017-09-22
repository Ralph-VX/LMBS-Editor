package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

public class SkillMotionCommandSetHitStop extends SkillMotionCommandBase {

	public final String type = "sethitstop";
	public int length;
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) {
		length = ((Number)list.get("length")).intValue();
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ": " + length + " Frames";
	}

	@Override
	public String commandListName() {
		return "Set Hit Stop";
	}

	@Override
	public String typeName() {
		return type;
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("length", this.length);
		return map;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		this.setProperty(data);
	}
}
