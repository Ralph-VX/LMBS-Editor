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
	public void setProperty(LinkedHashMap<String, Object> list) {
		dx = ((Number)list.get("dx")).intValue();
		dy = ((Number)list.get("dy")).intValue();
		dur = ((Number)list.get("dur")).intValue();
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ": x: " + dx + ", y: " + dy + ", in " + dur + " Frames";
	}

	@Override
	public String commandListName() {
		return "Move Weapon";
	}

	@Override
	public String typeName() {
		return type;
	}
	
	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("dx", this.dx);
		map.put("dy", this.dy);
		map.put("dur", this.dur);
		return map;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		this.setProperty(data);
	}

}
