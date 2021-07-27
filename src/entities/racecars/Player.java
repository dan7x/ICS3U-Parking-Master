package entities.racecars;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import audio.SettingsNdAudio;
import display.GameDisplay;
import gfx.Assets;
import handler.Handler;
import states.ConfigState;
import states.GameState;
import states.MenuState;
import states.State;
import tiles.Tile;
import tracks.Track;

public class Player extends Racecar{
	
	private int gear = 0; 
	private int prevGear = 0;
	
	private boolean hasReset;
	private static int ticker = 0;
	
	private int overSelector;
	private boolean[] buttons = new boolean[3];
	
	public Player(Handler handler, double x, double y) {
		super(handler, x, y, Racecar.DEFAULT_CAR_WIDTH, Racecar.DEFAULT_CAR_HEIGHT);
		this.hasReset = false;
		this.overSelector = 0;
		//sets the hitbox offset relative to the player's x and y
		bounds.x = 25;
		bounds.y = 25;
		bounds.width = DEFAULT_CAR_WIDTH - 2 * bounds.x;
		bounds.height = DEFAULT_CAR_HEIGHT - 2 * bounds.y;
	}

	public void tick() {
		ticker++;
		if(GameState.getGameActive() == 0 || GameState.getGameActive() == 4){
			getInput();
			move();
		}else if(ticker > 25 && (GameState.getGameActive() == 1 || GameState.getGameActive() == 2) && ticker % 5 == 0){
			getInput();
		}
	}
	
	/*
	 * desc: gets input from user and acts accordingly every tick.
	 * parameters: none; obtained from KeyManager class
	 * return: some action based on keypress.
	 */
	private void getInput() { 
		xMove = 0;
		yMove = 0;
		if(handler.getKeyManager().pause) {
			MenuState menu = new MenuState(handler);
			State.setState(menu);
		}
		
		if(handler.getKeyManager().pKey) {
			if(gear != 0)
				changeGear(0);
		}
		if(handler.getKeyManager().oKey) {
			if(gear != 1 && vector[0] == 0)
				changeGear(1);
		}
		if(handler.getKeyManager().iKey) {
			if(gear != 2)
				changeGear(2);
		}
		if(handler.getKeyManager().uKey) {
			if(gear != 3 && vector[0] == 0)
				changeGear(3);
		}
		
			
		if(handler.getKeyManager().gas) {
			if(GameState.getGameActive() == 0 || GameState.getGameActive() == 4) {
				if(vector[0] < topSpeed && gear != 2 && gear != 0)
					vector[0] += accel;
			} else if (GameState.getGameActive() == 1 || GameState.getGameActive() == 2) {
				if(overSelector == 0) {
					ConfigState config = new ConfigState(handler);
					State.setState(config);
				}else if(overSelector == 1) {
					MenuState menustate = new MenuState(handler);
					State.setState(menustate);
				}else if(overSelector == 2) {
					System.exit(1);
				}
			}
		}else if(!handler.getKeyManager().gas || gear == 2) {
				if(vector[0] > accel)
					vector[0] -= accel;
				else if(vector[0] > 0 && vector [0] <= 5 * accel)
					vector[0] = 0;
		}
		
		if(handler.getKeyManager().brake || (gear == 0 && vector[0] > 0)) {
			if(vector[0] > 0 && vector[0] - 5 * accel > 0)
				vector[0] -= 5 * accel;
			else if(vector[0] > 0)
				vector[0] = 0;
		}
		
		if(handler.getKeyManager().left) {
			if((GameState.getGameActive() == 0 || GameState.getGameActive() == 4) && vector[0] > 0) {
				if(dTurn < 0.1)
					dTurn += 0.02;
				if(vector[1] - dTurn <= 0)
					vector[1] = (double)(2 * Math.PI);
				vector[1] -= dTurn;
				if(dTurn < 0.05)
					dTurn += 0.01;
				//System.out.println("R");
			} else if(GameState.getGameActive() == 1 || GameState.getGameActive() == 2) {
				if(overSelector > 0) {
					overSelector--;
				}
			}
		}else if(!handler.getKeyManager().left && dTurn > 0) {
			dTurn = 0;
		}
		
		if(handler.getKeyManager().right) {
			if((GameState.getGameActive() == 0 || GameState.getGameActive() == 4) && vector[0] > 0) {
				if(dTurn < 0.1)
					dTurn += 0.02;
				vector[1] += dTurn;
				//System.out.println(dTurn);
				if(vector[1] >= (double)(2 * Math.PI))
					vector[1] = 0;
			}else if(GameState.getGameActive() == 1 || GameState.getGameActive() == 2) {
				if(overSelector < buttons.length - 1) {
					overSelector++;
				}
			}
		}else if(!handler.getKeyManager().right && dTurn > 0) {
			dTurn = 0;
		}
		
		if((gear == 3) || ((gear == 2 || gear== 0) && prevGear == 3)) {
			xMove = (double)(vector[0] * Math.cos(vector[1]));
			yMove = (double)(vector[0] * Math.sin(vector[1]));
		}else if(gear == 1 || ((gear == 2 || gear == 0) && prevGear == 1)) {
			xMove = (double)(-vector[0] * Math.cos(vector[1]));
			yMove = (double)(-vector[0] * Math.sin(vector[1]));
		}
	}
	
