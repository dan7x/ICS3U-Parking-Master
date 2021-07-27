package handler;

import game.Game;
import input.KeyManager;
import tracks.Track;

public class Handler {
	
	private Game game;
	private Track track;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}
}
