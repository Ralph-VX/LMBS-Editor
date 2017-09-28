package kien.lmbseditor.core.animation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class AnimationLMBSProperty {

	static public LinkedHashMap<String, Class<? extends AnimationLMBSTimingBase>> timingTypeToClass;
	static public LinkedHashMap<Class<? extends AnimationLMBSTimingBase>, String> classToTimingType;
	
	public AnimationCoordinateObject x;
	public AnimationCoordinateObject y;
	public int animationId;
	public int delay;
	public boolean mirror;
	public boolean follow;
	public ArrayList<AnimationLMBSTimingBase> timing;
	
	public AnimationLMBSProperty() {
		x = new AnimationCoordinateObject();
		y = new AnimationCoordinateObject();
		timing = new ArrayList<AnimationLMBSTimingBase>();
		animationId = 0;
		delay = 0;
		mirror = false;
		follow = false;
	}
	
	
	static {
		timingTypeToClass = new LinkedHashMap<String, Class<? extends AnimationLMBSTimingBase>>();
		timingTypeToClass.put("damage", AnimationLMBSTimingDamage.class);
		timingTypeToClass.put("projectile", AnimationLMBSTimingProjectile.class);
		classToTimingType = new LinkedHashMap<Class<? extends AnimationLMBSTimingBase>, String>();
		for (Entry<String, Class<? extends AnimationLMBSTimingBase>> set : timingTypeToClass.entrySet()) {
			classToTimingType.put(set.getValue(), set.getKey());
		}
	}
	
}
