import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*; 

@SuppressWarnings("unused")
public class Boulder extends GameObject {

	//each boulder is given an initial random health between 1 and 100
	private int health; 

	//both height and width will be determined by the health of the boulder
	private int height;
	private int width;

	private int extraBit;

	private int healthLevel;

	private boolean boulderBroke;
	//x and y are the coordinates of the boulder on the panel
	
	public static final int INIT_POSX = 0; 
	public static final int INIT_POSY = 200;
	public static final int INIT_VX = 0; 
	public static final int INIT_VY = 0;
	public static double HEIGHT = (Math.sqrt(2)/2);
	public static double WIDTH = (Math.sqrt(2)/2);
	
	
	
	//constructor
	public Boulder(int courtWidth, int courtHeight) {
  
  	//super the constructor
		super(INIT_VX, INIT_VY, INIT_POSX, INIT_POSY, 
				(int)(20 * HEIGHT), (int) (20 * WIDTH),
				courtWidth, courtHeight);

		int newHealth = (int)(Math.random() * 99) + 1;
		this.setHealth(newHealth);

		if(newHealth >= 75){
			this.healthLevel = 3;
		}
		else if(newHealth >= 50){
			this.healthLevel = 2;
		}
		else{
			this.healthLevel = 1;
		}

		this.boulderBroke = false;
	}


	//health setter
	public void setHealth(int desiredHealth){
		this.health = desiredHealth;
	}
	
	//health getter!
	public int getHealth() {
		return this.health;
	}

	public int getExtraBit() {
		return this.extraBit;
	}

	public boolean getBoulderBroke(){
		return this.boulderBroke;
	}

	public void setBoulderBroke(boolean bool){
		this.boulderBroke = bool;
	}


	@Override
	public void move() {
		this.setPx(this.getPx() + this.getVx());
		this.setPy(this.getPy() + this.getVy());
		this.setVy(this.getVy() + 1);
	}

	@Override
	public void draw(Graphics g) {
		if(this.health >= 75) {
			this.width = ((int) (100 * WIDTH));
			this.height = ((int) (100 * HEIGHT));
			this.setWidth((int) (100 * WIDTH));
			this.setHeight((int) (100 * WIDTH));
			this.extraBit = 100 - this.width;
			g.setColor(Color.RED);
			g.fillOval(this.getPx() - 50, this.getPy() - 50, 100, 100);
			g.setColor(Color.BLACK);
			g.drawOval(this.getPx() - 50, this.getPy() - 50, 100, 100);
		}

		else if (this.health >= 50) {
			this.width = ((int)(75 * WIDTH));
			this.height = ((int)(75 * HEIGHT));
			this.setWidth((int)(75 * WIDTH));
			this.setHeight((int)(75 * WIDTH));
			this.extraBit = 75 - this.width;
			g.setColor(Color.GREEN);
			g.fillOval(this.getPx() - 37, this.getPy() - 37, 75, 75);
			g.setColor(Color.BLACK);
			g.drawOval(this.getPx() - 37, this.getPy() - 37, 75, 75);
			if(this.healthLevel == 3){
				this.healthLevel = 2;
				this.boulderBroke = true;
			}
		}

		else if (this.health >= 1) {
			this.width = ((int)(60 * WIDTH));
			this.height = ((int)(60 * WIDTH));
			this.setWidth((int)(60 * WIDTH));
			this.setHeight((int)(60 * WIDTH));
			this.extraBit = 60 - this.width;
			g.setColor(Color.BLUE);
			g.fillOval(this.getPx() - 30, this.getPy() - 30, 60, 60);
			g.setColor(Color.BLACK);
			g.drawOval(this.getPx() - 30, this.getPy() - 30, 60, 60);
			if(this.healthLevel == 2){
				 this.healthLevel = 1;
				 this.boulderBroke = true;
			}
		}
	}
}