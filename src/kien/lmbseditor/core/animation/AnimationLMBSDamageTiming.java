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
public class AnimationLMBSDamageTiming {
	public Rectangle rect;
	public int dur;
	public int interval;
	public double damagePer;
	public Point2D.Double knockback;
	public int knockdir;
	
	public AnimationLMBSDamageTiming() {
		this.rect = new Rectangle();
		this.dur = 0;
		this.damagePer = 0;
		this.knockback = new Point2D.Double();
		this.knockdir = 0;
		this.interval = 1;
	}
	
	public LinkedHashMap<String, Object> obtainPropertyList() {
		try {
			return JSON.decode(this.getClass().getResourceAsStream("animationTimingDamage.json"));
		} catch (JSONException | IOException e) {
			e.printStackTrace();
			
		}
		return new LinkedHashMap<String, Object>();
	}
	
	public LinkedHashMap<String, Object> obtainDataList() {
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
	
}
