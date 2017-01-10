package kien.lmbseditor.window;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import kien.lmbseditor.core.EditorProperty;

import javax.swing.JLabel;

public class PoseExtractorSeparatePanel extends JPanel {
	
	public BufferedImage output;
	public boolean isLoop;
	private JPanel panelPreview;
	private JLabel lblPoseName;
	
	/**
	 * Create the panel.
	 */
	public PoseExtractorSeparatePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Pose Name:");
		panel.add(lblNewLabel);
		
		lblPoseName = new JLabel("New label");
		panel.add(lblPoseName);
		
		panelPreview = new JPanel(){
			public void paintComponent(Graphics g) {
				PoseExtractorSeparatePanel.this.onPaintPanel(g);
			}
		};
		add(panelPreview, BorderLayout.CENTER);
	}
	
	public void setup(BufferedImage sub, String name) {
		this.output = sub;
		lblPoseName.setText(name);
		this.repaint();
	}

	protected void onPaintPanel(Graphics g) {
		g.clearRect(0, 0, panelPreview.getWidth(), panelPreview.getHeight());
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(EditorProperty.getBackgroundTexturePaint());
		g2.fillRect(0, 0, panelPreview.getWidth(), panelPreview.getHeight());
		AffineTransform at = g2.getTransform();
		if (this.output != null) {
			g2.translate(panelPreview.getWidth()/2 - output.getWidth()/2, panelPreview.getHeight()/2 - output.getHeight()/2);
			g2.drawImage(output, 0, 0, this);
		}
		g2.setTransform(at);
	}

}
