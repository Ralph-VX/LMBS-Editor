package kien.lmbseditor.core;

import java.awt.geom.Point2D;

import kien.util.Rectangle;

/**
 * An object describe single object inside animation descriptor
 * @author Kien-PC
 * 
 */
public class AnimationObject {
	public Rectangle rect;
	public int dur;
	public double damagePer;
	public Point2D.Double knockback;
	public int knockdir;
	
	public AnimationObject() {
		this.rect = new Rectangle();
		this.dur = 0;
		this.damagePer = 0;
		this.knockback = new Point2D.Double();
		this.knockdir = 0;
	}
	
}
