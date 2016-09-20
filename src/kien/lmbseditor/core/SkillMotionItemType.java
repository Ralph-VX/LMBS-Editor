package kien.lmbseditor.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

public class SkillMotionItemType extends BaseItemType {
	
	private File src;
	private boolean dirty;
	public ArrayList<SkillMotionCommandBase> list;
	
	public SkillMotionItemType(File file) throws JSONException, FileNotFoundException, IOException {
		src = file;
		ArrayList<LinkedHashMap<String, Object>> json;
		json = JSON.decode(new FileReader(src));
		this.list = SkillMotionCommands.convertJsonToObjectList(json);
	}
	
	@Override
	public String getFilename() {
		// TODO Auto-generated method stub
		return src.getName();
	}

	@Override
	public String getListname() {
		// TODO Auto-generated method stub
		return "Skill - " + src.getName();
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return dirty;
	}

	@Override
	public boolean haveFile() {
		// TODO Auto-generated method stub
		return src != null;
	}

	@Override
	public void setFile(File f) {
		// TODO Auto-generated method stub
		this.src = f;
	}
	
	
	
}
