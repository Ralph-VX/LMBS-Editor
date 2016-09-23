package kien.lmbseditor.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
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
		Date d = new Date();
		motionTypeToClass = new LinkedHashMap<String, Class<? extends SkillMotionCommandBase>>();
		motionNameToType = new LinkedHashMap<String, String>();

		Reflections reflections = new Reflections("kien.lmbseditor.core.motion");
		Set<Class<? extends SkillMotionCommandBase>> set = reflections.getSubTypesOf(SkillMotionCommandBase.class);
		ArrayList<Class< ? extends SkillMotionCommandBase>> list = new ArrayList<Class< ? extends SkillMotionCommandBase>>();
		list.addAll(set);
		list.sort(new Comparator<Class>() {

			@Override
			public int compare(Class o1, Class o2) {
				// TODO Auto-generated method stub
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
				// TODO Auto-generated catch block
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
				obj = motionTypeToClass.get(type).newInstance();
				obj.setProperty(jsonobj);
				out.add(obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				KienLogger.error("Error occured while resolving type \"" + type + "\":", e);
			}
		}
		out.add(new SkillMotionCommandEmpty());
		return out;
	}
}
