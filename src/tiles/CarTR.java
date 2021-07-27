package tiles;

import gfx.Assets;

public class CarTR extends Tile{
	
	public CarTR(int id) {
		super(Assets.carTR, id);
	}
	
	public boolean isSolid() {
		return true;
	}
}
