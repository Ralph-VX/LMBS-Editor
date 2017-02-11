package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogProjectile;

public class SkillMotionCommandProjectile extends SkillMotionCommandBase {
	public final String type = "projectile";
	public String classname;
	public String parameters;
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		classname = (String)list.get("classname");
		parameters = (String)list.get("parameters");
	}

	@Override
	public MotionPropertyDialogBase obtainDialog() {
		return new MotionPropertyDialogProjectile();
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ":" + classname + ", " + parameters;
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		MotionPropertyDialogProjectile d = (MotionPropertyDialogProjectile)dialog;
		this.classname = d.classname;
		this.parameters = d.parameters;

	}

	@Override
	public String commandListName() {
		return "Projectile";
	}

	@Override
	public String typeName() {
		return type;
	}

}
