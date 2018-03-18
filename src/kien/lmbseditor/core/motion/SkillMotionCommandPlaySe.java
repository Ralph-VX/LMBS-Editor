package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;
import kien.util.Util;

public class SkillMotionCommandPlaySe extends SkillMotionCommandBase {
	
	public final String type = "playse";
	public String name;
	public int volume;
	public int pitch;
	public int pan;
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) {
		this.name = (String) list.get("name");
		this.volume = Util.getJSONInt(list, "volume", 90);
		this.pitch = Util.getJSONInt(list, "pitch", 100);
		this.pan = Util.getJSONInt(list, "pan", 0);
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		LinkedHashMap<String, Object> list = new LinkedHashMap<>();
		list.put("name", this.name);
		list.put("volume", this.volume);
		list.put("pitch", this.pitch);
		list.put("pan", this.pan);
		return list;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		this.setProperty(data);
	}

	@Override
	public String commandListName() {
		// TODO Auto-generated method stub
		return "Play Se";
	}

	@Override
	public String typeName() {
		// TODO Auto-generated method stub
		return type;
	}

}
