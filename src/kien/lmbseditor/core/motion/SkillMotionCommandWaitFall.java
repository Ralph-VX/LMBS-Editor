package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;

public class SkillMotionCommandWaitFall extends SkillMotionCommandBase {
	public final String type = "waitfall";
	public final int dur = 1;
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
		return "Wait Until Falling End";
	}

	@Override
	public String typeName() {
		return type;
	}

}
