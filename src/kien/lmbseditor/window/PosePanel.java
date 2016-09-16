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
import kien.util.KienLogger;

public class PosePanel extends JPanel {

	public BufferedImage pose;
	public String posename;
	public String charactername;
	public int curFrame;
	public int maxFrame;
	public Rectangle rect;
	
	public PosePanel() {
		this.pose = null;
		this.posename = "";
		this.charactername = "";
		this.curFrame = -1;
		this.maxFrame = -1;
		this.rect = new Rectangle();
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
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform original = g2.getTransform();
		g2.clearRect(0, 0, this.getWidth(), this.getHeight());
		g2.setPaint(EditorProperty.getBackgroundTexturePaint());
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2.setPaint(null);
		if (this.pose != null) {
			g2.translate(this.getWidth()/2, this.getHeight());
			if (this.maxFrame != -1 && this.curFrame != -1){
				int fw = pose.getWidth() / this.maxFrame;
				int fx = fw * this.curFrame;
				g2.translate(-fw/2, -pose.getHeight());
				g2.drawImage(pose.getSubimage(fx, 0, fw, pose.getHeight()), 0, 0, this);
			} else {
				g2.translate(-pose.getWidth()/2, -pose.getHeight());
				g2.drawImage(pose, 0, 0, this);
			}
			if (this.rect != null) {
				g2.setTransform(original);
				g2.translate(this.getWidth()/2, this.getHeight());
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
}
