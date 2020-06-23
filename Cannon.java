//import apple.laf.JRSUIConstants;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

@SuppressWarnings("unused")
public class Cannon extends GameObject{
	
	public static final int SIZE = 40;
	public static final int INIT_VX = 0; 
	public static final int INIT_VY = 0; 
	
	public Cannon(int courtWidth, int courtHeight) {
		super(INIT_VX, INIT_VY, courtWidth/2, (courtHeight - 30) - SIZE/2, 
				SIZE, SIZE, courtWidth, courtHeight);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(this.getPx() -  SIZE /2 , this.getPy() - SIZE / 2, SIZE, SIZE);
	}



}