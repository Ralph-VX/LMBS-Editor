package kien.lmbseditor.core.animation;

import java.util.LinkedHashMap;

public abstract class AnimationLMBSTimingBase {
	public abstract LinkedHashMap<String, Object> obtainData();
	
	public abstract LinkedHashMap<String, Object> obtainPropertyList();
	
	public abstract void updateData(LinkedHashMap<String, Object> data);
	
}
