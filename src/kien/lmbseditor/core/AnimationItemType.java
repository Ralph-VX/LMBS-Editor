package kien.lmbseditor.core;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import kien.lmbseditor.core.animation.AnimationLMBSDamageTiming;
import kien.util.KienLogger;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import net.arnx.jsonic.TypeReference;

public class AnimationItemType extends BaseItemType {
	public LinkedHashMap<Integer, ArrayList<AnimationLMBSDamageTiming>> data;
	public File src;
	public int maxFrame;
	
	public AnimationItemType(File f) throws JSONException, IOException {
		src = f;
		FileReader fr = new FileReader(src);;
		data = JSON.decode(fr, new TypeReference<LinkedHashMap<Integer, ArrayList<AnimationLMBSDamageTiming>>>(){});
		fr.close();
		this.refreshMax();
	}
	
	public AnimationItemType() {
		src = null;
		data = new LinkedHashMap<Integer, ArrayList<AnimationLMBSDamageTiming>>();
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
	
	public void updateData(int frame, int index, double rectx, double recty, double rectwidth, double rectheight, int dur, double damage, double knockx, double knocky, boolean knockd) {
		ArrayList<AnimationLMBSDamageTiming> list = data.get(frame);
		if (list == null) {
			list = new ArrayList<AnimationLMBSDamageTiming>();
		}
		AnimationLMBSDamageTiming obj= list.remove(index);
		if (obj == null) {
			obj = new AnimationLMBSDamageTiming();
		}
		obj.rect.updateRect(rectx,recty,rectwidth,rectheight);
		obj.dur = dur;
		obj.knockback.setLocation(knockx,knocky);
		obj.knockdir = knockd ? 1 : 0;
		list.add(index, obj);
		data.put(frame, list);
		this.setDirty();
		this.refreshMax();
	}
	
	public void newData(int frame) {
		ArrayList<AnimationLMBSDamageTiming> list = data.get(frame);
		if (list == null) {
			list = new ArrayList<AnimationLMBSDamageTiming>();
		}
		AnimationLMBSDamageTiming obj = new AnimationLMBSDamageTiming();
		list.add(obj);
		data.put(frame, list);
		this.setDirty();
		this.refreshMax();
	}
	
	public void deleteData(int frame, int index) {
		ArrayList<AnimationLMBSDamageTiming> list = data.get(frame);
		list.remove(index);
		data.put(frame, list);
		this.setDirty();
		this.refreshMax();
	}
	
	public void refreshMax() {
		this.maxFrame = Collections.max(data.keySet());
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
