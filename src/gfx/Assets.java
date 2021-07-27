package gfx;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int WIDTH = 32, HEIGHT = 32;
	
	public static BufferedImage player, dash, tarmac, grass, cone, median, dashes, P, Hmedian, Hdash,
	carTL, carTR, carBL, carBR, hctl, hctr, hcbr, hcbl,
	parkL, parkR, parkU, parkD,
	HCD, HCU, HCR, HCL,
	menuBack, editBack, configBack, settingsBack,
	grassB, grassR, grassT, grassL, grassBR, grassTR, grassTL, grassBL;
	
	
	public static void init() { //each image is taken from the sheet and loaded based on x and y within the image.
		SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/sheet.png"));
		player = ImageLoader.LoadImage("/textures/player.png");
		menuBack = ImageLoader.LoadImage("/textures/menuBackground.png");
		editBack = ImageLoader.LoadImage("/textures/editorBack.png");
		configBack = ImageLoader.LoadImage("/textures/configBack.png");
		settingsBack = ImageLoader.LoadImage("/textures/settingsBack.png");
		
		grass = sheet.crop(3 * WIDTH, 0, WIDTH, HEIGHT);
		tarmac = sheet.crop(2 * WIDTH, 0, WIDTH, HEIGHT);
		
		parkL = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);
		parkR = sheet.crop(5 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
		
		parkU = sheet.crop(4 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		parkD = sheet.crop(5 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		
		carBL = sheet.crop(4 * WIDTH, 0, WIDTH, HEIGHT);
		carBR = sheet.crop(6 * WIDTH, 0, WIDTH, HEIGHT);
		carTL = sheet.crop(1 * WIDTH, HEIGHT, WIDTH, HEIGHT);
		carTR = sheet.crop(2 * WIDTH, HEIGHT, WIDTH, HEIGHT);
		
		median = sheet.crop(7 * WIDTH, 0, WIDTH, HEIGHT);
		dashes = sheet.crop(0* WIDTH, HEIGHT, WIDTH, HEIGHT);
		P = sheet.crop(3 * WIDTH, HEIGHT, WIDTH, HEIGHT);
		
		grassB = sheet.crop(4 * WIDTH, HEIGHT, WIDTH, HEIGHT);
		grassR = sheet.crop(5 * WIDTH, HEIGHT, WIDTH, HEIGHT);
		grassT = sheet.crop(6 * WIDTH, HEIGHT, WIDTH, HEIGHT);
		grassL = sheet.crop(7 * WIDTH, HEIGHT, WIDTH, HEIGHT);
		
		grassBR = sheet.crop(0 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		grassTR = sheet.crop(1 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		grassTL = sheet.crop(2 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		grassBL = sheet.crop(3 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		
		HCD = sheet.crop(6 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		HCU = sheet.crop(7 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		HCL = sheet.crop(0 * WIDTH, 3 * HEIGHT, WIDTH, HEIGHT);
		HCR = sheet.crop(1 * WIDTH, 3 * HEIGHT, WIDTH, HEIGHT);
		
		cone = sheet.crop(2 * WIDTH, 3 * HEIGHT, WIDTH, HEIGHT);
		Hmedian = sheet.crop(3 * WIDTH, 3 * HEIGHT, WIDTH, HEIGHT);
		Hdash = sheet.crop(4 * WIDTH, 3 * HEIGHT, WIDTH, HEIGHT);
		
		hctl = sheet.crop(7 * WIDTH, 3 * HEIGHT, WIDTH, HEIGHT);
		hctr = sheet.crop(5 * WIDTH, 3 * HEIGHT, WIDTH, HEIGHT);
		hcbl = sheet.crop(0 * WIDTH, 4 * HEIGHT, WIDTH, HEIGHT);
		hcbr = sheet.crop(6 * WIDTH, 3 * HEIGHT, WIDTH, HEIGHT);
		
		//player = sheet.crop(4 * WIDTH, 0, WIDTH, HEIGHT);
	}
}
