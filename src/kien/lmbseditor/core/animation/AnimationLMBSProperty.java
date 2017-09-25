package kien.lmbseditor.core.animation;

import java.util.ArrayList;

public class AnimationLMBSProperty {
	public AnimationCoordinateObject x;
	public AnimationCoordinateObject y;
	public int animationId;
	public int delay;
	public boolean mirror;
	public boolean follow;
	public ArrayList<AnimationLMBSDamageTiming> timing;
	
	public AnimationLMBSProperty() {
		x = new AnimationCoordinateObject();
		y = new AnimationCoordinateObject();
		timing = new ArrayList<AnimationLMBSDamageTiming>();
		animationId = 0;
		delay = 0;
		mirror = false;
		follow = false;
	}
}
