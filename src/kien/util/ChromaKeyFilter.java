package kien.util;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

public class ChromaKeyFilter extends RGBImageFilter {
	
	public Color chromaKey;
	public boolean includeAlpha;
	
	public ChromaKeyFilter() {
		super();
		this.chromaKey = null;
		this.includeAlpha = false;
	}
	
	public ChromaKeyFilter(Color key, boolean alpha) {
		super();
		this.chromaKey = key;
		this.includeAlpha = alpha;
	}
	
	@Override
	public int filterRGB(int x, int y, int rgb) {
		if (this.chromaKey == null) {
			return rgb;
		}
		int chromaVal = this.chromaKey.getRGB();
		int rgbVal = rgb;
		if (!includeAlpha) {
			chromaVal = chromaVal & 0x00ffffff;
			rgbVal = rgb & 0x00ffffff;
		}
		if (chromaVal != rgbVal) {
			return rgb;
		}
		return 0x00000000;
	}
}
