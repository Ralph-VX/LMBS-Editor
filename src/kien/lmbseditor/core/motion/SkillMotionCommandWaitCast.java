package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogWait;

public class SkillMotionCommandWaitCast extends SkillMotionCommandBase {

	public final String type = "waitcast";
	public int dur;
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		dur = ((Number)list.get("dur")).intValue();
	}

	@Override
	public MotionPropertyDialogBase obtainDialog() {
		// TODO Auto-generated method stub
		MotionPropertyDialogWait d = new MotionPropertyDialogWait() {
			@Override
			public void setObject(SkillMotionCommandBase object) {
				SkillMotionCommandWaitCast src = (SkillMotionCommandWaitCast)object;
				this.dur = src.dur;
				this.textField.setValue(dur);
			}
		};
		d.setTitle("Wait Cast");
		return d;
	}

	@Override
	public String obtainCommandRepresentation() {
		// TODO Auto-generated method stub
		return indentString() + "Åü" + commandListName() + ": " + dur + " Frames";
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		MotionPropertyDialogWait d = (MotionPropertyDialogWait)dialog;
		dur = d.dur;
	}

	@Override
	public String commandListName() {
		// TODO Auto-generated method stub
		return "Wait Cast";
	}

	@Override
	public String typeName() {
		return type;
	}
}
