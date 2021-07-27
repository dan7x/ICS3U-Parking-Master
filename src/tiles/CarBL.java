package tiles;

import gfx.Assets;

public class CarBL extends Tile{
	
	public CarBL(int id) {
		super(Assets.carBL, id);
	}
	
	public boolean isSolid() {
		return true;
	}
}
