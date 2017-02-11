package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import org.reflections.util.Utils;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogRotation;
import kien.util.Util;

public class SkillMotionCommandRotation extends SkillMotionCommandBase {

	public final String type = "rotation";
	public int rotation;
	public int dir;
	public int dur;
	public int rounds;
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		dur = ((Number)list.get("dur")).intValue();
		dir = ((Number)list.get("dir")).intValue();
		rotation = ((Number)list.get("rotation")).intValue();
		rounds = Util.getJSONInt(list, "rounds", 0);
	}

	@Override
	public MotionPropertyDialogBase obtainDialog() {
		return new MotionPropertyDialogRotation();
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + 
				": " + "Target: " + rotation + " degree, " + 
				dur + " Frames, Inverted: " + (dir > 0 ? "true" : "false") + 
				(rounds > 0 ? "for " + rounds + "rounds" : "" );
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		MotionPropertyDialogRotation d = (MotionPropertyDialogRotation)dialog;
		dur = d.dur;
		dir = d.dir;
		rotation = d.rotation;
		rounds = d.rounds;
	}

	@Override
	public String commandListName() {
		return "Rotation";
	}

	@Override
	public String typeName() {
		return type;
	}
}
