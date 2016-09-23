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
		// TODO Auto-generated method stub
		dx = ((Number)list.get("dx")).intValue();
		dy = ((Number)list.get("dy")).intValue();
		dur = ((Number)list.get("dur")).intValue();
		
	}

	@Override
	public MotionPropertyDialogBase obtainDialog() {
		// TODO Auto-generated method stub
		return new MotionPropertyDialogMove();
	}

	@Override
	public String obtainCommandRepresentation() {
		// TODO Auto-generated method stub
		return indentString() + "Åü" + commandListName() + ": x: " + dx + ", y: " + dy + ", in" + dur + " Frames";
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		// TODO Auto-generated method stub
		MotionPropertyDialogMove d = (MotionPropertyDialogMove)dialog;
		dx = d.dx;
		dy = d.dy;
		dur = d.dur;
	}

	@Override
	public String commandListName() {
		// TODO Auto-generated method stub
		return "Move";
	}

	@Override
	public String typeName() {
		// TODO Auto-generated method stub
		return type;
	}

}
