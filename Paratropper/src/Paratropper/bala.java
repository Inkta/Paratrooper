package Paratropper;

public class bala extends ElementMobil{
	double x;
	double y;
	public bala(String imatge, int velL, double x, double y) {
		super(imatge, velL);
		this.x = x;
		this.y = y;
	}
	
	public boolean Mou(joc pantalla) {
		this.getImatge().move(-this.x*this.velocitat, this.y*this.velocitat);
		return true;
	}
	
	
}
