package kien.lmbseditor.core;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import kien.lmbseditor.mv.Animation;
import kien.lmbseditor.window.MainWindow;
import kien.util.KienLogger;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import net.arnx.jsonic.TypeReference;

public class LMBSEditorMain {
	static public void main(String args[]){
		EditorProperty.init();
		MainWindow.main(args);
	}
}
