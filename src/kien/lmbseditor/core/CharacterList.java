package kien.lmbseditor.core;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CharacterList {
	public LinkedHashMap<String, CharacterSet> lists;
	
	public CharacterList() {
		lists = new LinkedHashMap<String, CharacterSet>();
	}
	
	public boolean isDirty() {
		ArrayList<CharacterSet> charlist = (ArrayList<CharacterSet>) lists.values();
		for (int n = 0; n < charlist.size(); n++ ) {
			if (charlist.get(n).isDirty()) {
				return true;
			}
		}
		return false;
	}
	
}
