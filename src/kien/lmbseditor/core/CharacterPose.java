package kien.lmbseditor.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;


public class CharacterPose {
	public String poseName;
	public File poseFile;
	public PoseProperty property;
	public File propertyFile;
	/**
	 * width of pose read from filename.
	 */
	public int width;
	/**
	 * height of pose read from filename
	 */
	public int height;
	/**
	 * frame count of pose read from filename.
	 */
	public int frame;
	/**
	 * Loop property of the pose
	 */
	public boolean loop;
	/**
	 * Initialize a new pose with a pose name;
	 * Currently not supported.
	 * @param name
	 */
	public CharacterPose(String name) {
		poseName = name;
		poseFile = null;
		property = new PoseProperty();
		propertyFile = null;
		width = -1;
		height = -1;
		frame = 1;
	}
	
	public void saveJson() {
		if (propertyFile == null) {
			propertyFile = new File(poseFile.getParent() + "\\" + poseName + ".json");
		}
		try {
			JSON.encode(property, new FileWriter(propertyFile), true);
			this.clearDirty();
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean haveJsonFile() {
		return this.propertyFile != null;
	}
	
	public boolean isDirty() {
		return property.isDirty();
	}
	
	public void clearDirty() {
		property.clearDirty();
	}
	
	public void applyPoseProperty() {
		if (propertyFile == null) {
			property.setMaxFrame(this.frame);
			property.loop = this.loop;
			if (this.width >= 0) {
				property.setWidth(this.width);
			}
			if (this.height >= 0) {
				property.setHeight(this.height);
			}
		}
	}
	
}
