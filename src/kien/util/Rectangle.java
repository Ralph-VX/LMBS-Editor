package kien.util;

import java.awt.geom.Rectangle2D;

public class Rectangle {
	public double x;
	public double y;
	public double width;
	public double height;
	
	public void updateRect(double rectx, double recty, double rectwidth, double rectheight) {
		x = rectx;
		y = recty;
		width = rectwidth;
		height = rectheight;
	}

	public Rectangle2D.Double toRectangle2D() {
		return new Rectangle2D.Double(x, y, width, height);
	}
}
