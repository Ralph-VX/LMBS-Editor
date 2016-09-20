package kien.lmbseditor.core;

import java.io.File;

public abstract class BaseItemType {
	
	private boolean dirty;
	
	public abstract String getFilename();

	public abstract String getListname();
	
	public abstract boolean haveFile();
	
	public abstract void setFile(File f);
	
	public boolean isDirty() {
		return this.dirty;
	}
	
	public void setDirty() {
		this.dirty = true;
	}
	
	public void clearDirty() {
		this.dirty = false;
	}
	
}
