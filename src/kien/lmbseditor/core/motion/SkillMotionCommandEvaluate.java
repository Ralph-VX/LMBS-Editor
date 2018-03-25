package kien.lmbseditor.core.motion;

import java.util.LinkedHashMap;

public class SkillMotionCommandEvaluate extends SkillMotionCommandBase {

	public final String type = "evaluate";
	public String expression;
	
	public SkillMotionCommandEvaluate() {
		expression = "";
	}
	
	@Override
	public void setProperty(LinkedHashMap<String, Object> list) {
		try {
			this.expression = (String) list.get("expression");
		} catch (Exception e) {
			throw new IllegalArgumentException("Provided JSON Object is not available!");
		}
	}

	@Override
	public String obtainCommandRepresentation() {
		return super.obtainCommandRepresentation() + ": " + 
				(expression.length() > 15 ? expression.substring(0,  15) : expression) +
				(expression.length() > 15 ? "..." : "");
	}

	@Override
	public String commandListName() {
		return "Evaluate";
	}

	@Override
	public String typeName() {
		return type;
	}

	@Override
	protected void updatePropertyFromMap(LinkedHashMap<String, Object> data) {
		this.setProperty(data);
	}

	@Override
	public LinkedHashMap<String, Object> obtainPropertyList() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("expression", this.expression);
		return map;
	}
}
