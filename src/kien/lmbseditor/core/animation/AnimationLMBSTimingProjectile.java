package kien.lmbseditor.core.animation;

import java.io.IOException;
import java.util.LinkedHashMap;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

public class AnimationLMBSTimingProjectile extends AnimationLMBSTimingBase {
	
	public String classname;
	public String parameters;
	public String type = "projectile";
	
	public AnimationLMBSTimingProjectile() {
		classname = "";
		parameters = "";
	}

	@Override
	public LinkedHashMap<String, Object> obtainData() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("classname", classname);
		map.put("parameters", parameters);
		return map;
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
	public void updateData(LinkedHashMap<String, Object> data) {
		this.classname = (String) data.get("classname");
		this.parameters = (String) data.get("parameters");
	}

	@Override
	public String obtainRepresentingString() {
		return "Projectile: " + classname + ", " + parameters;
	}

	@Override
	public void loadJSON(LinkedHashMap<String, Object> data) {
		this.updateData(data);
	}

}
