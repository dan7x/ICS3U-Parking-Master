package tiles;

import gfx.Assets;

public class GrassL extends Tile{
	
	public GrassL(int id) {
		super(Assets.grassL, id);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSolid() {
		return true;
	}
}
