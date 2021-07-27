package tiles;


import gfx.Assets;

public class CarBR extends Tile{
	
	public CarBR(int id) {
		super(Assets.carBR, id);
	}
	
	public boolean isSolid() {
		return true;
	}
}
