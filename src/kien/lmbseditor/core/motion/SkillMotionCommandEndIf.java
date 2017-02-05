package kien.lmbseditor.core.motion;

import java.awt.Color;
import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;

public class SkillMotionCommandEndIf extends SkillMotionCommandBase {
	public final String type = "endif";
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MotionPropertyDialogBase obtainDialog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtainCommandRepresentation() {
		// TODO Auto-generated method stub
		return indentString() + "Åü" + commandListName();
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String commandListName() {
		// TODO Auto-generated method stub
		return "End If";
	}

	@Override
	public String typeName() {
		// TODO Auto-generated method stub
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
