package kien.lmbseditor.core.animation;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.LinkedHashMap;

import kien.util.Rectangle;
import kien.util.Util;
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
	public int knocklength;
	public String type = "damage";
	
	public AnimationLMBSTimingDamage() {
		this.rect = new Rectangle();
		this.dur = 0;
		this.damagePer = 0;
		this.knockback = new Point2D.Double();
		this.knockdir = 0;
		this.knocklength = 0;
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
		map.put("knocklength", knocklength);
		map.put("dur", this.dur);
		return map;
	}

	@Override
	public void updateData(LinkedHashMap<String, Object> data) {
		this.rect.x = Util.getJSONDouble(data, "x", 0);
		this.rect.y = Util.getJSONDouble(data, "y", 0);
		this.rect.width = Util.getJSONDouble(data, "width", 0);
		this.rect.height = Util.getJSONDouble(data, "height", 0);
		this.knockback.x = Util.getJSONDouble(data, "knockbackx", 0);
		this.knockback.y = Util.getJSONDouble(data, "knockbacky", 0);
		this.knockdir = Util.getJSONBoolean(data, "knockdir", false) ? 1 : 0;
		this.damagePer = Util.getJSONDouble(data, "damage", 0);
		this.interval = Util.getJSONInt(data, "interval", 0);
		this.knocklength = Util.getJSONInt(data, "knocklength", 0);
		this.dur = Util.getJSONInt(data, "dur", 1);
	}
	
	@Override
	public String obtainRepresentingString() {
		return "Rect: " + rect.x + ", " + rect.y + ", " + rect.width + ", " + rect.height;
	}

	@Override
	public void loadJSON(LinkedHashMap<String, Object> data) {
		try {
			this.damagePer = Util.getJSONDouble(data, "damagePer", 0);
			this.dur = Util.getJSONInt(data, "dur", 1);
			this.interval = Util.getJSONInt(data, "interval", -1);
			this.knockdir = Util.getJSONInt(data, "knockdir", 0);
			this.knocklength = Util.getJSONInt(data, "knocklength", 0);
			LinkedHashMap<String, Object> r = (LinkedHashMap<String, Object>) data.get("rect");
			this.rect.x = Util.getJSONDouble(r, "x", 0);
			this.rect.y = Util.getJSONDouble(r, "y", 0);
			this.rect.width = Util.getJSONDouble(r, "width", 0);
			this.rect.height = Util.getJSONDouble(r, "height", 0);
			LinkedHashMap<String, Object> k = (LinkedHashMap<String, Object>) data.get("knockback");
			this.knockback.x = Util.getJSONDouble(k, "x", 0);
			this.knockback.y = Util.getJSONDouble(k, "y", 0);
		} catch (Exception e) {
			this.rect = new Rectangle();
			this.dur = 0;
			this.damagePer = 0;
			this.knockback = new Point2D.Double();
			this.knockdir = 0;
			this.knocklength = 0;
			this.interval = 1;
		}

	}
}
