package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

public class SkillMotionCommandShowSkillName extends SkillMotionCommandBase {
	
	public final String type = "showskillname";
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {

	}

	@Override
	public String commandListName() {
		return "Show Skill Name";
	}

	@Override
	public String typeName() {
		return type;
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		return null;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
	}

}
