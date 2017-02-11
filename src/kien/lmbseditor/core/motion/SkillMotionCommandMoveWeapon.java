package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;

public class SkillMotionCommandMoveWeapon extends SkillMotionCommandBase {

	public final String type = "moveweapon";
	public int dx;
	public int dy;
	public int dur;
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		dx = ((Number)list.get("dx")).intValue();
		dy = ((Number)list.get("dy")).intValue();
		dur = ((Number)list.get("dur")).intValue();
	}

	@Override
	public MotionPropertyDialogBase obtainDialog() {
		return null;
	}

	@Override
	public String obtainCommandRepresentation() {
		return null;
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {

	}

	@Override
	public String commandListName() {
		return null;
	}

	@Override
	public String typeName() {
		return null;
	}

}
