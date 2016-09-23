package kien.lmbseditor.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import kien.lmbseditor.core.EditorProperty;

public class PosePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BufferedImage pose;
	public String posename;
	public String charactername;
	public int weaponX;
	public int weaponY;
	public int weaponAngle;
	public boolean hideWeapon;
	public int curFrame;
	public int maxFrame;
	public Rectangle rect;
	private double scale;
	
	public PosePanel() {
		this.pose = null;
		this.posename = "";
		this.charactername = "";
		this.curFrame = -1;
		this.maxFrame = -1;
		this.rect = new Rectangle();
		scale = 1;
	}
	
	public void loadImage(File imageFile) {
		try {
			pose = ImageIO.read(imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pose = null;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform original = g2.getTransform();
		g2.clearRect(0, 0, this.getWidth(), this.getHeight());
		g2.setPaint(EditorProperty.getBackgroundTexturePaint());
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2.setPaint(null);
		if (this.pose != null) {
			if (this.maxFrame != -1 && this.curFrame != -1){
				int fw = pose.getWidth() / this.maxFrame;
				int fx = fw * this.curFrame;
				AffineTransform pat = new AffineTransform();
				pat.translate(this.getWidth()/2, this.getHeight());
				pat.scale(this.scale, this.scale);
				pat.translate(-fw/2, -pose.getHeight());
				g2.drawImage(pose.getSubimage(fx, 0, fw, pose.getHeight()), pat, this);
			} else {
				AffineTransform pat = new AffineTransform();
				pat.translate(this.getWidth()/2, this.getHeight());
				pat.scale(this.scale, this.scale);
				pat.translate(-pose.getWidth()/2, -pose.getHeight());
				g2.drawImage(pose, pat, this);
			}
			if (EditorProperty.weaponList.current != null && !this.hideWeapon) {
				int ox = EditorProperty.weaponList.current.json.ox;
				int oy = EditorProperty.weaponList.current.json.oy;
				BufferedImage img = EditorProperty.currentWeaponImage;
				AffineTransform imgat = new AffineTransform();
				imgat.translate(this.getWidth()/2, this.getHeight());
				imgat.scale(this.scale, this.scale);
				imgat.translate(0, -pose.getHeight()/2);
				imgat.translate(weaponX, weaponY);
				imgat.rotate(Math.toRadians(weaponAngle + EditorProperty.weaponList.current.json.angle));
				imgat.translate(-img.getWidth()/2-ox, -img.getHeight()/2-oy);
				g2.drawImage(img, imgat, this);
				AffineTransform overat = new AffineTransform();
				overat.translate(this.getWidth()/2, this.getHeight());
				overat.scale(this.scale, this.scale);
				overat.translate(0, -pose.getHeight()/2);
				overat.translate(weaponX, weaponY);
				g2.setTransform(overat);
				g.setColor(new Color(255,255,255,128));
				g.fillRect(-1, -1, 3, 3);
				g.setColor(new Color(255,255,255,128));
				g.drawOval(-30, -30, 60, 60);
				g.fillRect(-60, -2, 60, 4);
			}
			if (this.rect != null) {
				g2.setTransform(original);
				g2.translate(this.getWidth()/2, this.getHeight());
				g2.scale(this.scale,this.scale);
				g2.translate(-rect.width/2, -rect.height);
				g2.setColor(new Color(224,255,255,128));
				g2.fillRect(rect.x, rect.y, rect.width, rect.height);
			}
		}
		g2.setTransform(original);
	}

	public void clear() {
		this.pose = null;		
	}

	public void setScale(double i) {
		this.scale = i;
	}
}