	/*
	 * desc: changes gear to desired gear. records prev gear. important cause if going from R to N different from D to N
	 * parameters: desired gear
	 * return: changes gear, records previous gear
	 */
	private void changeGear(int changeTo) { 
		prevGear = gear;
		gear = changeTo;
	}
	
	private void drawGearBoxAuto(Graphics g) { //draws the gearbox in the top right corner
		int dim[] = {1100, 30, 75, 200};
		g.setColor(Color.BLACK);
		g.fillRect(dim[0], dim[1], dim[2], dim[3] - 60);
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(dim[0] + (2 * dim[2]) / 4, dim[1] + (2 * dim[2]) / 5, dim[2] / 3, dim[3] / 8);
		g.fillRect((dim[0] + (2 * dim[2]) / 4) + 10, (dim[1] + (2 * dim[2]) / 5) + (dim[3] / 8), (dim[2] / 3) - 10, dim[3] / 16);
		g.fillRect((dim[0] + (2 * dim[2]) / 4) + 7, (dim[1] + (2 * dim[2]) / 5) + (dim[3] / 8) + (dim[3] / 16), (dim[2] / 3) -7, dim[3] / 14);
		g.fillRect(dim[0] +  (3 * dim[2]) / 5, dim[1] + (6 * dim[3] / 15) + 1, (dim[2] / 3) - 10, dim[3] / 14);
		g.fillRect(dim[0] +  (3 * dim[2]) / 5 - 3, dim[1] + (6 * dim[3] / 15) + 1 + (dim[3] / 14), (dim[2] / 3) - 10, dim[3] / 14);
		
		g.setColor(Color.blue);
		g.drawRect(dim[0], dim[1], dim[2], dim[3] - 60);
		
		g.setColor(Color.WHITE);
		g.drawRect(dim[0] + (2 * dim[2]) / 4, dim[1] + (2 * dim[2]) / 5, dim[2] / 3, dim[3] / 8);
		g.drawRect((dim[0] + (2 * dim[2]) / 4) + 10, (dim[1] + (2 * dim[2]) / 5) + (dim[3] / 8), (dim[2] / 3) - 10, dim[3] / 16);
		g.drawRect((dim[0] + (2 * dim[2]) / 4) + 7, (dim[1] + (2 * dim[2]) / 5) + (dim[3] / 8) + (dim[3] / 16), (dim[2] / 3) -7, dim[3] / 14);
		g.drawRect(dim[0] +  (3 * dim[2]) / 5, dim[1] + (6 * dim[3] / 15) + 1, (dim[2] / 3) - 10, dim[3] / 14);
		g.drawRect(dim[0] +  (3 * dim[2]) / 5 - 3, dim[1] + (6 * dim[3] / 15) + 1 + (dim[3] / 14), (dim[2] / 3) - 10, dim[3] / 14);
		
		g.setColor(Color.WHITE);
		if(gear == 0) {
			g.fillRect(dim[0] + (2 * dim[2]) / 4 - 5, dim[1] + (2 * dim[2]) / 5 + 5, dim[2] / 3 + 10, dim[3] / 20);
		}else if (gear == 1) {
			g.fillRect(dim[0] + (2 * dim[2]) / 4 - 5, dim[1] + (2 * dim[2]) / 5 + 40, dim[2] / 3 + 10, dim[3] / 20);
		}else if (gear == 2) {
			g.fillRect(dim[0] + (2 * dim[2]) / 4 - 5, dim[1] + (2 * dim[2]) / 5 + 53, dim[2] / 3 + 10, dim[3] / 20);
		}else if (gear == 3) {
			g.fillRect(dim[0] + (2 * dim[2]) / 4 - 5, dim[1] + (2 * dim[2]) / 5 + 66, dim[2] / 3 + 10, dim[3] / 20);
		}
		
		g.setFont(new Font("Courier", Font.BOLD, 16));
		g.drawString("P", dim[0] + dim[2] / 5, dim[1] + (5 *dim[3]) / 20 -5);
		g.drawString("R", dim[0] + dim[2] / 5, dim[1] + dim[3] / 3 + 12);
		g.drawString("N", dim[0] + dim[2] / 5, dim[1] + (4 * dim[3]) / 10 + 12);
		g.drawString("D", dim[0] + dim[2] / 5, dim[1] + (15 * dim[3]) / 28);
	}
	
