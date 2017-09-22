package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

public class SkillMotionCommandFrameBackward extends SkillMotionCommandBase {
	public final String type = "backward";
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		
	}

	@Override
	public String commandListName() {
		return "Frame Backward";
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
