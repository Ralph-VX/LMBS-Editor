package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogMove;

public class SkillMotionCommandMove extends SkillMotionCommandBase {
	
	public final String type = "move";
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
		return new MotionPropertyDialogMove();
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ": x: " + dx + ", y: " + dy + ", in" + dur + " Frames";
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		MotionPropertyDialogMove d = (MotionPropertyDialogMove)dialog;
		dx = d.dx;
		dy = d.dy;
		dur = d.dur;
	}

	@Override
	public String commandListName() {
		return "Move";
	}

	@Override
	public String typeName() {
		return type;
	}

}
