package game;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


import display.GameDisplay;
import gfx.Assets;
import handler.Handler;
import input.KeyManager;
import states.MenuState;
import states.State;

public class Game implements Runnable {
	
	private GameDisplay display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//states
	private State menuState;
	
	//input
	private KeyManager keyManager;
	
	//handler
	private Handler handler;
	
	public Game(String title, int width, int height) { //constructor for the game class (width of window, height of window, title of window, keyManager class for inputs.
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	/*
	 * Description - initializes game and creates the display for it. also loads all the images.
	 * no parameters
	 * return: sets state to the main menu.
	 */
	private void init() {
		display = new GameDisplay(title, width, height);
		GameDisplay.GetFrame().addKeyListener(keyManager);
		Assets.init();
		
		handler = new Handler(this);
		
		menuState = new MenuState(handler);
		State.setState(menuState);
	}
	
	private void tick() {//constantly checks for keys pressed
		keyManager.tick(); 
		if(State.getState() != null) {
			State.getState().tick();
		}
	}
	
	private void render() { //draws stuff to a canvas before drawing it onto the actual monitor.
		bs = display.GetCanvas().getBufferStrategy();
		if(bs == null) {
			display.GetCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//clear
		g.clearRect(0, 0, width, height);
		//draw
		
		if(State.getState() != null) { //renders based on the "render()" method in each game state.
			State.getState().render(g);
		}
		
		//end draw
		bs.show();
		g.dispose();
	}
	
	public void run() { //30 fps max because of how i rendered the player and the menu button selection. too high and it goes too fast and bad things will happen
		init();
		
		int fps = 30;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick; //whenever one "tick" passes, this will become bigger than one. after that, it gets reset and will happen again.
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000) {
				//System.out.println("Ticks and frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
