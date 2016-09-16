package kien.lmbseditor.core;

public class PoseFrameProperty {
	public int width;
	public int height;
	public int weaponX;
	public int weaponY;
	public int weaponAngle;
	
	public PoseFrameProperty() {
		width = 1;
		height = 1;
		weaponX = 0;
		weaponY = 0;
		weaponAngle = 0;
	}
	
	public PoseFrameProperty(PoseFrameProperty other) {
		this.width = other.width;
		this.height = other.height;
		this.weaponAngle = other.weaponAngle;
		this.weaponX = other.weaponX;
		this.weaponY = other.weaponY;
	}
	
}
