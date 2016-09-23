package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogWait;

public class SkillMotionCommandSetHitStop extends SkillMotionCommandBase {

	public final String type = "sethitstop";
	public int length;
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		length = ((Number)list.get("length")).intValue();
	}

	@Override
	public MotionPropertyDialogBase obtainDialog() {
		MotionPropertyDialogWait d = new MotionPropertyDialogWait(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void setObject(SkillMotionCommandBase object) {
				SkillMotionCommandSetHitStop src = (SkillMotionCommandSetHitStop)object;
				this.textField.setValue(src.length);
			}
		};
		d.setTitle("Set Hit Stop");
		d.textField.setToolTipText("The length of hit stop when following damage occurs");
		d.labelTitle.setText("Length");
		d.labelTitle.setToolTipText("The length of hit stop when following damage occurs");
		return d;
	}

	@Override
	public String obtainCommandRepresentation() {
		// TODO Auto-generated method stub
		return indentString() + "Åü" + commandListName() + ": " + length + " Frames";
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		MotionPropertyDialogWait d = (MotionPropertyDialogWait)dialog;
		length = d.dur;
	}

	@Override
	public String commandListName() {
		// TODO Auto-generated method stub
		return "Set Hit Stop";
	}

	@Override
	public String typeName() {
		return type;
	}
}
