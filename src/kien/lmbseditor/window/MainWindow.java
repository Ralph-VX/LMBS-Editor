package kien.lmbseditor.window;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Dimension;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;

import kien.lmbseditor.core.AnimationItemType;
import kien.lmbseditor.core.BaseItemType;
import kien.lmbseditor.core.EditorProperty;
import kien.lmbseditor.core.SkillMotionItemType;
import kien.util.KienLogger;
import kien.util.Util;

import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class MainWindow {

	private ArrayList<BaseItemType> items;

	private JFrame frame;
	private int fixedTab;
	public JMenuBar menuBar;
	public JMenu mnFile;
	public JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.frame.setVisible(true);
			while (EditorProperty.projectDirectory == null) {
				int ret = JOptionPane.showConfirmDialog(window.frame,
						"This Application need to select a valid RPG Maker MV project directory to work. Please select one directory, or click \"Cancel\"to terminate program.",
						"Warning", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				if (ret == 2) {
					System.exit(0);
				} else {
					JFileChooser jfc = new JFileChooser();
					jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					ret = jfc.showOpenDialog(window.frame);
					while (true) {
						if (ret == -1) {
							KienLogger.logger.severe("Error had occured while choosing directory");
							System.exit(0);
						} else if (ret == 1) {
							break;
						} else {
							File f = jfc.getSelectedFile();
							KienLogger.logger.info("Selected: " + f.getAbsolutePath());
							if (f.exists() && f.isDirectory()) {
								File project = new File(f.getAbsolutePath() + "\\Game.rpgproject");
								if (project.exists()) {
									EditorProperty.setProjectDirectory(f.getAbsolutePath());
									break;
								}
							}
							ret = JOptionPane.showConfirmDialog(window.frame,
									"Not a valid RPG Maker MV project directory. Please select another valid directory, or click\"Cancel\" to terminate programe.",
									"Error", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
							if (ret == 2) {
								System.exit(0);
							} else {
								ret = jfc.showOpenDialog(window.frame);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		this.items = new ArrayList<BaseItemType>();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		fixedTab = 0;
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(640, 480));
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnFile = new JMenu("File(F)");
		mnFile.setMnemonic('F');
		menuBar.add(mnFile);

		JMenu mnNew = new JMenu("New...");
		mnFile.add(mnNew);

		JMenuItem menuItem = new JMenuItem("Animation Description");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnimationItemType ait = new AnimationItemType();
				AnimationDescriptionPanel p = new AnimationDescriptionPanel(ait);
				int n = tabbedPane.getTabCount();
				p.setTabIndex(n);
				tabbedPane.addTab(ait.getListname(), p);
				MainWindow.this.items.add(ait);
				tabbedPane.setSelectedIndex(n);
			}
		});
		mnNew.add(menuItem);

		JMenuItem menuItem_2 = new JMenuItem("Skill Motion Desciption");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SkillMotionItemType ait = new SkillMotionItemType();
				SkillMotionDescriptionPanel p = new SkillMotionDescriptionPanel(ait);
				int n = tabbedPane.getTabCount();
				p.setTabIndex(n);
				tabbedPane.addTab(ait.getListname(), p);
				MainWindow.this.items.add(ait);
				tabbedPane.setSelectedIndex(n);
			}
		});
		mnNew.add(menuItem_2);

		JMenu mnOpen = new JMenu("Open...");
		mnFile.add(mnOpen);

		JMenuItem mntmNewMenuItem = new JMenuItem("Animation Description");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Open Animation Description File");
				jfc.setCurrentDirectory(new File(EditorProperty.projectDirectory));
				jfc.setFileFilter(new FileNameExtensionFilter("Javascript Object Notation File", "json"));
				int ret = jfc.showOpenDialog(frame);
				if (ret != 0) {
					return;
				}
				File f = jfc.getSelectedFile();
				if (f.exists()) {
					AnimationItemType ait = null;
					try {
						ait = new AnimationItemType(f);
					} catch (Exception error) {
						JOptionPane.showConfirmDialog(frame,
								"An error occured while reading the file.\n It may not be a valid animation description file.",
								"Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						ait = null;
					}
					if (ait != null) {
						AnimationDescriptionPanel p = new AnimationDescriptionPanel(ait);
						int n = tabbedPane.getTabCount();
						p.setTabIndex(n);
						tabbedPane.addTab(ait.getListname(), p);
						MainWindow.this.items.add(ait);
						tabbedPane.setSelectedIndex(n);
					}
				}
			}
		});
		mnOpen.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Skill Motion Desciption");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Open Skill Motion Description File");
				jfc.setCurrentDirectory(new File(EditorProperty.projectDirectory));
				jfc.setFileFilter(new FileNameExtensionFilter("Javascript Object Notation File", "json"));
				int ret = jfc.showOpenDialog(frame);
				if (ret != 0) {
					return;
				}
				File f = jfc.getSelectedFile();
				if (f.exists()) {
					SkillMotionItemType ait = null;
					try {
						ait = new SkillMotionItemType(f);
					} catch (Exception error) {
						JOptionPane.showConfirmDialog(frame,
								"An error occured while reading the file.\n It may not be a valid skill motion description file.",
								"Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						ait = null;
					}
					if (ait != null) {
						SkillMotionDescriptionPanel p = new SkillMotionDescriptionPanel(ait);
						int n = tabbedPane.getTabCount();
						p.setTabIndex(n);
						tabbedPane.addTab(ait.getListname(), p);
						MainWindow.this.items.add(ait);
						tabbedPane.setSelectedIndex(n);
					}
				}

			}
		});
		mnOpen.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Save");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.onSavePerformed();
			}
		});
		mnFile.add(mntmNewMenuItem_4);

		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.onSaveAsPerformed();
			}
		});
		mnFile.add(mntmSaveAs);

		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.onColsePerformed();
			}
		});
		mnFile.add(mntmClose);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		mnFile.add(mntmExit);

		JMenu mnEdite = new JMenu("Edit(E)");
		menuBar.add(mnEdite);

		JMenuItem mntmPreferences = new JMenuItem("Preferences...");
		mntmPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditorPreferencesWindow dialog = new EditorPreferencesWindow();
				Rectangle rect1 = dialog.getBounds();
				Rectangle rect2 = MainWindow.this.frame.getBounds();
				dialog.setLocation(Util.centerRects(rect1, rect2));
				dialog.setVisible(true);
			}
		});

		JMenuItem mntmBackgroundColorSetting = new JMenuItem("Background Color Setting...");
		mntmBackgroundColorSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BackgroundColorConfig dialog = new BackgroundColorConfig();
				Rectangle rect1 = dialog.getBounds();
				Rectangle rect2 = MainWindow.this.frame.getBounds();
				dialog.setLocation(Util.centerRects(rect1, rect2));
				dialog.setVisible(true);
			}
		});
		mnEdite.add(mntmBackgroundColorSetting);
		mnEdite.add(mntmPreferences);

		// GridBagLayout setting
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		frame.getContentPane().add(tabbedPane, gbc_tabbedPane);

		CharacterPosePanel p = new CharacterPosePanel();
		int n = tabbedPane.getTabCount();
		p.setTabIndex(n);
		tabbedPane.addTab("Character Poses", p);
		tabbedPane.setSelectedIndex(n);
		fixedTab++;

		WeaponPropertyPanel p2 = new WeaponPropertyPanel();
		int n2 = tabbedPane.getTabCount();
		p2.setTabIndex(n2);
		tabbedPane.addTab("Weapon Property", p2);
		tabbedPane.setSelectedIndex(n);
		fixedTab++;

	}

	private void onColsePerformed() {
		int index = tabbedPane.getSelectedIndex();
		if (index >= fixedTab) {
			BaseItemType bit = items.get(index - fixedTab);
			if (bit.isDirty()) {
				int result = JOptionPane.showConfirmDialog(frame, "Exists changes not saved. Save it before closing?",
						"Not saved", JOptionPane.YES_NO_CANCEL_OPTION);
				switch (result) {
				case JOptionPane.YES_OPTION:
					if (!this.onSavePerformed()) {
						return;
					}
					break;
				case JOptionPane.CANCEL_OPTION:
					return;
				case JOptionPane.CLOSED_OPTION:
					return;
				}
			}
			items.remove(index - fixedTab);
			tabbedPane.remove(index);
		}
	}

	private boolean onSaveAsPerformed() {
		int index = tabbedPane.getSelectedIndex();
		if (index == 0) {
			return this.onSaveCharacterPose();
		} else if (index == 1) {
			return this.onSaveWeapon();
		} else {
			BaseItemType bit = items.get(index - fixedTab);
			JFileChooser jfc = new JFileChooser() {
				@Override
				public void approveSelection() {
					File f = getSelectedFile();
					if (f.getAbsolutePath().endsWith(".json") && f.exists() && getDialogType() == SAVE_DIALOG) {
						int result = JOptionPane.showConfirmDialog(this, "The file exists, overwrite?", "Existing file",
								JOptionPane.YES_NO_CANCEL_OPTION);
						switch (result) {
						case JOptionPane.YES_OPTION:
							super.approveSelection();
							return;
						case JOptionPane.NO_OPTION:
							return;
						case JOptionPane.CLOSED_OPTION:
							return;
						case JOptionPane.CANCEL_OPTION:
							cancelSelection();
							return;
						}
					}
					super.approveSelection();
				}
			};
			if (bit.haveFile()) {
				jfc.setCurrentDirectory(bit.getFile().getParentFile());
			}
			jfc.setDialogTitle("Save Skill Motion Description File");
			jfc.setCurrentDirectory(new File(EditorProperty.projectDirectory));
			jfc.setFileFilter(new FileNameExtensionFilter("Javascript Object Notation File", "json"));
			int ret = jfc.showSaveDialog(frame);
			if (ret != 0) {
				return false;
			} else {
				String fullpath = jfc.getSelectedFile().getAbsolutePath();
				if (!fullpath.endsWith(".json")) {
					fullpath += ".json";
				}
				bit.setFile(new File(fullpath));
				tabbedPane.setTitleAt(index, bit.getListname());
			}
			bit.saveFile();
		}
		return true;
	}

	private boolean onSavePerformed() {
		int index = tabbedPane.getSelectedIndex();
		if (index == 0) {
			return this.onSaveCharacterPose();
		} else if (index == 1) {
			return this.onSaveWeapon();
		} else {
			BaseItemType bit = items.get(index - fixedTab);
			if (!bit.haveFile()) {
				JFileChooser jfc = new JFileChooser() {
					@Override
					public void approveSelection() {
						File f = getSelectedFile();
						if (f.getAbsolutePath().endsWith(".json") && f.exists() && getDialogType() == SAVE_DIALOG) {
							int result = JOptionPane.showConfirmDialog(this, "The file exists, overwrite?",
									"Existing file", JOptionPane.YES_NO_CANCEL_OPTION);
							switch (result) {
							case JOptionPane.YES_OPTION:
								super.approveSelection();
								return;
							case JOptionPane.NO_OPTION:
								return;
							case JOptionPane.CLOSED_OPTION:
								return;
							case JOptionPane.CANCEL_OPTION:
								cancelSelection();
								return;
							}
						}
						super.approveSelection();
					}
				};
				jfc.setDialogTitle("Save Skill Motion Description File");
				jfc.setCurrentDirectory(new File(EditorProperty.projectDirectory));
				jfc.setFileFilter(new FileNameExtensionFilter("Javascript Object Notation File", "json"));
				int ret = jfc.showSaveDialog(frame);
				if (ret != 0) {
					return false;
				} else {
					String fullpath = jfc.getSelectedFile().getAbsolutePath();
					if (!fullpath.endsWith(".json")) {
						fullpath += ".json";
					}
					bit.setFile(new File(fullpath));
					tabbedPane.setTitleAt(index, bit.getListname());
				}
			}
			bit.saveFile();
		}
		return true;
	}

	private boolean onSaveWeapon() {
		return false;
	}
	
	private boolean onSaveCharacterPose() {
		return false;

	}

}
