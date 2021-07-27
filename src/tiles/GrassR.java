package tiles;

import gfx.Assets;

public class GrassR extends Tile{
	
	public GrassR(int id) {
		super(Assets.grassR, id);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSolid() {
		return true;
	}
}
