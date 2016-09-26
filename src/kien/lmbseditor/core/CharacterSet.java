package kien.lmbseditor.core;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Class represents a set of pose for a character.
 * @author Kien-PC
 *
 */
public class CharacterSet {
	/**
	 * Name of the directory
	 */
	public String characterName;
	
	public LinkedHashMap<String, CharacterPose> poses;
	
	public CharacterSet() {
		poses = new LinkedHashMap<String, CharacterPose>();
	}
	
	public boolean isDirty() {
		Collection<CharacterPose> list =  poses.values();
		for(CharacterPose pose : list) {
			if (pose.isDirty()) {
				return true;
			}
		}
		return false;
	}

	public void save() {
		for (CharacterPose item : poses.values() ) {
			if (item.isDirty()) {
				item.saveJson();
			}
		}
	}

	public void forceSave() {
		for (CharacterPose item : poses.values() ) {
			item.saveJson();
		}
	}
	
}
