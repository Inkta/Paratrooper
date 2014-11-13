package Paratropper;
import java.util.ArrayList;

public class Cano extends Element{
	int bales;
	public Cano(String imatge, int a) {
		super(imatge);
		this.bales = a;
	}
	
	public bala Dispara(double x, double y) {
		this.bales = this.bales - 1;
		bala a = new bala("bola.png",-10,x,y);
		a.getImatge().setLocation(this.getImatge().getX()+this.getImatge().getWidth()/2-5, this.getImatge().getY());
		a.getImatge().setSize(10,10);
		return a;
	}
}
