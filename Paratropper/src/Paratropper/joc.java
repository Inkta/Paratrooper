package Paratropper;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class joc extends GraphicsProgram {
	public Cano cano;
	public int rotacio = 0;
	public double angle = 0;
	Poblat lloc = new Poblat(this);
	
	public final void run() {
		setSize(1200,800);
		pause(100);
		double[] MidaPantalla = {getWidth(), getHeight()};
		cano = PosicionaCano(MidaPantalla);
		addKeyListeners(this);
		while(lloc.batalla()) {}
	}
	
	private Cano PosicionaCano(double[] MidaPantalla) {
		Cano cano = new Cano("base.png",50);
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
		case KeyEvent.VK_UP:
			break;
		case KeyEvent.VK_LEFT:
			this.angle = this.angle - 0.25;
			break;
		case KeyEvent.VK_RIGHT:
			this.angle = this.angle + 0.25;
			break;
		case KeyEvent.VK_S:
			lloc.AfegirBala(cano.Dispara(Math.sin(this.angle),Math.cos(this.angle)));
			break;
		default:
			break;
		}
	}
}
