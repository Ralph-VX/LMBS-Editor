package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogRotation;

public class SkillMotionCommandRotation extends SkillMotionCommandBase {

	public final String type = "rotation";
	public int rotation;
	public int dir;
	public int dur;
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		dur = ((Number)list.get("dur")).intValue();
		dir = ((Number)list.get("dir")).intValue();
		rotation = ((Number)list.get("rotation")).intValue();
	}

	@Override
	public MotionPropertyDialogBase obtainDialog() {
		return new MotionPropertyDialogRotation();
	}

	@Override
	public String obtainCommandRepresentation() {
		// TODO Auto-generated method stub
		return indentString() + "��" + commandListName() + ": " + "Target: " + rotation + " degree, " + dur + " Frames, Inverted: " + (dir > 0 ? "true" : "false");
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		MotionPropertyDialogRotation d = (MotionPropertyDialogRotation)dialog;
		dur = d.dur;
		dir = d.dir;
		rotation = d.rotation;
	}

	@Override
	public String commandListName() {
		// TODO Auto-generated method stub
		return "Rotation";
	}

	@Override
	public String typeName() {
		return type;
	}
}