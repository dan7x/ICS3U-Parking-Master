package tiles;

import gfx.Assets;

public class GrassTL extends Tile{

	public GrassTL(int id) {
		super(Assets.grassTL, id);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSolid() {
		return true;
	}

}
