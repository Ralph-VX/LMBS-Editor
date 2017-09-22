package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

public class SkillMotionCommandChangePose extends SkillMotionCommandBase {

	public final String type = "pose";
	public String name;
	
	public SkillMotionCommandChangePose() {
		name = "";
	}
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) {
		try {
			this.name = (String) list.get("name");
		} catch (Exception e) {
			throw new IllegalArgumentException("Provided JSON Object is not available!");
		}
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ": " + name;
	}

	@Override
	public String commandListName() {
		return "Change Pose";
	}

	@Override
	public String typeName() {
		return type;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		this.setProperty(data);
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("name", this.name);
		return map;
	}
}
