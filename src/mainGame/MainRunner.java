package mainGame;

import processing.core.*;

import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.util.*;

public class MainRunner extends PApplet {
	int speed = 5;
	static int deaths = 0;
	int width = 1000, height = 750;
	int level = 1;
	int x = 0, y = 0;
	int size = 25;
	int origX = 0, origY = 0;
	
	boolean shouldSetup = true;
	
	boolean upPress = false;
	boolean leftPress = false;
	boolean downPress = false;
	boolean rightPress = false;
	
	ArrayList<Coins> coins;
	ArrayList<Enemies> enemies;
	ArrayList<Coins> origCoins;
	
	
	public void setup() {
		size(width, height);
		background(199, 206, 209);
		x = 50;
		y = 50;
		origX = x;
		origY = y;
		coins = new ArrayList<Coins>();
		enemies = new ArrayList<Enemies>();
		coins.add(new Coins(450, 200, 15));
		coins.add(new Coins(450, 300, 15));
		coins.add(new Coins(450, 400, 15));
		coins.add(new Coins(450, 500, 15));
		
		origCoins = (ArrayList<Coins>)coins.clone();
		
		enemies.add(new Enemies(450, 200, 15, 225, 200, 700, 200, 'H', true, 10));
		enemies.add(new Enemies(450, 300, 15, 225, 200, 700, 200, 'H', false, 10));
		enemies.add(new Enemies(450, 400, 15, 225, 200, 700, 200, 'H', true, 10));
		enemies.add(new Enemies(450, 500, 15, 225, 200, 700, 200, 'H', false, 10));
	}
	
	public void draw() {
		switch (level) {
			case 0: mainMenu(); break;
			case 1: levelOne(); break;
			case 2: levelTwo(); break;
			case 3: levelThree(); break;
			case 4: levelFour(); break;
			case 5: levelFive(); break;
			case 6: levelSix(); break;
			case 7: levelSeven(); break;
		}
		
		printIt("Deaths: " + deaths);
		printIt("Level: " + level);
		
	}
	
	public void mainMenu() {
		
	}
	
	public void levelOne() {
		resetBackground();
		strokeWeight(5.0f);
		line(25, 25, 25, 575);
		line(25, 25, 125, 25);
		line(125, 25, 125, 450);
		line(25, 575, 700, 575);
		line(125, 450, 200, 450);
		line(200, 450, 200, 100);
		line(200, 100, 800, 100);
		line(700, 575, 700, 200);
		line(700, 200, 800, 200);
		line(800, 200, 800, 100);
		noStroke();
		fill(75, 214, 80, 50);
		rect(27, 27, 97, 100);
		rect(700, 103, 98, 96);
		
		movePlayer();
		
		if (x < 25)
			x = 25;
		else if (x+25 > 125 && x < 200 && (y < 450 || y+25 > 700)) {
			if (x < 125)
				x = 100;
			else
				x = 200;
		}
		else if (x+25>700 && (y<100 || y+25 > 200))
			x = 675;
		
		if (y < 25)
			y = 25;
		else if (y+size > 575)
			y = 575-size;
		else if (y<100 && x+size>125)
			y = 100;
		
		drawPlayer();
		
		drawCoinsAndEnemies();
		
		removeCoinsAndEnemies();
		
		if (this.x > 700 && this.x < 800) {
			if (this.y > 100 && this.y < 200) {
				if (coins.size() < 1) {
					nextLevel();
				}
			}
		}
	}
	
