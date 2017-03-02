package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogRotation;
import kien.util.Util;

public class SkillMotionCommandRotateWeapon extends SkillMotionCommandBase {
	
	public final String type = "rotateweapon";
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
		MotionPropertyDialogRotation diag = new MotionPropertyDialogRotation(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void setObject(SkillMotionCommandBase object) {
				SkillMotionCommandRotateWeapon obj = (SkillMotionCommandRotateWeapon)object;
				rotationField.setValue(obj.rotation);
				durationField.setValue(obj.dur);
				invertCheck.setSelected(obj.dir > 0);
				roundField.setValue(obj.rounds);
				clearDirty();
			}
		};
		diag.setTitle("Rotate Weapon");
		return diag;
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		MotionPropertyDialogRotation d = (MotionPropertyDialogRotation) dialog;
		dur = d.dur;
		dir = d.dir;
		rotation = d.rotation;
		rounds = d.rounds;
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + 
				": " + "Target: " + rotation + " degree, " + 
				dur + " Frames, Inverted: " + (dir > 0 ? "true" : "false") + 
				(rounds > 0 ? "for " + rounds + " rounds" : "" );
	}

	@Override
	public String commandListName() {
		return "Rotate Weapon";
	}

	@Override
	public String typeName() {
		return type;
	}

}
