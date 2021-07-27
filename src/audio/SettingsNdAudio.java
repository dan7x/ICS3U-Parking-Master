package audio;

import java.applet.Applet;
import java.applet.AudioClip;

import utilites.Utilities;

@SuppressWarnings("deprecation")
public class SettingsNdAudio {
	static AudioClip song;  //music playing in game
	static AudioClip horn; //car sound effect
	private static String[] music = {"despacito.wav", "crabbe.wav", "pirate.au"}; //string containing filenames
	private static int musicSelector = 0; //selects from index of music array
	private static boolean musicOn = true; //boolean for whether or not music is on
	private static boolean sfxOn = true; //sound effects on or no
	
	public static String[] getMusic() {
		return music;
	}

	public static void setMusic(String[] music) {
		SettingsNdAudio.music = music;
	}

	private static boolean isHandicap = false;
	
	public static void incrementSelector() {
		if(musicSelector < music.length - 1)
			musicSelector++;
	}
	
	public static void decrementSelector() {
		if (musicSelector > 0)
			musicSelector--;
	}
	
	public static boolean isHandicap() {
		return isHandicap;
	}

	public static void setHandicap(boolean isHandicap) {
		SettingsNdAudio.isHandicap = isHandicap;
	}

	public static boolean isMusicOn() {
		return musicOn;
	}

	public static boolean isSfxOn() {
		return sfxOn;
	}
	public static void setSftOn(boolean sff) {
		sfxOn = sff;
	}
	
	/*
	 * desc: constructor method. called when program first starts up, and plays the song specified by the string, and loops is (file, true, false).
	 * parameters: the file, whether to loop, and whether or not the loop was stopped.
	 */
	
	public SettingsNdAudio(String audioFile, boolean isLoop, boolean wasStopped) {
		song = Applet.newAudioClip (Utilities.getCompleteURL (audioFile));
		horn = Applet.newAudioClip (Utilities.getCompleteURL ("beep.au"));
		System.out.println(Utilities.getCompleteURL (audioFile));
		if(!wasStopped && isLoop)
			song.loop();
	}
	
	public static void stopLoop() {
		musicOn = false;
		song.stop();
	}
	
	public static void playCrash() {
		if(sfxOn == true) {
			horn.play();
		}
	}
	
	/*
	 * desc: changes music to specified selector. selector picks from music[] array.
	 * parameters: selector (index of music array
	 * return:  changes the song playing in the back
	 */
	
	public static void changeTrack(int selector) {
		String track = music[musicSelector];
		song.stop();
		song = Applet.newAudioClip (Utilities.getCompleteURL (track));
		System.out.println(Utilities.getCompleteURL (track));
		song.loop();
	}
	
	public static int getMusicSelector() {
		return musicSelector;
	}

	public static void setMusicSelector(int musicSelector) {
		SettingsNdAudio.musicSelector = musicSelector;
	}

	public static void startLoop() {
		musicOn = true;
		song.loop();
	}
}
