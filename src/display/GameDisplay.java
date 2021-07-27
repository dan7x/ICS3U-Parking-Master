package display;
import java.awt.*;

import javax.swing.JFrame;

public class GameDisplay {
	private static JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int width, height;
	
	public GameDisplay(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		CreateDisplay();
	}
	
	private void CreateDisplay() {
		frame = new JFrame(title); //set title
		frame.setSize(width, height);//set size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes when hit x
		frame.setResizable(false); //cant resize or else game breaks
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);//visible
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));//size of canvas
		canvas.setMaximumSize(new Dimension(width,height));//if max size = min size size wont change.
		canvas.setMinimumSize(new Dimension(width,height));
		canvas.setFocusable(false);
		
		frame.add(canvas); //adds canvas to jframe
		frame.pack();
	}
	
	public Canvas GetCanvas() {
		return canvas;
	}
	
	public static JFrame GetFrame() {
		return frame;
	}
}
