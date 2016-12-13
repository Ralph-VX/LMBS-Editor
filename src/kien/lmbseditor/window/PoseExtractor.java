package kien.lmbseditor.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import kien.lmbseditor.core.CharacterPose;
import kien.lmbseditor.core.CharacterSet;
import kien.lmbseditor.core.EditorProperty;
import kien.lmbseditor.core.PoseFrameProperty;
import kien.lmbseditor.core.PoseProperty;
import kien.lmbseditor.core.SkillMotionItemType;
import kien.util.KienLogger;
import kien.util.Util;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class PoseExtractor extends JDialog {

	static final String[] defaultNames = {"Walk","Stand","Cast","Guard","Damage","Evade","Thrust","Swing","Missile","General","Magic","Item","Escape","Victory","NearDeath","StatusAilment","Sleep","Dead"};
	static final boolean[] defaultLoops = {true, true, true, true, false, false, false ,false, false ,false ,false, false, false ,false ,true, true, true, true};
	
	private final JPanel contentPanel = new JPanel();
	private PoseExtractorSeparatePanel poseExtractorSeparatePanel;
	private ArrayList<Boolean> lastLoops;
	private ArrayList<JTextField> names;
	private ArrayList<JCheckBox> loops;
	private JButton okButton;
	private BufferedImage src;
	private ArrayList<BufferedImage> outputs;
	private JTextField characterName;

	/**
	 * Create the dialog.
	 */
	public PoseExtractor() {
		setTitle("Pose Extractor");
		setModal(true);
		outputs = new ArrayList<BufferedImage>();
		names = new ArrayList<JTextField>();
		loops = new ArrayList<JCheckBox>();
		lastLoops = new ArrayList<Boolean>();
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow]"));
		{
			poseExtractorSeparatePanel = new PoseExtractorSeparatePanel();
			poseExtractorSeparatePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			contentPanel.add(poseExtractorSeparatePanel, "cell 0 0 3 1,grow");
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "cell 0 1,grow");
			panel.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow][grow][grow]"));
			{
				JTextField name1 = new JTextField();
				panel.add(name1, "flowx,cell 0 0,growx");
				name1.setColumns(10);
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 0");
				loops.add(loop1);
			}
			{
				JTextField name1 = new JTextField();
				panel.add(name1, "flowx,cell 0 1,growx");
				name1.setColumns(10);
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 1");
				loops.add(loop1);
			}
			{
				JTextField name1 = new JTextField();
				panel.add(name1, "flowx,cell 0 2,growx");
				name1.setColumns(10);
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 2");
				loops.add(loop1);
			}
			{
				JTextField name1 = new JTextField();
				name1.setColumns(10);
				panel.add(name1, "flowx,cell 0 3,growx");
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 3");
				loops.add(loop1);
			}
			{
				JTextField name1 = new JTextField();
				name1.setColumns(10);
				panel.add(name1, "flowx,cell 0 4,growx");
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 4");
				loops.add(loop1);
			}
			{
				JTextField name1 = new JTextField();
				name1.setColumns(10);
				panel.add(name1, "flowx,cell 0 5,growx");
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 5");
				loops.add(loop1);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "cell 1 1,grow");
			panel.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow][grow][grow]"));
			{
				JTextField name1 = new JTextField();
				panel.add(name1, "flowx,cell 0 0,growx");
				name1.setColumns(10);
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 0");
				loops.add(loop1);
			}
			{
				JTextField name1 = new JTextField();
				panel.add(name1, "flowx,cell 0 1,growx");
				name1.setColumns(10);
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 1");
				loops.add(loop1);
			}
			{
				JTextField name1 = new JTextField();
				panel.add(name1, "flowx,cell 0 2,growx");
				name1.setColumns(10);
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 2");
				loops.add(loop1);
			}
			{
				JTextField name1 = new JTextField();
				name1.setColumns(10);
				panel.add(name1, "flowx,cell 0 3,growx");
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 3");
				loops.add(loop1);
			}
			{
				JTextField name1 = new JTextField();
				name1.setColumns(10);
				panel.add(name1, "flowx,cell 0 4,growx");
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 4");
				loops.add(loop1);
			}
			{
				JTextField name1 = new JTextField();
				name1.setColumns(10);
				panel.add(name1, "flowx,cell 0 5,growx");
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 5");
				loops.add(loop1);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "cell 2 1,grow");
			panel.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow][grow][grow]"));
			{
				JTextField name1 = new JTextField();
				panel.add(name1, "flowx,cell 0 0,growx");
				name1.setColumns(10);
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 0");
				loops.add(loop1);
			}
			{
				JTextField name1 = new JTextField();
				panel.add(name1, "flowx,cell 0 1,growx");
				name1.setColumns(10);
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 1");
				loops.add(loop1);
			}
			{
				JTextField name1 = new JTextField();
				panel.add(name1, "flowx,cell 0 2,growx");
				name1.setColumns(10);
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 2");
				loops.add(loop1);
			}
			{
				JTextField name1 = new JTextField();
				name1.setColumns(10);
				panel.add(name1, "flowx,cell 0 3,growx");
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 3");
				loops.add(loop1);
			}
			{
				JTextField name1 = new JTextField();
				name1.setColumns(10);
				panel.add(name1, "flowx,cell 0 4,growx");
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 4");
				loops.add(loop1);
			}
			{
				JTextField name1 = new JTextField();
				name1.setColumns(10);
				panel.add(name1, "flowx,cell 0 5,growx");
				names.add(name1);
			}
			{
				JCheckBox loop1 = new JCheckBox("Loop");
				panel.add(loop1, "cell 0 5");
				loops.add(loop1);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnNewButton = new JButton("Choose Source");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFileChooser jfc = new JFileChooser();
						jfc.setDialogTitle("Open Source File");
						jfc.setFileFilter(new FileNameExtensionFilter("Portable Network Graphics", "png"));
						int ret = jfc.showOpenDialog(PoseExtractor.this);
						if (ret != 0) {
							return;
						}
						File f = jfc.getSelectedFile();
						if (f.exists() && f.getName().endsWith(".png")) {
							PoseExtractor.this.setup(f);
						}

					}
				});
				buttonPane.add(btnNewButton);
			}
			{
				JLabel lblNewLabel = new JLabel("Character Name");
				buttonPane.add(lblNewLabel);
			}
			{
				characterName = new JTextField();
				buttonPane.add(characterName);
				characterName.setColumns(10);
			}
			{
				okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PoseExtractor.this.onSave();
					}
				});
				okButton.setActionCommand("Save");
				okButton.setEnabled(false);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PoseExtractor.this.dispatchEvent(new WindowEvent(PoseExtractor.this,WindowEvent.WINDOW_CLOSING));
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.initialize();
	}
	
	protected void setup(File f) {
		this.setDefaultValues();
		outputs.clear();
		try {
			src = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		for (int n = 0; n < 18; n++) {
			setupSubImage(n);
		}
		characterName.setText(f.getName().replaceFirst("[.][^.]+$", ""));
		okButton.setEnabled(true);
	}
	
	private void setupSubImage(int n) {
		if (outputs.size() > n) {
			outputs.remove(n);
		}
		int cw = src.getWidth() / 9;
		int ch = src.getHeight() / 6;
		boolean loop = loops.get(n).isSelected();
		BufferedImage sub;
		if (loop) {
			sub = new BufferedImage(cw * 4, ch, BufferedImage.TYPE_INT_ARGB);
		} else {
			sub = new BufferedImage(cw * 3, ch, BufferedImage.TYPE_INT_ARGB);
		}
		int sx = cw * 3 * (n / 6);
		int sy = ch * (n % 6);
		Graphics2D g = sub.createGraphics();
		g.drawImage(src.getSubimage(sx, 		sy, cw, ch), 0, 	0, this);
		g.drawImage(src.getSubimage(sx+cw, 		sy, cw, ch), cw, 	0, this);
		g.drawImage(src.getSubimage(sx+cw*2, 	sy, cw, ch), cw*2, 	0, this);
		if (loop) {
			g.drawImage(src.getSubimage(sx+cw, sy, cw, ch), cw*3, 0, this);
		}
		g.dispose();
		outputs.add(n,sub);
	}

	private void setDefaultValues() {
		for (int n = 0; n < 18; n++) {
			names.get(n).setText(defaultNames[n]);
			names.get(n).setEnabled(true);
			lastLoops.set(n,defaultLoops[n]);
			loops.get(n).setSelected(defaultLoops[n]);
			loops.get(n).setEnabled(true);
		}
	}

	private void initialize() {
		for (JTextField f : names) {
			f.addMouseListener(new MouseListener () {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					if (!outputs.isEmpty()){
						PoseExtractor.this.poseExtractorSeparatePanel.setup(outputs.get(names.indexOf(f)), f.getText());
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			f.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					update();
				}

				public void removeUpdate(DocumentEvent e) {
					update();
				}

				public void insertUpdate(DocumentEvent e) {
					update();
				}

				public void update() {
					if (!outputs.isEmpty()){
						PoseExtractor.this.poseExtractorSeparatePanel.setup(outputs.get(names.indexOf(f)), f.getText());
					}
				}
			});
			f.setEnabled(false);
			f.setText("");
		}
		lastLoops.clear();
		for (JCheckBox c : loops) {
			lastLoops.add(false);
			c.setSelected(false);
			c.setEnabled(false);
			c.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent e) {
					if (src != null) {
						if (lastLoops.get(loops.indexOf(c)) != c.isSelected()) {
							setupSubImage(loops.indexOf(c));
							lastLoops.set(loops.indexOf(c), c.isSelected());
						}
						PoseExtractor.this.poseExtractorSeparatePanel.setup(PoseExtractor.this.outputs.get(PoseExtractor.this.loops.indexOf(c)), PoseExtractor.this.names.get(PoseExtractor.this.loops.indexOf(c)).getText());
					}
				}
				
			});
		}
	}
	
	protected void onSave() {
		String cname = characterName.getText();
		File parent = new File(EditorProperty.poseDirectory.getAbsolutePath() + "\\" + cname);
		if (!parent.exists() && !parent.mkdir()) {
			JOptionPane.showConfirmDialog(this, "Cannot create the directory: " + parent.getAbsolutePath(), "Error", JOptionPane.OK_OPTION);
			return;
		}
		CharacterSet set = new CharacterSet();
		set.characterName = cname;
		for (JTextField f : names) {
			int index = names.indexOf(f);
			String pname = f.getText();
			if (pname.isEmpty()){
				continue;
			}
			File imgFile = new File(parent.getAbsolutePath() + "\\" + pname + ".png");
			BufferedImage img = outputs.get(index);
			try {
				ImageIO.write(outputs.get(index), "png", imgFile);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			CharacterPose pose = new CharacterPose(pname);
			pose.propertyFile = new File(parent.getAbsolutePath() + "\\" + pname + ".json");
			pose.poseFile = imgFile;
			pose.property.loop = loops.get(index).isSelected();
			pose.property.setMaxFrame(pose.property.loop ? 4 : 3);
			int cw = img.getWidth() / pose.property.frameCount;
			int ch = img.getHeight();
			for (int n = 0; n < pose.property.frameCount; n++ ) {
				PoseFrameProperty frame = pose.property.frames.get(n);
				frame.width = Util.getWidthFit(img.getSubimage(cw * n, 0, cw, ch));
				frame.height = Util.getHeightFit(img.getSubimage(cw * n, 0, cw, ch));
			}
			set.poses.put(pname, pose);
		}
		set.save();
		EditorProperty.characterList.lists.put(cname,set);
		JOptionPane.showConfirmDialog(this, "Successfully saved " + set.poses.values().size() + " poses out of 18.", "Info", JOptionPane.OK_OPTION);
		this.dispatchEvent(new WindowEvent(PoseExtractor.this,WindowEvent.WINDOW_CLOSING));
	}


}
