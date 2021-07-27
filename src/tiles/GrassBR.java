package tiles;

import gfx.Assets;

public class GrassBR extends Tile{
	public GrassBR(int id) {
		super(Assets.grassBR, id);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSolid() {
		return true;
	}
}
