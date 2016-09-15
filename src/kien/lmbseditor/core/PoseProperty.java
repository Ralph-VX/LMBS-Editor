package kien.lmbseditor.core;

import java.util.ArrayList;

/**
 * Contents of the json file for character poses.
 * @author Kien-PC
 *
 */
public class PoseProperty {
	/**
	 * The amount of frames in this pose.
	 */
	public int frameCount;
	/**
	 * Loop property of the pose.
	 */
	public boolean loop;
	
	public ArrayList<PoseFrameProperty> frames;
	
	private boolean dirty;
	
	public PoseProperty() {
		this.frameCount = 0;
		frames = new ArrayList<PoseFrameProperty>();
	}
	
	public void setMaxFrame(int n) {
		if (frameCount != n) {
			frameCount = n;
			while (frames.size() > n) {
				frames.remove(frames.size() -1);
			}
			while (frames.size() < n) {
				frames.add(new PoseFrameProperty());
			}
			this.dirty = true;
		}
	}
	
	public boolean isDirty() {
		return dirty;
	}
	
	public void setDirty() {
		this.dirty = true;
	}
	
	public void clearDirty() {
		this.dirty = false;
	}
	
	public void setFrameProperty(int frame, int nwidth, int nheight, int nwx, int nwy, int nwa) {
		if (frame < this.frameCount) {
			PoseFrameProperty prop = frames.get(frame);
			prop.width = nwidth;
			prop.height = nheight;
			prop.weaponX = nwx;
			prop.weaponY = nwy;
			prop.weaponAngle = nwa;
			frames.set(frame, prop);
			this.dirty = true;
		}
	}
	
	public void setHeight(int nheight) {
		for (PoseFrameProperty prop : frames) {
			prop.height = nheight;
		}
		this.dirty = true;
	}

	public void setWidth(int nwidth) {
		for (PoseFrameProperty prop : frames) {
			prop.width = nwidth;
		}
		this.dirty = true;
	}
	
}
