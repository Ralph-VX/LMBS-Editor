package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

public class SkillMotionCommandProjectile extends SkillMotionCommandBase {
	public final String type = "projectile";
	public String classname;
	public String parameters;
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) {
		classname = (String)list.get("classname");
		parameters = (String)list.get("parameters");
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ":" + classname + ", " + parameters;
	}

	@Override
	public String commandListName() {
		return "Projectile";
	}

	@Override
	public String typeName() {
		return type;
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("classname", this.classname);
		map.put("parameters", this.parameters);
		return map;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		this.setProperty(data);
	}

}