	/*
	 * desc: winchecker. checks the four corners of the hitbox. it also checks for handicap mode. if four corners match a valid parking spot, the player wins.
	 * no parameters
	 * return: whether or not player wins. if player wins, change game state.
	 */
	
	private void boxCorners() {
		int tileTL = Track.getTiles()[(int) ((x + bounds.x) / Tile.TILEWIDTH)][(int) ((y + bounds.y)/ Tile.TILEHEIGHT)];
		int tileTR = Track.getTiles()[(int) ((x + bounds.x + bounds.width) / Tile.TILEWIDTH)][(int) ((y + bounds.y)/ Tile.TILEHEIGHT)];
		int tileBL = Track.getTiles()[(int) ((x + bounds.x) / Tile.TILEWIDTH)][(int) ((y + bounds.y + bounds.height)/ Tile.TILEHEIGHT)];
		int tileBR = Track.getTiles()[(int) ((x + bounds.x + bounds.width) / Tile.TILEWIDTH)][(int) ((y + bounds.y + bounds.height)/ Tile.TILEHEIGHT)];
		if(vector[0] == 0 && ((tileTL == 2 && tileTR == 3 && tileBL == 5 && tileBR == 4)||
				(tileTL == 21 && tileTR == 22 && tileBL == 24 && tileBR == 23) || 
				(SettingsNdAudio.isHandicap() && tileTL == 25 && tileTR == 26 && tileBL == 28 && tileBR == 27) ||
				(SettingsNdAudio.isHandicap() && tileTL == 29 && tileTR == 30 && tileBL == 32 && tileBR == 31)
				)) {
			if(!hasReset) {
				ticker = 0;
				hasReset = true;
			}
			if(GameState.getGameActive() == 0) {
				GameState.setGameActive(2);
			}else if(GameState.getGameActive() == 4) {
				if(GameState.getLevel() + 1 < 10) {
					GameState.setLevel(GameState.getLevel() + 1);
					GameState newLv = new GameState(handler, GameState.getLevel());
					State.setState(newLv);
				} else
					GameState.setGameActive(2);
			}
		}
	}
	
	public static void clearTicker() {
		ticker = 0;
	}

