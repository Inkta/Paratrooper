package Paratropper;
import acm.graphics.GImage;


public class Heli extends ElementMobil{
	Soldat saltador = new Soldat();
	GImage imatge1;
	GImage imatge2;
	GImage imatgeR;
	GImage imatge2R;
	boolean canvi = false;
	int direccio;
	int lloc;
	int tius = 1;
	
	public int getTius() {
		return tius;
	}

	public Heli(String imatge, String imatge2,String imatgeR, String imatge2R, int velL, int c, int d) {
		super(imatge,velL);
		this.imatge1 = new GImage(imatge);
		this.imatge2 = new GImage(imatge2);
		this.direccio = c;
		this.lloc = d;
		this.imatgeR = new GImage(imatgeR);
		this.imatge2R = new GImage(imatge2R);
		this.imatge.setSize(220,75);
		this.imatge1.setSize(220,75);
		this.imatge2.setSize(220,75);
		this.imatgeR.setSize(220,75);
		this.imatge2R.setSize(220,75);
		
	}
	
	public GImage getImatge() {
		
		return this.imatge;
	}
	
	public void CanviaImatge(joc pantalla) {
		this.canvi = !this.canvi;
		if (this.direccio == 0) {
			if (this.canvi) {
				this.imatge2R.setLocation(this.imatge.getX(),this.imatge.getY());
				pantalla.remove(this.imatge);
				this.imatge = imatge2R;
				pantalla.add(this.imatge);
			} else {
				this.imatgeR.setLocation(this.imatge.getX(),this.imatge.getY());
				pantalla.remove(this.imatge);
				this.imatge = this.imatgeR;
				pantalla.add(this.imatge);
			}
		} else {
			if (this.canvi) {
				this.imatge2.setLocation(this.imatge.getX(),this.imatge.getY());
				pantalla.remove(this.imatge);
				this.imatge = this.imatge2;
				pantalla.add(this.imatge);
			} else {
				this.imatge1.setLocation(this.imatge.getX(),this.imatge.getY());
				pantalla.remove(this.imatge);
				this.imatge = this.imatge1;
				pantalla.add(this.imatge);
			}
		}
	}

	public boolean Mou(joc pantalla) {
		CanviaImatge(pantalla);
		if (this.direccio == 0) {
			this.getImatge().move(this.velocitat,0);
		} else {
			this.getImatge().move(-this.velocitat,0);
		}
		return true;
	}

	public int getDireccio() {
		return direccio;
	}

	public void setDireccio(int direccio) {
		this.direccio = direccio;
	}

	public int getLloc() {
		return lloc;
	}

	public void setLloc(int lloc) {
		this.lloc = lloc;
	}

	public Soldat Salta() {
			this.tius--;
			return saltador;
	}

	public Soldat getSaltador() {
		return saltador;
	}

	public void setSaltador(Soldat saltador) {
		this.saltador = saltador;
	}
}
	
