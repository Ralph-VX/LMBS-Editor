package kien.lmbseditor.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.reflections.Reflections;

import com.sun.org.apache.bcel.internal.util.ClassPath;

import kien.lmbseditor.core.motion.SkillMotionCommandApplyDamage;
import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import kien.lmbseditor.core.motion.SkillMotionCommandChangePose;
import kien.lmbseditor.core.motion.SkillMotionCommandWait;
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
		for (Class<? extends SkillMotionCommandBase> klass : reflections.getSubTypesOf(SkillMotionCommandBase.class)) {
			try {
				SkillMotionCommandBase obj = klass.newInstance();
				motionTypeToClass.put(obj.typeName(), klass);
				motionNameToType.put(obj.commandListName(), obj.typeName());
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
		return out;
	}
}
