package Paratropper;
import acm.graphics.GImage;

public abstract class Element {
	GImage imatge;
	
	public GImage getImatge() {
		return imatge;
	}

	public void setImatge(GImage imatge) {
		this.imatge = imatge;
	}

	public Element(String imatge) {
		this.imatge = new GImage(imatge);
	}
}
