package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

public class SkillMotionCommandEndDamage extends SkillMotionCommandBase {
	public final String type = "enddamage";
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		
	}

	@Override
	public String commandListName() {
		return "End Damage";
	}

	@Override
	public String typeName() {
		return type;
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		return null;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		
	}

}
