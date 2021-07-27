package gfx;
import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;	
	}
	
	public BufferedImage crop(int x, int y, int width, int height) { //crops a portion of buffered image, used to get textures.
		return sheet.getSubimage(x, y, width, height);
	}
}
