package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;

public class SkillMotionCommandFrameForward extends SkillMotionCommandBase {
	public final String type = "forward";
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
		return "Frame Forward";
	}

	@Override
	public String typeName() {
		return type;
	}

}
