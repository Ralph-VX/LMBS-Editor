package kien.lmbseditor.core;

public class PoseFrameProperty {
	public int width;
	public int height;
	public int weaponX;
	public int weaponY;
	public int weaponAngle;
	public boolean hideWeapon;
	
	public PoseFrameProperty() {
		width = 1;
		height = 1;
		weaponX = 0;
		weaponY = 0;
		weaponAngle = 0;
		hideWeapon = false;
	}
	
	public PoseFrameProperty(PoseFrameProperty other) {
		super();
		this.overwrite(other);
	}

	public void overwrite(PoseFrameProperty other) {
		this.width = other.width;
		this.height = other.height;
		this.weaponAngle = other.weaponAngle;
		this.weaponX = other.weaponX;
		this.weaponY = other.weaponY;
		this.hideWeapon = other.hideWeapon;
		
	}
	
}
