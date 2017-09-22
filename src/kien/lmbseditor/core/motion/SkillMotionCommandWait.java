package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogWait;

public class SkillMotionCommandWait extends SkillMotionCommandBase {

	public final String type = "wait";
	public int dur;
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) {
		dur = ((Number)list.get("dur")).intValue();
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ": " + dur + " Frames";
	}

	@Override
	public String commandListName() {
		return "Wait";
	}

	@Override
	public String typeName() {
		return type;
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("dur", this.dur);
		return map;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		this.setProperty(data);
	}
}
