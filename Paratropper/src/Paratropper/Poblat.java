package Paratropper;
import java.util.ArrayList;

public class Poblat {
	ArrayList<ElementMobil> Elements = new ArrayList<ElementMobil>();
	ArrayList<ElementMobil> Borrare = new ArrayList<ElementMobil>();
	ArrayList<Heli> Helis = new ArrayList<Heli>();
	ArrayList<Element> Invasors = new ArrayList<Element>();
	ArrayList<ElementMobil> Proxims = new ArrayList<ElementMobil>();
	ArrayList<persona> moriran = new ArrayList<persona>();
	joc pantalla;
	public Poblat(joc pantalla) {
		this.pantalla = pantalla;
	}
	
	public Boolean batalla() {
		CreaHeli();
		MoureElements();
		ComprovaSurt();
		Saltadors();
		Bales();
		Invasors();
		Proxims();
		moriran();
		return ComprovaPartida();
		
	}
	
	public void moriran() {
		for (int i=moriran.size()-1; i >=0; i--) {
			moriran.get(i).Mou(pantalla);
			if (moriran.get(i).getImatge().getY() > 750) {
				pantalla.remove(moriran.get(i).getImatge());
				moriran.remove(i);
			}
		}
	}
	
	public Boolean ComprovaPartida() {
		if (Invasors.size() > 6) {
			return false;
		} else {
			return true;
		}
	}
	
	public void Proxims() {
		for (int i=Proxims.size()-1; i >=0; i--) {
			Elements.add(Proxims.get(i));
			pantalla.add(Proxims.get(i).getImatge());
		}
		Proxims.clear();
	}
	
	public void Invasors() {
		for (ElementMobil element: Elements) {
			if (element instanceof Soldat) {
				Soldat aquest = (Soldat) element;
				if (aquest.getPersona().getImatge().getY() > 750) {		
						Borrare.add(element);
						Invasors.add(element);
						pantalla.remove(aquest.getParacaigudes().getImatge());
				}
			}
		}
	}
	
	public void AfegirBala(bala a) {
		Proxims.add(a);
	}
	
	public void Borra() {
		for (ElementMobil element : Borrare) {
			Elements.remove(element);
		}
		
		Borrare.clear();
	}
	
	public void Bales() {
		for (ElementMobil element: Elements) {
			if (element instanceof bala) {
				for (ElementMobil rival: Elements) {
					if (rival instanceof Soldat) {
						Soldat aquest = (Soldat) rival;
						if (element.getImatge().getBounds().intersects(aquest.getPersona().getImatge().getBounds())) {
							pantalla.remove(element.getImatge());
							pantalla.remove(aquest.getPersona().getImatge());
							pantalla.remove(aquest.getParacaigudes().getImatge());
							Borrare.add(element);
							Borrare.add(rival);
						} else if (element.getImatge().getBounds().intersects(aquest.getParacaigudes().getImatge().getBounds())) {
							pantalla.remove(aquest.getParacaigudes().getImatge());
							pantalla.remove(element.getImatge());
							Borrare.add(element);
							Borrare.add(rival);
							aquest.getParacaigudes().SetVida(false);
							aquest.getPersona().SetVel(20);
							moriran.add(aquest.getPersona());
						}
					}
				}
			}
		}
		
		Borra();
	}
	
	public void Saltadors() {
		for (Heli helicopter : Helis) {
			if (helicopter.getImatge().getX() > helicopter.getLloc()-20 && helicopter.getImatge().getX() < helicopter.getLloc()) {
				if (helicopter.getTius() > 0) {
					Soldat aquest = helicopter.Salta();
					Elements.add(aquest);
					aquest.getPersona().getImatge().setSize(50,50);
					aquest.getParacaigudes().getImatge().setSize(80,80);
					aquest.getPersona().getImatge().setLocation(helicopter.getImatge().getX(),helicopter.getImatge().getHeight() + helicopter.getImatge().getY()+50);
					aquest.getParacaigudes().getImatge().setLocation(helicopter.getImatge().getX()-15,helicopter.getImatge().getHeight() + helicopter.getImatge().getY()+10);
					pantalla.add(aquest.getParacaigudes().getImatge());
					pantalla.add(aquest.getPersona().getImatge());
				}
			}
		}
	}
	
	public void ComprovaSurt() {
		for (ElementMobil element : Elements) {
			if (element.getImatge().getX() > 1200 || element.getImatge().getX() < -300 || element.getImatge().getY() > 800 || element.getImatge().getY() < 0) {
				pantalla.remove(element.getImatge());
				Borrare.add(element);
			}
		}
		
		Borra();
		
	}
	
	public void MoureElements() {
		for (ElementMobil element : Elements) {
			element.Mou(this.pantalla);
		}
		pantalla.pause(20);
	}
	
	public void CreaHeli() {
		if (Math.random() > 0.99) {
			Heli nau = new Heli("hell2.png","hell3.png","hell2R.png","hell3R.png",5,(int)(Math.random()*2),(int)(Math.random()*1001));
			
			if (nau.getDireccio() == 0) {
				nau.getImatge().setLocation(-220,100);
			} else {
				nau.getImatge().setLocation(pantalla.getWidth(),0);
			}
			
			pantalla.add(nau.getImatge());
			Elements.add(nau);
			Helis.add(nau);
		}
	}
}
