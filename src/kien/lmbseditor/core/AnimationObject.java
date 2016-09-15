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
	public Rectangle2D.Float rect;
	public int dur;
	public float damagePer;
	public Point2D.Float knockback;
	public int knockdir;
	
	public AnimationObject() {
		this.rect = new Rectangle2D.Float();
		this.dur = 0;
		this.damagePer = 0;
		this.knockback = new Point2D.Float();
		this.knockdir = 0;
	}
	
}
