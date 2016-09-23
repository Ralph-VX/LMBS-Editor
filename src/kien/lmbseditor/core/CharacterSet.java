package kien.lmbseditor.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import net.arnx.jsonic.JSONException;

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
	
	
}
