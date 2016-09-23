package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;

public class SkillMotionCommandProjectile extends SkillMotionCommandBase {
	public final String type = "projectile";
	public String classname;
	public String parameters;
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		// TODO Auto-generated method stub
		classname = (String)list.get("classname");
		parameters = (String)list.get("parameters");
	}

	@Override
	public MotionPropertyDialogBase obtainDialog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtainCommandRepresentation() {
		// TODO Auto-generated method stub
		return indentString() + "Åü" + commandListName() + classname + ", " + parameters;
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		// TODO Auto-generated method stub

	}

	@Override
	public String commandListName() {
		// TODO Auto-generated method stub
		return "Projectile";
	}

	@Override
	public String typeName() {
		// TODO Auto-generated method stub
		return type;
	}

}
