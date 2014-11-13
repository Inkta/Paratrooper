package Paratropper;

public class Para extends ElementMobil{
	boolean vida = true;
	public Para(String imatge, int velL) {
		super(imatge,velL);
	}
	
	public void SetVida(boolean a) {
		this.vida = a;
	}
	
}
