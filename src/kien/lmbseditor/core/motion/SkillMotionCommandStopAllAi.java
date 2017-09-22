package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;

public class SkillMotionCommandStopAllAi extends SkillMotionCommandBase {
	public final String type = "startallai";
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		
	}

	@Override
	public String commandListName() {
		return "Start All Ai";
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
