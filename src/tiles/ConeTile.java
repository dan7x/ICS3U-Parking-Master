package tiles;

import gfx.Assets;

public class ConeTile extends Tile{

	public ConeTile(int id) {
		super(Assets.cone, id);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSolid() {
		return true;
	}
	
}
