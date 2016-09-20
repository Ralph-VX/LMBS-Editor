package kien.lmbseditor.core.motion;

import java.awt.geom.Point2D;
import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogApplyDamage;
import kien.lmbseditor.window.motion.MotionPropertyDialogBase;

public class SkillMotionCommandApplyDamage extends SkillMotionCommandBase {

	public final String type = "applydamage";
	public double damage;
	public Point2D.Double knockback;
	public int knockdir;
	
	public SkillMotionCommandApplyDamage() {
		super();
		knockback = new Point2D.Double();
	}
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws IllegalArgumentException {
		try {
		damage = ((Number)list.get("damage")).doubleValue();
		knockback = new Point2D.Double();
		knockback.x = ((Number)((LinkedHashMap<String, Object>)list.get("knockback")).get("x")).doubleValue();
		knockback.y = ((Number)((LinkedHashMap<String, Object>)list.get("knockback")).get("y")).doubleValue();
		knockdir = ((Number)list.get("damage")).intValue();
		} catch (Exception e) {
			throw new IllegalArgumentException("Provided JSON Object is not available!");
		}
	}

	@Override
	public Class<? extends MotionPropertyDialogBase> obtainDialogClass() {
		// TODO Auto-generated method stub
		return MotionPropertyDialogApplyDamage.class;
	}

	@Override
	public String obtainCommandRepresentation() {
		// TODO Auto-generated method stub
		return commandListName() + ": " + damage*100 + "% damage, knockback: " + knockback.x + ", " + knockback.y + ", Inverted: " + (knockdir > 0 ? "true" : "false");
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		MotionPropertyDialogApplyDamage d = (MotionPropertyDialogApplyDamage) dialog;
		this.damage = d.damage;
		this.knockback = d.knockback;
		this.knockdir = d.knockdir;
		this.setDirty();
	}

	@Override
	public String commandListName() {
		// TODO Auto-generated method stub
		return "Apply Damage";
	}
	
	@Override
	public String typeName() {
		return type;
	}
	
}
