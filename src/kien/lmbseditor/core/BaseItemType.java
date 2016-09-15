package kien.lmbseditor.core;

import java.io.File;

public abstract class BaseItemType {
	
	public abstract String getFilename();

	public abstract String getListname();
	
	public abstract boolean isDirty();
	
	public abstract boolean haveFile();
	
	public abstract void setFile(File f);
}
