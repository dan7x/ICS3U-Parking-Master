package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import audio.SettingsNdAudio;
import display.GameDisplay;
import gfx.Assets;
import handler.Handler;

public class MapConfigState extends State{

	private long ticker = 0;
	private int iSelect;
	private boolean selected[] = {true, false};
	
	public MapConfigState(Handler handler) {
		super(handler);
		handler.getKeyManager().clearKeys();
		this.iSelect = 0;
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
				if(iSelect == 1) {
					handler.getKeyManager().gas = false;
					SettingsNdAudio.playCrash();
					MapState map = new MapState(handler, true);
					State.setState(map);
				}else if(iSelect == 0) {
					handler.getKeyManager().gas = false;
					SettingsNdAudio.playCrash();
					MapState map = new MapState(handler, false);
					State.setState(map);
				}
			}
			if(handler.getKeyManager().pause) {
				handler.getKeyManager().gas = false;
				MenuState menu = new MenuState(handler);
				State.setState(menu);
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
		if(selected) {
			g.setColor(Color.YELLOW);
			g.fillRect(GameDisplay.GetFrame().getWidth() / 2 - width / 2 - 15, yPos * GameDisplay.GetFrame().getHeight() / 5 - 15, width + 30, height + 30);
		}
		g.setColor(Color.BLACK);
		g.fillRect(GameDisplay.GetFrame().getWidth() / 2 - width / 2, yPos * GameDisplay.GetFrame().getHeight() / 5, width, height);
		g.setColor(Color.WHITE);
		int fontSize = 18;
		Font font = new Font ("Courier New", Font.BOLD, fontSize);
		g.setFont(font);
		g.drawString(text, (GameDisplay.GetFrame().getWidth() / 2) - 100,yPos *  GameDisplay.GetFrame().getHeight() / 5 + height / 2);
	}

	public void render(Graphics g) { //calls the drawBtn method
		g.drawImage(Assets.configBack, 0, 0, null);
		int btnHeight = 60;
		int btnWidth = 280;
		drawBtn(g, "Load Existing Map", btnWidth, btnHeight, selected[1], 2);
		drawBtn(g, "Create New Map", btnWidth, btnHeight, selected[0], 1);
	}

}
