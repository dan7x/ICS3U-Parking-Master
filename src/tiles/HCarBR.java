package tiles;

import gfx.Assets;

public class HCarBR extends Tile{
	public HCarBR(int id) {
		super(Assets.hcbr, id);

	}
	public boolean isSolid() {
		return true;
	}
}
