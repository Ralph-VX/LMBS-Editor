package kien.lmbseditor.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import kien.lmbseditor.core.EditorProperty;

public class WeaponImagePanel extends JPanel {
	
	public BufferedImage img;
	public int ox;
	public int oy;
	public int angle;
	public boolean showOverlay;
	
	@Override
	public void paintComponent(Graphics graphic) {
		Graphics2D g = (Graphics2D)graphic;
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		super.paintComponent(g);
		g.setPaint(EditorProperty.getBackgroundTexturePaint());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		AffineTransform at = g.getTransform();
		g.translate(this.getWidth()/2, this.getHeight()/2);
		AffineTransform at2 = g.getTransform();
		if (img != null) {
			g.rotate(Math.toRadians(angle), ox, oy);
			g.translate(-img.getWidth()/2, -img.getHeight()/2);
			g.drawImage(img, 0, 0, this);
			g.setColor(new Color(255,255,255,128));
			g.setTransform(at2);
			if (showOverlay) {
				g.translate(ox, oy);
				g.fillRect(-1, -1, 3, 3);
				g.setColor(new Color(255,255,255,128));
				g.drawOval(-30, -30, 60, 60);
				g.fillRect(0, -2, 60, 4);
			}
		}
		
		g.setTransform(at);
	}
}
