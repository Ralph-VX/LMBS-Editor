package kien.lmbseditor.core;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import kien.lmbseditor.core.animation.AnimationLMBSProperty;
import kien.lmbseditor.core.animation.AnimationLMBSTimingBase;
import kien.util.KienLogger;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import net.arnx.jsonic.TypeReference;

public class AnimationItemType extends BaseItemType {
	public AnimationLMBSProperty data;
	public File src;
	public int maxFrame;
	
	public AnimationItemType(File f) throws JSONException, IOException {
		src = f;
		this.loadData(f);
	}
	
	@SuppressWarnings("unchecked")
	private void loadData(File f) throws JSONException, IOException {
		FileReader src = new FileReader(f);
		this.data = JSON.decode(src,new TypeReference<AnimationLMBSProperty>() {});
		src.close();
		src = new FileReader(f);
		LinkedHashMap<String, Object> json = JSON.decode(src);
		LinkedHashMap<String, ArrayList<Object>> timing = (LinkedHashMap<String, ArrayList<Object>>) json.get("timing");
		for (String frameNumber : timing.keySet()) {
			ArrayList<AnimationLMBSTimingBase> list = new ArrayList<AnimationLMBSTimingBase>();
			ArrayList<Object> jsonlist = timing.get(frameNumber);
			for (Object obj : jsonlist) {
				try {
					LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>)obj;
					AnimationLMBSTimingBase b = AnimationLMBSProperty.loadJSON(data);
					if (b != null) {
						list.add(b);
					}
				} catch (Exception e) {
					
				}
			}
			this.data.timing.put(Integer.valueOf(frameNumber), list);
		}
		src.close();
	}
	
	public AnimationItemType() {
		src = null;
		data = new AnimationLMBSProperty();
		this.setDirty();
	}
	
	public void save() throws IOException {
		if (src != null) {
			FileWriter fw = new FileWriter(src);
			JSON.encode(data, fw, !EditorProperty.useMinifiedJson);
		}
	}
	
	@Override
	public String getFilename() {
		return haveFile() ? src.getName() : "Unnamed" ;
	}
	
	@Override
	public String getListname() {
		return "Animation - " + this.getFilename();
	}
	
	@Override
	public boolean haveFile() {
		return src != null;
	}
	
	@Override
	public void setFile(File f) {
		src = f;
	}
	
	@Override
	public File defaultDirectory() {
		return new File(EditorProperty.projectDirectory + "\\data\\animations");
	}
	
	@Override
	public File getFile() {
		return src;
	}
	
	@Override
	public void saveFile() {
		KienLogger.logger.info(this.data.toString());
		try {
			JSON.encode(this.data, new FileWriter(src), !EditorProperty.useMinifiedJson);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		this.clearDirty();
	}
	
}
