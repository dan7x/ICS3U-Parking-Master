package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import audio.SettingsNdAudio;
import display.GameDisplay;
import gfx.Assets;
import handler.Handler;

public class SettingsState extends State{
	
	private int ticker = 0;
	
	//default values
	private boolean[] settings = {
			SettingsNdAudio.isMusicOn()//music, s
			,SettingsNdAudio.isSfxOn()//sfx, w
			,SettingsNdAudio.isHandicap(), //handicap, h
			true //switch songs
	};
	
	public SettingsState(Handler handler) {
		super(handler);
		handler.getKeyManager().clearKeys(); //clears keys when loading every game state to prevent computer from thinking a key is pressed when its not
	}

	@Override
	/*
	 * description: This method is called once every frame in the game and checks input.
	 * parameters: Uses the keys array in KeyManager class to find what keys are pressed
	 * return: some action based on what key is pressed
	 */
	public void tick() {
		ticker++;
		if(handler.getKeyManager().wKey && ticker % 5 == 0) {//wKey to toggle music
			SettingsNdAudio.playCrash();
			if(settings[0]) {
				SettingsNdAudio.stopLoop();
				settings[0] = false;
			}else {
				SettingsNdAudio.startLoop();
				settings[0] = true;
			}
		}
		if(handler.getKeyManager().qKey && ticker % 5 == 0) {//qKey is to change song. index of music[] array in settingsNdAudio decreases
			SettingsNdAudio.playCrash();
			SettingsNdAudio.decrementSelector();
			SettingsNdAudio.changeTrack(SettingsNdAudio.getMusicSelector());
		}
		if(handler.getKeyManager().eKey && ticker % 5 == 0) {//eKey is to change song. index of music[] array in settingsNdAudio increases
			SettingsNdAudio.playCrash();
			SettingsNdAudio.incrementSelector();
			SettingsNdAudio.changeTrack(SettingsNdAudio.getMusicSelector());
		}
		if(handler.getKeyManager().hKey && ticker % 5 == 0) { //toggle handicap mode.
			SettingsNdAudio.playCrash();
			if(settings[2]) {
				SettingsNdAudio.setHandicap(false);
				settings[2] = false;
			}else {
				SettingsNdAudio.setHandicap(true);
				settings[2] = true;
			}
		}
		if(handler.getKeyManager().pause) { //esc goes to menu
			MenuState menu = new MenuState(handler);
			State.setState(menu);
		}
		if(handler.getKeyManager().sKey && ticker % 5 == 0){ //toggles sound fx
			if(settings[1]) {
				settings[1] = false;
				SettingsNdAudio.setSftOn(false);
			}else {
				settings[1] = true;
				SettingsNdAudio.setSftOn(true);
			}
			SettingsNdAudio.playCrash();
		}
	}

	/*
	 * desc:
	 * parameters: graphics object, text to be displayed, width and height, setting (whether text is true or not), and y position (i.e., first button from top is yPos = 1).
	 * return: draws a box with a color based on whether the stuff inside is true or false.
	 */
	
	private void drawTable(Graphics g, String text, int width, int height, boolean setting, int yPos) { 
		if(setting) {
			g.setColor(Color.GREEN);
			g.fillRect(GameDisplay.GetFrame().getWidth() / 2 - width / 2 - 15, yPos * GameDisplay.GetFrame().getHeight() / 5 - 15, width + 30, height + 30);
		} else {
			g.setColor(Color.RED);
			g.fillRect(GameDisplay.GetFrame().getWidth() / 2 - width / 2 - 15, yPos * GameDisplay.GetFrame().getHeight() / 5 - 15, width + 30, height + 30);
		}
		g.setColor(Color.BLACK);
		int fontSize = 18;
		Font font = new Font ("Courier New", 1, fontSize);
		g.setFont(font);
		g.drawString(text, GameDisplay.GetFrame().getWidth() / 2 - 300,yPos *  GameDisplay.GetFrame().getHeight() / 5 + height / 2);
	}
	
	@Override
	/*
	 * desc : renders stuff to screen every frame
	 * parameters: graphics object
	 *return: displays the settings options on the screen
	 */
	public void render(Graphics g) {
		g.drawImage(Assets.settingsBack,0,0,null);
		drawTable(g, "Toggle music - press W", 600, 30, settings[0], 1);
		drawTable(g, "Toggle SFX - press S", 600, 30, settings[1], 2);
		drawTable(g, "Toggle Handicap Mode - press H", 600, 30, settings[2], 3);
		drawTable(g, "Switch songs - Q / E, Now Playing: " + SettingsNdAudio.getMusic()[SettingsNdAudio.getMusicSelector()], 600, 30, settings[3], 4);
	}

}
