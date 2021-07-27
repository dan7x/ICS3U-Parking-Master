package tracks;

import java.awt.Graphics;

import game.Game;
import handler.Handler;
import states.MenuState;
import states.State;
import tiles.Tile;
import utilites.Utilities;

public class Track {
	
	private Handler handler ;
	private static int width;
	private static int height;
	private int xInit, yInit;
	private static int[][] tiles;
	
	public static int[][] getTiles() {
		return tiles;
	}

	public void setTiles(int[][] tiles) {
		Track.tiles = tiles;
	}

	public Track(Handler handler, String path) {
		this.handler = handler;
		loadWorld(path);
	}
	
	public int getxInit() {
		return xInit;
	}

	public void setxInit(int xInit) {
		this.xInit = xInit;
	}

	public int getyInit() {
		return yInit;
	}

	public void setyInit(int yInit) {
		this.yInit = yInit;
	}

	public void tick() {
		
	}
	
	public void render(Graphics g) { //finds what tile is at a given x and y pos (in tile terms) and renders it to the screen
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				getTile(x, y).render(g, x, y);
			}
		}
	}
	
	/*
	 * desc: gets a given tile at the position x,y in the array with the track
	 * parameters; x,y location within the tile grid
	 * return: the tile object at the xy position
	 */
	
	public static Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
			
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null) {
			return Tile.roadTile;
		}
		return t;
	}
	
	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	
	/*
	 * Desc: loads the text file containing the track into a string, and then an array of tiles.
	 * Parameters: path of file
	 * Return: a loaded tiles array
	 */
	
	private void loadWorld(String path) {
		try {
		String file = Utilities.loadFileAsString(path);
		String[] tokens = file.split("\\s+"); //splits "builder" string from utilities class into an array of values
		width = Utilities.parseInt(tokens[0]);
		height = Utilities.parseInt(tokens[1]);
		xInit =  Utilities.parseInt(tokens[2]);
		yInit = Utilities.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y< height; y++) {
			for(int x = 0;x < width; x++) {
				tiles[x][y] = Utilities.parseInt(tokens[(x + y * width) + 4]); // converts the 1d array to the game map, +4 because the first 4 items are map data
			}
		}
		}catch(Exception e){
			System.out.println();
		}
	}
	
}
