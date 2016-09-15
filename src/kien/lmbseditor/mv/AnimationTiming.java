package kien.lmbseditor.mv;

import java.util.ArrayList;

public class AnimationTiming {
	/**
	 * flashColor[0] = red, <br>
	 * flashColor[1] = green, <br>
	 * flashColor[2] = blue, <br>
	 * flashColor[3] = alpha <br>
	 * all value ranged from 0-255
	 */
	public ArrayList<Integer> flashColor;
	public int flashDuration;
	/**
	 * 0 = None, <br>
	 * 1 = Target, <br>
	 * 2 = Screen, <br>
	 * 3 = Hide Target
	 */
	public int flashScope;
	public int frame;
	public SEObject se;
}
