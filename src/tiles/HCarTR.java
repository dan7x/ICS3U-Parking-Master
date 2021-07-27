package tiles;

import gfx.Assets;

public class HCarTR extends Tile{
	public HCarTR(int id) {
		super(Assets.hctr, id);

	}
	public boolean isSolid() {
		return true;
	}
}
