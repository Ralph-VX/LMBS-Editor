package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogWait;

public class SkillMotionCommandHideMessage extends SkillMotionCommandBase {
	
	public final String type = "hidemessage";
	public int channel;
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		channel = ((Number)list.get("channel")).intValue();
	}

	@Override
	public MotionPropertyDialogBase obtainDialog() {
		MotionPropertyDialogWait d = new MotionPropertyDialogWait() {
			@Override
			public void setObject(SkillMotionCommandBase obj) {
				SkillMotionCommandHideMessage o = (SkillMotionCommandHideMessage)obj;
				dur = o.channel;
			}
		};
		d.setTitle("Hide Message");
		d.labelTitle.setText("channel");
		d.labelTitle.setToolTipText("Channel index of the message that will be hided");
		d.textField.setToolTipText("Channel index of the message that will be hided");
		return d;
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		MotionPropertyDialogWait d = (MotionPropertyDialogWait)dialog;
		channel = d.dur;
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

}
