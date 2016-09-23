package kien.lmbseditor.core.motion;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
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
	public MotionPropertyDialogBase obtainDialog() {
		// TODO Auto-generated method stub
		return new MotionPropertyDialogStartDamage();
	}

	@Override
	public String obtainCommandRepresentation() {
		// TODO Auto-generated method stub
		return indentString() + "Ÿ" + commandListName() + ": " + damage + "%, rect: " + rect.x + ", " + rect.y + ", " + rect.width + ", " + rect.height + " knockback: " + knockback.x + ", " + knockback.y + " inverted: " + (knockdir > 0 ? "true" : "false") ;
	}

	@Override
	public void updateProperty(MotionPropertyDialogBase dialog) {
		MotionPropertyDialogStartDamage d = (MotionPropertyDialogStartDamage)dialog;
		rect.x = d.rx;
		rect.y = d.ry;
		rect.width = d.rw;
		rect.height = d.rh;
		knockback.x = d.kx;
		knockback.y = d.ky;
		damage = d.dm;
		knockdir = d.ki;
		
	}

	@Override
	public String commandListName() {
		// TODO Auto-generated method stub
		return "Start Damage";
	}

	@Override
	public String typeName() {
		// TODO Auto-generated method stub
		return type;
	}

}
