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
		damage = 1;
		knockdir = 0;
	}
	
	@SuppressWarnings("unchecked")
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
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ": " + damage*100 + "% damage, knockback: " + knockback.x + ", " + knockback.y + ", Inverted: " + (knockdir > 0 ? "true" : "false");
	}

	@Override
	public String commandListName() {
		return "Apply Damage";
	}
	
	@Override
	public String typeName() {
		return type;
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, Object> prop = new LinkedHashMap<String, Object>();
		prop.put("damage", this.damage);
		prop.put("knockbackx", this.knockback.x);
		prop.put("knockbacky", this.knockback.y);
		prop.put("knockdir", this.knockdir > 0);
		return prop;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		this.damage = (double) data.get("damage");
		this.knockback.x = (double) data.get("knockbackx");
		this.knockback.y = (double) data.get("knockbacky");
		this.knockdir = ((boolean) data.get("knockdir") ? 1 : 0);
	}
	
}
