package Paratropper;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

public class joc extends GraphicsProgram {
	public Cano cano;
	public int rotacio = 0;
	public double angle = 0;
	Poblat lloc;
	GLabel tirs;
	boolean pulsat = false;
	GImage start = new GImage("Start.png");
	GImage fi = new GImage("Final.png");
	GImage background = new GImage("background.jpg");
	
	public final void run() {
		setSize(1200,800);
		pause(100);
		while (1 > 0) {
			double[] MidaPantalla = {getWidth(), getHeight()};
			addKeyListeners(this);
			start.setSize(MidaPantalla[0],MidaPantalla[1]);
			add(start);
			while (!pulsat) {System.out.print("");}
			remove(start);
			background.setSize(MidaPantalla[0],MidaPantalla[1]);
			add(background);
			lloc = new Poblat(this);
			cano = PosicionaCano(MidaPantalla);
			while(lloc.batalla(cano,tirs)) {}
			pause(100);
			remove(background);
			lloc.clearCanvas(cano);
			pause(50);
			pulsat = false;
			fi.setSize(MidaPantalla[0],MidaPantalla[1]);
			add(fi);
			while(!pulsat) {System.out.print("");}
			remove(fi);
		}
	}
	
	private Cano PosicionaCano(double[] MidaPantalla) {
		Cano cano = new Cano("base-1.png",50);
		cano.getImatge().setSize(100,65);
		pause(100);
		cano.getImatge().setLocation(MidaPantalla[0]/2-cano.getImatge().getWidth()/2, MidaPantalla[1]-cano.getImatge().getHeight());
		add(cano.getImatge());
		return cano;
	}
	
	public Cano Arma() {
		return cano;
	}
	
	public final void keyPressed(final KeyEvent e) {
		Cano cano = Arma();
		switch(e.getKeyCode()) {
		case KeyEvent.VK_F1:
			pulsat = true;
			break;
		case KeyEvent.VK_UP:
			break;
		case KeyEvent.VK_LEFT:
			this.angle = this.angle - 0.25;
			break;
		case KeyEvent.VK_RIGHT:
			this.angle = this.angle + 0.25;
			break;
		case KeyEvent.VK_S:
			if (cano.getBales() >= 0) {
				lloc.AfegirBala(cano.Dispara(Math.sin(this.angle),Math.cos(this.angle)));
			}
			break;
		default:
			break;
		}
	}
}
