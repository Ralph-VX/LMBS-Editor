package kien.lmbseditor.core;

import java.util.LinkedHashMap;

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
	}
	
}
