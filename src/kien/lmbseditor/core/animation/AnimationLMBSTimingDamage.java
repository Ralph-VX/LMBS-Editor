package kien.lmbseditor.core.animation;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.LinkedHashMap;

import kien.util.Rectangle;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

/**
 * An object describe single object inside animation descriptor
 * @author Kien-PC
 * 
 */
public class AnimationLMBSTimingDamage extends AnimationLMBSTimingBase {
	public Rectangle rect;
	public int dur;
	public int interval;
	public double damagePer;
	public Point2D.Double knockback;
	public int knockdir;
	public String type = "damage";
	
	public AnimationLMBSTimingDamage() {
		this.rect = new Rectangle();
		this.dur = 0;
		this.damagePer = 0;
		this.knockback = new Point2D.Double();
		this.knockdir = 0;
		this.interval = 1;
	}
	
	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		try {
			return JSON.decode(this.getClass().getResourceAsStream("animationTimingDamage.json"));
		} catch (JSONException | IOException e) {
			e.printStackTrace();
			
		}
		return new LinkedHashMap<String, Object>();
	}
	
	@Override
	public LinkedHashMap<String, Object> obtainData() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("x", rect.x);
		map.put("y", rect.y);
		map.put("width", rect.width);
		map.put("height", rect.height);
		map.put("knockbackx", knockback.x);
		map.put("knockbacky", knockback.y);
		map.put("knockdir", this.knockdir > 0);
		map.put("damage", damagePer);
		map.put("interval", interval);
		return map;
	}

	@Override
	public void updateData(LinkedHashMap<String, Object> data) {
		this.rect.x = ((Number)data.get("x")).doubleValue();
		this.rect.y = ((Number)data.get("y")).doubleValue();
		this.rect.width = ((Number)data.get("width")).doubleValue();
		this.rect.height = ((Number)data.get("height")).doubleValue();
		this.knockback.x = ((Number)data.get("knockbackx")).doubleValue();
		this.knockback.y = ((Number)data.get("knockbacky")).doubleValue();
		this.knockdir = (boolean)data.get("knockdir") ? 1 : 0;
		this.damagePer = ((Number)data.get("damage")).doubleValue();
		this.interval = ((Number)data.get("interval")).intValue();
	}
	
	@Override
	public String obtainRepresentingString() {
		return "Rect: " + rect.x + ", " + rect.y + ", " + rect.width + ", " + rect.height;
	}
}
