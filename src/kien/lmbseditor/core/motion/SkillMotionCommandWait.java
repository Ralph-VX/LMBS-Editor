package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogWait;

public class SkillMotionCommandWait extends SkillMotionCommandBase {

	public final String type = "wait";
	public int dur;
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		dur = ((Number)list.get("dur")).intValue();
	}

	@Override
	public MotionPropertyDialogBase obtainDialog() {
		// TODO Auto-generated method stub
		return new MotionPropertyDialogWait();
	}

	@Override
	public String obtainCommandRepresentation() {
		// TODO Auto-generated method stub
		return indentString() + "��" + commandListName() + ": " + dur + " Frames";
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		MotionPropertyDialogWait d = (MotionPropertyDialogWait)dialog;
		dur = d.dur;
	}

	@Override
	public String commandListName() {
		// TODO Auto-generated method stub
		return "Wait";
	}

	@Override
	public String typeName() {
		return type;
	}
}
