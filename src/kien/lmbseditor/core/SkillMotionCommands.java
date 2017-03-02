package kien.lmbseditor.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Set;

import org.reflections.Reflections;

import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import kien.lmbseditor.core.motion.SkillMotionCommandEmpty;
import kien.util.KienLogger;

public class SkillMotionCommands {
	static public LinkedHashMap<String, Class<? extends SkillMotionCommandBase>> motionTypeToClass;
	static public LinkedHashMap<String, String> motionNameToType;
	
	static {
		init();
	}
	
	static public void init() {
		motionTypeToClass = new LinkedHashMap<String, Class<? extends SkillMotionCommandBase>>();
		motionNameToType = new LinkedHashMap<String, String>();

		Reflections reflections = new Reflections("kien.lmbseditor.core.motion");
		Set<Class<? extends SkillMotionCommandBase>> set = reflections.getSubTypesOf(SkillMotionCommandBase.class);
		ArrayList<Class< ? extends SkillMotionCommandBase>> list = new ArrayList<Class< ? extends SkillMotionCommandBase>>();
		list.addAll(set);
		list.sort(new Comparator<Class< ? extends SkillMotionCommandBase>>() {

			@Override
			public int compare(Class< ? extends SkillMotionCommandBase> o1, Class< ? extends SkillMotionCommandBase> o2) {
				return o1.getName().compareTo(o2.getName());
			}
			
		});
		for (Class<? extends SkillMotionCommandBase> klass : list) {
			try {
				SkillMotionCommandBase obj = klass.newInstance();
				if (obj.includeAvailable()){
					motionTypeToClass.put(obj.typeName(), klass);
					motionNameToType.put(obj.commandListName(), obj.typeName());
				}
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	static public ArrayList<SkillMotionCommandBase> convertJsonToObjectList(ArrayList<LinkedHashMap<String, Object>> src) {
		ArrayList<SkillMotionCommandBase> out = new ArrayList<SkillMotionCommandBase>();
		for (LinkedHashMap<String, Object> jsonobj : src) {
			String type = (String)jsonobj.get("type");
			SkillMotionCommandBase obj = null;
			try {
				if (motionTypeToClass.get(type) != null) {
					obj = motionTypeToClass.get(type).newInstance();
					obj.setProperty(jsonobj);
					out.add(obj);
				} else {
					KienLogger.logger.severe("Failed to load a json object:\n  Missing property \"type\"");
				}
			} catch (Exception e) {
				KienLogger.error("Error occured while resolving type \"" + type + "\":", e);
			}
		}
		out.add(new SkillMotionCommandEmpty());
		return out;
	}
}
