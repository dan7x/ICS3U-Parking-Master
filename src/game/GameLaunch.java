package game;

import java.io.IOException;

import audio.SettingsNdAudio;

public class GameLaunch {

	public static void main(String[] args) throws IOException{
		Game game = new Game("Titledf", 1200, 600);//basically opens the game
		new SettingsNdAudio("despacito.wav", true, false); //starts playing music
		game.start();
	}

}
