package tiles;

import gfx.Assets;

public class CarTL extends Tile{

	public CarTL(int id) {
		super(Assets.carTL, id);
	}
	
	public boolean isSolid() {
		return true;
	}
}
