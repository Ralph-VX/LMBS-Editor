package kien.lmbseditor.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

public class CharacterList {
	public LinkedHashMap<String, CharacterSet> lists;
	
	public CharacterList() {
		lists = new LinkedHashMap<String, CharacterSet>();
	}
	
	public boolean isDirty() {
		for (CharacterSet chara : lists.values()) {
			if (chara.isDirty()) {
				return true;
			}
		}
		return false;
	}

	public void save() {
		for (CharacterSet chara : lists.values()) {
			chara.save();
		}
		saveList();
	}

	public void forceSave() {
		for (CharacterSet chara : lists.values()) {
			chara.forceSave();
		}
		saveList();
	}
	
	private void saveList() {
		LinkedHashMap<String, ArrayList<String>> list = new LinkedHashMap<String, ArrayList<String>>();
		for (String charName : lists.keySet()) {
			ArrayList<String> poses = new ArrayList<String>();
			CharacterSet set = lists.get(charName);
			poses.addAll(set.poses.keySet());
			list.put(charName, poses);
		}
		try {
			JSON.encode(list, new FileWriter(new File(EditorProperty.projectDirectory + "\\data\\characterList.json")), true);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
