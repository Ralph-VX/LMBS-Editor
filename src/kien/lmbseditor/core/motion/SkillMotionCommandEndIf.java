package kien.lmbseditor.core.motion;

import java.awt.Color;
import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import net.arnx.jsonic.JSONHint;

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
	@JSONHint(ignore = true)
	public int getDepth() {
		return depth-1;
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