	public void levelTwo() {
		if (shouldSetup) {
			speed = 5;
			coins = new ArrayList<Coins>();
			coins.add(new Coins(450, 350, 15));
			origCoins = (ArrayList<Coins>)coins.clone();
			//enemies = new ArrayList<Enemies>();
			enemies.add(new Enemies(225, 350, 15, 225, 125, 225, 600, 'V', true, 10));
			enemies.add(new Enemies(350, 350, 15, 350, 125, 350, 600, 'V', false, 10));
			enemies.add(new Enemies(575, 350, 15, 575, 125, 575, 600, 'V', true, 10));
			enemies.add(new Enemies(700, 350, 15, 700, 125, 700, 600, 'V', false, 10));
			this.x = 75;
			this.y = 350;
			origX = x;
			origY = y;
			shouldSetup = false;
		}
		resetBackground();
		strokeWeight(5.0f);
		line(50, 300, 150, 300);
		line(50, 400, 150, 400);
		line(50, 300, 50, 400);
		line(150, 300, 150, 100);
		line(150, 400, 150, 600);
		line(150, 100, 750, 100);
		line(150, 600, 750, 600);
		line(750, 100, 750, 300);
		line(750, 600, 750, 400);
		line(750, 300, 850, 300);
		line(750, 400, 850, 400);
		line(850, 300, 850, 400);
		
		fill(75, 214, 80, 50);
		strokeWeight(0);
		rect(50, 300, 100, 100);
		rect(750, 300, 100, 100);
		
		movePlayer();
		
		if (x < 50)
			x = 50;
		
		if (x >= 50 && x < 150) {
			if (y+size > 400)
				y = 400-size;
			else if (y < 300)
				y = 300;
		}
		
		if (x >= 150 && x < 750) {
			if (y < 100)
				y = 100;
			else if (y+size > 600)
				y = 600-size;
		}
		
		if (x+size >= 750) {
			if (y < 300)
				x = 750-size;
			else if (y+size > 400)
				x = 750-size;
		}
		
		if (x+size >= 850)
			x = 850-size;
		
		drawPlayer();
		
		drawCoinsAndEnemies();
		
		removeCoinsAndEnemies();
		
		if (this.x+size > 750 && this.x < 850) {
			if (this.y+size > 300 && this.y < 400) {
				if (coins.size() < 1) {
					nextLevel();
				}
			}
		}
		
		
	}
	
	public void levelThree() {
		resetBackground();
		strokeWeight(5.0f);
		
		if (shouldSetup) {
			speed = 3;
			coins = new ArrayList<Coins>();
			coins.add(new Coins(495, 445, 15));
			origCoins = (ArrayList<Coins>)coins.clone();
			enemies = new ArrayList<Enemies>();
			enemies.add(new Enemies(410, 360, 15, 410, 360, 600, 550, 'D', true, 10));
			enemies.add(new Enemies(600, 360, 15, 410, 360, 600, 550, 'E', true, 10));
			this.x = 407;
			this.y = 307;
			origX = x;
			origY = y;
			shouldSetup = false;
		}
		
		line(400, 300, 400, 550);
		line(400, 300, 450, 300);
		line(450, 300, 450, 350);
		line(450, 350, 600, 350);
		line(600, 350, 600, 550);
		line(400, 550, 600, 550);
		
		fill(75, 214, 80, 50);
		strokeWeight(0);
		rect(400, 300, 50, 50);
		
		movePlayer();
		
		if (y < 300)
			y = 300;
		
		if (y+size > 550)
			y = 550-size;
		
		if (x < 400)
			x = 400;
		
		if (x+size > 600)
			x = 600-size;
		
		if (y >= 300 && y+size <= 350)
			if (x+size > 450)
				x = 450-size;
		
		if (x+size > 450 && x <= 600)
			if (y<350)
				y = 350;
		
		
		drawPlayer();
		
		drawCoinsAndEnemies();
		
		removeCoinsAndEnemies();
		
		if (x+size>400 && x<450) {
			if (y+size>300 && y < 350) {
				if (coins.size() < 1) {
					nextLevel();
				}
			}
		}
	}
	
