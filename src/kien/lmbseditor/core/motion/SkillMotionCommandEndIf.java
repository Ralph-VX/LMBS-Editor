package kien.lmbseditor.core.motion;

import java.awt.Color;
import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;

public class SkillMotionCommandEndIf extends SkillMotionCommandBase {
	public final String type = "endif";
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
		return "End If";
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
	public Color commandColor() {
		return Color.BLUE;
	}
}
