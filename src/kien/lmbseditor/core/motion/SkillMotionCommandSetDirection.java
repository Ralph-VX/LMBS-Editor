package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

public class SkillMotionCommandSetDirection extends SkillMotionCommandBase {

	public final String type = "setdirection";
	public int dir;
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) {
		dir = ((Boolean)list.get("dir")) ? 6 : 4;
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ": " + (dir == 4 ? "To Left" : "To Right");
	}

	@Override
	public String commandListName() {
		return "Set Direction";
	}

	@Override
	public String typeName() {
		return type;
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("dir", this.dir == 6 ? true : false);
		return map;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		this.setProperty(data);
	}
}
