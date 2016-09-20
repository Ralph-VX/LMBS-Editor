package kien.lmbseditor.core;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * An object describe single object inside animation descriptor
 * @author Kien-PC
 * 
 */
public class AnimationObject {
	public Rectangle2D.Double rect;
	public int dur;
	public double damagePer;
	public Point2D.Double knockback;
	public int knockdir;
	
	public AnimationObject() {
		this.rect = new Rectangle2D.Double();
		this.dur = 0;
		this.damagePer = 0;
		this.knockback = new Point2D.Double();
		this.knockdir = 0;
	}
	
}
