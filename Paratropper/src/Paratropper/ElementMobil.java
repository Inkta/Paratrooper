package Paratropper;

public abstract class ElementMobil extends Element{
	int velocitat;
	public ElementMobil(String imatge, int a) {
		super(imatge);
		this.velocitat = a;
	}
	
	public boolean Mou(joc pantalla) {
		this.getImatge().move(0,this.velocitat);
		return true;
	}
	
}