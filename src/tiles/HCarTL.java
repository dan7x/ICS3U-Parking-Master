package tiles;

import gfx.Assets;

public class HCarTL extends Tile{

	public HCarTL(int id) {
		super(Assets.hctl, id);

	}
	public boolean isSolid() {
		return true;
	}
}
