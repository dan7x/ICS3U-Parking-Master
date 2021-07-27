package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import audio.SettingsNdAudio;
import display.GameDisplay;
import gfx.Assets;
import handler.Handler;

public class MenuState extends State{
	
	private long ticker = 0;
	private int iSelect = 0;
	private boolean selected[] = {true, false, false};
	
	public MenuState(Handler handler) {
		super(handler);
		GameState.setGameActive(0);
		handler.getKeyManager().clearKeys();
	}
	/*
	 * desc - every tick, the game checks what button is selected. 
	 * parameters - based on keypress
	 * return - selects a button or clicks based on the keypress
	 */
	public void tick() {
		ticker++;
		if(ticker % 3 == 0) {
			if(handler.getKeyManager().gas) {
				if(iSelect == 0) {
					handler.getKeyManager().gas = false;
					SettingsNdAudio.playCrash();
					ConfigState config = new ConfigState(handler);
					State.setState(config);
					handler.getKeyManager().gas = false;
				}else if(iSelect == 1) {
					handler.getKeyManager().gas = false;
					SettingsNdAudio.playCrash();
					MapConfigState mapConfig = new MapConfigState(handler);
					State.setState(mapConfig);
					handler.getKeyManager().gas = false;
				}else if(iSelect == 2) {
					handler.getKeyManager().gas = false;
					SettingsNdAudio.playCrash();
					SettingsState settings = new SettingsState(handler);
					State.setState(settings);
					handler.getKeyManager().gas = false;
				}
			}
			if(handler.getKeyManager().up) {
				if(iSelect > 0)
					iSelect--;
				for(int i = 0; i < selected.length; i++)
					selected[i] = false;
				selected[iSelect] = true;
			}
			if(handler.getKeyManager().down) {
				if(iSelect < selected.length - 1)
					iSelect++;
				for(int i = 0; i < selected.length; i++)
					selected[i] = false;
				selected[iSelect] = true;
			}
		}
	}
	/*
	 * desc - renders the buttons
	 * parameters - graphics object, text in button, width and height, whether or not its selected, and the y position (i.e., first button in the column
	 * return - selects a button or clicks based on the keypress
	 */
	private void drawBtn(Graphics g, String text, int width, int height, boolean selected, int yPos) {
		int shifter = 200;
		if(selected) {
			g.setColor(Color.YELLOW);
			g.fillRect(GameDisplay.GetFrame().getWidth() / 2 - width / 2 - 15, yPos * GameDisplay.GetFrame().getHeight() / 7 - 15 +shifter, width + 30, height + 30);
		}
		g.setColor(Color.BLACK);
		g.fillRect(GameDisplay.GetFrame().getWidth() / 2 - width / 2, yPos * GameDisplay.GetFrame().getHeight() / 7 + shifter, width, height);
		g.setColor(Color.WHITE);
		int fontSize = 18;
		Font font = new Font ("Courier New", 1, fontSize);
		g.setFont(font);
		g.drawString(text, GameDisplay.GetFrame().getWidth() / 2 - 50,yPos *  GameDisplay.GetFrame().getHeight() / 7 + height / 2 + shifter);
	}

	public void render(Graphics g) {
		g.drawImage(Assets.menuBack, 0, 0, null);
		int btnHeight = 60;
		int btnWidth = 280;
		drawBtn(g, "Start", btnWidth, btnHeight, selected[0], 1);
		drawBtn(g, "Map Editor", btnWidth, btnHeight, selected[1], 2);
		drawBtn(g, "Settings", btnWidth, btnHeight, selected[2], 3);
	}

}
