package Paratropper;

public class persona extends ElementMobil {
	boolean vida = true;
	public persona(String imatge, int velL) {
		super(imatge,velL);
	}
	
	public void SetVel(int vel) {
		this.velocitat = vel;
	}
	
	public void SetVida(boolean a) {
		this.vida = a;
	}
}
