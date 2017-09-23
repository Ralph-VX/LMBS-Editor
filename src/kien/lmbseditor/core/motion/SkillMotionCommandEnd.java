package kien.lmbseditor.core.motion;

import java.awt.Color;
import java.util.LinkedHashMap;

import net.arnx.jsonic.JSONHint;

public class SkillMotionCommandEnd extends SkillMotionCommandBase {
	public final String type = "end";

	public SkillMotionCommandEnd() {
		super();
	}
	
	public SkillMotionCommandEnd(SkillMotionCommandBase parent) {
		super();
		this.setParent(parent);
	}
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) throws Exception {
		
	}

	@Override
	@JSONHint(ignore = true)
	public int getDepth() {
		return depth-1;
	}
	@Override
	public String commandListName() {
		return "End";
	}

	@Override
	public String typeName() {
		return type;
	}
	
	@Override
	public boolean includeAvailable() {
		return false;
	}

	@Override
	public Color commandColor() {
		return Color.BLUE;
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		return null;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		
	}
}
