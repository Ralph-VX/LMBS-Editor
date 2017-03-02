package kien.lmbseditor.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import kien.lmbseditor.mv.Animation;
import kien.util.KienLogger;
import kien.util.Util;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import net.arnx.jsonic.TypeReference;

public class EditorProperty {
	
	static public Preferences prefs;
	static public String projectDirectory;
	static public int backgroundRed;
	static public int backgroundGreen;
	static public int backgroundBlue;
	static public ArrayList<Animation> animations;
	static public File poseDirectory;
	static public CharacterList characterList;
	static public File weaponDirectory;
	static public WeaponList weaponList;
	static public String currentWeaponName;
	static public BufferedImage currentWeaponImage;
	
	static {
		init();
	}
	
	static public void init() {
		prefs = Preferences.userNodeForPackage(kien.lmbseditor.core.LMBSEditorMain.class);
		projectDirectory = prefs.get("projectDirectory", null);
		backgroundRed = prefs.getInt("backgroundRed", 0);
		backgroundGreen = prefs.getInt("backgroundGreen", 0);
		backgroundBlue = prefs.getInt("backgroundBlue", 255);
		currentWeaponName = prefs.get("currentweapon", "");
		loadProject();
	}
	
	static public void updateCurrentWeapon(WeaponSet n) {
		weaponList.current = n;
		if (n != null) {
			try {
				currentWeaponImage = ImageIO.read(n.imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static public void onClose() {
		prefs.put("currentweapon", weaponList.current != null ? weaponList.current.name : "");
	}
	
	static public void setProjectDirectory(String newDirectory) {
		projectDirectory = newDirectory;
		prefs.put("projectDirectory",newDirectory);
		loadProject();
	}
	
	static public void setBackgroundColor(int red, int green, int blue) {
		backgroundRed = red;
		backgroundGreen = green;
		backgroundBlue = blue;
		prefs.putInt("backgroundRed", red);
		prefs.putInt("backgroundGreen", green);
		prefs.putInt("backgroundBlue", blue);
	}
	
	static public Color getBackgroundColor() {
		return new Color(backgroundRed,backgroundGreen,backgroundBlue);
	}
	
	static public TexturePaint getBackgroundTexturePaint() {
		BufferedImage bi = new BufferedImage(32,32, BufferedImage.TYPE_INT_ARGB);
		Color color = getBackgroundColor();
		Graphics2D g = (Graphics2D)bi.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, 16, 16);
		g.fillRect(16, 16, 16, 16);
		g.setColor(color.darker());
		g.fillRect(16, 0, 16, 16);
		g.fillRect(0, 16, 16, 16);
		g.dispose();
		return new TexturePaint(bi,new Rectangle(0,0,32,32));
	}

	static public TexturePaint getBackgroundTexturePaint(Color color) {
		BufferedImage bi = new BufferedImage(32,32, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D)bi.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, 16, 16);
		g.fillRect(16, 16, 16, 16);
		g.setColor(color.darker());
		g.fillRect(16, 0, 16, 16);
		g.fillRect(0, 16, 16, 16);
		g.dispose();
		return new TexturePaint(bi,new Rectangle(0,0,32,32));
	}
	
	static public void loadProject() {
		animations = null;
		if (projectDirectory != null) {
			String dataFolder = projectDirectory + "\\data\\";
			File animationjson = new File(dataFolder + "Animations.json");
			if (animationjson.exists()) {
				try {
					animations = JSON.decode(new FileReader(animationjson), new TypeReference<ArrayList<Animation>> () {});
				} catch (JSONException | IOException e) {
					e.printStackTrace();
					animations = new ArrayList<Animation>();
				}
			}
			poseDirectory = new File(projectDirectory + "\\img\\sv_actors\\");
			weaponDirectory = new File(projectDirectory + "\\img\\weapons\\");
			initAllCharacters();
			initAllWeapons();
		}
	}
	
	private static void initAllWeapons() {
		weaponList = new WeaponList();
		File[] files = weaponDirectory.listFiles();
		if (files != null) {
			for (File f : files) {
				weaponList.addFile(f);
			}
		}
		updateCurrentWeapon(weaponList.list.get(currentWeaponName));
	}

	static private void initAllCharacters() {
		characterList = new CharacterList();
		File[] subdirectories = Util.getSubDirectories(poseDirectory);
		for (int n = 0; n < subdirectories.length; n++) {
			File f = subdirectories[n];
			characterList.lists.put(f.getName(), initCharacterSet(f.getName()));
		}
		
	}
	
	static public CharacterSet initCharacterSet(String characterName) {
		File directory = new File(poseDirectory.getPath() + "\\" +  characterName + "\\");
		CharacterSet set = new CharacterSet();
		set.characterName = characterName;
		set.poses = new LinkedHashMap<String, CharacterPose>();
		if (directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles(new FilenameFilter() {
		        @Override
		        public boolean accept(File dir, String name) {
		            return name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".json");
		        }
		    });
			for (int n = 0; n < files.length; n++) {
				File f = files[n];
				if (f.getName().endsWith(".png")) {
					Pattern p = Pattern.compile("(.+?)(?:\\[(.*)\\])?\\.png");
					Matcher m = p.matcher(f.getName());
					m.matches();
					String posename = m.group(1);
					if (posename != null) {
						CharacterPose pose = set.poses.get(posename);
						if (pose == null) {
							pose = new CharacterPose(posename);
						}
						pose.poseFile = f;
						String prop = m.group(2);
						if (prop != null) {
							Pattern p2 = Pattern.compile("F(\\d+)");
							Matcher m2 = p2.matcher(prop);
							if (m2.find()) {
								pose.frame = Integer.parseInt(m2.group(1));
							} else {
								pose.frame = 1;
							}
							p2 = Pattern.compile("W(\\d+)");
							m2 = p2.matcher(prop);
							if (m2.find()) {
								pose.width = Integer.parseInt(m2.group(1));
							}
							p2 = Pattern.compile("H(\\d+)");
							m2 = p2.matcher(prop);
							if (m2.find()) {
								pose.height = Integer.parseInt(m2.group(1));
							}
							
						}
						set.poses.put(posename, pose);
					}
				} else {
					Pattern p = Pattern.compile("^(.+)\\.json");
					Matcher m = p.matcher(f.getName());
					m.matches();
					String posename = m.group(1);
					if (posename != null) {
						CharacterPose pose = set.poses.get(posename);
						if (pose == null) {
							pose = new CharacterPose(posename);
						}
						try {
							pose.property = JSON.decode(new FileReader(f), new TypeReference<PoseProperty>() {});
							pose.propertyFile = f;
						} catch (Exception e) {
							KienLogger.logger.severe("Error occured while reading the file: " + f.getName());
							e.printStackTrace();
							pose.property = null;
							pose.propertyFile = null;
						}
						set.poses.put(posename, pose);
					}
				}
			}
		}
		for (CharacterPose pose : set.poses.values()) {
			pose.applyPoseProperty();
		}
		return set;
	}
	
	static public void saveCharacter() {
		characterList.save();
	}
	
	static public void forceSaveCharacter() {
		characterList.forceSave();
	}
	
	static public void saveWeapon() {
		weaponList.save();
	}
	
}
