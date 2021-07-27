package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import display.GameDisplay;
import gfx.Assets;
import handler.Handler;
import tiles.Tile;
import utilites.Utilities;

public class MapState extends State{

	private int[][] contents = new int[40][20];
	private int[] selected = {0,0};
	private int[] tileBox = new int[40];
	private int selectorBox = 0;
	private long ticker;
	
	public MapState(Handler handler, boolean isLoader) {
		super(handler);
		if(isLoader) {
			@SuppressWarnings("unused")
			String tName = "";
			try{
				tName = JOptionPane.showInputDialog(GameDisplay.GetFrame(),"Enter the track to load (as it appears in resources folder). Omit the '.txt' at the end.", null);
			}catch(Exception e) {
				System.out.println(">:( no no no");
			}
			
			try {
				String file = Utilities.loadFileAsString(System.getProperty ("user.dir") + "/resources/tracks/" + tName + ".txt");
				String[] tokens = file.split("\\s+"); //splits "builder" string from utilities class into an array of values
				
				for(int j = 0; j < contents[0].length; j++) {
					for(int i = 0; i < contents.length; i++) {
						contents[i][j] = Utilities.parseInt(tokens[(i + j * contents.length) + 4]); // converts the 1d array to the game map, +4 because the first 4 items are map data
					}
				}
			}catch(Exception e){
				System.out.println();
			}
			handler.getKeyManager().clearKeys();
		}
	}

