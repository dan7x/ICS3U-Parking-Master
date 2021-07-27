package tiles;

import gfx.Assets;

public class HCarBL extends Tile{
	public HCarBL(int id) {
		super(Assets.hcbl, id);

	}
	
	public boolean isSolid() {
		return true;
	}

}
