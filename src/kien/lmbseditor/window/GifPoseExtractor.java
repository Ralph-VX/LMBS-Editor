package kien.lmbseditor.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import kien.lmbseditor.window.character.PoseExtractorSeparatePanel;
import kien.util.ChromaKeyFilter;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("unused")
public class GifPoseExtractor extends JDialog {

	private static final long serialVersionUID = 322324815771054281L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel lblTotalFrame;
	private JLabel lblCurrentFrame;
	private JTextField textFieldPoseName;
	private JFormattedTextField formattedFrameSeparation;
	private JButton btnPrevFrame;
	private JButton btnNextFrame;
	private JFormattedTextField formattedChromaRGB;
	private JCheckBox chckbxEnabled;
	private ArrayList<BufferedImage> images;
	private ChromaKeyFilter filter;
	private int curFrame = 0;
	private int maxFrame = 0;
	private int frameSeparate;
	private JLabel lblIncluded;
	
	
	/**
	 * Create the dialog.
	 */
	public GifPoseExtractor() {
		setBounds(100, 100, 534, 425);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		filter = new ChromaKeyFilter();
		{
			PoseExtractorSeparatePanel poseExtractorSeparatePanel = new PoseExtractorSeparatePanel();
			contentPanel.add(poseExtractorSeparatePanel);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new MigLayout("", "[40px][100px,grow][40px][100px,grow][40px][100px]", "[][][][]"));
			{
				JLabel lblPoseName = new JLabel("Pose Name:");
				panel.add(lblPoseName, "cell 0 0,alignx right");
			}
			{
				textFieldPoseName = new JTextField();
				panel.add(textFieldPoseName, "cell 1 0 3 1,growx");
			}
			{
				JLabel lblNewLabel = new JLabel("Current Frame:");
				panel.add(lblNewLabel, "cell 4 0,alignx right");
			}
			{
				lblCurrentFrame = new JLabel("Not Valid");
				panel.add(lblCurrentFrame, "cell 5 0");
			}
			{
				JLabel lblTotalFrames = new JLabel("Total Frames:");
				panel.add(lblTotalFrames, "cell 0 1,alignx right");
			}
			{
				lblTotalFrame = new JLabel("Not Valid");
				panel.add(lblTotalFrame, "cell 1 1,growx");
			}
			{
				JLabel lblFrameSeparation = new JLabel("Frame Separation:");
				panel.add(lblFrameSeparation, "cell 2 1,alignx right");
			}
			{
				DecimalFormat f = new DecimalFormat("0");
				formattedFrameSeparation = new JFormattedTextField(f);
				panel.add(formattedFrameSeparation, "cell 3 1,growx");
			}
			{
				JLabel lblInPose = new JLabel("In Pose:");
				panel.add(lblInPose, "cell 4 1,alignx right");
			}
			{
				lblIncluded = new JLabel("Not Valid");
				panel.add(lblIncluded, "cell 5 1");
			}
			{
				JLabel lblUseChromaKey = new JLabel("Chroma Key:");
				panel.add(lblUseChromaKey, "cell 0 2,alignx right");
			}
			{
				chckbxEnabled = new JCheckBox("Enabled");
				panel.add(chckbxEnabled, "cell 1 2");
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Chroma RGB:");
				panel.add(lblNewLabel_1, "cell 2 2,alignx right");
			}
			{
				DecimalFormat f = new DecimalFormat("HHHHHHHH");
				formattedChromaRGB = new JFormattedTextField(f);
				panel.add(formattedChromaRGB, "cell 3 2,growx");
			}
			{
				btnPrevFrame = new JButton("Prev Frame");
				panel.add(btnPrevFrame, "cell 4 2");
			}
			{
				btnNextFrame = new JButton("Next Frame");
				panel.add(btnNextFrame, "cell 5 2");
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton button = new JButton("Choose Source");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFileChooser jfc = new JFileChooser();
						jfc.setDialogTitle("Open Source File");
						jfc.setFileFilter(new FileNameExtensionFilter("Graphics Interchange Format", "gif"));
						int ret = jfc.showOpenDialog(GifPoseExtractor.this);
						if (ret != 0) {
							return;
						}
						File f = jfc.getSelectedFile();
						if (f.exists() && f.getName().endsWith(".gif")) {
							GifPoseExtractor.this.setup(f);
						}
					}
				});
				buttonPane.add(button);
			}
			{
				JLabel label = new JLabel("Character Name");
				buttonPane.add(label);
			}
			{
				textField = new JTextField();
				textField.setColumns(10);
				buttonPane.add(textField);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		clear();
	}

	protected void clear() {
		textFieldPoseName.setEnabled(false);
		formattedFrameSeparation.setEnabled(false);
		btnPrevFrame.setEnabled(false);
		btnNextFrame.setEnabled(false);
		chckbxEnabled.setEnabled(false);
		formattedChromaRGB.setEnabled(false);
	}

	private void initialize() {
		
		textFieldPoseName.setEnabled(true);
		formattedFrameSeparation.setEnabled(true);
		if (images.size() > 1) {
			btnNextFrame.setEnabled(true);
		}
		chckbxEnabled.setEnabled(true);
		formattedChromaRGB.setEnabled(true);
		
	}
	
	protected void setup(File f) {
		try {
			ImageReader reader = (ImageReader)ImageIO.getImageReadersByFormatName("gif").next();
			ImageInputStream iis = ImageIO.createImageInputStream(f);
			reader.setInput(iis);
			int max = reader.getNumImages(true);
			images = new ArrayList<BufferedImage>();
			for (int i = 0; i < max; i++) {
				images.add(reader.read(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
