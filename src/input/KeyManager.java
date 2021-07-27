package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	
	private boolean[] keys;
	public boolean gas, left, right, brake,
	pKey, oKey, iKey, uKey,
	LKey, jKey,
	enter, down, up,
	sKey, wKey, hKey, qKey, eKey,
	L, R,
	pause;
	
	public KeyManager() {
		keys = new boolean[256]; //this stores array of booleans of whether or not keys are held down.
	}
	
	public void tick() {
		gas = keys[KeyEvent.VK_K];
		brake = keys[KeyEvent.VK_J];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
		pKey = keys[KeyEvent.VK_P];
		oKey = keys[KeyEvent.VK_O];
		iKey = keys[KeyEvent.VK_I];
		uKey = keys[KeyEvent.VK_U];
		
		wKey = keys[KeyEvent.VK_W];
		sKey = keys[KeyEvent.VK_S];
		eKey = keys[KeyEvent.VK_E];
		qKey = keys[KeyEvent.VK_Q];
		
		down = keys[KeyEvent.VK_DOWN];
		up = keys[KeyEvent.VK_UP];
		
		LKey = keys[KeyEvent.VK_L];
		jKey = keys[KeyEvent.VK_J];
		hKey = keys[KeyEvent.VK_H];
		
		L = keys[KeyEvent.VK_LEFT];
		R = keys[KeyEvent.VK_RIGHT];
		
		pause = keys[KeyEvent.VK_ESCAPE];
	}
	
	public void clearKeys() { //sets all to false. done before every changestate to prevent bugs.
		for(int i = 0; i < 256; i++) {
			keys[i] = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

}
