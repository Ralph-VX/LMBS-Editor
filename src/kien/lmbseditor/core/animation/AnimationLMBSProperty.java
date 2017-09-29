package kien.lmbseditor.core.animation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	
	public void addTiming(AnimationLMBSTimingBase timing) {
		this.timing.add(timing);
		Collections.sort(this.timing, new TimingComparator());
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
	
	public class TimingComparator implements Comparator<AnimationLMBSTimingBase> {

		@Override
		public int compare(AnimationLMBSTimingBase o1, AnimationLMBSTimingBase o2) {
			try {
				return classToTimingType.get(o1).compareTo(classToTimingType.get(o2));
			} catch (Exception e) {
				return 0;
			}
		}
		
	}
	
}
