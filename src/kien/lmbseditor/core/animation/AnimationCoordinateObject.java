package kien.lmbseditor.core.animation;

import java.math.BigDecimal;

public class AnimationCoordinateObject {
	private Double valued;
	private String values;
	public String origin;
	private boolean isString;
	
	public Object getValue() {
		return isString ? values : valued;
	}
	
	public void setValue(Object obj) {
		if (obj instanceof Number) {
			valued = ((Number)obj).doubleValue();
			isString = false;
		} else if (obj instanceof String) {
			try {
				valued = Double.parseDouble(((String)obj));
				isString = false;
			} catch (Exception e) {
				values = (String)obj;
				isString = true;
			}
		}
	}
	
	public AnimationCoordinateObject() {
		valued = new Double(0);
		values = "";
		isString = false;
		origin = "user";
	}
	
	
}
