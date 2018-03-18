package kien.lmbseditor.core.motion;

import java.awt.geom.Point2D;
import java.util.LinkedHashMap;

import kien.util.Rectangle;

public class SkillMotionCommandStartDamage extends SkillMotionCommandBase {
	
	public final String type = "startdamage";
	public Rectangle rect;
	public double damage;
	public Point2D.Double knockback;
	public int knockdir;
	public int knocklength;
	
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
		knocklength = ((Number)list.get("knocklength")).intValue();
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
		map.put("knocklength", this.knocklength);
		return map;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> list) {
		this.rect.x = ((Number)list.get("x")).doubleValue();
		this.rect.y = ((Number)list.get("y")).doubleValue();
		this.rect.width = ((Number)list.get("width")).doubleValue();
		this.rect.height = ((Number)list.get("height")).doubleValue();
		this.damage = ((Number)list.get("damage")).doubleValue();
		this.knockback.x = ((Number)list.get("knockbackx")).doubleValue();
		this.knockback.y = ((Number)list.get("knockbacky")).doubleValue();
		this.knockdir = ((boolean) list.get("knockdir") ? 1 : 0);
		this.knocklength = ((Number)list.get("knocklength")).intValue();
	}

}
