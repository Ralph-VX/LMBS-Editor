package kien.lmbseditor.core.animation;

import java.awt.geom.Point2D;

import kien.util.Rectangle;

/**
 * An object describe single object inside animation descriptor
 * @author Kien-PC
 * 
 */
public class AnimationLMBSDamageTiming {
	public Rectangle rect;
	public int dur;
	public int interval;
	public double damagePer;
	public Point2D.Double knockback;
	public int knockdir;
	
	public AnimationLMBSDamageTiming() {
		this.rect = new Rectangle();
		this.dur = 0;
		this.damagePer = 0;
		this.knockback = new Point2D.Double();
		this.knockdir = 0;
		this.interval = 1;
	}
	
}
