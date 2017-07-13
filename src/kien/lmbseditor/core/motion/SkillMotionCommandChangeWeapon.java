package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogChangePose;

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
	public MotionPropertyDialogBase obtainDialog() {
		MotionPropertyDialogChangePose d = new MotionPropertyDialogChangePose() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void setObject(SkillMotionCommandBase object) {
				SkillMotionCommandChangeWeapon o = (SkillMotionCommandChangeWeapon)object;
				this.textField.setText(o.name);
				this.result = o.name;
			}
		};
		d.setTitle("Change Weapon");
		d.labelTitle.setText("name");
		d.labelTitle.setToolTipText("The name of weapon image to be changed");
		d.textField.setToolTipText("The name of weapon image to be changed");
		return d;
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ": " + name;
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		MotionPropertyDialogChangePose d = (MotionPropertyDialogChangePose) dialog;
		if (d.result != null) {
			name = d.result;
		} else {
			name = "";
		}
	}

	@Override
	public String commandListName() {
		return "Change Weapon";
	}

	@Override
	public String typeName() {
		return type;
	}
}
