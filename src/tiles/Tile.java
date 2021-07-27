package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//puts tiles into the tile array
	public static Tile[] tiles = new Tile[256];
	public static Tile roadTile = new RoadBlack(0);
	public static Tile grassTile = new GrassTile(1);
	
	public static Tile dasch1 = new LPark(2);
	public static Tile dasch2 = new RPark(3);
	public static Tile dasch3 = new RPark(4);
	public static Tile dasch4 = new LPark(5);
	
	public static Tile ctr = new CarTR(7);
	public static Tile ctl = new CarTL(6);
	public static Tile cbl = new CarBL(9);
	public static Tile cbr = new CarBR(8);
	
	public static Tile median = new MedianTile(10);
	public static Tile dashed = new DashTile(11);
	public static Tile P = new PTile(12);
	
	public static Tile grassB = new GrassB(13);
	public static Tile grassT = new GrassT(15);
	public static Tile grassR = new GrassR(14);
	public static Tile grassL = new GrassL(16);
	
	public static Tile grassBL = new GrassBL(20);
	public static Tile grassTL = new GrassTL(19);
	public static Tile grassBR = new GrassBR(17);
	public static Tile grassTR = new GrassTR(18);
	
	public static Tile Hdasch1 = new ParkTop(21);
	public static Tile Hdasch2 = new ParkTop(22);
	public static Tile Hdasch3 = new ParkD(23);
	public static Tile Hdasch4 = new ParkD(24);
	
	public static Tile handi1 = new CapL(25);
	public static Tile handi2 = new CapR(26);
	public static Tile handi3 = new RPark(27);
	public static Tile handi4 = new LPark(28);
	
	public static Tile Hhandi1 = new CapTop(29);
	public static Tile Hhandi2 = new ParkTop(30);
	public static Tile Hhandi3 = new ParkD(31);
	public static Tile Hhandi4 = new CapDown(32);
	
	public static Tile Hcar1 = new HCarTL(33);
	public static Tile Hcar2 = new HCarTR(34);
	public static Tile Hcar3 = new HCarBR(35);
	public static Tile Hcar4 = new HCarBL(36);
	
	public static Tile cone = new ConeTile(37);
	public static Tile Hmedian = new Hmedian(38);
	public static Tile Hdash = new Hdash(39);
	
	public static final int TILEWIDTH = 30, TILEHEIGHT = 30;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x * TILEWIDTH, y * TILEHEIGHT, TILEWIDTH, TILEHEIGHT, null);
	}

	public void render(Graphics g, int x, int y,int width,int height) {
		g.drawImage(texture, x, y, width, height, null);
	}
	
	public void render(Graphics g, int x, int y, int scalar) {
		g.drawImage(texture, x * TILEWIDTH * scalar, y * TILEHEIGHT * scalar, TILEWIDTH * scalar, TILEHEIGHT * scalar, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getId() {
		return id;
	}
}
