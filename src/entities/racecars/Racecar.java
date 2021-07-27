package entities.racecars;

import audio.SettingsNdAudio;
import entities.Entity;
import handler.Handler;
import states.GameState;
import tiles.Tile;
import tracks.Track;

public abstract class Racecar extends Entity{
	
	public static final int DEFAULT_CAR_WIDTH = 70,
							DEFAULT_CAR_HEIGHT = 70;
	protected double accel = 0.06f;
	protected double topSpeed = 8.0f;
	protected double vector[] = {
			0, //speed
			0 //direction in radians
			};
	protected double dTurn = 0;
	protected double xMove, yMove;
	
	public Racecar(Handler handler, double x, double y, int width, int height) {
		super(handler, x, y, width, height);
		this.vector[0] = 0;
		this.vector[1] = 0;
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
		moveX();
		moveY();
	}
	/*
	 * desc: moves player in x direction and checks collisions by seeing if corners are touching any solid tiles when moved.
	 * parameters: none.
	 * return: moves the player in x direction
	 */
	public void moveX() {
		if(xMove > 0) {//move right 
			int temp = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			if(!collisionWithTile(temp, (int)(y + bounds.y)/Tile.TILEHEIGHT) && !collisionWithTile(temp, (int)(y + bounds.y + bounds.height)/Tile.TILEHEIGHT)) {
				x += xMove;
			}else {
				x = temp * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
				GameState.setGameActive(1);
				SettingsNdAudio.playCrash();
				Player.clearTicker();
				vector[0] = 0;
			}
		}else if(xMove < 0) {//move left
			int temp = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			if(!collisionWithTile(temp, (int)(y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(temp, (int)(y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			}else {
				x = temp * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
				GameState.setGameActive(1);
				SettingsNdAudio.playCrash();
				Player.clearTicker();
				vector[0] = 0;
			}
		}
	}
	/*
	 * desc: moves player in y direction and checks collisions by seeing if corners are touching any solid tiles when moved.
	 * parameters: none.
	 * return: moves the player in y direction
	 */
	public void moveY() {
		if(yMove < 0) {
			int temp = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
			if(!collisionWithTile(((int)(x + bounds.x)/ Tile.TILEWIDTH), temp) && !collisionWithTile(((int)(x + bounds.x + bounds.width)/ Tile.TILEWIDTH), temp)){
				y += yMove;
			}else {
				y = temp * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
				GameState.setGameActive(1);
				SettingsNdAudio.playCrash();
				Player.clearTicker();
				vector[0] = 0;
			}
		}else if(yMove > 0) {
			int temp = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			if(!collisionWithTile(((int)(x + bounds.x)/ Tile.TILEWIDTH), temp) && !collisionWithTile(((int)(x + bounds.x + bounds.width)/ Tile.TILEWIDTH), temp)){
				y += yMove;
			}else {
				y = temp * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
				GameState.setGameActive(1);
				SettingsNdAudio.playCrash();
				Player.clearTicker();
				vector[0] = 0;
			}
		}
	}
	
	/*
	 * desc: if a tile is solid, then it will have a property of isSolid = true.
	 * parameter: x and y of tile to find whether its solid
	 * return: is it solid? if yes, then its collidable and will be used in the above methods
	 */
	protected boolean collisionWithTile(int x, int y) {
		handler.getTrack();
		return Track.getTile(x, y).isSolid();
	}
	
	//getters n setters below
	public double getxMove() {
		return xMove;
	}

	public void setxMove(double xMove) {
		this.xMove = xMove;
	}

	public double getyMove() {
		return yMove;
	}

	public void setyMove(double yMove) {
		this.yMove = yMove;
	}
	
}
