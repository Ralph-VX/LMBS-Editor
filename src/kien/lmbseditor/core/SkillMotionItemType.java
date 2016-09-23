package kien.lmbseditor.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import kien.lmbseditor.core.motion.SkillMotionCommandBase;
import kien.lmbseditor.core.motion.SkillMotionCommandEmpty;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

public class SkillMotionItemType extends BaseItemType {
	
	private File src;
	public ArrayList<SkillMotionCommandBase> list;
	
	public SkillMotionItemType(File file) throws JSONException, FileNotFoundException, IOException {
		src = file;
		ArrayList<LinkedHashMap<String, Object>> json;
		json = JSON.decode(new FileReader(src));
		this.list = SkillMotionCommands.convertJsonToObjectList(json);
	}
	
	public SkillMotionItemType() {
		src = null;
		this.list = new ArrayList<SkillMotionCommandBase>();
		list.add(new SkillMotionCommandEmpty());
		this.setDirty();
	}
	
	@Override
	public String getFilename() {
		// TODO Auto-generated method stub
		return haveFile() ? src.getName() : "Unnamed" ;
	}

	@Override
	public String getListname() {
		// TODO Auto-generated method stub
		return "Skill - " + this.getFilename();
	}

	@Override
	public boolean isDirty() {
		for (SkillMotionCommandBase i : list) {
			if (i.isDirty()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean haveFile() {
		return src != null;
	}

	@Override
	public void setFile(File f) {
		this.src = f;
	}

	@Override
	public File getFile() {
		return src;
	}
	
	@Override
	public void setDirty() {
	}
	
	@Override
	public void clearDirty() {
		for (SkillMotionCommandBase i : list) {
			i.clearDirty();
		}
	}

	@Override
	public void saveFile() {
		ArrayList<SkillMotionCommandBase> output = new ArrayList<SkillMotionCommandBase>();
		for (SkillMotionCommandBase item : list) {
			if (item.includeJSON()) {
				output.add(item);
			}
		}
		
		try {
			JSON.encode(output, new FileWriter(src), true);
			this.clearDirty();
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
