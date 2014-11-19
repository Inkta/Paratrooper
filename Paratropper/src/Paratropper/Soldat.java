package Paratropper;

public class Soldat extends ElementMobil{
	persona persona = new persona("Pedobear-1.png", 2);
	Para paracaigudes = new Para("paracaigudes-1.png", 2);
	
	public Soldat() {
		super("",0);
	}
	public boolean TincPara() {
		if (paracaigudes == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean Mou(joc pantalla) {
		if (this.persona.vida == true && this.paracaigudes.vida == true) {
			this.persona.Mou(pantalla);
			this.paracaigudes.Mou(pantalla);
			return true;
		} else {
			return false;
		}
	}
	
	public persona getPersona() {
		return persona;
	}
	public void setPersona(persona persona) {
		this.persona = persona;
	}
	public Para getParacaigudes() {
		return paracaigudes;
	}
	public void setParacaigudes(Para paracaigudes) {
		this.paracaigudes = paracaigudes;
	}
}
