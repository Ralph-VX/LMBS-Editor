package kien.lmbseditor.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import kien.lmbseditor.core.EditorProperty;
import net.miginfocom.swing.MigLayout;

public class BackgroundColorConfig extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private int red;
	private int green;
	private int blue;
	private JSlider greenSlider;
	private JSlider redSlider;
	private JSlider blueSlider;
	private JPanel previewPanel;
	/**
	 * Create the dialog.
	 */
	public BackgroundColorConfig() {
		setTitle("Background Color");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		red = EditorProperty.backgroundRed;
		green = EditorProperty.backgroundGreen;
		blue = EditorProperty.backgroundBlue;
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[60][200][120,grow]", "[40][40][40][40][40]"));
		{
			JLabel lblRed = new JLabel("Red");
			contentPanel.add(lblRed, "cell 0 1,alignx center");
		}
		{
			redSlider = new JSlider();
			redSlider.setMaximum(255);
			redSlider.setValue(red);
			redSlider.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					BackgroundColorConfig.this.onRedSliderChange();
				}
			});
			contentPanel.add(redSlider, "cell 1 1");
		}
		{
			previewPanel = new JPanel() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void paintComponent(Graphics g) {
					BackgroundColorConfig parent = BackgroundColorConfig.this;
					Graphics2D g2 = (Graphics2D)g;
					g2.setPaint(EditorProperty.getBackgroundTexturePaint(new Color(parent.red, parent.green, parent.blue)));
					g2.fillRect(0, 0, this.getWidth(), this.getHeight());
				}
			};
			previewPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			contentPanel.add(previewPanel, "cell 2 1 1 3,grow");
		}
		{
			JLabel lblGreen = new JLabel("Green");
			contentPanel.add(lblGreen, "cell 0 2,alignx center");
		}
		{
			greenSlider = new JSlider();
			greenSlider.setMaximum(255);
			greenSlider.setValue(green);
			greenSlider.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					BackgroundColorConfig.this.onGreenSliderChange();
				}
			});
			contentPanel.add(greenSlider, "cell 1 2");
		}
		{
			JLabel lblBlue = new JLabel("Blue");
			contentPanel.add(lblBlue, "cell 0 3,alignx center");
		}
		{
			blueSlider = new JSlider();
			blueSlider.setMaximum(255);
			blueSlider.setValue(blue);
			blueSlider.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					BackgroundColorConfig.this.onBlueSliderChange();
				}
			});
			contentPanel.add(blueSlider, "cell 1 3");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						BackgroundColorConfig.this.onOk();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						BackgroundColorConfig.this.onCancel();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void onRedSliderChange() {
		this.red = redSlider.getValue();
		if (this.previewPanel != null) {
			this.previewPanel.repaint();
		}
	}
	private void onGreenSliderChange() {
		this.green = greenSlider.getValue();
		if (this.previewPanel != null) {
			this.previewPanel.repaint();
		}
	}
	private void onBlueSliderChange() {
		this.blue = blueSlider.getValue();
		if (this.previewPanel != null) {
			this.previewPanel.repaint();
		}
	}
	
	private void onOk(){
		if (this.red != EditorProperty.backgroundRed || this.green != EditorProperty.backgroundGreen ||
				this.blue != EditorProperty.backgroundBlue) {
			EditorProperty.setBackgroundColor(red, green, blue);
		}
		this.onCancel();
	}
	
	private void onCancel() {
		this.setVisible(false);
		this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
	}

}
