package kien.lmbseditor.core;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import kien.lmbseditor.core.animation.AnimationLMBSProperty;
import kien.lmbseditor.core.animation.AnimationLMBSTimingDamage;
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
		FileReader fr = new FileReader(src);;
		data = JSON.decode(fr, new TypeReference<AnimationLMBSProperty>(){});
		fr.close();
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
