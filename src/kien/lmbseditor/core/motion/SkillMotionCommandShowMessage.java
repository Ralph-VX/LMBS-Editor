package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

public class SkillMotionCommandShowMessage extends SkillMotionCommandBase {

	public final String type = "showmessage";
	public int channel;
	public String string;
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) {
		channel = ((Number)list.get("channel")).intValue();
		string = (String)list.get("string");
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ", Text: " + string + ", in channel " + channel;
	}
	
	@Override
	public String commandListName() {
		return "Show Message";
	}

	@Override
	public String typeName() {
		return type;
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("channel", this.channel);
		map.put("string", this.string);
		return map;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		this.setProperty(data);
	}

}
