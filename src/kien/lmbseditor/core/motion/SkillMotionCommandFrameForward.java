package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;

public class SkillMotionCommandFrameForward extends SkillMotionCommandBase {
	public final String type = "forward";
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
		return indentString() + "��" + commandListName();
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String commandListName() {
		// TODO Auto-generated method stub
		return "Frame Forward";
	}

	@Override
	public String typeName() {
		// TODO Auto-generated method stub
		return type;
	}

}