	public void levelFour() {
		resetBackground();
		strokeWeight(5.0f);
		
		if (shouldSetup) {
			speed = 5;
			coins = new ArrayList<Coins>();
			coins.add(new Coins(400, 375, 15));
			coins.add(new Coins(500, 375, 15));
			coins.add(new Coins(600, 375, 15));
			origCoins = (ArrayList<Coins>)coins.clone();
			enemies = new ArrayList<Enemies>();
			enemies.add(new Enemies(500, 375, 15, 310, 185, 690, 565, 'D', true, 10));
			enemies.add(new Enemies(500, 375, 15, 310, 185, 690, 565, 'E', true, 10));
			enemies.add(new Enemies(500, 375, 15, 310, 185, 690, 565, 'D', false, 10));
			enemies.add(new Enemies(500, 375, 15, 310, 185, 690, 565, 'E', false, 10));
			enemies.add(new Enemies(500, 375, 15, 310, 375, 690, 375, 'H', true, 10));
			enemies.add(new Enemies(500, 375, 15, 310, 375, 690, 375, 'H', false, 10));
			this.x = 489;
			this.y = 114;
			origX = x;
			origY = y;
			shouldSetup = false;
		}
		
		line(300, 175, 450, 175);
		line(550, 175, 700, 175);
		line(300, 175, 300, 575);
		line(700, 175, 700, 575);
		line(300, 575, 450, 575);
		line(550, 575, 700, 575);
		line(450, 175, 450, 75);
		line(550, 175, 550, 75);
		line(450, 75, 550, 75);
		line(450, 575, 450, 675);
		line(550, 575, 550, 675);
		line(450, 675, 550, 675);
		
		fill(75, 214, 80, 50);
		strokeWeight(0);
		rect(450, 75, 100, 100);
		rect(450, 575, 100, 100);
		
		movePlayer();
		
		if (x < 300)
			x = 300;
		else if (x+size > 700)
			x = 700-size;
		
		if (y < 75)
			y = 75;
		else if (y+size > 675)
			y = 675-size;
		
		if ((y>=75 && y+size<=175) || (y>=575 && y+size<=675)) {
			if (x < 450)
				x = 450;
			else if (x+size>550)
				x = 550 - size;
		}
		
		if (x < 450 || x+size > 550) {
			if (y < 175)
				y = 175;
			else if (y+size > 575)
				y = 575-size;
		}
		
		drawPlayer();
		
		drawCoinsAndEnemies();
		
		removeCoinsAndEnemies();
		
		if (x+size >= 450 && x < 550)
			if (y+size > 575 && y <= 675)
				if (coins.size() < 1)
					nextLevel();
	}
	
	public void levelFive() {
		resetBackground();
		strokeWeight(5.0f);
		
		if (shouldSetup) {
			speed = 5;
			coins = new ArrayList<Coins>();
			coins.add(new Coins(240, 375, 15));
			coins.add(new Coins(380, 375, 15));
			coins.add(new Coins(520, 375, 15));
			coins.add(new Coins(660, 375, 15));
			origCoins = (ArrayList<Coins>)coins.clone();
			enemies = new ArrayList<Enemies>();
			enemies.add(new Enemies(240, 375, 15, 100, 310, 380, 450, 'D', true, 10));
			enemies.add(new Enemies(240, 375, 15, 100, 310, 380, 450, 'E', true, 10));
			enemies.add(new Enemies(380, 375, 15, 240, 310, 520, 450, 'D', false, 10));
			enemies.add(new Enemies(380, 375, 15, 240, 310, 520, 450, 'E', false, 10));
			enemies.add(new Enemies(520, 375, 15, 380, 310, 660, 450, 'D', true, 10));
			enemies.add(new Enemies(520, 375, 15, 380, 310, 660, 450, 'E', true, 10));
			enemies.add(new Enemies(660, 375, 15, 520, 310, 800, 450, 'D', false, 10));
			enemies.add(new Enemies(660, 375, 15, 520, 310, 800, 450, 'E', false, 10));
			this.x = 101;
			this.y = 359;
			origX = x;
			origY = y;
			shouldSetup = false;
		}
		
		line(100, 300, 700, 300);
		line(100, 450, 800, 450);
		line(100, 300, 100, 450);
		line(700, 300, 700, 200);
		line(700, 200, 800, 200);
		line(800, 200, 800, 450);
		
		fill(75, 214, 80, 50);
		strokeWeight(0);
		rect(700, 200, 100, 100);
		
		movePlayer();
		
		
		if (x < 100)
			x = 100;
		else if (x+size > 800)
			x = 800-size;
		
		if (y < 200)
			y = 200;
		
		if (y+size > 450)
			y = 450-size;
		
		if (x >= 100 && x+size < 700) {
			if (y < 300)
				y = 300;
		}
		
		if (y >= 200 && y+size <= 300) {
			if (x < 700)
				x = 700;
		}
		
		
		drawPlayer();
		
		drawCoinsAndEnemies();
		
		removeCoinsAndEnemies();
		
		if (x+size > 700 && x <= 800)
			if (y < 300 && y+size >= 200)
				if (coins.size() < 1)
					nextLevel();
	}
	
