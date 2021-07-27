package tiles;

import gfx.Assets;

public class GrassTR extends Tile{
	public GrassTR(int id) {
		super(Assets.grassTR, id);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSolid() {
		return true;
	}
}
