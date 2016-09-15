package kien.lmbseditor.mv;

import java.util.ArrayList;

public class Animation {
	public int id;
	public int animation1Hue;
	public String animation1Name;
	public int animation2Hue;
	public String animation2Name;
	/**
	 * frames[n] = nth frame<br>
	 * frames[n][n] = nth frames nth cell.<br>
	 * frames[n][n] = [pattern-1, x, y, scale, rotation, mirror, opacity, blendmode]<br>
	 * mirror: 1 = true, 0 = false<br>
	 * blendmode: 0 = normal, 1 = additive, 2 = multiply, 3 = screen<br>
	 */
	public ArrayList<ArrayList<ArrayList<Float>>> frames;
	public String name;
	/**
	 * 0 = Head,<br>
	 * 1 = Center,<br>
	 * 2 = F
	 */
	public int position; 
	public ArrayList<AnimationTiming> timings;
	
}
