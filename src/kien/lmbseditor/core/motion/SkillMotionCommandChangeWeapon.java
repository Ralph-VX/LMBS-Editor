package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

public class SkillMotionCommandChangeWeapon extends SkillMotionCommandBase {

	public final String type = "changeweapon";
	public String name;

	public SkillMotionCommandChangeWeapon() {
		name = "";
	}

	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws IllegalArgumentException {
		try {
			this.name = (String) list.get("name");
		} catch (Exception e) {
			throw new IllegalArgumentException("Provided JSON Object is not available!");
		}
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ": " + name;
	}

	@Override
	public String commandListName() {
		return "Change Weapon";
	}

	@Override
	public String typeName() {
		return type;
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("name", this.name);
		return map;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		this.setProperty(data);
	}
}