	public void levelSix() {
		resetBackground();
		strokeWeight(5.0f);
		
		if (shouldSetup) {
			speed = 5;
			coins = new ArrayList<Coins>();
			coins.add(new Coins(350, 250, 15));
			coins.add(new Coins(350, 350, 15));
			coins.add(new Coins(350, 450, 15));
			coins.add(new Coins(650, 250, 15));
			coins.add(new Coins(650, 350, 15));
			coins.add(new Coins(650, 450, 15));
			coins.add(new Coins(450, 450, 15));
			coins.add(new Coins(550, 450, 15));
			origCoins = (ArrayList<Coins>)coins.clone();
			enemies = new ArrayList<Enemies>();
			enemies.add(new Enemies(350, 250, 15, 310, 250, 400, 250, 'H', true, 5));
			enemies.add(new Enemies(350, 350, 15, 310, 350, 400, 350, 'H', false, 5));
			enemies.add(new Enemies(350, 450, 15, 310, 450, 400, 450, 'H', true, 5));
			enemies.add(new Enemies(650, 250, 15, 610, 250, 700, 250, 'H', false, 5));
			enemies.add(new Enemies(650, 350, 15, 610, 350, 700, 350, 'H', true, 5));
			enemies.add(new Enemies(650, 450, 15, 610, 450, 700, 450, 'H', false, 5));
			enemies.add(new Enemies(450, 450, 15, 450, 410, 450, 500, 'V', false, 5));
			enemies.add(new Enemies(550, 450, 15, 550, 410, 550, 500, 'V', true, 5));
			this.x = 338;
			this.y = 138;
			origX = x;
			origY = y;
			shouldSetup = false;
		}
		
		line(300, 100, 400, 100);
		line(400, 100, 400, 400);
		line(300, 100, 300, 500);
		line(300, 500, 700, 500);
		line(400, 400, 600, 400);
		line(600, 400, 600, 100);
		line(700, 500, 700, 100);
		line(600, 100, 700, 100);
		
		fill(75, 214, 80, 50);
		strokeWeight(0);
		rect(300, 100, 100, 100);
		rect(600, 100, 100, 100);
		
		movePlayer();
		
		if (x < 300)
			x = 300;
		else if (x+size > 700)
			x = 700-size;
		
		if (y < 100)
			y = 100;
		else if (y+size > 500)
			y = 500-size;
		
		
		if (y >= 100 && y < 400 && y+size-400 < 0) {
			if (x+size > 400 && x < 600) {
				if (x+size - 400 < 100)
					x = 400-size;
				else
					x = 600;
			}
		}
		
		if (x+size > 400 && x < 600) {
			if (y < 400)
				y = 400;
		}
		
		
		drawPlayer();
		
		drawCoinsAndEnemies();
		
		removeCoinsAndEnemies();
		
		if (x+size >= 600 && x <= 700)
			if (y+size >= 100 && y < 200)
				if (coins.size() < 1)
					nextLevel();
	}
	
	public void levelSeven() {
		resetBackground();
		strokeWeight(5.0f);
		
	}
	
