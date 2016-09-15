package kien.lmbseditor.core;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import net.arnx.jsonic.TypeReference;

public class AnimationItemType extends BaseItemType {
	public LinkedHashMap<Integer, ArrayList<AnimationObject>> data;
	public File src;
	public boolean dirty;
	public int maxFrame;
	
	public AnimationItemType(String filename) throws JSONException, IOException {
		src = new File(filename);
		FileReader fr = new FileReader(src);;
		data = JSON.decode(fr, new TypeReference<LinkedHashMap<Integer, ArrayList<AnimationObject>>>(){});
		fr.close();
		this.refreshMax();
		dirty = false;
	}
	
	public AnimationItemType() {
		src = null;
		data = new LinkedHashMap<Integer, ArrayList<AnimationObject>>();
	}
	
	public void save() throws IOException {
		if (src != null) {
			FileWriter fw = new FileWriter(src);
			JSON.encode(data, fw, true);
		}
	}
	
	public String getFilename() {
		return src.getName();
	}
	
	public String getListname() {
		return "Animation - " + this.getFilename();
	}
	
	public boolean isDirty() {
		return dirty;
	}
	
	public void setDirty() {
		dirty = true;
	}
	
	public boolean haveFile() {
		return src == null;
	}
	
	public void setFile(File f) {
		src = f;
	}
	
	public void updateData(int frame, int index, float rectx, float recty, float rectwidth, float rectheight, int dur, float damage, float knockx, float knocky, boolean knockd) {
		ArrayList<AnimationObject> list = data.get(frame);
		if (list == null) {
			list = new ArrayList<AnimationObject>();
		}
		AnimationObject obj= list.remove(index);
		if (obj == null) {
			obj = new AnimationObject();
		}
		obj.rect.setRect(rectx,recty,rectwidth,rectheight);
		obj.dur = dur;
		obj.knockback.setLocation(knockx,knocky);
		obj.knockdir = knockd ? 1 : 0;
		list.add(index, obj);
		data.put(frame, list);
		this.setDirty();
		this.refreshMax();
	}
	
	public void newData(int frame) {
		ArrayList<AnimationObject> list = data.get(frame);
		if (list == null) {
			list = new ArrayList<AnimationObject>();
		}
		AnimationObject obj = new AnimationObject();
		list.add(obj);
		data.put(frame, list);
		this.setDirty();
		this.refreshMax();
	}
	
	public void deleteData(int frame, int index) {
		ArrayList<AnimationObject> list = data.get(frame);
		list.remove(index);
		data.put(frame, list);
		this.setDirty();
		this.refreshMax();
	}
	
	public void refreshMax() {
		this.maxFrame = Collections.max(data.keySet());
	}
	
}
