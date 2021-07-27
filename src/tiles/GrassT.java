package tiles;

import gfx.Assets;

public class GrassT extends Tile{
	public GrassT(int id) {
		super(Assets.grassT, id);
	}
	
	public boolean isSolid() {
		return true;
	}
}
