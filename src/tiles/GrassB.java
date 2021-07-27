package tiles;

import gfx.Assets;

public class GrassB extends Tile{
	public GrassB(int id) {
		super(Assets.grassB, id);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSolid() {
		return true;
	}
}
