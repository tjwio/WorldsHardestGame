package mainGame;

public class Enemies extends Thing {
	private int speed = 5;
	private boolean forward;
	private char dir;
	private int minX;
	private int minY;
	private int maxX;
	private int maxY;
	
	public Enemies(int xc, int yc, int s, int miX, int miY, int maX, int maY, char c, boolean forw, int sp) {
		super(xc, yc, s);
		this.minX = miX;
		this.maxX = maX;
		this.minY = miY;
		this.maxY = maY;
		this.dir = c;
		this.forward = forw;
		this.speed = sp;
	}
	
	public void increment() {
		if (this.dir == 'H') {
			if (forward) {
				this.x += speed;
				if (this.x + size > maxX) {
					this.x = maxX - size;
					forward = false;
				}
			}
			
			else {
				this.x -= speed;
				if (this.x < minX) {
					this.x = minX;
					forward = true;
				}
			}
		}
		
		else if (this.dir == 'V'){
			if (forward) {
				this.y += speed;
				if (this.y + size > maxY) {
					this.y = maxY - size;
					forward = false;
				}
			}
			
			else {
				this.y -= speed;
				if (this.y < minY) {
					this.y = minY;
					forward = true;
				}
			}
		}
		
		else if (this.dir == 'D') {
			if (forward) {
				this.x += speed/2;
				this.y += speed/2;
				
				if (this.y + size > maxY) {
					this.y = maxY - size;
					forward = false;
				}
				else if (this.x + size > maxX) {
					this.x = maxX - size;
					forward = false;
				}
			}
			
			else {
				this.x -= speed/2;
				this.y -= speed/2;
				
				if (this.y < minY) {
					this.y = minY;
					forward = true;
				}
				
				else if (this.x < minX) {
					this.x = minX;
					forward = true;
				}
			}
		}
		
		else if (this.dir == 'E') {
			if (forward) {
				this.x -= speed/2;
				this.y += speed/2;
				
				if (this.y + size > maxY) {
					this.y = maxY - size;
					forward = false;
				}
				else if (this.x < minX) {
					this.x = minX;
					forward = false;
				}
			}
			
			else {
				this.x += speed/2;
				this.y -= speed/2;
				
				if (this.y < minY) {
					this.y = minY;
					forward = true;
				}
				
				else if (this.x+size > maxX) {
					this.x = maxX-size;
					forward = true;
				}
			}
		}
	}
}
