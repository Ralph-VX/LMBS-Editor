package kien.lmbseditor.core;

import java.awt.geom.Point2D;

import kien.lmbseditor.core.motion.SkillMotionCommandApplyDamage;
import kien.lmbseditor.window.MainWindow;
import kien.util.KienLogger;
import net.arnx.jsonic.JSON;

public class LMBSEditorMain {
	static public void main(String args[]){
		EditorProperty.init();
		MainWindow.main(args);
	}
}
