package states;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import display.GameDisplay;
import entities.racecars.Player;
import handler.Handler;
import tracks.Track;

public class GameState extends State {
	
	private Player player;
	private Track track;
	private static int gameActive;
	private static String[] levels = {"L_ONE", "L_TWO", "L_THREE", "L_FOUR", "L_FIVE", "L_SIX", "L_SEVEN", "L_EIGHT", "L_NINE", "L_TEN"};
	private static int level;
	
	public GameState(Handler handler) {
		super(handler);
		String tName = "";
		gameActive = 0;
		level = 0;
		try{
			tName = JOptionPane.showInputDialog(GameDisplay.GetFrame(),"Enter the track name (as it appears in resources folder). Omit the '.txt' at the end.", null);
		}catch(Exception e) {
			System.out.println(">:( no no no");
		}
		if(gameActive == 0)
			track = new Track(handler, "resources/tracks/" + tName + ".txt");
		else if (gameActive == 4) {
			track = new Track(handler, "resources/tracks/" + levels[level] + ".txt");
		}
		handler.setTrack(track);
		player = new Player(handler, track.getxInit(), track.getyInit());
		handler.getKeyManager().clearKeys();
	}
	
	public GameState(Handler handler, int level) {
		super(handler);
		track = new Track(handler, "resources/tracks/" + levels[level] + ".txt");
		handler.setTrack(track);
		player = new Player(handler, track.getxInit(), track.getyInit());
	}
	
	public static int getLevel() {
		return level;
	}

	public static void setLevel(int inp) {
		level = inp;
	}

	public void tick() {
		track.tick();
		player.tick();
	}

	public void render(Graphics g) {
		track.render(g);
		player.render(g);
	}

	public static int getGameActive() {
		return gameActive;
	}

	public static void setGameActive(int game) {
		gameActive = game;
	}
}