	public void tick() {
			
		ticker++;
		if(ticker % 4 == 0) {
			if(handler.getKeyManager().gas) {
				if(selectorBox != 2 && selectorBox != 6 && selectorBox != 21 && selectorBox != 25 && selectorBox != 29 && selectorBox != 33)
					contents[selected[0]][selected[1]] = selectorBox;
				else {
					if(selected[0] + 1 < contents.length && selected[1] + 1 < contents[0].length) {
						contents[selected[0]][selected[1]] = selectorBox;
						contents[selected[0] + 1][selected[1]] = selectorBox + 1;
						contents[selected[0] + 1][selected[1] + 1] = selectorBox + 2;
						contents[selected[0]][selected[1] + 1] = selectorBox + 3;
					}
				}
			} 
			if(handler.getKeyManager().pause) {
				MenuState menu = new MenuState(handler);
				State.setState(menu);
			}
			if(handler.getKeyManager().right) {
				if(selected[0] < contents.length - 1) {
					selected[0]++;
				}
			}
			if(handler.getKeyManager().left) {
				if(selected[0] > 0) {
					selected[0]--;
				}
			}
			if(handler.getKeyManager().wKey) {
				if(selected[1] > 0) {
					selected[1]--;
				}
			}
			if(handler.getKeyManager().sKey) {
				if(selected[1] < contents[0].length - 1) {
					selected[1]++;
				}
			}
			if(handler.getKeyManager().jKey) {
				if(selectorBox > 0) {
					if(selectorBox == 6 || selectorBox == 10 || selectorBox == 25 || selectorBox == 29 || selectorBox == 33 || selectorBox == 37)
						selectorBox-= 4;
					else
						selectorBox--;
				}
			}
			if(handler.getKeyManager().LKey) {
				if(selectorBox < tileBox.length - 1) {
					if(selectorBox == 2 || selectorBox == 6 || selectorBox == 21 || selectorBox == 25 || selectorBox == 29 ||selectorBox == 33) //add park
						selectorBox+= 4;
					else
						selectorBox++;
				}
			}
			if(handler.getKeyManager().pKey) {
				PrintWriter outFile = null;
				String tName = JOptionPane.showInputDialog(GameDisplay.GetFrame(),"Enter the track name (as it appears in resources folder). Omit the '.txt' at the end. If it already exists, it will be replaced.", null);
				try {
					outFile = new PrintWriter(new FileWriter("resources/tracks/" + tName + ".txt"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				int xInit = Integer.parseInt(JOptionPane.showInputDialog(GameDisplay.GetFrame(),"Enter x value between 0 and 40", null));
				xInit = xInit * Tile.TILEWIDTH;
				int yInit = Integer.parseInt(JOptionPane.showInputDialog(GameDisplay.GetFrame(),"Enter y value between 0 and 20", null));
				yInit = yInit * Tile.TILEWIDTH;
				outFile.print("40 20\n+" + xInit + " " + yInit+"\n");
				
				
				for(int j = 0; j < contents[j].length; j++) {
					for(int i = 0; i < contents.length; i++) {
						outFile.print(contents[i][j] + " ");
					}
					outFile.println();
				}
				outFile.close();
				MenuState menu = new MenuState(handler);
				State.setState(menu);
			}
		}
	}

	BufferedImage images[] = {Assets.tarmac, //0
			Assets.grass, //1 -
			
			Assets.parkL, //2 -
			Assets.parkR, //3 
			Assets.parkR, //4
			Assets.parkL, //5
			
			Assets.carTL, //6 -
			Assets.carTR, //7
			Assets.carBR, //8
			Assets.carBL, //9
			
			Assets.median, //10 -
			Assets.dashes, //11 -
			Assets.P, //12 -
			Assets.grassB, //13 -
			Assets.grassR,//14 -
			Assets.grassT, //15 -
			Assets.grassL, //16 -
			Assets.grassBR, //17 -
			Assets.grassTR, //18 -
			Assets.grassTL, //19 -
			Assets.grassBL, //20 -
			
			Assets.parkU, //horz spot 21 -
			Assets.parkU,
			Assets.parkD,
			Assets.parkD,
			Assets.HCL, //hc vert 25 -
			Assets.HCR,
			Assets.parkR,
			Assets.parkL,
			Assets.HCU,//hc horz 29 -
			Assets.parkU,
			Assets.parkD,
			Assets.HCD,
			
			Assets.hctl,
			Assets.hctr,
			Assets.hcbr,
			Assets.hcbl,
			
			Assets.cone,
			Assets.Hmedian,
			Assets.Hdash
			}; //10 tiles fill later
	
	public void render(Graphics g) {
		g.drawImage(Assets.editBack, 0,0,null);
		Font font = new Font("Courier New", Font.BOLD, 18);
		g.setFont(font);
		int yOffset = 70;
		
		int xPos = 650;
		int yPos = 110;
		int scale = 30;
		g.setColor(Color.BLACK);
		Font smallFont = new Font("Courier New", Font.BOLD, 12);
		g.setFont(smallFont);
		for(int j = 0; j < contents[j].length; j++) {
			for(int i = 0; i < contents.length; i++) {
				if(selected[0] == i && selected[1] == j) {
					g.drawString("Selected Coordinates: (" + i + ", " + j + ")", xPos, yPos + 7 * scale);
				}else {
					g.drawImage(images[contents[i][j]], i * (Tile.TILEWIDTH / 2), j*(Tile.TILEHEIGHT / 2) + yOffset, Tile.TILEWIDTH/2, Tile.TILEHEIGHT/2,null);
				}
			}
		}
		g.drawString("Welcome to the editor!", xPos, yPos);
		g.drawString("Use J and L to move focus left or right in toolbox.", xPos, yPos + 1 * scale);
		g.drawString("Press K to place the block down at the selected yellow box.", xPos, yPos + 2 * scale);
		g.drawString("W A S D to move about the map.", xPos, yPos + 3 * scale);
		g.drawString("Press P to save and esc to return to menu.", xPos, yPos + 4 * scale);
		g.drawString("Saving files will override existing ones with same name", xPos, yPos + 5 * scale);
		g.drawString("Welcome to the editor!", xPos, yPos + 6 * scale);
		
		g.drawImage(images[contents[selected[0]][selected[1]]], selected[0] * (Tile.TILEWIDTH / 2), selected[1]*(Tile.TILEHEIGHT / 2) + yOffset, Tile.TILEWIDTH/2, Tile.TILEHEIGHT/2,null);
		g.setColor(Color.YELLOW);
		g.drawRect(selected[0] * (Tile.TILEWIDTH/ 2), selected[1]*(Tile.TILEHEIGHT / 2) + yOffset, Tile.TILEWIDTH / 2, Tile.TILEHEIGHT/2);
		Color toolCol = new Color(137, 129, yOffset);
		g.setColor(Color.BLACK);
		int dx = 5;
		int dy = 5;
		xPos = 30;
		yPos = 390;
		
		g.fillRect(xPos - dx, yPos - dy, 1100 + 2 * dx, 150 + 2 * dy);
		g.setColor(toolCol);
		g.fillRect(xPos, yPos, 1100, 150);
		g.setColor(Color.BLACK);
		g.fillRect(xPos - 4, yPos - 4, 158, 32);
		toolCol = new Color(204, 190, 0);
		g.setColor(toolCol);
		g.fillRect(xPos, yPos, 150, 25);
		g.setColor(Color.BLACK);
		g.drawString("Toolbox", xPos + 20, yPos + 18);
		int distApt = 40;
		int iOffset = 0;
		int aptOffset = 0;
		xPos = 650;
		yPos = 110;
		
		String[] tileNames = {
				"Road",
				"Grass", 
				"Player parking",
				"Occupied spot",
				"Median",
				"Dashed line",
				"P Sign", 
				"Grass Bounds (Bottom)",
				"Grass Bounds (Right)", 
				"Grass Bounds (Top)", 
				"Grass Bounds (Left)", 
				"Grass Corner (BR)",
				"Grass Corner (TR)",
				"Grass Corner (TL)", 
				"Grass Corner (BL)",
				"Horizontal player parking",
				"Vertical Handicap Spot",
				"Horizontal Handicap Spot",
				"Horizontal Occupied Spot",
				"Traffic Cone",
				"Horizontal Median",
				"Horizontal Dashed Line"
				};
		for(int i = 0; i < tileBox.length; i++) {
			g.setColor(Color.BLACK);
			if(i == selectorBox) {
				g.setColor(Color.ORANGE);
				g.fillRect(47 + (i - iOffset) * distApt+ aptOffset, 447, 33, 33); //select rect
				g.setColor(Color.BLACK);
				g.drawString(tileNames[i - iOffset], 50 + (i - iOffset) * distApt + aptOffset, 445);
				g.drawString("Selected Toolbox Item:" + tileNames[i - iOffset], xPos, yPos + 8 * scale);
			}
			else
				g.drawRect(50 + (i - iOffset) * distApt + aptOffset, 450, 30, 30);
			
			g.drawImage(images[i], 50 + (i - iOffset) * distApt + aptOffset, 450, 30, 30,null);
			if(i == 2 || i == 6 || i == 21 || i == 25 || i == 29 || i == 33) {
				int height = 30;
				int width = 30;
				g.drawImage(images[i + 1], 50 + (i - iOffset) * distApt + width+ aptOffset, 450, width, height, null);
				g.drawImage(images[i + 2], 50 + (i - iOffset) * distApt + width+ aptOffset, 450 + height, width, height, null);
				g.drawImage(images[i + 3], 50 + (i - iOffset) * distApt + aptOffset, 450 + height, width, height, null);
				i+=3;
				iOffset+=3;
				aptOffset += width;
			}
		}
	}

}
