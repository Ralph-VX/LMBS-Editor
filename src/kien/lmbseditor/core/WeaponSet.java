package kien.lmbseditor.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

public class WeaponSet {
	
	public File imageFile;
	public File jsonFile;
	public String name;
	public WeaponProperty json;
	private boolean dirty;

	public WeaponSet() {
		imageFile = null;
		jsonFile = null;
		name = "";
		json = new WeaponProperty();
	}
	
	public boolean isDirty() {
		return dirty;
	}
	
	public void setDirty() {
		dirty = true;
	}
	
	public void clearDirty() {
		dirty = false;
	}

	public void save() {
		try {
			JSON.encode(json, new FileWriter(jsonFile), true);
			this.clearDirty();
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
	}

	public void makeJSON() {
		if (jsonFile == null && imageFile != null) {
			jsonFile = new File(imageFile.getParent() +  "\\"  + name + ".json");
		}
		this.setDirty();
	}

}
