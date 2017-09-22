package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

public class SkillMotionCommandEmpty extends SkillMotionCommandBase {
	public final String type = "empty";
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		
	}

	@Override
	public String commandListName() {
		return "";
	}

	@Override
	public String typeName() {
		return type;
	}
	
	@Override
	public boolean includeAvailable() {
		return false;
	}
	
	@Override
	public boolean includeJSON() {
		return false;
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		return null;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		
	}
}
