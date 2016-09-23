package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogChangePose;

public class SkillMotionCommandChangePose extends SkillMotionCommandBase {

	public final String type = "pose";
	public String name;
	
	public SkillMotionCommandChangePose() {
		name = "";
	}
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws IllegalArgumentException {
		try {
			this.name = (String) list.get("name");
		} catch (Exception e) {
			throw new IllegalArgumentException("Provided JSON Object is not available!");
		}
	}

	@Override
	public MotionPropertyDialogBase obtainDialog() {
		// TODO Auto-generated method stub
		return new MotionPropertyDialogChangePose();
	}

	@Override
	public String obtainCommandRepresentation() {
		// TODO Auto-generated method stub
		return indentString() + "Åü" + commandListName() + ": " + name;
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		MotionPropertyDialogChangePose d = (MotionPropertyDialogChangePose)dialog;
		if (d.result != null) {
			name = d.result;
		} else {
			name = "";
		}
	}

	@Override
	public String commandListName() {
		// TODO Auto-generated method stub
		return "Change Pose";
	}

	@Override
	public String typeName() {
		return type;
	}
}
