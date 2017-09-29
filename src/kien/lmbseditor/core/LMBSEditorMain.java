package kien.lmbseditor.core;

import java.util.ArrayList;

import kien.lmbseditor.core.animation.AnimationLMBSProperty;
import kien.lmbseditor.core.animation.AnimationLMBSTimingBase;
import kien.lmbseditor.core.animation.AnimationLMBSTimingDamage;
import kien.lmbseditor.window.MainWindow;

public class LMBSEditorMain {
	static public void main(String args[]){
		EditorProperty.init();
		MainWindow.main(args);
	}
}
