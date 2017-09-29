package kien.lmbseditor.core.animation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import kien.lmbseditor.core.EditorProperty;
import kien.lmbseditor.mv.Animation;

public class AnimationLMBSProperty {

	static public LinkedHashMap<String, Class<? extends AnimationLMBSTimingBase>> timingTypeToClass;
	static public LinkedHashMap<Class<? extends AnimationLMBSTimingBase>, String> classToTimingType;
	
	public AnimationCoordinateObject x;
	public AnimationCoordinateObject y;
	public int animationId;
	public int delay;
	public boolean mirror;
	public boolean follow;
	public ArrayList<ArrayList<AnimationLMBSTimingBase>> timing;
	
	public AnimationLMBSProperty() {
		x = new AnimationCoordinateObject();
		y = new AnimationCoordinateObject();
		timing = new ArrayList<ArrayList<AnimationLMBSTimingBase>>();
		animationId = 0;
		delay = 0;
		mirror = false;
		follow = false;
	}
	
	public void addTiming(int frameNumber, AnimationLMBSTimingBase timing) {
		this.timing.get(frameNumber).add(timing);
		Collections.sort(this.timing.get(frameNumber), new TimingComparator());
	}
	
	public void updateAnimatinoId(int id) {
		this.animationId = id;
		this.refreshTimingList();
	}
	
	private void refreshTimingList() {
		Animation anim = EditorProperty.animations.get(this.animationId);
		if (anim == null) {
			this.timing.clear();
		} else {
			if (this.timing.size() > anim.frames.size()) {
				while (this.timing.size() > anim.frames.size()){
					this.timing.remove(anim.frames.size());
				}
			} else {
				while (this.timing.size() < anim.frames.size()) {
					this.timing.add(new ArrayList<AnimationLMBSTimingBase>());
				}
			}
		}
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
				return classToTimingType.get(o1.getClass()).compareTo(classToTimingType.get(o2.getClass()));
			} catch (Exception e) {
				return 0;
			}
		}
		
	}
	
}
