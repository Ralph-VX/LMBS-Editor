package kien.lmbseditor.core;

import java.io.File;

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

}