	public void movePlayer() {
		if (upPress)
			y -= speed;
		if (leftPress)
			x -= speed;
		if (downPress)
			y += speed;
		if (rightPress)
			x += speed;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key != KeyEvent.VK_UNDEFINED)
		{
			if (key == KeyEvent.VK_UP)
			{
				//y -= speed;
				upPress = true;
			}
			if (key == KeyEvent.VK_DOWN)
			{
				//y += speed;
				downPress = true;
			}
			
			if (key == KeyEvent.VK_RIGHT)
			{
				//x += speed;
				rightPress = true;
			}
			
			if (key == KeyEvent.VK_LEFT)
			{
				//x -= speed;
				leftPress = true;
			}
			
			if (key == KeyEvent.VK_SPACE) {
				loop();
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key != KeyEvent.VK_UNDEFINED)
		{
			if (key == KeyEvent.VK_UP)
			{
				//y -= speed;
				upPress = false;
			}
			if (key == KeyEvent.VK_DOWN)
			{
				//y += speed;
				downPress = false;
			}
			
			if (key == KeyEvent.VK_RIGHT)
			{
				//x += speed;
				rightPress = false;
			}
			
			if (key == KeyEvent.VK_LEFT)
			{
				//x -= speed;
				leftPress = false;
			}
		}
	}
	
	public void drawPlayer() {
		stroke(0);
		strokeWeight(1.0f);
		fill(255, 0, 0);
		rect(x, y, size, size);
	}
	
	public void drawCoinsAndEnemies() {
		stroke(0);
		strokeWeight(1.0f);
		fill(255, 255, 0);
		for (Coins c : coins) {
			ellipse(c.getX(), c.getY(), c.getSize(), c.getSize());
		}
		
		fill(0, 0, 255);
		for (Enemies e : enemies) {
			e.increment();
			ellipse(e.getX(), e.getY(), e.getSize(), e.getSize());
		}
	}
	
	public void removeCoinsAndEnemies() {
		int rem = -1;
		
		for (int i = 0; i < coins.size(); i++) {
			int xc1 = coins.get(i).getX() - coins.get(i).getSize()/2;
			int xc2 = coins.get(i).getX() + coins.get(i).getSize()/2;
			int yc1 = coins.get(i).getY() - coins.get(i).getSize()/2;
			int yc2 = coins.get(i).getY() + coins.get(i).getSize()/2;
			
			int x2 = this.x + size;
			int y2 = this.y + size;
			
			if ((x2 >= xc1 && xc1 >= x) || (xc2 >= x && x2 >= xc2)) {
				if ((y2 >= yc1 && yc1 >= y) || (yc2 >= y && y2 >= yc2)) {
					rem = i;
					break;
				}
			}
		}
		
		if (rem > -1)
			coins.remove(rem);
		
		for (Enemies e : enemies) {
			int xc1 = e.getX() - e.getSize()/2;
			int xc2 = e.getX() + e.getSize()/2;
			int yc1 = e.getY() - e.getSize()/2;
			int yc2 = e.getY() + e.getSize()/2;
			
			int x2 = this.x + size;
			int y2 = this.y + size;
			
			if ((x2 >= xc1 && xc1 >= x) || (xc2 >= x && x2 >= xc2)) {
				if ((y2 >= yc1 && yc1 >= y) || (yc2 >= y && y2 >= yc2)) {
					deaths++;
					this.x = origX;
					this.y = origY;
					coins = (ArrayList<Coins>)origCoins.clone();
				}
			}
		}
	}
	
	public void nextLevel() {
		level++;
		resetBackground();
		shouldSetup = true;
		PFont f = createFont("Arial", 50, true);
		printIt("Level: " + level + "\nPress Space", f, 50, 400, 400);
		noLoop();
	}
	
	public void printIt(String s) {
		PFont f = createFont("Arial", 16, true);
		printIt(s, f, 36);
	}
	
	public void printIt(String s, PFont f, int size) {
		if (s.contains("Deaths"))
			printIt(s, f, size, 25, 700);
		else if (s.contains("Level"))
			printIt(s, f, size, 800, 700);
	}
	
	public void printIt(String s, PFont f, int size, int x, int y) {
		textFont(f, size);
		fill(20);
		
		text(s, x, y);
	}
	
	public void resetBackground() {
		background(199, 206, 209);
	}
}
