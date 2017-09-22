package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.util.Util;

public class SkillMotionCommandRotation extends SkillMotionCommandBase {

	public final String type = "rotation";
	public int rotation;
	public int dir;
	public int dur;
	public int rounds;
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) {
		dur = ((Number)list.get("dur")).intValue();
		dir = ((Number)list.get("dir")).intValue();
		rotation = ((Number)list.get("rotation")).intValue();
		rounds = Util.getJSONInt(list, "rounds", 0);
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + 
				": " + "Target: " + rotation + " degree, " + 
				dur + " Frames, Inverted: " + (dir > 0 ? "true" : "false") + 
				(rounds > 0 ? " for " + rounds + " rounds" : "" );
	}

	@Override
	public String commandListName() {
		return "Rotation";
	}

	@Override
	public String typeName() {
		return type;
	}
	
	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("rotation", this.rotation);
		map.put("dir", this.dir);
		map.put("dur", this.dur);
		map.put("rounds", this.rounds);
		return map;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		this.setProperty(data);
	}
}
