package kien.lmbseditor.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import kien.lmbseditor.core.AnimationObject;
import kien.lmbseditor.core.EditorProperty;
import kien.lmbseditor.mv.Animation;
import kien.util.KienLogger;

public class AnimationContent extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -909798655021372590L;
	public int frameNumber;
	public Animation animation;
	ArrayList<ArrayList<Double>> frame;
	ArrayList<AnimationObject> rects;
	
	private BufferedImage image1;
	private BufferedImage image2;
	
	public AnimationContent() {
		super();
		this.frameNumber = -1;
		this.animation = new Animation();
		this.frame = new ArrayList<ArrayList<Double>>();
		this.image1 = null;
		this.image2 = null;
		this.rects = null;
		this.setLayout(null);
		this.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	}
	
	public void setAnimation(Animation anim) {
		this.animation = anim;
		if (this.animation != null) {
			this.loadImage();
			if (this.frameNumber >= 0) {
				frame = animation.frames.get(frameNumber);
				this.repaint();
			}
		}
	}
	
	public void setFrame(int f) {
		frameNumber = f;
		if (this.animation != null && this.frameNumber >= 0) {
			frame = animation.frames.get(frameNumber); 
			this.repaint();
		}
	}
	
	public void setRects(ArrayList<AnimationObject> list) {
		this.rects = list;
		this.repaint();
	}
	
	public void loadImage() {
		if (this.image1 != null) {
			this.image1.flush();
		}
		if (this.image2 != null) {
			this.image2.flush();
		}
		if (animation.animation1Name != null && !animation.animation1Name.isEmpty()){
			try {
				image1 = ImageIO.read(new File(EditorProperty.projectDirectory + "\\img\\animations\\" + animation.animation1Name + ".png"));
				//Util.rotateHue(image1, animation.animation1Hue);
			} catch (IOException e) {
				KienLogger.error("An error occured while loading Animatino Image File : ", e);
				image1 = null;
			}
		}
		if (animation.animation2Name != null && !animation.animation2Name.isEmpty()) {
			try {
				image2 = ImageIO.read(new File(EditorProperty.projectDirectory + "\\img\\animations\\" + animation.animation2Name + ".png"));
				//Util.rotateHue(image2, animation.animation2Hue);
			} catch (IOException e) {
				KienLogger.error("An error occured while loading Animatino Image File : ", e);
				image2 = null;
			}
		}
	} 
	
	@Override
	@SuppressWarnings("unused")
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g.create();
		g2.clearRect(0, 0, this.getWidth(), this.getHeight());
		g2.setPaint(EditorProperty.getBackgroundTexturePaint());
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		if (animation == null || frameNumber == -1 || (image1 == null && image2 == null)) {
			return;
		}
		
		if (this.animation.position == 0) {
			g2.translate(this.getWidth()/2, 30);
		} else if (this.animation.position == 1) {
			g2.translate(this.getWidth()/2, (this.getHeight()-30)/2);
		} else {
			g2.translate(this.getWidth()/2, this.getHeight()-30);
		}
		
		AffineTransform original = g2.getTransform();
		for (int n = 0; n < frame.size(); n++) {
			AffineTransform at = new AffineTransform();
			ArrayList<Double> cell = frame.get(n);
			int pattern = (int) Math.round(cell.get(0));
			if (pattern >= 0) {
				int sx = (pattern % 5) * 192;
				int sy = (int) Math.round(Math.floor(pattern % 100 / 5) * 192);
				BufferedImage img = pattern < 100 ? image1 : image2;
				if (img != null) {
					boolean mirror = Math.round(cell.get(5)) > 0 ? true : false;
					int rotation = (int) Math.round(cell.get(4));
					double scale = cell.get(3) / 100;
					double tx = cell.get(1) - 192/2 * scale;
					double ty = cell.get(2) - 192/2 * scale;
					int opacity = (int) Math.round(cell.get(6));
					at.rotate(Math.toRadians(rotation));
					at.scale(mirror ? -scale : scale, scale);
					g2.translate(tx, ty);
					
					g2.drawImage(img.getSubimage(sx, sy, 192, 192), at, this);
					g2.setTransform(original);
				}
				
			}
		}
		if (this.rects != null) {
			for (int n = 0; n < rects.size(); n++) {
				Rectangle2D.Double rect = rects.get(n).rect.toRectangle2D();
				g2.setColor(new Color(255,255,255,192));
				g2.fillRect((int)Math.round(rect.x), (int)Math.round(rect.y), (int)Math.round(rect.width), (int)Math.round(rect.height));
				g2.setColor(new Color(0,0,0,255));
				g2.drawString(Integer.toString(n+1), (int)rect.x, (int)rect.y+g2.getFont().getSize());
			}
		}
	}

	public void clear() {
		this.animation = null;
		this.frameNumber = -1;
	}
	
	
}
