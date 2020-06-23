import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

@SuppressWarnings("unused")
public class Bullet extends GameObject{
	
	public static final int SIZE = 10;
	public static final int INIT_POSX = 0;
	public static final int INIT_POSY = 0;
	public static final int INIT_VX = 0;
	public static final int INIT_VY = -15;
	

	public Bullet(int courtWidth, int courtHeight) {
		super(INIT_VX, INIT_VY, INIT_POSX, INIT_POSY, SIZE, SIZE, 
			  courtWidth, courtHeight);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(this.getPx() - SIZE / 2 , this.getPy() - SIZE / 2, 10, 10);
	}


}