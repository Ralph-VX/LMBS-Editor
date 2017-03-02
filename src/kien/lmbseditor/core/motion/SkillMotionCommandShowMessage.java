package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogShowMessage;

public class SkillMotionCommandShowMessage extends SkillMotionCommandBase {

	public final String type = "showmessage";
	public int channel;
	public String string;
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		channel = ((Number)list.get("channel")).intValue();
		string = (String)list.get("string");
	}

	@Override
	public MotionPropertyDialogBase obtainDialog() {
		return new MotionPropertyDialogShowMessage();
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		MotionPropertyDialogShowMessage d = (MotionPropertyDialogShowMessage)dialog;
		this.channel = d.channel;
		this.string = d.string;
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

}
