package mainGame;

public abstract class Thing {
	protected int x;
	protected int y;
	protected int size;
	
	public Thing(int xc, int yc, int s) {
		this.x = xc;
		this.y = yc;
		this.size = s;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getSize() {
		return this.size;
	}
}