	public void render(Graphics g) {
		drawGearBoxAuto(g);
		//System.out.println(vector[1]);
		
//		g.setColor(Color.red); //hitbox
//		g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
		if(GameState.getGameActive() == 4 && GameState.getLevel() == 0) {
			int xPos = 70;
			int scale = 15;
			int yPos = 45;
			g.drawString("CONTROLS:", xPos, 30);
			g.drawString("P - Park   O - Reverse   I - Neutral   U - Drive", xPos, yPos);
			g.drawString("A - Left   D - Right   K - Accelerate   J - Brake", xPos, yPos + scale);
			g.drawString("Esc - Main Menu", xPos, yPos + scale * 2);
			g.drawString("OBJECTIVE: Park in the spot indicated and come to a complete stop.", xPos, yPos + scale * 3);
			g.drawString("Handicap spots can be used to win. Enable/disable in Main Menu > Settings > H", xPos, yPos + scale * 29);
			Font font = new Font("Courier New", Font.BOLD, 30);
			g.setFont(font);
			g.drawString("PARK HERE ====>", 820, 310);
		} else if(GameState.getGameActive() == 4 && GameState.getLevel() == 2) {
			Font font = new Font("Courier New", Font.BOLD, 30);
			g.setFont(font);
			g.drawString("Hint: Reverse",30, 500);
		}
		//draws player. first it rotates the graphics by radians specified by vector[1], draws player, and rotates back.
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform old = g2.getTransform();
		g2.rotate(vector[1], (int)(x + DEFAULT_CAR_WIDTH / 2), (int)(y + DEFAULT_CAR_HEIGHT / 2));
		g2.drawImage(Assets.player, (int)x, (int)y, this.width, this.height, null);
		//g2.drawImage(Assets.carDirection[0], (int)x, (int)y, width, height, null);
		boxCorners();
		g2.setTransform(old);
		
		if(GameState.getGameActive() == 1 || GameState.getGameActive() == 2) { //if game over (gameactive 1 and 2 are win and lose screens), then show buttons to retry, leave, or close program.
			//System.out.println(overSelector);
			int scale = 300;
			int offsetL = 200;
			int strY = 385;
			g.setColor(Color.YELLOW);
			g.fillRect((GameDisplay.GetFrame().getX() / 4) + (offsetL), 350, 250, 70);
			if(overSelector == 0) {
				g.fillRect((GameDisplay.GetFrame().getX() / 4) + (offsetL) - 10, 350 - 10, 250 + 20, 70 + 20);
			}
			else if(overSelector == 1) {
				g.fillRect((GameDisplay.GetFrame().getX() / 4)+ (scale + offsetL)- 10, 350 - 10, 250+ 20, 70+ 20);
			}
			else if(overSelector == 2) {
				g.fillRect((GameDisplay.GetFrame().getX() / 4) + (2 * scale + offsetL)- 10, 350- 10, 250 + 20, 70+ 20);
			}
			g.setColor(Color.BLACK);
			g.fillRect((GameDisplay.GetFrame().getX() / 4) + (offsetL), 350, 250, 70);
			g.fillRect((GameDisplay.GetFrame().getX() / 4)+ (scale + offsetL), 350, 250, 70);
			g.fillRect((GameDisplay.GetFrame().getX() / 4) + (2 * scale + offsetL), 350, 250, 70);
			g.setColor(Color.WHITE);
			Font font = new Font("Courier New", Font.BOLD, 40);
			g.setFont(font);
			g.drawString("Retry", (GameDisplay.GetFrame().getX() / 4) + ( offsetL), strY);
			g.drawString("Main Menu",(GameDisplay.GetFrame().getX() / 4)+ (scale + offsetL), strY);
			g.drawString("Exit",(GameDisplay.GetFrame().getX() / 4) + (2 * scale + offsetL), strY);
			Font fontGame = new Font("Courier New", Font.BOLD, 60);
			g.setFont(fontGame);
			int[] fontpos = {150, 250};
			if(GameState.getGameActive() == 1) {
				g.setColor(Color.RED);
				g.drawString("YOU CRASHED THE CAR! >:(", fontpos[0], fontpos[1]);
			}
			else if(GameState.getGameActive() == 2) {
				g.setColor(Color.GREEN);
				g.drawString("YOU WIN! :)",fontpos[0], fontpos[1]);
			}
		}
	}

}
