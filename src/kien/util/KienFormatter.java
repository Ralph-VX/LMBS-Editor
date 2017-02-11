package kien.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class KienFormatter extends Formatter {

	private SimpleDateFormat d;
	
	public KienFormatter() {
		d = new SimpleDateFormat("HH:mm:ss");
	}
	
	@Override
	public String format(LogRecord record) {
		StringBuffer buf = new StringBuffer();
		buf	.append("["+d.format(new Date(record.getMillis()))+"]")
		    .append("")
		    .append("["+record.getLevel().getName()+"]")
		    .append(" : ")
		    .append(record.getMessage())
		    .append("\n");
		return buf.toString();
	}

}
