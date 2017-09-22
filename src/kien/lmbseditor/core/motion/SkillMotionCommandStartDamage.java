package kien.lmbseditor.core.motion;

import java.awt.geom.Point2D;
import java.util.LinkedHashMap;

import kien.lmbseditor.window.motion.MotionPropertyDialogBase;
import kien.lmbseditor.window.motion.MotionPropertyDialogStartDamage;
import kien.util.Rectangle;

public class SkillMotionCommandStartDamage extends SkillMotionCommandBase {
	
	public final String type = "startdamage";
	public Rectangle rect;
	public double damage;
	public Point2D.Double knockback;
	public int knockdir;
	
	public SkillMotionCommandStartDamage() {
		knockback = new Point2D.Double();
		rect = new Rectangle();
		knockdir = 0;
		damage = 1;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		LinkedHashMap<String, Object> jrect = (LinkedHashMap<String, Object>) list.get("rect");
		rect.x = ((Number)jrect.get("x")).doubleValue();
		rect.y = ((Number)jrect.get("y")).doubleValue();
		rect.width = ((Number)jrect.get("width")).doubleValue();
		rect.height = ((Number)jrect.get("height")).doubleValue();
		LinkedHashMap<String, Object> jpoint = (LinkedHashMap<String, Object>) list.get("knockback");
		knockback.x = ((Number)jpoint.get("x")).doubleValue();
		knockback.y = ((Number)jpoint.get("y")).doubleValue();
		knockdir = ((Number)list.get("knockdir")).intValue();
		damage = ((Number)list.get("damage")).doubleValue();
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ": " + damage + "%, rect: " + rect.x + ", " + rect.y + ", " + rect.width + ", " + rect.height + " knockback: " + knockback.x + ", " + knockback.y + " inverted: " + (knockdir > 0 ? "true" : "false") ;
	}

	@Override
	public String commandListName() {
		return "Start Damage";
	}

	@Override
	public String typeName() {
		return type;
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("x", this.rect.x);
		map.put("y", this.rect.y);
		map.put("width", this.rect.width);
		map.put("height", this.rect.height);
		map.put("knockbackx", this.knockback.x);
		map.put("knockbacky", this.knockback.y);
		map.put("knockdir", this.knockdir > 0);
		map.put("damage", this.damage);
		return map;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		this.rect.x = (double) data.get("x");
		this.rect.y = (double) data.get("y");
		this.rect.width = (double) data.get("width");
		this.rect.height = (double) data.get("height");
		this.knockback.x = (double) data.get("knockbackx");
		this.knockback.y = (double) data.get("knockbacky");
		this.knockdir = (boolean) data.get("knockdir") ? 1 : 0;
		this.damage = (double) data.get("damage");
	}

}
