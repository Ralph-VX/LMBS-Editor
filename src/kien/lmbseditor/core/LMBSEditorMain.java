package kien.lmbseditor.core;

import kien.lmbseditor.window.MainWindow;

public class LMBSEditorMain {
	static public void main(String args[]){
		System.out.println(Double.parseDouble("12.23+this.x"));
		EditorProperty.init();
		MainWindow.main(args);
	}
}
