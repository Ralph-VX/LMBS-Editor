package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

public class SkillMotionCommandHideMessage extends SkillMotionCommandBase {
	
	public final String type = "hidemessage";
	public int channel;
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list){
		channel = ((Number)list.get("channel")).intValue();
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ", in channel " + channel;
	}
	
	@Override
	public String commandListName() {
		return "Hide Message";
	}

	@Override
	public String typeName() {
		return type;
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("channel", this.channel);
		return map;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		this.setProperty(data);
	}

}
