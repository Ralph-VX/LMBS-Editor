package kien.lmbseditor.core;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import net.arnx.jsonic.TypeReference;

public class WeaponList {
	public LinkedHashMap<String, WeaponSet> list;
	public WeaponSet current;
	
	public WeaponList() {
		list = new LinkedHashMap<String, WeaponSet>();
		current = null;
	}
	
	public boolean isDirty() {
		for (WeaponSet set : list.values()) {
			if (set.isDirty()) {
				return true;
			}
		}
		return false; 
	}

	public void addFile(File f) {
		String filename = f.getName();
		String name = filename.replaceFirst("[.][^.]+$", "");
		WeaponSet set = list.get(name);
		if (set == null) {
			set = new WeaponSet();
		}
		set.name = name;
		if (filename.endsWith(".json")) {
			set.jsonFile = f;
			try {
				set.json = JSON.decode(new FileReader(f), new TypeReference<WeaponProperty>() {} );
			} catch (JSONException | IOException e) {
				e.printStackTrace();
				set.json = new WeaponProperty();
			}
		} else {
			set.imageFile = f;
		}
		if (set.jsonFile == null) {
			set.makeJSON();
		}
		list.put(name, set);
	}

	public void save() {
		for (WeaponSet set : list.values()) {
			set.save();
		}
	}
	
}
