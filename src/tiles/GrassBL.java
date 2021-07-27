package tiles;

import gfx.Assets;

public class GrassBL extends Tile{
	public GrassBL(int id) {
		super(Assets.grassBL, id);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSolid() {
		return true;
	}
}
