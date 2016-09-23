package kien.util;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	
	static public String getUniqueFilename(String directory, final String basename, String extension, int valueSize) {
		String name = basename;
		File dir = new File(directory);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (dir.exists()) {
			File[] files = dir.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					// TODO Auto-generated method stub
					return name.startsWith(basename);
				}
				
			});
			int postfix = 0;
			Pattern match = Pattern.compile("(.+?)(\\d+)");
			for (File file : files) {
				Matcher result = match.matcher(file.getName());
				if (result.matches()){
					int npost = Integer.parseInt(result.group(2));
					if (npost >= postfix) {
						postfix = npost+1;
					}
				}
			}
			name = basename + String.format("%0" + valueSize + "d", postfix);
		}
		return name;
		
	}
	/**
	 * calculate coordinate that will put rect 1's center fit in rect2's center
	 * @param rect1 rect that will be moved
	 * @param rect2 rect that will not be moved
	 * @return new coordinate that will achieve this
	 */
	static public Point centerRects(Rectangle rect1, Rectangle rect2) {
		Point p = new Point();
		p.x = rect2.x + (rect2.width - rect1.width) / 2;
		p.y = rect2.y + (rect2.height - rect1.height) / 2;
		return p;
	}
	
	static public void rotateHue(BufferedImage input, int offset) {
		int[] rgb = null;
		int imgw = input.getWidth();
		int imgh = input.getHeight();
		rgb = input.getRGB(0, 0, imgw, imgh, null, 0, imgw);
		offset = ((offset % 360 ) + 360) % 360;
		for (int y = 0; y <imgh; y++) {
			int yindex = y * imgw;
			for (int x = 0; x < imgw; x++) {
				int index = x+yindex;
				int pixel = rgb[index];
				int a = (pixel & 0xff000000) >>> 24;
				int r = (pixel & 0x00ff0000) >>  16;
				int g = (pixel & 0x0000ff00) >>  8;
				int b = (pixel & 0x000000ff);
				int[] hsl = rgb2hsl(r,g,b);
				int h = (hsl[0] + offset) % 360;
				int s = hsl[1];
				int l = hsl[2];
				input.setRGB(x, y, hsla2rgba(h,s,l,a));
			}
		}
		
	}
	
	private static DataBuffer getBuffer(int transferType, int size) {
		switch(transferType) {
		case DataBuffer.TYPE_BYTE: 
			return new DataBufferByte(size);
		case DataBuffer.TYPE_DOUBLE:
			return new DataBufferDouble(size);
		case DataBuffer.TYPE_FLOAT:
			return new DataBufferFloat(size);
		case DataBuffer.TYPE_INT:
			return new DataBufferInt(size);
		case DataBuffer.TYPE_SHORT:
			return new DataBufferShort(size);
		case DataBuffer.TYPE_USHORT:
			return new DataBufferUShort(size);
		default:
			return null;
		}
	}
	
	static private int[] rgb2hsl(int r, int g, int b) {
		
		int cmax = Math.max(r, Math.max(g, b));
		int cmin = Math.min(r, Math.min(g, b));
		
		int h = 0;
		int s = 0;
		int l = (cmin+cmax)/2;
		int delta = cmax-cmin;
		if (delta > 0) {
			if (r == cmax) {
				h = 60 * (((g - b) / delta + 6) % 6);
			} else if (g == cmax) {
				h = 60 * ((b - r) / delta + 2);
			} else {
				h = 60 * ((r - g) / delta + 4);
			}
			s = delta / (255 - Math.abs(2 * l - 255));
		}
		
		return new int[]{h,s,l} ;
	}
	
	static private int hsla2rgba(int h, int s, int l, int a) {
		int c = (255 - Math.abs(2 * l - 255)) * s;
		int x = c * (1 - Math.abs((h / 60) % 2 - 1 ));
		int m = l - c / 2;
		int cm = c + m;
		int xm = x + m;
		if ( h < 60) {
			return (a << 24) | (cm << 16) | (xm << 8) | (m);
		} else if (h < 120) {
			return (a << 24) | (xm << 16) | (cm << 8) | (m);
		} else if (h < 180) {
			return (a << 24) | (m << 16) | (cm << 8) | (xm);
		} else if (h < 240) {
			return (a << 24) | (m << 16) | (xm << 8) | (cm);
		} else if (h < 300) {
			return (a << 24) | (xm << 16) | (m << 8) | (cm);
		} else {
			return (a << 24) | (cm << 16) | (m << 8) | (xm);
		}
	}
	
	static public File[] getSubDirectories(File directory) {
		if (directory.isDirectory()) {
			return directory.listFiles(File::isDirectory);
		}
		return null;
	}
	/**
	 * Sort a provided collection.
	 * Provided from: <a href="http://stackoverflow.com/questions/740299/how-do-i-sort-a-set-to-a-list-in-java">StackOverflow</a> 
	 * @param c
	 * @return
	 */
	public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
	  List<T> list = new ArrayList<T>(c);
	  java.util.Collections.sort(list);
	  return list;
	}
}
