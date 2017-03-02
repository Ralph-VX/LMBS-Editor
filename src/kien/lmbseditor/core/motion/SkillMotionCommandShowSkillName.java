package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;

public class SkillMotionCommandShowSkillName extends SkillMotionCommandBase {
	
	public final String type = "showskillname";
	
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
		return "Show Skill Name";
	}

	@Override
	public String typeName() {
		return type;
	}

}
