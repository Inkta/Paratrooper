package Paratropper;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import acm.graphics.GLabel;

public class Poblat {
	double dificultat = 0;
	int Assessinats = 0;
	GLabel morts;
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
	
	public Boolean batalla(Cano cano, GLabel tirs) {
		this.morts = new GLabel(this.Assessinats+"",1000,100);
		this.morts.setColor(Color.white);
		this.morts.setFont(new Font("Serif", Font.BOLD, 21));
		tirs = new GLabel(cano.getBales()+"",100,700);
		tirs.setColor(Color.white);
		tirs.setFont(new Font("Serif", Font.BOLD, 21));
		pantalla.add(this.morts);
		pantalla.add(tirs);
		CreaHeli();
		MoureElements();
		ComprovaSurt();
		Saltadors();
		Bales(cano);
		Invasors();
		Proxims();
		moriran();
		pantalla.remove(tirs);
		pantalla.remove(this.morts);
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
						Invasors.add(aquest.getPersona());
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
	
	public void Bales(Cano cano) {
		for (ElementMobil element: Elements) {
			if (element instanceof bala) {
				for (ElementMobil rival: Elements) {
					if (rival instanceof Soldat) {
						Soldat aquest = (Soldat) rival;
						if (element.getImatge().getBounds().intersects(aquest.getPersona().getImatge().getBounds())) {
							this.dificultat+=0.001;
							this.Assessinats+=1;
							pantalla.remove(element.getImatge());
							pantalla.remove(aquest.getPersona().getImatge());
							pantalla.remove(aquest.getParacaigudes().getImatge());
							Borrare.add(element);
							Borrare.add(rival);
							cano.setBales(5+cano.getBales());
						} else if (element.getImatge().getBounds().intersects(aquest.getParacaigudes().getImatge().getBounds())) {
							this.Assessinats+=1;
							this.dificultat+=0.001;
							pantalla.remove(aquest.getParacaigudes().getImatge());
							pantalla.remove(element.getImatge());
							Borrare.add(element);
							Borrare.add(rival);
							aquest.getParacaigudes().SetVida(false);
							aquest.getPersona().SetVel(20);
							moriran.add(aquest.getPersona());
							cano.setBales(5+cano.getBales());
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
			if (element.getImatge().getX() > 1200 || element.getImatge().getX() < 0 || element.getImatge().getY() > 800 || element.getImatge().getY() < 0) {
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
		if (Math.random() > 0.99-dificultat) {
			Heli nau = new Heli("hell2-1.png","hell3-1.png","hell2R-1.png","hell3R-1.png",5,(int)(Math.random()*2),(int)(Math.random()*1001));
			
			if (nau.getDireccio() == 0) {
				nau.getImatge().setLocation(0,100);
			} else {
				nau.getImatge().setLocation(pantalla.getWidth(),0);
			}
			
			pantalla.add(nau.getImatge());
			Elements.add(nau);
			Helis.add(nau);
		}
	}
	
	public void clearCanvas(Cano cano) {
		for (Element a : Elements) {
			if (a instanceof Soldat) {
				Soldat aquest = (Soldat) a;
				pantalla.remove(aquest.getPersona().getImatge());
				pantalla.remove(aquest.getParacaigudes().getImatge());
			}
			pantalla.remove(a.getImatge());
		}
		Elements.clear();
		
		for (Element a : Borrare) {
			pantalla.remove(a.getImatge());
		}
		Borrare.clear();
		
		for (Element a : Helis) {
			pantalla.remove(a.getImatge());
		}
		Helis.clear();
		for (Element a : Invasors) {
			pantalla.remove(a.getImatge());
		}
		Invasors.clear();
		
		for (Element a : Proxims) {
			pantalla.remove(a.getImatge());
		}
		Proxims.clear();
		
		for (Element a : moriran) {
			pantalla.remove(a.getImatge());
		}
		moriran.clear();
		pantalla.remove(cano.getImatge());
		
	}
}
