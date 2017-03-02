package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogMove;

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
		MotionPropertyDialogMove diag = new MotionPropertyDialogMove(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void setObject(SkillMotionCommandBase object) {
				SkillMotionCommandMoveWeapon src = (SkillMotionCommandMoveWeapon)object;
				this.xField.setValue(src.dx);
				this.yField.setValue(src.dy);
				this.durationField.setValue(src.dur);
			}
		};
		diag.setTitle("Move Weapon");
		return diag;
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ": x: " + dx + ", y: " + dy + ", in " + dur + " Frames";
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		MotionPropertyDialogMove diag = (MotionPropertyDialogMove) dialog;
		this.dx = diag.dx;
		this.dy = diag.dy;
		this.dur = diag.dur;
	}

	@Override
	public String commandListName() {
		return "Move Weapon";
	}

	@Override
	public String typeName() {
		return type;
	}

}
